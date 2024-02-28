import java.util.ArrayList;
import java.util.List;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.*;
import java.util.Random;
import pathfinding.*;

public class Cities {

    public static Mesh cities(Mesh mesh, int numCities) {
        List<Vertex> vertices = new ArrayList<>();
        List<Segment> segments = new ArrayList<>();
        List<Polygon> polygons = new ArrayList<>();
        List<Integer> cityIdx = new ArrayList<>();

        vertices = mesh.getVerticesList();
        segments = mesh.getSegmentsList();
        polygons = mesh.getPolygonsList();

        Random random = new Random();
        int countCities = 0;
        int randomIdx;
        int randomNum;
        Property name = Property.newBuilder().setKey("city").setValue("0").build();

        while (countCities != numCities) {
            randomIdx = random.nextInt();
            if (polygons.get(randomIdx).getProperties(1).getValue() == "land") {
                List<Integer> segmentIdx = polygons.get(randomIdx).getSegmentIdxsList();
                randomNum = random.nextInt(segmentIdx.size());
                Vertex targetVertex = vertices.get(segments.get(segmentIdx.get(randomNum)).getV1Idx());
                Vertex vertex = Vertex.newBuilder(targetVertex).addProperties(0, name).build();
                vertices.set(segments.get(segmentIdx.get(randomNum)).getV1Idx(), vertex);
                cityIdx.add(segments.get(segmentIdx.get(randomNum)).getV1Idx());
            }
        }


        return Mesh.newBuilder().addAllPolygons(polygons).addAllSegments(segments).addAllVertices(vertices).build();
    }

    public static void starNetwork(List<Vertex> vertices, List<Segment> segments, List<Integer> cityIdx) {
        Graph graph = new Graph();
        graph.convert(vertices, segments);
        for (int i = 0; i < cityIdx.size(); i++) {
            graph.addCity(cityIdx.get(i));
        }
        Node hub = graph.getNode(cityIdx.get(0));
        for (int j = 1; j < cityIdx.size(); j++) {
            graph.addNetwork(graph.shortestPath(hub, graph.getNode(cityIdx.get(j))));
        }

    }
}
