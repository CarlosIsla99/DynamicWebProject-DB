package servlets.modelos;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import servlets.bibliotecas.Validaciones;

public class Coche {
	
	private Long id;
	private String matricula;
	private String marca;
	private String modelo;
	private String color;
	private int potencia;
	private int cilindrada;
	private boolean reserva;
	
	private Map<String, String> errores = new HashMap<>();

	public Coche(Long id, String matricula, String marca, String modelo, String color, int potencia, int cilindrada, boolean reserva) {
		setId(id);
		setMatricula(matricula);
		setMarca(marca);
		setModelo(modelo);
		setColor(color);
		setPotencia(potencia);
		setCilindrada(cilindrada);
		setReserva(reserva);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		if (!Validaciones.validarMatricula(matricula)) {
			errores.put("matricula", "Matrícula no válida");
		}
		this.matricula = matricula;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		if(marca == null || marca.trim().length() < 3 || marca.matches(".*\\d.*")) {
			errores.put("marca", "Debe tener al menos 3 letras");
		}
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		if(modelo == null || modelo.trim().length() == 0) {
			errores.put("modelo", "El modelo no puede estar vac�o");
		}
		this.modelo = modelo;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		if(marca == null || marca.trim().length() < 3 || marca.matches(".*\\d.*")) {
			errores.put("color", "Debe tener al menos 3 letras");
		}
		this.color = color;
	}

	public int getPotencia() {
		return potencia;
	}

	public void setPotencia(int potencia) {
		if(potencia < 40) {
			errores.put("potencia", "La potencia no puede ser inferior a 40CV");
		} else if (potencia > 1800) {
			errores.put("potencia", "La potencia puede sobrepasar los 1800CV");
		}
		this.potencia = potencia;
	}

	public int getCilindrada() {
		return cilindrada;
	}

	public void setCilindrada(int cilindrada) {
		if(cilindrada < 50) {
			errores.put("cilindrada", "La cilindrada no puede ser inferior a 50cc");
		} else if(cilindrada > 8000) {
			errores.put("cilindrada", "La cilindrada no puede sobrepasar los 8000cc");
		}
		this.cilindrada = cilindrada;
	}

	public boolean isReserva() {
		return reserva;
	}

	public void setReserva(boolean reserva) {
		this.reserva = reserva;
	}

	public Map<String, String> getErrores() {
		return errores;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cilindrada, color, id, marca, matricula, modelo, potencia, reserva);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coche other = (Coche) obj;
		return cilindrada == other.cilindrada && Objects.equals(color, other.color) && Objects.equals(id, other.id)
				&& Objects.equals(marca, other.marca) && Objects.equals(matricula, other.matricula)
				&& Objects.equals(modelo, other.modelo) && potencia == other.potencia && reserva == other.reserva;
	}

	@Override
	public String toString() {
		return "Coche [id=" + id + ", matricula=" + matricula + ", marca=" + marca + ", modelo=" + modelo + ", color="
				+ color + ", potencia=" + potencia + ", cilindrada=" + cilindrada + ", reserva=" + reserva + "]";
	}

}
