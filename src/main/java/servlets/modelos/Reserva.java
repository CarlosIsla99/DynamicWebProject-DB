package servlets.modelos;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Reserva {
	
	private Long id;
	private Long idCoche;
	private String nombre;
	private String email;
	private LocalDateTime fechaHora;
	private Integer numeroPersonas;
	private String comentario;
	
	private Map<String, String> errores = new HashMap<>();
	
	public Reserva(Long id, Long idCoche, String nombre, String email, LocalDateTime fechaHora, Integer numeroPersonas,
			String comentario) {
		super();
		setId(id);
		setIdCoche(idCoche);
		setNombre(nombre);
		setEmail(email);
		setFechaHora(fechaHora);
		setNumeroPersonas(numeroPersonas);
		setComentario(comentario);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdCoche() {
		return idCoche;
	}

	public void setIdCoche(Long idCoche) {
		this.idCoche = idCoche;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		
		if (nombre == null || nombre.trim().length() < 3) {
			errores.put("nombre", "El nombre debe tener 3 o más caracteres");
		}
		
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		
		if (email == null) {
			throw new RuntimeException("No se ha recibido el email");
		}

		if (email.trim().length() > 0 && !email.trim().matches("^\\w+@\\w+\\.\\w+$")) {
			errores.put("email", "Debes introducir un formato de email válido");
		}
		
		this.email = email;
	}

	public LocalDateTime getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(LocalDateTime fechaHora) {
			
		if (fechaHora == null || fechaHora.isBefore(LocalDateTime.now()) || fechaHora.isAfter(LocalDateTime.now().plusMonths(1))) {
			errores.put("fechaHora", "No se pueden hacer reservas en el pasado ni de más de un mes");
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
			throw new RuntimeException("No se han recibido ningún comentario");
		}
		
		this.comentario = comentario;
	}
	
	public Map<String, String> getErrores() {
		return errores;
	}

	@Override
	public int hashCode() {
		return Objects.hash(comentario, email, errores, fechaHora, id, idCoche, nombre, numeroPersonas);
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
		return Objects.equals(comentario, other.comentario) && Objects.equals(email, other.email)
				&& Objects.equals(errores, other.errores) && Objects.equals(fechaHora, other.fechaHora)
				&& Objects.equals(id, other.id) && Objects.equals(idCoche, other.idCoche)
				&& Objects.equals(nombre, other.nombre) && Objects.equals(numeroPersonas, other.numeroPersonas);
	}

	@Override
	public String toString() {
		return "Reserva [id=" + id + ", idCoche=" + idCoche + ", nombre=" + nombre + ", email=" + email + ", fechaHora="
				+ fechaHora + ", numeroPersonas=" + numeroPersonas + ", comentario=" + comentario + ", errores="
				+ errores + "]";
	}

}
