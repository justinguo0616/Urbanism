package pathfinding;

public class Edge {

    private Node node1;
    private Node node2;
    private double length;

    public Edge(Node firstNode, Node secondNode) {
        node1 = firstNode;
        node2 = secondNode;
        double x1 = node1.getxCoord();
        double y1 = node1.getyCoord();
        double x2 = node2.getxCoord();
        double y2 = node2.getyCoord();
        length = (Math.sqrt( Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2)) );
    }

    public Node getNode1() {
        return node1;
    }

    public Node getNode2() {
        return node2;
    }
}
