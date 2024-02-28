package pathfinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.*;

public class Node {

    private int elevation = 0;
    private int position;
    private String name = "none";
    private double xCoord;
    private double yCoord;
    private List<Integer> adjacentNodes = new ArrayList<>();
    private List<Integer> segmentList = new ArrayList<>();

    public Node(Vertex vertex, int positionInList, String newName, int newElevation) {
        xCoord = vertex.getX();
        yCoord = vertex.getY();
        position = positionInList;
        elevation = newElevation;
        name = newName;
    }

    public Node(Vertex vertex, int positionInList, String newName) {
        xCoord = vertex.getX();
        yCoord = vertex.getY();
        position = positionInList;
        name = newName;
    }

    public Node(Vertex vertex, int positionInList, int newElevation) {
        xCoord = vertex.getX();
        yCoord = vertex.getY();
        position = positionInList;
        elevation = newElevation;
    }

    public Node(Vertex vertex, int positionInList) {
        xCoord = vertex.getX();
        yCoord = vertex.getY();
        position = positionInList;
    }

    public Node(double x, double y, int positionInList) {
        xCoord = x;
        yCoord = y;
        position = positionInList;
    }

    public Node(double x, double y) {
        xCoord = x;
        yCoord = y;
    }

    public Node() {
    }

    public void addAdjacentNodes(int addedNode) {
        adjacentNodes.add(addedNode);
    }

    public void addSegment (int segmentIdx) {
        segmentList.add(segmentIdx);
    }

    public void setxCoord(double x) {
        xCoord = x;
    }

    public void setyCoord(double y) {
        yCoord = y;
    }

    public void setName(String newName) {
        name = newName;
    }

    public double getxCoord() {
        return xCoord;
    }

    public double getyCoord() {
        return yCoord;
    }

    public int getPosition() {
        return position;
    }

    public List<Integer> getAdjacentNodes() {
        return adjacentNodes;
    }

    public List<Integer> getSegmentList() {
        return segmentList;
    }
}
