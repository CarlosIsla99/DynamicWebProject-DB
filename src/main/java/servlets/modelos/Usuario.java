package servlets.modelos;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Usuario {
	public enum Roles {ADMIN, CLIENTE};
	
	private Long id;
	private String email;
	private String password;
	private String telefono;
	private Roles rol;
	
	private Map<String, String> errores = new HashMap<>();
	
	public Usuario(Long id, String email, String password, String telefono, Roles rol) {
		setId(id);
		setEmail(email);
		setPassword(password);
		setTelefono(telefono);
		setRol(rol);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		
		if(password.trim().length()<4) {
			errores.put("password", "La contraseña debe tener al menos 4 carácteres");
		}
		
		this.password = password;
	}
	
	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		
		if(telefono.length()<9 || telefono.length()>9) {
			errores.put("telefono", "Teléfono no válido");
		}
		
		this.telefono = telefono;
	}

	public Roles getRol() {
		return rol;
	}

	public void setRol(Roles rol) {
		this.rol = rol;
	}
	
	public Map<String, String> getErrores() {
		return errores;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, id, password, rol, telefono);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id)
				&& Objects.equals(password, other.password) && rol == other.rol
				&& Objects.equals(telefono, other.telefono);
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", email=" + email + ", password=" + password + ", telefono=" + telefono + ", rol="
				+ rol + "]";
	}

}
