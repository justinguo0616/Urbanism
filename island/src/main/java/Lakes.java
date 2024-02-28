import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import java.util.ArrayList;
import java.util.List;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.*;
import java.util.Random;

public class Lakes {


    public static Mesh lake(Mesh mesh, int numLakes, String[] args) {
        Configuration config = new Configuration(args);
        ArrayList<Polygon> polygon_List = new ArrayList<>();
        List<Polygon> polygonList = mesh.getPolygonsList();
        for(int k=0;k< polygonList.size();k++){
            polygon_List.add(polygonList.get(k));
        }
        List<Segment> segment_List = mesh.getSegmentsList();
        List<Vertex> vertices_List = mesh.getVerticesList();
        List<Integer> neighbours = new ArrayList<>();

        for (int i = 0; i < numLakes; i++) {
            Random random = new Random();
            int x = random.nextInt(polygon_List.size());
            Polygon selectedPolygon = polygon_List.get(x);
            if (!selectedPolygon.getProperties(1).getValue().equals("land") && !selectedPolygon.getProperties(2).getValue().equals("0")) {
                i--;
            } else {
                neighbours = selectedPolygon.getNeighborIdxsList();
                int count = 0;
                for (int j = 0; j < neighbours.size(); j++) {
                    Polygon neighbour = polygon_List.get(neighbours.get(j));
                    if (!neighbour.getProperties(1).getValue().equals("ocean") && !neighbour.getProperties(1).getValue().equals("lake")) {
                        count += 1;
                    }
                }
                if (count == neighbours.size()) {
                    String color = 51 + "," + 153 + "," + 255;
                    Property lake = Property.newBuilder().setKey("type").setValue("lake").build();
                    Property fill = Property.newBuilder().setKey("rgb_color").setValue(color).build();
                    Polygon newPolygon = Polygon.newBuilder(selectedPolygon).setProperties(0, fill).setProperties(1,lake).build();
                    polygon_List.set(x,newPolygon);


                    for (int j = 0; j < neighbours.size(); j++) {
                        Polygon neighbour = polygon_List.get(neighbours.get(j));
                        if (!neighbour.getProperties(1).getValue().equals("lake") && !neighbour.getProperties(1).getValue().equals("beach") && neighbour.getProperties(2).getValue().equals("0")) {
                            if(config.soil()==null){
                                String colorNew = 36 + "," + 100 + "," + 7;
                                Property filled = Property.newBuilder().setKey("rgb_color").setValue(colorNew).build();
                                Polygon newPoly = Polygon.newBuilder(neighbour).setProperties(0, filled).build();
                                polygon_List.set(neighbours.get(j),newPoly);
                            }
                            else if(config.soil().equals("dry")){
                                String colorNew = 34 + "," + 139 + "," + 34;
                                Property filled = Property.newBuilder().setKey("rgb_color").setValue(colorNew).build();
                                Polygon newPoly = Polygon.newBuilder(neighbour).setProperties(0, filled).build();
                                polygon_List.set(neighbours.get(j),newPoly);

                            }
                            else if(config.soil().equals("wet")){
                                String colorNew = 2 + "," + 48 + "," + 32;
                                Property filled = Property.newBuilder().setKey("rgb_color").setValue(colorNew).build();
                                Polygon newPoly = Polygon.newBuilder(neighbour).setProperties(0, filled).build();
                                polygon_List.set(neighbours.get(j),newPoly);
                            }

                        }
                    }
                }
                else{
                    i--;
                }
            }
        }
        return Mesh.newBuilder().addAllPolygons(polygon_List).addAllSegments(segment_List).addAllVertices(vertices_List).build();
    }
}

