package alarma;

import java.util.Date;

import com.zeroc.Ice.Current;

import servicios.AlarmaService;
import servicios.Moneda;
import tracker.PetitionTrackerImp;

public class Alarma implements AlarmaService {

    public static final int ALARMA_INGREDIENTE = 1;
    public static final int ALARMA_MONEDA_CIEN = 2;
    public static final int ALARMA_MONEDA_DOS = 3;
    public static final int ALARMA_MONEDA_QUI = 4;
    public static final int ALARMA_SUMINISTRO = 5;
    public static final int ALARMA_MAL_FUNCIONAMIENTO = 6;

    private AlarmasManager manager;
    private PetitionTrackerImp petitionTrackerImp;

    public Alarma(AlarmasManager manager, PetitionTrackerImp petitionTrackerImp) {
        this.manager = manager;
        this.petitionTrackerImp = petitionTrackerImp;
    }

    @Override
    public void recibirNotificacionEscasezIngredientes(String iDing, int idMaq, Current current) {
        petitionTrackerImp.increaseCount();
        manager.alarmaMaquina(ALARMA_INGREDIENTE, idMaq, new Date());
        petitionTrackerImp.decreaseCount();
    }

    @Override
    public void recibirNotificacionInsuficienciaMoneda(Moneda moneda, int idMaq, Current current) {
        petitionTrackerImp.increaseCount();
        switch (moneda) {
            case CIEN:
                manager.alarmaMaquina(ALARMA_MONEDA_CIEN, idMaq, new Date());
                break;
            case DOCIENTOS:
                manager.alarmaMaquina(ALARMA_MONEDA_DOS, idMaq, new Date());
                break;
            case QUINIENTOS:
                manager.alarmaMaquina(ALARMA_MONEDA_QUI, idMaq, new Date());
                break;
            default:
                break;
        }
        petitionTrackerImp.decreaseCount();
    }

    @Override
    public void recibirNotificacionEscasezSuministro(String idSumin, int idMaq, Current current) {
        // suministro
        petitionTrackerImp.increaseCount();
        manager.alarmaMaquina(ALARMA_SUMINISTRO, idMaq, new Date());
        petitionTrackerImp.decreaseCount();
    }

    @Override
    public void recibirNotificacionAbastesimiento(int idMaq, String idInsumo, int cantidad, Current current) {
        // TODO validar el insumo
        petitionTrackerImp.increaseCount();
        manager.desactivarAlarma(ALARMA_INGREDIENTE, idMaq, new Date());
        petitionTrackerImp.decreaseCount();
    }

    @Override
    public void recibirNotificacionMalFuncionamiento(int idMaq, String descri, Current current) {
        petitionTrackerImp.increaseCount();
        manager.alarmaMaquina(ALARMA_MAL_FUNCIONAMIENTO, idMaq, new Date());
        petitionTrackerImp.decreaseCount();
    }

    @Override
    public int petitionCount(Current current) {
        return petitionTrackerImp.getCount();
    }

}
