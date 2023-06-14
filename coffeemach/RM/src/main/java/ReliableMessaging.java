import java.util.ArrayList;
import java.util.List;

import com.zeroc.Ice.*;

import servicios.ReliableMessagingService;
import servicios.ServicioAbastecimiento;

public class ReliableMessaging{
    public static void main(String[] args) {
        List<String> extPar = new ArrayList<>();
    try (Communicator communicator = Util.initialize(args, "reliableMessaging.cfg", extPar)) {
      ObjectAdapter adapter = communicator.createObjectAdapter("ReliableMessaging");
      ReliableMessagingServiceImp reliableMessagingServiceImp = new ReliableMessagingServiceImp();
      adapter.add(reliableMessagingServiceImp, Util.stringToIdentity("RM"));
      adapter.activate();
      communicator.waitForShutdown();
    }
    }
}
