import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import java.util.ArrayList;
import java.util.List;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.*;
import java.util.Random;

public class Elevation {

    public static Mesh elevation(Mesh mesh, String[] args) {
        Configuration config = new Configuration(args);
        List<Segment> segment_List =  mesh.getSegmentsList();
        List<Vertex> vertices_List = mesh.getVerticesList();
        ArrayList<Polygon> polygon_List = new ArrayList<>();
        List<Polygon> polygonList = mesh.getPolygonsList();

        for(int k = 0;k< polygonList.size();k++){
            polygon_List.add(polygonList.get(k));
        }
        Property altitude = Property.newBuilder().setKey("elevation").setValue("0").build();
        for (int i = 0; i < polygon_List.size(); i++) {
            if (polygonList.get(i).getProperties(1).getValue().equals("ocean")) {
                Polygon newPolygon = Polygon.newBuilder(polygonList.get(i)).addProperties(2, altitude).build();
                polygon_List.set(i, newPolygon);
            }
            else if (polygonList.get(i).getProperties(1).getValue().equals("land")) {
                Polygon newPolygon = Polygon.newBuilder(polygonList.get(i)).addProperties(2, altitude).build();
                polygon_List.set(i, newPolygon);
            }
            else if (polygonList.get(i).getProperties(1).getValue().equals("beach")) {
                Polygon newPolygon = Polygon.newBuilder(polygonList.get(i)).addProperties(2, altitude).build();
                polygon_List.set(i, newPolygon);
            }
        }
        if (config.altitude() == null){ }
        else if(config.altitude().equals("field")){ }

        else if (config.altitude().equals("mountain")) {
            int count = 0;
            List<Integer> neighbours = new ArrayList<>();
            List<Integer> neighboursNeighbours = new ArrayList<>();
            while (count < 3) {
                Random random = new Random();
                int x = random.nextInt(polygon_List.size());
                Polygon selectedPolygon = polygon_List.get(x);

                if (selectedPolygon.getProperties(1).getValue().equals("land")) {
                    count++;
                    String color1 = 196 + "," + 147 + "," + 0;
                    Property filled = Property.newBuilder().setKey("rgb_color").setValue(color1).build();
                    Property mountain = Property.newBuilder().setKey("type").setValue("mountain").build();
                    Property altitude1 = Property.newBuilder().setKey("elevation").setValue("3").build();
                    Polygon newPoly = Polygon.newBuilder(polygonList.get(x)).setProperties(0, filled).setProperties(1, mountain).addProperties(2, altitude1).build();
                    polygon_List.set(x, newPoly);


                    neighbours = selectedPolygon.getNeighborIdxsList();
                    for (int k = 0; k < neighbours.size(); k++) {
                        Polygon neighbour = polygon_List.get(neighbours.get(k));
                        if (!neighbour.getProperties(1).getValue().equals("ocean") && neighbour.getProperties(2).getValue().equals("0")) {
                            String color2 =  153 + "," + 102 + "," + 0;
                            Property filled2 = Property.newBuilder().setKey("rgb_color").setValue(color2).build();
                            Property altitude2 = Property.newBuilder().setKey("elevation").setValue("2").build();
                            Polygon newPolygon = Polygon.newBuilder(polygonList.get(neighbours.get(k))).setProperties(0, filled2).setProperties(1, mountain).addProperties(2, altitude2).build();
                            polygon_List.set(neighbours.get(k), newPolygon);
                        }
                    }
                    for (int p = 0; p < neighbours.size(); p++) {
                        Polygon currentPoly = polygon_List.get(neighbours.get(p));
                        neighboursNeighbours = currentPoly.getNeighborIdxsList();
                        for (int k = 0; k < neighboursNeighbours.size(); k++) {
                            if (!polygon_List.get(neighboursNeighbours.get(k)).getProperties(1).getValue().equals("ocean") && polygon_List.get(neighboursNeighbours.get(k)).getProperties(2).getValue().equals("0")) {
                                String color2 = 102 + "," + 51 + "," + 0;
                                Property filled3 = Property.newBuilder().setKey("rgb_color").setValue(color2).build();
                                Property altitude3 = Property.newBuilder().setKey("elevation").setValue("1").build();
                                Polygon newPolygon = Polygon.newBuilder(polygonList.get(neighboursNeighbours.get(k))).setProperties(0, filled3).setProperties(1, mountain).addProperties(2, altitude3).build();
                                polygon_List.set(neighboursNeighbours.get(k), newPolygon);
                            }
                        }
                    }
                }
            }
        }

        else if (config.altitude().equals("plateau")) {
            int count = 0;
            List<Integer> neighbours = new ArrayList<>();
            List<Integer> neighboursNeighbours = new ArrayList<>();
            String color = 196 + "," + 147 + "," + 0;
            altitude = Property.newBuilder().setKey("elevation").setValue("3").build();
            Property filled = Property.newBuilder().setKey("rgb_color").setValue(color).build();
            while (count < 3) {
                Random random = new Random();
                int x = random.nextInt(polygon_List.size());
                Polygon selectedPolygon = polygon_List.get(x);

                if (selectedPolygon.getProperties(1).getValue().equals("land")) {
                    count++;
                    Property plateau = Property.newBuilder().setKey("type").setValue("plateau").build();
                    Polygon newPoly = Polygon.newBuilder(polygonList.get(x)).setProperties(0, filled).setProperties(1, plateau).addProperties(2, altitude).build();
                    polygon_List.set(x, newPoly);


                    neighbours = selectedPolygon.getNeighborIdxsList();
                    for (int k = 0; k < neighbours.size(); k++) {
                        Polygon neighbour = polygon_List.get(neighbours.get(k));
                        if (!neighbour.getProperties(1).getValue().equals("ocean") && neighbour.getProperties(2).getValue().equals("0")) {
                            Polygon newPolygon = Polygon.newBuilder(polygonList.get(neighbours.get(k))).setProperties(0, filled).setProperties(1, plateau).addProperties(2, altitude).build();
                            polygon_List.set(neighbours.get(k), newPolygon);
                        }
                    }
                    for (int p = 0; p < neighbours.size(); p++) {
                        Polygon currentPoly = polygon_List.get(neighbours.get(p));
                        neighboursNeighbours = currentPoly.getNeighborIdxsList();
                        for (int k = 0; k < neighboursNeighbours.size(); k++) {
                            if (!polygon_List.get(neighboursNeighbours.get(k)).getProperties(1).getValue().equals("ocean") && polygon_List.get(neighboursNeighbours.get(k)).getProperties(2).getValue().equals("0")) {
                                Polygon newPolygon = Polygon.newBuilder(polygonList.get(neighboursNeighbours.get(k))).setProperties(0, filled).setProperties(1, plateau).addProperties(2, altitude).build();
                                polygon_List.set(neighboursNeighbours.get(k), newPolygon);
                            }
                        }
                    }
                }
            }
        }
        return Mesh.newBuilder().addAllPolygons(polygon_List).addAllSegments(segment_List).addAllVertices(vertices_List).build();
    }
}