import com.zeroc.Ice.*;

import McControlador.ControladorMQ;

import java.util.*;
import servicios.*;

public class CoffeeMach {
  public static void main(String[] args) {
    List<String> extPar = new ArrayList<>();
    try (Communicator communicator = Util.initialize(args, "coffeMach.cfg", extPar)) {

      /*
       * AlarmaServicePrx alarmaS = AlarmaServicePrx.checkedCast(
       * communicator.propertyToProxy("alarmas")).ice_twoway();
       * VentaServicePrx ventas = VentaServicePrx.checkedCast(
       * communicator.propertyToProxy("ventas")).ice_twoway();
       * RecetaServicePrx recetaServicePrx = RecetaServicePrx.checkedCast(
       * communicator.propertyToProxy("recetas")).ice_twoway();
       */

      BrokerServicePrx brokerServicePrx = BrokerServicePrx.checkedCast(
          communicator.propertyToProxy("broker")).ice_twoway();
          
      ReliableMessagingServicePrx rmServicePrx = ReliableMessagingServicePrx.checkedCast(
          communicator.propertyToProxy("rm")).ice_twoway();

      ObjectAdapter adapter = communicator.createObjectAdapter("CoffeMach");

      ControladorMQ service = new ControladorMQ();
      service.setRM(rmServicePrx);
      service.run();
      adapter.add((ServicioAbastecimiento) service, Util.stringToIdentity("abastecer"));

      adapter.activate();
      communicator.waitForShutdown();
    }
  }
}
