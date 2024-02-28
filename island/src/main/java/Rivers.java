import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import java.util.ArrayList;
import java.util.List;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.*;
import java.util.Random;

public class Rivers {

    public static Mesh river(Mesh mesh, int numRivers) {
        ArrayList<Polygon> polygon_List = new ArrayList<>();
        List<Polygon> polygonList = mesh.getPolygonsList();
        for(int k=0;k< polygonList.size();k++){
            polygon_List.add(polygonList.get(k));
        }
        ArrayList<Segment> segment_List = new ArrayList<>();
        List<Segment> segmentList = mesh.getSegmentsList();
        for(int k=0;k< segmentList.size();k++){
            segment_List.add(segmentList.get(k));
        }



        List<Vertex> vertices_List = mesh.getVerticesList();
        List<Integer> vertices = new ArrayList<>();
        List<Integer> neighbours = new ArrayList<>();




        int count = 0;
        while (count < numRivers) {
            Random random = new Random();
            int x = random.nextInt(polygon_List.size());
            Polygon selectedPolygon = polygon_List.get(x);
            if((selectedPolygon.getProperties(1).getValue().equals("mountain") ||(selectedPolygon.getProperties(1).getValue().equals("plateau"))|| selectedPolygon.getProperties(1).getValue().equals("lake"))){
                count = count+1;
                List<Integer> segmentIds = selectedPolygon.getSegmentIdxsList();
                Random rand = new Random();
                int y = random.nextInt(segmentIds.size());
                int z = selectedPolygon.getSegmentIdxs(y);
                Segment selectedSegment = segment_List.get(z);
                int vertex1_idx = selectedSegment.getV1Idx();
                int vertex2_idx = selectedSegment.getV2Idx();
                String color =  51 + "," + 153 + "," + 255;
                Property fill = Property.newBuilder().setKey("rgb_color").setValue(color).build();
                if(selectedSegment.getPropertiesCount() != 0){
                    Property flow = Property.newBuilder().setKey("type").setValue("riverFlow").build();
                    Segment new_segment = Segment.newBuilder(selectedSegment).addProperties(0, fill).addProperties(1,flow).build();
                    segment_List.set(z, new_segment);
                }
                else{
                    Property stream = Property.newBuilder().setKey("type").setValue("river").build();
                    Segment new_segment = Segment.newBuilder(selectedSegment).addProperties(0, fill).addProperties(1,stream).build();
                    segment_List.set(z, new_segment);
                }



                int counting=0;
                int current_elevation = Integer.parseInt(selectedPolygon.getProperties(2).getValue());
                neighbours = selectedPolygon.getNeighborIdxsList();
                int j=0;
                for (j = 0; j < neighbours.size(); j++) {
                    Polygon neighbour = polygon_List.get(neighbours.get(j));
                    if (((Integer.parseInt(neighbour.getProperties(2).getValue()) <= current_elevation) || neighbour.getProperties(1).getValue().equals("land"))&&(!neighbour.getProperties(1).getValue().equals("ocean")||neighbour.getProperties(1).getValue().equals("lake"))) {
                        selectedPolygon=neighbour;
                        List<Integer> neighbourSegmentIds = neighbour.getSegmentIdxsList();
                        boolean segmentAdded = false;
                        for (int n = 0; n < neighbourSegmentIds.size(); n++) {
                            int t = neighbour.getSegmentIdxs(n);
                            Segment a = segment_List.get(z);
                            Segment b = segment_List.get(t);
                            if ((a.getV1Idx() == b.getV1Idx() && a.getV2Idx() == b.getV2Idx()) || (a.getV1Idx() == b.getV2Idx() && a.getV2Idx() == b.getV1Idx())) {
                                z=t;
                                Segment currentSegment = segment_List.get(t);
                                int selectedVertex1Id = currentSegment.getV1Idx();
                                int selectedVertex2Id = currentSegment.getV2Idx();
                                for (int q = 0; q < neighbourSegmentIds.size(); q++) {
                                    int p = neighbour.getSegmentIdxs(q);
                                    Segment riverSelection = segment_List.get(p);
                                    if ((riverSelection.getV1Idx() == selectedVertex1Id && riverSelection.getV2Idx() != selectedVertex2Id) || (riverSelection.getV1Idx() != selectedVertex1Id && riverSelection.getV2Idx() == selectedVertex2Id) || (riverSelection.getV1Idx() == selectedVertex2Id && riverSelection.getV2Idx() != selectedVertex1Id) || (riverSelection.getV1Idx() != selectedVertex2Id && riverSelection.getV2Idx() == selectedVertex1Id)) {
                                        String newColor =  51 + "," + 153 + "," + 255;
                                        Property filling = Property.newBuilder().setKey("rgb_color").setValue(newColor).build();
                                        if(riverSelection.getPropertiesCount() != 0){
                                            Property flow = Property.newBuilder().setKey("type").setValue("riverFlow").build();
                                            Segment newestSegment = Segment.newBuilder(riverSelection).addProperties(0, filling).addProperties(1, flow).build();
                                            segment_List.set(p, newestSegment);
                                            segmentAdded = true;
                                        }
                                        else{
                                            Property stream = Property.newBuilder().setKey("type").setValue("river").build();
                                            Segment newestSegment = Segment.newBuilder(riverSelection).addProperties(0, filling).addProperties(1, stream).build();
                                            segment_List.set(p, newestSegment);
                                            segmentAdded = true;
                                        }
                                        break;
                                    }
                                }
                                if (segmentAdded) {
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            else{ }
        }
        return Mesh.newBuilder().addAllPolygons(polygon_List).addAllSegments(segment_List).addAllVertices(vertices_List).build();
    }
}