package servlets.dal;

import servlets.modelos.*;

public interface DaoReserva extends Dao<Reserva> {

	Long encontrarCochePorIdReserva(Long idResrva);
}
