import org.junit.jupiter.api.Test;
import pathfinding.Edge;
import pathfinding.Graph;
import pathfinding.Node;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.ArrayList;

class GraphTest {

    @Test
    void findShortestPathBetweenFiveNodes() {
        List<Node> expected = new ArrayList<>();
        Graph graph = new Graph();
        Node node1 = new Node(0, 0, 0);
        Node node2 = new Node(1, 1, 1);
        Node node3 = new Node(1, 2, 2);
        Node node4 = new Node(4, 3, 3);
        Node node5 = new Node(5, 5, 4);
        Edge edge1 = new Edge(node1, node2);
        Edge edge2 = new Edge(node1, node3);
        Edge edge3 = new Edge(node2, node3);
        Edge edge4 = new Edge(node3, node4);
        Edge edge5 = new Edge(node4, node5);
        Edge edge6 = new Edge(node2, node5);
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addNode(node4);
        graph.addNode(node5);
        graph.addEdge(edge1);
        graph.addEdge(edge2);
        graph.addEdge(edge3);
        graph.addEdge(edge4);
        graph.addEdge(edge5);
        graph.addEdge(edge6);
        graph.findAdjacent();
        expected.add(node1);
        expected.add(node3);
        expected.add(node4);
        expected.add(node5);
        assertEquals(expected, graph.shortestPath(node1, node5));
    }

    @Test
    void findShortestPathBetweenTenNodes() {
        List<Node> expected = new ArrayList<>();
        Graph graph = new Graph();
        Node node1 = new Node(0, 0, 0);
        Node node2 = new Node(1, 1, 1);
        Node node3 = new Node(1, 2, 2);
        Node node4 = new Node(4, 3, 3);
        Node node5 = new Node(5, 5, 4);
        Node node6 = new Node(3, 7, 5);
        Node node7 = new Node(5, 6, 6);
        Node node8 = new Node(8, 8, 7);
        Node node9 = new Node(7, 9, 8);
        Node node10 = new Node(10, 10, 9);
        Edge edge1 = new Edge(node1, node2);
        Edge edge2 = new Edge(node1, node3);
        Edge edge3 = new Edge(node2, node3);
        Edge edge4 = new Edge(node3, node4);
        Edge edge5 = new Edge(node4, node5);
        Edge edge6 = new Edge(node2, node5);
        Edge edge7 = new Edge(node5, node6);
        Edge edge8 = new Edge(node5, node7);
        Edge edge9 = new Edge(node6, node7);
        Edge edge10 = new Edge(node7, node9);
        Edge edge11 = new Edge(node5, node8);
        Edge edge12 = new Edge(node8, node9);
        Edge edge13 = new Edge(node8, node10);
        Edge edge14 = new Edge(node9, node10);
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addNode(node4);
        graph.addNode(node5);
        graph.addNode(node6);
        graph.addNode(node7);
        graph.addNode(node8);
        graph.addNode(node9);
        graph.addNode(node10);
        graph.addEdge(edge1);
        graph.addEdge(edge2);
        graph.addEdge(edge3);
        graph.addEdge(edge4);
        graph.addEdge(edge5);
        graph.addEdge(edge6);
        graph.addEdge(edge7);
        graph.addEdge(edge8);
        graph.addEdge(edge9);
        graph.addEdge(edge10);
        graph.addEdge(edge11);
        graph.addEdge(edge12);
        graph.addEdge(edge13);
        graph.addEdge(edge14);
        graph.findAdjacent();
        expected.add(node1);
        expected.add(node3);
        expected.add(node4);
        expected.add(node5);
        expected.add(node8);
        expected.add(node10);
        assertEquals(expected, graph.shortestPath(node1, node10));
    }

    @Test
    void noNodesShouldFail() {
        Node node1 = new Node();
        Node node2 = new Node();
        Graph graph = new Graph();
        try {
            graph.shortestPath(node1, node2);
            fail();
        }
        catch (Exception e) {
            assertEquals("Index 0 out of bounds for length 0", e.getMessage());
        }
    }
}