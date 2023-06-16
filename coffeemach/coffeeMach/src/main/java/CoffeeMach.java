import com.zeroc.Ice.*;

import McControlador.ControladorMQ;

import java.util.*;

import com.zeroc.Ice.Properties;
import servicios.*;

public class CoffeeMach {
  public static void main(String[] args) {
    List<String> extPar = new ArrayList<>();
    try (Communicator communicator = Util.initialize(args, "coffeMach.cfg", extPar)) {
      communicator.getProperties().setProperty("CoffeMach.Endpoints", "tcp -h "+args[0]+" -p "+args[1]);
      /*
       * AlarmaServicePrx alarmaS = AlarmaServicePrx.checkedCast(
       * communicator.propertyToProxy("alarmas")).ice_twoway();
       * VentaServicePrx ventas = VentaServicePrx.checkedCast(
       * communicator.propertyToProxy("ventas")).ice_twoway();
       * RecetaServicePrx recetaServicePrx = RecetaServicePrx.checkedCast(
       * communicator.propertyToProxy("recetas")).ice_twoway();
       */
          
      ReliableMessagingServicePrx rmServicePrx = ReliableMessagingServicePrx.checkedCast(
          communicator.propertyToProxy("rm")).ice_twoway();

      ObjectAdapter adapter = communicator.createObjectAdapter("CoffeMach");

      ControladorMQ service = new ControladorMQ();

      for(int i = 1; i <= 10; i ++) {
        rmServicePrx.receiveEscasezIngrediente("algo", 1);
      }
      service.setRM(rmServicePrx);
      service.run();
      adapter.add((ServicioAbastecimiento) service, Util.stringToIdentity("abastecer"));

      adapter.activate();
      communicator.waitForShutdown();
    }
  }
}
