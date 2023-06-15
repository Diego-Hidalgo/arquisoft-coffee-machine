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

      String address = args[0];
      int port = Integer.parseInt(args[1]);

      BrokerServicePrx brokerServicePrx = BrokerServicePrx.checkedCast(
          communicator.propertyToProxy("broker")).ice_twoway();

      ObjectAdapter adapter = communicator.createObjectAdapter("ReliableMessaging");
      ReliableMessagingServiceImp reliableMessagingServiceImp = new ReliableMessagingServiceImp(address, port);
      reliableMessagingServiceImp.setCommunicator(communicator);
      reliableMessagingServiceImp.setBroker(brokerServicePrx);
      adapter.add(reliableMessagingServiceImp, Util.stringToIdentity("RM"));
      adapter.activate();
      reliableMessagingServiceImp.run();

      communicator.waitForShutdown();
    }
  }
}
