import java.util.ArrayList;
import com.zeroc.Ice.*;
import java.util.List;

import guiInventario.Interfaz;

public class BodegaCentral {

    public static void main(String[] args) {
        List<String> extArgs = new ArrayList<>();
        try (Communicator communicator = Util.initialize(args, "bodegaControl.cfg", extArgs)) {
            ObjectAdapter adapter = communicator.createObjectAdapter("Bodega");
            Interfaz service = new Interfaz(communicator);

            service.run();
            // De pronto esta línea de abajo puede fallar.
            communicator.waitForShutdown();
        }

    }
}
