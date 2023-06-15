import com.zeroc.Ice.*;

import McControlador.ControladorMQ;

import java.util.*;
import servicios.*;

public class CoffeeMach {
  public static void main(String[] args) {
    List<String> extPar = new ArrayList<>();
    try (Communicator communicator = Util.initialize(args, "coffeMach.cfg", extPar)) {

      BrokerServicePrx brokerServicePrx = BrokerServicePrx.checkedCast(
              communicator.propertyToProxy("broker")).ice_twoway();

      ObjectAdapter adapter = communicator.createObjectAdapter("CoffeMach");

      ControladorMQ service = new ControladorMQ();

      service.run();
      adapter.add((ServicioAbastecimiento) service, Util.stringToIdentity("abastecer"));

      adapter.activate();
      communicator.waitForShutdown();
    }
  }
}
