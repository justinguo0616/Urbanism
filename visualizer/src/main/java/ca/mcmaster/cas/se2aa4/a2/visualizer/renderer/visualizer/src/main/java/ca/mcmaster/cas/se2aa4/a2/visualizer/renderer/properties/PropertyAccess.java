package ca.mcmaster.cas.se2aa4.a2.visualizer.renderer.visualizer.src.main.java.ca.mcmaster.cas.se2aa4.a2.visualizer.renderer.properties;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.List;
import java.util.Optional;

public interface PropertyAccess<T> {

    Optional<T> extract(List<Structs.Property> props);

}

