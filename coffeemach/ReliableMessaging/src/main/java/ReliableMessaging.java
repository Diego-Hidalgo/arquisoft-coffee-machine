import java.util.ArrayList;
import java.util.List;

import com.zeroc.Ice.*;

import servicios.BodegaServicePrx;
import servicios.LogisticServicePrx;
import servicios.ReliableMessagingService;
import servicios.ReliableMessagingServicePrx;
import servicios.ServicioAbastecimiento;

public class ReliableMessaging {
  public static void main(String[] args) {
    List<String> extPar = new ArrayList<>();
    System.out.println("Running Before!");
    try (Communicator communicator = Util.initialize(args, "reliableMessaging.cfg", extPar)) {

      System.out.println("Running!");

      try {
        ReliableMessagingServicePrx gatewayAcknowledgment = ReliableMessagingServicePrx.checkedCast(communicator.propertyToProxy("acknowledgement")).ice_twoway();
        ReliableMessagingServicePrx gatewayClient = ReliableMessagingServicePrx.checkedCast(communicator.propertyToProxy("reliableServer")).ice_twoway();
      } catch (ConnectionRefusedException e ) {
        System.out.println(e.getMessage());
        System.out.println("Failed gateway :c");
      }

      ReliableMessagingServicePrx gatewayAcknowledgment = null;
      ReliableMessagingServicePrx gatewayClient = null;

      System.out.println("Gateway error!");
      /*BodegaServicePrx bodegaServicePrx = BodegaServicePrx.checkedCast(
          communicator.propertyToProxy("bodega")).ice_twoway();

      LogisticServicePrx logisticServicePrx = LogisticServicePrx.checkedCast(
          communicator.propertyToProxy("logistica")).ice_twoway();*/

      ObjectAdapter adapter = communicator.createObjectAdapter("ReliableMessaging");
      ReliableMessagingServiceImp reliableMessagingServiceImp = new ReliableMessagingServiceImp(gatewayAcknowledgment, gatewayClient);
      reliableMessagingServiceImp.setCommunicator(communicator);

      adapter.add(reliableMessagingServiceImp, Util.stringToIdentity("RM"));
      adapter.activate();

      reliableMessagingServiceImp.run();

      communicator.waitForShutdown();
    }
  }
}
