package servlets.modelos;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Reserva {
	
	private Long id;
	private String nombre;
	private LocalDateTime fechaHora;
	private Integer numeroPersonas;
	private String comentario;
	private Long usuarios_id;
	private Long coches_id;
	
	private Map<String, String> errores = new HashMap<>();
	
	public Reserva(Long id, String nombre, LocalDateTime fechaHora, Integer numeroPersonas,
			String comentario, Long usuarios_id, Long coches_id) {
		super();
		setId(id);
		setNombre(nombre);
		setFechaHora(fechaHora);
		setNumeroPersonas(numeroPersonas);
		setComentario(comentario);
		setUsuarios_id(usuarios_id);
		setCoches_id(coches_id);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		
		if (nombre == null || nombre.trim().length() < 3) {
			errores.put("nombre", "El nombre debe tener 3 o m�s caracteres");
		}
		
		this.nombre = nombre;
	}

	public LocalDateTime getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(LocalDateTime fechaHora) {
			
		if (fechaHora == null || fechaHora.isBefore(LocalDateTime.now()) || fechaHora.isAfter(LocalDateTime.now().plusMonths(1))) {
			errores.put("fechaHora", "No se pueden hacer reservas en el pasado ni de m�s de un mes");
		}
			
		this.fechaHora = fechaHora;
		
	}

	public Integer getNumeroPersonas() {
		return numeroPersonas;
	}

	public void setNumeroPersonas(Integer numeroPersonas) {
		
		if (numeroPersonas == null || numeroPersonas < 1) {
			errores.put("numeroPersonas", "La reserva tiene que ser al menos para una persona");
		}
		
		this.numeroPersonas = numeroPersonas;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		
		if (comentario == null) {
			throw new RuntimeException("No se han recibido ning�n comentario");
		}
		
		this.comentario = comentario;
	}
	
	public Long getUsuarios_id() {
		return usuarios_id;
	}

	public void setUsuarios_id(Long usuarios_id) {
		this.usuarios_id = usuarios_id;
	}

	public Long getCoches_id() {
		return coches_id;
	}

	public void setCoches_id(Long coches_id) {
		this.coches_id = coches_id;
	}

	public Map<String, String> getErrores() {
		return errores;
	}

	@Override
	public int hashCode() {
		return Objects.hash(coches_id, comentario, errores, fechaHora, id, nombre, numeroPersonas, usuarios_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reserva other = (Reserva) obj;
		return Objects.equals(coches_id, other.coches_id) && Objects.equals(comentario, other.comentario)
				&& Objects.equals(errores, other.errores) && Objects.equals(fechaHora, other.fechaHora)
				&& Objects.equals(id, other.id) && Objects.equals(nombre, other.nombre)
				&& Objects.equals(numeroPersonas, other.numeroPersonas)
				&& Objects.equals(usuarios_id, other.usuarios_id);
	}

	@Override
	public String toString() {
		return "Reserva [id=" + id + ", nombre=" + nombre + ", fechaHora=" + fechaHora + ", numeroPersonas="
				+ numeroPersonas + ", comentario=" + comentario + ", usuarios_id=" + usuarios_id + ", coches_id="
				+ coches_id + ", errores=" + errores + "]";
	}


}
