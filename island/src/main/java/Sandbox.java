import ca.mcmaster.cas.se2aa4.a2.io.Structs.*;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;
import java.util.ArrayList;
import java.util.List;

public class Sandbox {

    public static Mesh sandbox(Mesh Mesh){

        List<Polygon> polygon_List = Mesh.getPolygonsList();
        List<Polygon> polygon_TempList = new ArrayList<>();
        List<Segment> segment_List = Mesh.getSegmentsList();
        List<Vertex> vertices_List = Mesh.getVerticesList();

        int midpoint_x = 960;
        int midpoint_y = -540;
        int count = 0;
        int lagoon = 0;
        int ocean = 0;
        int land = 0;

        for(int i=0;i < polygon_List.size();i++){
            count=0;
            lagoon = 0;
            ocean = 0;
            land = 0;
            Polygon polygon = polygon_List.get(i);
            List<Integer> segmentIds = polygon.getSegmentIdxsList();
            for(int j = 0;j < segmentIds.size();j++){
                Segment segment = segment_List.get(segmentIds.get(j));
                count +=2;
                int vertex1_idx = segment.getV1Idx();
                int vertex2_idx = segment.getV2Idx();
                Vertex vertex1 = vertices_List.get(vertex1_idx);
                Vertex vertex2 = vertices_List.get(vertex2_idx);
                double distance_vertex1 = Math.sqrt(Math.pow(vertex1.getX() - midpoint_x,2) +Math.pow(vertex1.getY() + midpoint_y,2));
                if(distance_vertex1 < 200.00){
                    lagoon += 1;
                }
                if(distance_vertex1 > 200.00 && distance_vertex1 < 400.00){
                    land += 1;
                }
                if (distance_vertex1 > 400.00){
                    ocean +=1;
                }
                double distance_vertex2 = Math.sqrt(Math.pow(vertex2.getX() - midpoint_x,2) +Math.pow(vertex2.getY() + midpoint_y,2));
                if(distance_vertex2 < 200.00){
                    lagoon += 1;
                }
                if(distance_vertex2 > 200.00 && distance_vertex2 < 400.00){
                    land += 1;
                }
                if (distance_vertex2 > 400.00){
                    ocean +=1;
                }

            }

            if (count == ocean){
                String colorCode = 0 + "," + 0 + "," + 255;
                Property fill = Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();
                Polygon newPolygon = Polygon.newBuilder(polygon).clearProperties().addProperties(fill).build();
                polygon_TempList.add(newPolygon);
            }
            else if (count == lagoon){
                String colorCode = 51 + "," + 153 + "," + 255;
                Property fill = Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();
                Polygon newPolygon = Polygon.newBuilder(polygon).clearProperties().addProperties(fill).build();
                polygon_TempList.add(newPolygon);
            }
            else if (count == land){
                String colorCode = 0 + "," + 153 + "," + 0;
                Property fill = Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();
                Polygon newPolygon = Polygon.newBuilder(polygon).clearProperties().addProperties(fill).build();
                polygon_TempList.add(newPolygon);
            }
            else{
                String colorCode = 255 + "," + 255 + "," + 0;
                Property fill = Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();
                Polygon newPolygon = Polygon.newBuilder(polygon).clearProperties().addProperties(fill).build();
                polygon_TempList.add(newPolygon);

            }
        }
        return Mesh.newBuilder().addAllPolygons(polygon_TempList).addAllSegments(segment_List).addAllVertices(vertices_List).build();
    }
}
