import com.zeroc.Ice.Current;
import com.zeroc.Ice.*;

import java.net.ConnectException;
import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.Queue;

import servicios.BrokerServicePrx;
import servicios.ReliableMessagingService;
import servicios.ReliableMessagingServicePrx;

public class ReliableMessagingServiceImp extends Thread implements ReliableMessagingService {

    private Communicator communicator;
    private Queue<String> alarmas;
    private Queue<String> escasezIngs;
    private BrokerServicePrx broker;

    public ReliableMessagingServiceImp(String address, int port) {
        alarmas = new LinkedList<>();
        escasezIngs = new LinkedList<>();
    }

    public synchronized void setCommunicator(Communicator com) {
        communicator = com;
    }

    public void setBroker(BrokerServicePrx broker) {
        this.broker = broker;
    }

    @Override
    public void receiveEscasezSuministro(String idSumin, int idMaq, Current current) {

    }

    private void sendEscasezSuministro(String msg) {

    }

    @Override
    public void receiveMalFuncionamiento(int idMaq, String descri, Current current) {

    }

    private void sendMalFuncionamiento(String msg) {

    }

    @Override
    public void receiveEscasezIngrediente (String ing, int cod, Current current) {

    }

    private void sendEscasezIngredientes(String msj) {

    }

     private ReliableMessagingServicePrx createReliableMessagingProxy(String address, int port) {
        System.out.println("Test error!5");
        ReliableMessagingServicePrx rmProxy = null;

        String proxyString = "CM:tcp -h " + address + " -p " + port;
        ObjectPrx base = communicator.stringToProxy(proxyString);
        rmProxy = ReliableMessagingServicePrx.checkedCast(base);
        if (rmProxy == null) {
            throw new IllegalStateException("Error al crear el proxy de ReliableMessagingService");
        }

        return rmProxy;
    }

    @Override
    public void run() {
        /*
         * while (true) {
         * System.out.println("Sape");
         * try {
         * Thread.sleep(10000);
         * } catch (InterruptedException e) {
         * continue;
         * }
         * }
         */

        while (true) {
            String alarm = "";
            String alarmIng = "";
            try {
                while (!alarmas.isEmpty() || !escasezIngs.isEmpty()) {
                    alarm = alarmas.peek();
                    alarmIng = escasezIngs.peek();
                    try {
                        System.out.println(alarm);
                        sendEscasezIngredientes(alarmIng);
                        escasezIngs.poll();
                        alarmas.poll();
                    } catch (ConnectionRefusedException e1) {
                        System.out.println("Error al enviar la alarma: " + e1.getMessage());
                        alarmas.offer(alarm);
                        alarmas.poll();
                        // Handle the exception, log it, or perform any other necessary actions
                    }
                    // Para demorarse medio segundo entre un envío de mensaje y otro, para que no
                    // envíe a millón
                    // Pues se consumen recursos.
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e15) {
                        System.out.println("Error al parar el tiempo: " + e15.getMessage());
                    }
                }
                Thread.yield();
            } catch (RuntimeException e2) {
                System.out.println("Error al procesar la cola de alarmas: " +
                        e2.getMessage());
                // Handle the exception, log it, or perform any other necessary actions
            }
        }

    }
}
