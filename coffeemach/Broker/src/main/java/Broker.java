import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.ObjectAdapter;
import com.zeroc.Ice.Util;

public class Broker {

    public static void main(String[] args) {
        java.util.List<String> extraArgs = new java.util.ArrayList<>();
        try (Communicator communicator = com.zeroc.Ice.Util.initialize(args, "broker.cfg", extraArgs)) {
            ObjectAdapter adapter = communicator.createObjectAdapter("BrokerServer");
            BrokerServiceImp brokerServiceImp = new BrokerServiceImp();
            adapter.add(brokerServiceImp, Util.stringToIdentity("Broker"));
            adapter.activate();
            communicator.waitForShutdown();
        }
    }

}