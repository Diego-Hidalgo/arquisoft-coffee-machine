package comunicacion;

import ServerControl.*;
import servicios.ServicioComLogistica;
import com.zeroc.Ice.*;
import tracker.PetitionTrackerImp;

import java.util.*;

public class ControlComLogistica implements ServicioComLogistica{
 
	private ServerControl control;
	private PetitionTrackerImp petitionTrackerImp;

	public ControlComLogistica(ServerControl con, PetitionTrackerImp petitionTrackerImp) {
		control = con;
		this.petitionTrackerImp = petitionTrackerImp;
	}

    @Override
	public List<String> asignacionMaquina(int codigoOperador, Current current) {
		petitionTrackerImp.increaseCount();
		List<String> result = control.listaAsignaciones(codigoOperador);
		petitionTrackerImp.decreaseCount();
		return result;
	}

	// Funciona correctamente
	@Override
	public List<String> asignacionMaquinasDesabastecidas(int codigoOperador, Current current) {
		petitionTrackerImp.increaseCount();
		List<String> result = control.listaAsignaciones(codigoOperador);
		petitionTrackerImp.decreaseCount();
		return result;
	}

	@Override
	public boolean inicioSesion(int codigoOperador, String password, Current current) {
		petitionTrackerImp.increaseCount();
		boolean result = control.existeOperador(codigoOperador, password);
		petitionTrackerImp.decreaseCount();
		return result;
	}

	@Override
	public int petitionCount(Current current) {
		return petitionTrackerImp.getCount();
	}

}
