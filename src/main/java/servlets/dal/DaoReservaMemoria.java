package servlets.dal;

import java.time.LocalDateTime;
import java.util.TreeMap;

import servlets.modelos.Reserva;

public class DaoReservaMemoria implements DaoReserva {
	private static final TreeMap<Long, Reserva> reservas = new TreeMap<>();

	static {
		reservas.put(1L, new Reserva(1L, "Cliente 1", LocalDateTime.of(2022, 06, 29, 20, 00), 2, "Esto es una reserva del cliente 1", 2L, 2L));
		reservas.put(2L, new Reserva(2L, "Cliente 2", LocalDateTime.of(2022, 06, 26, 21, 30), 1, "Esto es una reserva del cliente 2", 3L, 1L));
		reservas.put(3L, new Reserva(3L, "Cliente 2", LocalDateTime.of(2022, 06, 27, 16, 15), 3, "Esto es una reserva del cliente 2", 3L, 3L));
	}

	// SINGLETON
	private DaoReservaMemoria() {}

	private static final DaoReservaMemoria INSTANCIA = new DaoReservaMemoria();

	public static DaoReservaMemoria getInstancia() {
		return INSTANCIA;
	}
	// FIN SINGLETON

	@Override
	public Iterable<Reserva> obtenerTodos() {
		return reservas.values();
	}

	@Override
	public Reserva obtenerPorId(Long id) {
		return reservas.get(id);
	}

	@Override
	public void insertar(Reserva reserva) {
		Long id = reservas.size() > 0 ? reservas.lastKey() + 1L : 1L;
		reserva.setId(id);
		reservas.put(id, reserva);
	}

	@Override
	public void modificar(Reserva reserva) {
		reservas.put(reserva.getId(), reserva);
	}

	@Override
	public void borrar(Long id) {
		reservas.remove(id);
	}

	@Override
	public Long encontrarCochePorIdReserva(Long idResrva) {
		
		Long idCoche = null;
		
		for(Reserva reserva: reservas.values()) {
			if(reserva.getId() == idResrva) {
				idCoche = reserva.getCoches_id();
			}
		}
		
		return idCoche;
		
	}


}
