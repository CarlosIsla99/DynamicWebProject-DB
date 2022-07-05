package servlets.dal;

import servlets.modelos.*;

public interface DaoCoche extends Dao<Coche> {
	
	boolean obtenerReservaPorId(Long id);
	
	void setFalseWhenNoReserva(Long id);
	
	void setTrueReserva(Long id);
	
	boolean comprobarMatricula(String matricula);
}
