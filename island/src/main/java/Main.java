import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;


import java.awt.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Configuration config = new Configuration(args);
        Mesh theMesh = new MeshFactory().read(config.input());
        if(config.mode()!=null){
            if (config.mode().equals("lagoon")){
                Mesh sandbox = Sandbox.sandbox(theMesh);
                new MeshFactory().write(sandbox, config.output());
            }
            else if(config.mode().equals("island")){
                Mesh island = Island.island(theMesh,args);
                new MeshFactory().write(island, config.output());
            }
        }


    }
}
