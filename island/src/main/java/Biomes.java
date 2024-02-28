import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import java.util.ArrayList;
import java.util.List;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.*;

public class Biomes {
    public static Mesh biomes (Mesh mesh, String[] args) {
        Configuration config = new Configuration(args);
        ArrayList<Polygon> polygon_List = new ArrayList<>();
        List<Segment> segment_List =  mesh.getSegmentsList();
        List<Vertex> vertices_List = mesh.getVerticesList();
        List<Polygon> polygonList = mesh.getPolygonsList();
        List<Integer> neighbours = new ArrayList<>();
        Polygon currentPoly;
        boolean isForest = false;
        int temperature = 0;
        boolean humid = false;
        boolean isDry = false;

        for(int k = 0; k < polygonList.size(); k++){
            polygon_List.add(polygonList.get(k));
        }

        if (config.biomes().equals("cold")) {
            temperature = 1;
        }
        else if (config.biomes().equals("hot")) {
            temperature = -1;
        }
        else if (config.biomes().equals("wet")) {
            humid = true;
        }
        else if (config.biomes().equals("dry")) {
            isDry = true;
        }

        for (int i = 0; i < polygon_List.size(); i++) {
            currentPoly = polygon_List.get(i);
            neighbours = currentPoly.getNeighborIdxsList();
            if (Integer.parseInt(currentPoly.getProperties(2).getValue()) + temperature >= 2 && (currentPoly.getProperties(1).getValue().equals("land") || currentPoly.getProperties(1).getValue().equals("mountain"))) {
                String color = 255 + "," + 255 + "," + 255;
                Property filled = Property.newBuilder().setKey("rgb_color").setValue(color).build();
                Property biome = Property.newBuilder().setKey("biome").setValue("tundra").build();
                Polygon newPolygon = Polygon.newBuilder(polygonList.get(i)).setProperties(0, filled).addProperties(3, biome).build();
                polygon_List.set(i, newPolygon);
            }
            else {
                for (int j = 0; j < neighbours.size(); j++) {
                    if (polygonList.get(neighbours.get(j)).getProperties(1).getValue().equals("aquifer") || polygonList.get(neighbours.get(j)).getProperties(1).getValue().equals("lake") || currentPoly.getProperties(1).getValue().equals("aquifer")) {
                        String color = 1 + "," + 50 + "," + 32;
                        Property filled = Property.newBuilder().setKey("rgb_color").setValue(color).build();
                        Property biome = Property.newBuilder().setKey("biome").setValue("forest").build();
                        Polygon newPolygon = Polygon.newBuilder(polygonList.get(i)).setProperties(0, filled).addProperties(3, biome).build();
                        polygon_List.set(i, newPolygon);
                        isForest = true;
                    }
                }
                if (currentPoly.getProperties(1).getValue().equals("land") && humid) {
                    String color = 1 + "," + 50 + "," + 32;
                    Property filled = Property.newBuilder().setKey("rgb_color").setValue(color).build();
                    Property biome = Property.newBuilder().setKey("biome").setValue("forest").build();
                    Polygon newPolygon = Polygon.newBuilder(polygonList.get(i)).setProperties(0, filled).addProperties(3, biome).build();
                    polygon_List.set(i, newPolygon);
                    isForest = true;
                }
                else if (currentPoly.getProperties(1).getValue().equals("land") && !isForest && humid) {
                    Property biome = Property.newBuilder().setKey("biome").setValue("grassland").build();
                    Polygon newPolygon = Polygon.newBuilder(polygonList.get(i)).addProperties(3, biome).build();
                    polygon_List.set(i, newPolygon);
                }
                else if(currentPoly.getProperties(1).getValue().equals("land") && !isForest && isDry) {
                    String color = 255 + "," + 255 + "," + 0;
                    Property filled = Property.newBuilder().setKey("rgb_color").setValue(color).build();
                    Property biome = Property.newBuilder().setKey("biome").setValue("desert").build();
                    Polygon newPolygon = Polygon.newBuilder(polygonList.get(i)).setProperties(0, filled).addProperties(3, biome).build();
                    polygon_List.set(i, newPolygon);
                }
                isForest = false;
            }
        }

        return Mesh.newBuilder().addAllPolygons(polygon_List).addAllSegments(segment_List).addAllVertices(vertices_List).build();
    }
}