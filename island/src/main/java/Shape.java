import ca.mcmaster.cas.se2aa4.a2.io.Structs.*;
import java.util.ArrayList;
import java.util.List;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;

public class Shape {
    public static Mesh shape(Mesh mesh, String[] args) {
        Configuration config = new Configuration(args);
        List<Polygon> polygon_TempList = new ArrayList<>();
        List<Segment> segment_List =  mesh.getSegmentsList();
        List<Vertex> vertices_List = mesh.getVerticesList();

        if(config.shape()==null){
            List<Polygon> polygon_List = mesh.getPolygonsList();
            int midpoint_x = 960;
            int midpoint_y = -540;
            int count = 0;
            int ocean = 0;
            int land = 0;

            for (int i = 0; i < polygon_List.size(); i++) {
                count = 0;
                ocean = 0;
                land = 0;
                Polygon polygon = polygon_List.get(i);
                List<Integer> segmentIds = polygon.getSegmentIdxsList();
                for (int j = 0; j < segmentIds.size(); j++) {
                    Segment segment = segment_List.get(segmentIds.get(j));
                    count += 2;
                    int vertex1_idx = segment.getV1Idx();
                    int vertex2_idx = segment.getV2Idx();
                    Vertex vertex1 = vertices_List.get(vertex1_idx);
                    Vertex vertex2 = vertices_List.get(vertex2_idx);
                    double distance_vertex1 = Math.sqrt(Math.pow(vertex1.getX() - midpoint_x, 2) + Math.pow(vertex1.getY() + midpoint_y, 2));
                    if (distance_vertex1 < 400.00) {
                        land += 1;
                    }
                    if (distance_vertex1 > 400.00) {
                        ocean += 1;
                    }
                    double distance_vertex2 = Math.sqrt(Math.pow(vertex2.getX() - midpoint_x, 2) + Math.pow(vertex2.getY() + midpoint_y, 2));
                    if (distance_vertex2 < 400.00) {
                        land += 1;
                    }
                    if (distance_vertex2 > 400.00) {
                        ocean += 1;
                    }

                }
                if (count == ocean) {
                    String colorCode = 0 + "," + 0 + "," + 255;
                    Property Ocean = Property.newBuilder().setKey("type").setValue("ocean").build();
                    Property fill  = Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();
                    Polygon newPolygon = Polygon.newBuilder(polygon).clearProperties().addProperties(fill).addProperties(Ocean).build();
                    polygon_TempList.add(newPolygon);
                } else if (count == land) {
                    if(config.soil()==null){
                        String colorCode = 80 + "," + 200 + "," + 120;
                        Property Ocean = Property.newBuilder().setKey("type").setValue("land").build();
                        Property fill  = Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();
                        Polygon newPolygon = Polygon.newBuilder(polygon).clearProperties().addProperties(fill).addProperties(Ocean).build();
                        polygon_TempList.add(newPolygon);
                    }
                    else if(config.soil().equals("dry")){
                        String colorCode = 200 + "," + 247 + "," + 200;
                        Property Ocean = Property.newBuilder().setKey("type").setValue("land").build();
                        Property fill  = Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();
                        Polygon newPolygon = Polygon.newBuilder(polygon).clearProperties().addProperties(fill).addProperties(Ocean).build();
                        polygon_TempList.add(newPolygon);
                    }
                    else if(config.soil().equals("wet")){
                        String colorCode = 34 + "," + 139 + "," + 34;
                        Property Ocean = Property.newBuilder().setKey("type").setValue("land").build();
                        Property fill  = Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();
                        Polygon newPolygon = Polygon.newBuilder(polygon).clearProperties().addProperties(fill).addProperties(Ocean).build();
                        polygon_TempList.add(newPolygon);
                    }
                } else {
                    String colorCode = 255 + "," + 255 + "," + 0;
                    Property Beach = Property.newBuilder().setKey("type").setValue("beach").build();
                    Property fill = Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();
                    Polygon newPolygon = Polygon.newBuilder(polygon).clearProperties().addProperties(fill).addProperties(Beach).build();
                    polygon_TempList.add(newPolygon);
                }
            }
        }

        else {
            List<Polygon> polygon_List = mesh.getPolygonsList();
            polygon_TempList = new ArrayList<>();
            segment_List = mesh.getSegmentsList();
            vertices_List = mesh.getVerticesList();

            int midpoint_x = 960;
            int midpoint_y = -540;
            int count = 0;
            int ocean = 0;
            int land = 0;

            for (int i = 0; i < polygon_List.size(); i++) {
                count = 0;
                ocean = 0;
                land = 0;
                Polygon polygon = polygon_List.get(i);
                List<Integer> segmentIds = polygon.getSegmentIdxsList();
                for (int j = 0; j < segmentIds.size(); j++) {
                    Segment segment = segment_List.get(segmentIds.get(j));
                    count += 2;
                    int vertex1_idx = segment.getV1Idx();
                    int vertex2_idx = segment.getV2Idx();
                    Vertex vertex1 = vertices_List.get(vertex1_idx);
                    Vertex vertex2 = vertices_List.get(vertex2_idx);
                    if ( config.shape().equals("circle")) {
                        double distance_vertex1 = Math.sqrt(Math.pow(vertex1.getX() - midpoint_x, 2) + Math.pow(vertex1.getY() + midpoint_y, 2));
                        if (distance_vertex1 < 400.00) {
                            land += 1;
                        }
                        if (distance_vertex1 > 400.00) {
                            ocean += 1;
                        }
                        double distance_vertex2 = Math.sqrt(Math.pow(vertex2.getX() - midpoint_x, 2) + Math.pow(vertex2.getY() + midpoint_y, 2));
                        if (distance_vertex2 < 400.00) {
                            land += 1;
                        }
                        if (distance_vertex2 > 400.00) {
                            ocean += 1;
                        }
                    }
                    else if (config.shape().equals("square")) {
                        if((vertex1.getX()>=0&&vertex1.getX()<460)){
                            ocean+=1;
                        } else if ((vertex1.getX()>1460&&vertex1.getX()<=1920)) {
                            ocean+=1;
                        }
                        if(vertex1.getX()>460&&vertex1.getX()<1460){
                            if((vertex1.getY()>=0&&vertex1.getY()<140)||((vertex1.getY()>940)&&(vertex1.getY()<=1080))){
                                ocean+=1;
                            }
                        }
                        if((vertex2.getX()>=0&&vertex2.getX()<460)){
                            ocean+=1;
                        } else if ((vertex2.getX()>1460&&vertex2.getX()<=1920)) {
                            ocean+=1;
                        }
                        if(vertex2.getX()>460&&vertex2.getX()<1460){
                            if((vertex2.getY()>=0&&vertex2.getY()<140)||((vertex2.getY()>940)&&(vertex2.getY()<=1080))){
                                ocean+=1;
                            }
                        }
                        if(vertex1.getX()>460&&vertex1.getX()<1460){
                            if(vertex1.getY()>140&&vertex1.getY()<940){
                                land+=1;
                            }
                        }
                        if(vertex2.getX()>460&&vertex2.getX()<1460){
                            if(vertex2.getY()>140&&vertex2.getY()<940){
                                land+=1;
                            }
                        }
                    }
                }
                if (count == ocean) {
                    String colorCode = 0 + "," + 0 + "," + 255;
                    Property Ocean = Property.newBuilder().setKey("type").setValue("ocean").build();
                    Property fill  = Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();
                    Polygon newPolygon = Polygon.newBuilder(polygon).clearProperties().addProperties(fill).addProperties(Ocean).build();
                    polygon_TempList.add(newPolygon);
                } else if (count == land) {
                    if(config.soil()==null){
                        String colorCode = 80 + "," + 200 + "," + 120;
                        Property Ocean = Property.newBuilder().setKey("type").setValue("land").build();
                        Property fill  = Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();
                        Polygon newPolygon = Polygon.newBuilder(polygon).clearProperties().addProperties(fill).addProperties(Ocean).build();
                        polygon_TempList.add(newPolygon);
                    }
                    else if(config.soil().equals("dry")){
                        String colorCode = 200 + "," + 247 + "," + 200;
                        Property Ocean = Property.newBuilder().setKey("type").setValue("land").build();
                        Property fill  = Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();
                        Polygon newPolygon = Polygon.newBuilder(polygon).clearProperties().addProperties(fill).addProperties(Ocean).build();
                        polygon_TempList.add(newPolygon);
                    }
                    else if(config.soil().equals("wet")){
                        String colorCode = 34 + "," + 139 + "," + 34;
                        Property Ocean = Property.newBuilder().setKey("type").setValue("land").build();
                        Property fill  = Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();
                        Polygon newPolygon = Polygon.newBuilder(polygon).clearProperties().addProperties(fill).addProperties(Ocean).build();
                        polygon_TempList.add(newPolygon);
                    }
                } else {
                    String colorCode = 255 + "," + 255 + "," + 0;
                    Property Beach = Property.newBuilder().setKey("type").setValue("beach").build();
                    Property fill = Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();
                    Polygon newPolygon = Polygon.newBuilder(polygon).clearProperties().addProperties(fill).addProperties(Beach).build();
                    polygon_TempList.add(newPolygon);
                }
            }
        }
        return Mesh.newBuilder().addAllPolygons(polygon_TempList).addAllSegments(segment_List).addAllVertices(vertices_List).build();
    }
}
