package servlets.dal;

import java.util.TreeMap;

import servlets.modelos.*;

public class DaoCocheMemoria implements DaoCoche {
	
	private static final TreeMap<Long, Coche> coches = new TreeMap<>();
	
	static {
		coches.put(1L, new Coche(1L, "1263GGF", "Audi", "R8", "Gris", 570, 4163, true));
		coches.put(2L, new Coche(2L, "2314TSR", "BMW","I8", "Blanco", 231, 1497, true));
		coches.put(3L, new Coche(3L, "9637SHV", "Mercedes", "C180", "Negro", 156, 1595, true));
		coches.put(4L, new Coche(4L, "9475HNJ", "Seat", "Ibiza", "Rosa", 90, 1350, false));
		coches.put(5L, new Coche(5L, "2564MKL", "Citroen", "C5", "Amarillo", 156, 1595, false));
		coches.put(6L, new Coche(6L, "7364PRF", "Seat", "León", "Azul", 90, 1420, false));
	}
	
	private DaoCocheMemoria() {
	}

	private static final DaoCocheMemoria INSTANCIA = new DaoCocheMemoria();

	public static DaoCocheMemoria getInstancia() {
		return INSTANCIA;
	}

	@Override
	public Iterable<Coche> obtenerTodos() {
		return coches.values();
	}
	
	@Override
	public Coche obtenerPorId(Long id) {
		return coches.get(id);
	}

	@Override
	public void insertar(Coche coche) {
		Long id = coches.size() > 0 ? coches.lastKey() + 1L : 1L;
		coche.setId(id);
		coches.put(coche.getId(), coche);
	}

	@Override
	public void modificar(Coche coche) {
		coches.put(coche.getId(), coche);
	}

	@Override
	public void borrar(Long id) {
		coches.remove(id);	
	}
	
	public boolean obtenerReservaPorId(Long id) {
		
		boolean reservado = false;
		
		for(Coche coche: coches.values()) {
			if(coche.getId() == id) {
				reservado = coche.isReserva();
			}
		}
		
		return reservado;
	}
	

	@Override
	public void setFalseWhenNoReserva(Long idCoche) {
		
		for(Coche coche: coches.values()) {
			if(coche.getId() == idCoche) {
				coche.setReserva(false);;
			}
		}
		

	}

	@Override
	public void setTrueReserva(Long idCoche) {
		
		for(Coche coche: coches.values()) {
			if(coche.getId() == idCoche) {
				coche.setReserva(true);;
			}
		}
	}

	@Override
	public boolean comprobarMatricula(String matricula) {
		
		boolean existe = false;
		
		for(Coche coche: coches.values()) {
			if(coche.getMatricula().trim().equals(matricula)) {
				existe = true;;
			}
		}
		
		return existe;
	}

}
