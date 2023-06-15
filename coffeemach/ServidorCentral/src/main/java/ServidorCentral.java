
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import com.zeroc.Ice.*;
import comunicacion.*;
import interfaz.ControladorMensajes;
import interfaz.ControladorRecetas;
import receta.ProductoReceta;
import servicios.*;
import tracker.PetitionTrackerImp;
import ventas.VentasManager;
import ServerControl.*;
import alarma.Alarma;
import alarma.AlarmasManager;

public class ServidorCentral {

    public static void main(String[] args) {
        List<String> params = new ArrayList<>();
        try (Communicator communicator = Util.initialize(args, "server.cfg", params)) {

            ObjectAdapter adapter = communicator.createObjectAdapter("Server");

            PetitionTrackerImp petitionTrackerImp = new PetitionTrackerImp();

            ServerControl control = new ServerControl(communicator);

            ServicioComLogistica log = new ControlComLogistica(control, petitionTrackerImp);

            Alarma alarma = new Alarma(new AlarmasManager(communicator), petitionTrackerImp);

            ProductoReceta recetas = new ProductoReceta(petitionTrackerImp);
            recetas.setCommunicator(communicator);

            VentasManager ventas = new VentasManager(petitionTrackerImp);
            ventas.setCommunicator(communicator);

            adapter.add(alarma, Util.stringToIdentity("Alarmas"));
            adapter.add(ventas, Util.stringToIdentity("Ventas"));
            adapter.add(log, Util.stringToIdentity("logistica"));
            adapter.add(recetas, Util.stringToIdentity("Recetas"));

            String address =args[0];
            String port = args[1];

            BrokerServicePrx brokerServicePrx = BrokerServicePrx.checkedCast(communicator.propertyToProxy("broker")).ice_twoway();;

            AlarmaServicePrx alarmaServicePrx = AlarmaServicePrx.checkedCast(communicator.stringToProxy(" Alarmas:tcp -h "+address+" -p "+port));
            brokerServicePrx.subscribeAlarma(alarmaServicePrx);

            RecetaServicePrx recetaServicePrx = RecetaServicePrx.checkedCast(communicator.stringToProxy(" Recetas:tcp -h "+address+" -p "+port));
            brokerServicePrx.subscribeReceta(recetaServicePrx);

            VentaServicePrx ventaServicePrx = VentaServicePrx.checkedCast(communicator.stringToProxy(" Ventas:tcp -h "+address+" -p "+port));
            brokerServicePrx.subscribeVenta(ventaServicePrx);

            ControladorRecetas controladorRecetas = new ControladorRecetas();
            controladorRecetas.setRecetaService(recetas);
            controladorRecetas.run();

            ControladorMensajes controladorMensajes = new ControladorMensajes();
            adapter.add(controladorMensajes, Util.stringToIdentity("CM"));
            
            adapter.activate();
            controladorMensajes.start();
            communicator.waitForShutdown();

        }
    }

}
