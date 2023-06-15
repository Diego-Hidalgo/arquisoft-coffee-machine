import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

import com.zeroc.Ice.*;
import java.util.concurrent.*;

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

      String address = args[0];
      int port = Integer.parseInt(args[1]);

      /*
       * try {
       * //ReliableMessagingServicePrx gatewayAcknowledgment =
       * ReliableMessagingServicePrx.checkedCast(communicator.propertyToProxy(
       * "acknowledgement")).ice_twoway();
       * //ReliableMessagingServicePrx gatewayClient =
       * ReliableMessagingServicePrx.checkedCast(communicator.propertyToProxy(
       * "reliableServer")).ice_twoway();
       * } catch (ConnectionRefusedException e ) {
       * System.out.println(e.getMessage());
       * System.out.println("Failed gateway :c");
       * }
       * 
       * ReliableMessagingServicePrx gatewayAcknowledgment = null;
       * ReliableMessagingServicePrx gatewayClient = null;
       * 
       * System.out.println("Gateway error!");
       */

      System.out.println("Test error location!");
      ObjectAdapter adapter = communicator.createObjectAdapter("ReliableMessaging");
      ReliableMessagingServiceImp reliableMessagingServiceImp = new ReliableMessagingServiceImp(address, port);
      reliableMessagingServiceImp.setCommunicator(communicator);
      System.out.println("Test error location!2");
      adapter.add(reliableMessagingServiceImp, Util.stringToIdentity("RM"));
      adapter.activate();
      System.out.println("Test error location!3");
      reliableMessagingServiceImp.run();

      communicator.waitForShutdown();
    }
  }
}
