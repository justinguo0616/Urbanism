import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import pathfinding.*;

public class Island {

    public static Mesh island(Mesh mesh, String[] args){
        Configuration config = new Configuration(args);

        //SHAPE
        mesh=Shape.shape(mesh,args);

        //ELEVATION
        mesh = Elevation.elevation(mesh, args);

        //LAKES
        if (config.lakes() == null){}
        else{
            int numLakes = Integer.parseInt(config.lakes());
            mesh = Lakes.lake(mesh, numLakes,args);
        }

        //AQUIFERS
        if (config.aquifers() == null){}
        else{
            int numAquifers = Integer.parseInt(config.aquifers());
            mesh = Aquifers.aquifers(mesh, numAquifers,args);
        }

        //RIVERS
        if (config.rivers() == null||(config.altitude()==null)&&config.lakes()==null){}
        else{
            int numRivers = Integer.parseInt(config.rivers());
            mesh = Rivers.river(mesh, numRivers);
        }

        if (config.biomes() == null) {}
        else {
            mesh = Biomes.biomes(mesh, args);
        }

        if (config.city() == null) {}
        else {
            int numCities = Integer.parseInt(config.city());
            mesh = Cities.cities(mesh, numCities);
        }

        return mesh;
    }
}