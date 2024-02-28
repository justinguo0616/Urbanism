package ca.mcmaster.cas.se2aa4.a2.visualizer.renderer;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.visualizer.renderer.properties.ColorProperty;

import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.util.Iterator;
import java.util.Optional;

public class GraphicRenderer implements Renderer {

    private static final int THICKNESS = 3;
    public void render(Mesh aMesh, Graphics2D canvas) {
        canvas.setColor(Color.BLACK);
        Stroke stroke = new BasicStroke(0.2f);
        canvas.setStroke(stroke);
        drawPolygons(aMesh,canvas);
        drawRiver(aMesh, canvas);
    }

    private void drawPolygons(Mesh aMesh, Graphics2D canvas) {
        for(Structs.Polygon p: aMesh.getPolygonsList()){
            drawAPolygon(p, aMesh, canvas);
        }
    }

    private void drawAPolygon(Structs.Polygon p, Mesh aMesh, Graphics2D canvas) {
        Hull hull = new Hull();
        for(Integer segmentIdx: p.getSegmentIdxsList()) {
            hull.add(aMesh.getSegments(segmentIdx), aMesh);
        }
        Path2D path = new Path2D.Float();
        Iterator<Vertex> vertices = hull.iterator();
        Vertex current = vertices.next();
        path.moveTo(current.getX(), current.getY());
        while (vertices.hasNext()) {
            current = vertices.next();
            path.lineTo(current.getX(), current.getY());
        }
        path.closePath();
        canvas.draw(path);
        Optional<Color> fill = new ColorProperty().extract(p.getPropertiesList());
        if(fill.isPresent()) {
            Color old = canvas.getColor();
            canvas.setColor(fill.get());
            canvas.fill(path);
            canvas.setColor(old);
        }
    }
    private void drawRiver( Mesh aMesh, Graphics2D canvas) {
        for(Structs.Segment s: aMesh.getSegmentsList()){
            Optional<Color> fill = new ColorProperty().extract(s.getPropertiesList());
            if(fill.isPresent()) {
                int vertex1_idx = s.getV1Idx();
                int vertex2_idx = s.getV2Idx();
                Vertex vertex1 = aMesh.getVerticesList().get(vertex1_idx);
                Vertex vertex2 = aMesh.getVerticesList().get(vertex2_idx);
                canvas.setColor(fill.get());
                if(s.getProperties(1).getValue().equals("river")){
                    Stroke stroke = new BasicStroke(THICKNESS);
                    canvas.setStroke(stroke);
                    Line2D line = new Line2D.Float( (float) vertex1.getX(), (float) vertex1.getY(),
                            (float) vertex2.getX(), (float) vertex2.getY());
                    canvas.draw(line);
                }
                else if(s.getProperties(1).getValue().equals("riverFlow")){
                    Stroke stroke = new BasicStroke( 8);
                    canvas.setStroke(stroke);
                    Line2D line = new Line2D.Float( (float) vertex1.getX(), (float) vertex1.getY(),
                            (float) vertex2.getX(), (float) vertex2.getY());
                    canvas.draw(line);
                }

            }

        }
    }

}
