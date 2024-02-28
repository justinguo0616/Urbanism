package pathfinding;

import java.util.*;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.*;

public class Graph {

    private List<Node> nodeList = new ArrayList<>();
    private List<Edge> edgeList = new ArrayList<>();

    private List<List<Integer>> starNetwork = new ArrayList<>();

    public void convert(List<Vertex> vertices, List<Segment> segments) {
        Random random = new Random();

        for (int i = 0; i < vertices.size(); i++) {
            Node newNode = new Node(vertices.get(i), i, cityName(), random.nextInt(3));
            nodeList.add(newNode);
        }

        for (int j = 0; j < segments.size(); j++) {
            Edge newEdge = new Edge(nodeList.get(segments.get(j).getV1Idx()), nodeList.get(segments.get(j).getV2Idx()));
            edgeList.add(newEdge);
            nodeList.get(segments.get(j).getV1Idx()).addAdjacentNodes(segments.get(j).getV2Idx());
            nodeList.get(segments.get(j).getV2Idx()).addAdjacentNodes(segments.get(j).getV1Idx());
        }
    }

    public void addNode(Node node) {
        nodeList.add(node);
    }

    public void addEdge(Edge edge) {
        edgeList.add(edge);
    }

    public void addCity(int nodeNum) {
        nodeList.get(nodeNum).setName("city");
    }

    public void addNetwork(List<Integer> list) {
        starNetwork.add(list);
    }

    public void findAdjacent() {
        Edge currentEdge = edgeList.get(0);
        currentEdge.getNode1().addAdjacentNodes(currentEdge.getNode2().getPosition());
        currentEdge.getNode2().addAdjacentNodes(currentEdge.getNode1().getPosition());
        for (int i = 1; i < edgeList.size(); i++) {
            currentEdge = edgeList.get(i);
            currentEdge.getNode1().addAdjacentNodes(currentEdge.getNode2().getPosition());
            currentEdge.getNode2().addAdjacentNodes(currentEdge.getNode1().getPosition());
        }
    }

    public List shortestPath (Node source, Node end){
        List<Node> idealPath = new ArrayList<>();
        List<Integer> adjacentNodes = new ArrayList<>();
        Node currentNode = source;
        Node adjNode;
        int shortestDistanceNode = source.getPosition();
        double shortestDistance = Double.MAX_VALUE;
        double endX = end.getxCoord();
        double endY = end.getyCoord();
        double currX;
        double currY;
        double currentDistance;

        idealPath.add(currentNode);

        while (!currentNode.equals(end)) {
            adjacentNodes = currentNode.getAdjacentNodes();
            for (int i = 0; i < adjacentNodes.size(); i++) {
                adjNode = nodeList.get(adjacentNodes.get(i));
                currX = adjNode.getxCoord();
                currY = adjNode.getyCoord();
                currentDistance = (Math.sqrt( Math.pow((endX - currX), 2) + Math.pow((endY - currY), 2)) );
                if (currentDistance < shortestDistance && !idealPath.contains(adjNode)) {
                    shortestDistance = currentDistance;
                    shortestDistanceNode = adjNode.getPosition();
                }
            }
            idealPath.add(nodeList.get(shortestDistanceNode));
            currentNode = nodeList.get(shortestDistanceNode);
            shortestDistance = Double.MAX_VALUE;
        }

        return idealPath;
    }


    public String cityName() {
        Random random = new Random();
        int num = random.nextInt(100);
        String name;

        if (num < 90) {
            name = "none";
        }
        else if (num < 98) {
            name = "town";
        }
        else {
            name = "city";
        }

        return name;
    }

    public Node getNode(int index) {
        return nodeList.get(index);
    }

}
