package servlets.dal;

import java.sql.*;
import java.util.ArrayList;

import servlets.modelos.Reserva;

class DaoReservaMysql implements DaoReserva {
	
	private String url, user, pass;
	
	private static final String SQL_SELECT = "SELECT * FROM coches";
	private static final String SQL_SELECT_ID = "SELECT * FROM coches WHERE id = ?";
	private static final String SQL_INSERT = "INSERT INTO reservas (idcoche, nombre, email, fechahora, numeropersonas, comentario) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String SQL_UPDATE = "UPDATE reservas SET idcoche = ?, nombre = ?, email = ?, fechahora = ?, numeropersonas = ?, comentario = ?, usuarios_id = 0, coches_id = 0 WHERE id = ?";
	private static final String SQL_DELETE = "DELETE FROM reservas WHERE id = ?";
	
	public DaoReservaMysql(String url, String user, String pass, String driver) {
		this.url = url;
		this.user = user;
		this.pass = pass;
		
		// Esto sólo es necesario en proyectos web
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			throw new DaoException("No se ha encontrado el driver de base de datos", e);
		}
	}

	@Override
	public Iterable<Reserva> obtenerTodos() {
		
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(url, user, pass);
			pst = con.prepareStatement(SQL_SELECT);
			rs = pst.executeQuery();
			
			Reserva reserva;		
			ArrayList<Reserva> reservas = new ArrayList<Reserva>();
			
			while(rs.next()) {
				reserva = new Reserva(rs.getLong("id"), rs.getLong("idcoche"), rs.getString("nombre"), rs.getString("email"), rs.getTimestamp("fechahora").toLocalDateTime(), rs.getInt("numeropersonas"), rs.getString("cilindrada"));
				reservas.add(reserva);
			}
					
			return reservas;
			
			
		} catch (SQLException e) {
			throw new DaoException("No se ha encontrado ninguna reserva");
		} finally {
			
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
				}
			}
			
			if(pst != null) {
				try {
					con.close();
				} catch (SQLException e) {
				}
			}
			
			if(rs != null) {
				try {
					con.close();
				} catch (SQLException e) {
				}
			}
		}

	}

	@Override
	public Reserva obtenerPorId(Long id) {

		try (Connection con = DriverManager.getConnection(url, user, pass);
				PreparedStatement pst = con.prepareStatement(SQL_SELECT_ID);) {
			pst.setLong(1, id);
			
			try (ResultSet rs = pst.executeQuery()) {
				Reserva reserva = null;

				if (rs.next()) {
					reserva = new Reserva(rs.getLong("id"), rs.getLong("idcoche"), rs.getString("nombre"), rs.getString("email"), rs.getTimestamp("fechahora").toLocalDateTime(), rs.getInt("numeropersonas"), rs.getString("cilindrada"));
				}

				return reserva;
			}
		} catch (SQLException e) {
			throw new DaoException("No se ha podido obtener el registro", e);
		}
		
	}

	@Override
	public void insertar(Reserva reserva) {
		try (Connection con = DriverManager.getConnection(url, user, pass);
				PreparedStatement pst = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);) {
			
			pst.setLong(1, reserva.getIdCoche());
			pst.setString(2, reserva.getNombre());
			pst.setString(3, reserva.getEmail());
			//pst.setString(4, (reserva.getFechaHora().toString()));
			pst.setTimestamp(4, Timestamp.valueOf(reserva.getFechaHora()));
			pst.setInt(5, reserva.getNumeroPersonas());
			pst.setString(6, reserva.getComentario());

			pst.executeUpdate();
			
			ResultSet rs = pst.getGeneratedKeys();
			
			if (rs != null && rs.next()) {
			    reserva.setId(rs.getLong(1));
			}
			
		} catch (SQLException e) {
			throw new DaoException("No se ha podido insertar el registro", e);
		}
		
	}

	@Override
	public void modificar(Reserva objeto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void borrar(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Long encontrarCochePorIdReserva(Long idResrva) {
		// TODO Auto-generated method stub
		return null;
	}


}
