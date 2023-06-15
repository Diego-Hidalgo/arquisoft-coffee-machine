import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

import com.zeroc.Ice.*;
import java.util.concurrent.*;

import servicios.*;


public class ReliableMessaging {
  public static void main(String[] args) {
    List<String> extPar = new ArrayList<>();
    System.out.println("Running Before!");
    try (Communicator communicator = Util.initialize(args, "reliableMessaging.cfg", extPar)) {

      System.out.println("Running!");

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

      BrokerServicePrx brokerServicePrx = BrokerServicePrx.checkedCast(
              communicator.propertyToProxy("broker")).ice_twoway();

      ObjectAdapter adapter = communicator.createObjectAdapter("ReliableMessaging");
      ReliableMessagingServiceImp reliableMessagingServiceImp = new ReliableMessagingServiceImp();
      reliableMessagingServiceImp.setCommunicator(communicator);
      reliableMessagingServiceImp.setBroker(brokerServicePrx);
      System.out.println("Test error location!2");
      adapter.add(reliableMessagingServiceImp, Util.stringToIdentity("RM"));
      adapter.activate();
      System.out.println("Test error location!3");
      reliableMessagingServiceImp.run();

      communicator.waitForShutdown();
    }
  }
}
