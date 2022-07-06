package servlets.dal;

import java.sql.*;
import java.util.ArrayList;

import servlets.modelos.Reserva;

class DaoReservaMysql implements DaoReserva {
	
	private String url, user, pass;
	
	private static final String SQL_SELECT = "SELECT * FROM reservas";
	private static final String SQL_SELECT_ID = "SELECT * FROM reservas WHERE id = ?";
	private static final String SQL_SELECT_COCHEID_BY_RESERVAID = "SELECT coches_id FROM reservas WHERE id = ?";
	private static final String SQL_INSERT = "INSERT INTO reservas (nombre, fechahora, numeropersonas, comentario, usuarios_id, coches_id) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String SQL_UPDATE = "UPDATE reservas SET nombre = ?, fechahora = ?, numeropersonas = ?, comentario = ?, usuarios_id = ?, coches_id = ? WHERE id = ?";
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
				reserva = new Reserva(rs.getLong("id"), rs.getString("nombre"), rs.getTimestamp("fechahora").toLocalDateTime(), rs.getInt("numeropersonas"), rs.getString("comentario"), rs.getLong("usuarios_id"), rs.getLong("coches_id"));
				reservas.add(reserva);
			}
					
			return reservas;
			
			
		} catch (SQLException e) {
			throw new DaoException("No se ha encontrado ninguna reserva" + e);
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
					reserva = new Reserva(rs.getLong("id"), rs.getString("nombre"), rs.getTimestamp("fechahora").toLocalDateTime(), rs.getInt("numeropersonas"), rs.getString("comentario"), rs.getLong("usuarios_id"), rs.getLong("coches_id"));
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
			
			pst.setString(1, reserva.getNombre());
			//pst.setString(2, (reserva.getFechaHora().toString()));
			pst.setTimestamp(2, Timestamp.valueOf(reserva.getFechaHora()));
			pst.setInt(3, reserva.getNumeroPersonas());
			pst.setString(4, reserva.getComentario());
			pst.setLong(5, reserva.getUsuarios_id());
			pst.setLong(6, reserva.getCoches_id());

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
	public void modificar(Reserva reserva) {
		try (Connection con = DriverManager.getConnection(url, user, pass);
				PreparedStatement pst = con.prepareStatement(SQL_UPDATE);) {
				pst.setString(1, reserva.getNombre());
				//pst.setString(4, (reserva.getFechaHora().toString()));
				pst.setTimestamp(2, Timestamp.valueOf(reserva.getFechaHora()));
				pst.setInt(3, reserva.getNumeroPersonas());
				pst.setString(4, reserva.getComentario());
				pst.setLong(5, reserva.getUsuarios_id());
				pst.setLong(6, reserva.getCoches_id());
				
				pst.setLong(7, reserva.getId());

				if(pst.executeUpdate() != 1) {
					throw new DaoException("No se ha encontrado el usuario a modificar");
				}
				
			} catch (SQLException e) {
				throw new DaoException("No se ha podido modificar el registro", e);
			}
		
	}

	@Override
	public void borrar(Long id) {
		
		try (Connection con = DriverManager.getConnection(url, user, pass);
				PreparedStatement pst = con.prepareStatement(SQL_DELETE);) {
			
			pst.setLong(1, id);

			if(pst.executeUpdate() != 1) {
				throw new DaoException("No se ha encontrado el usuario a borrar");
			}
			
		} catch (SQLException e) {
			throw new DaoException("No se ha podido modificar el registro", e);
		}
		
	}

	@Override
	public Long encontrarCochePorIdReserva(Long idReserva) {
		
		try (Connection con = DriverManager.getConnection(url, user, pass);
				PreparedStatement pst = con.prepareStatement(SQL_SELECT_COCHEID_BY_RESERVAID);) {
			pst.setLong(1, idReserva);
			
			try (ResultSet rs = pst.executeQuery()) {
				Long coche_id = null;

				if (rs.next()) {
					coche_id = rs.getLong("coches_id");
				}

				return coche_id;
			}
		} catch (SQLException e) {
			throw new DaoException("No se ha podido obtener el registro" + e);
		}
		
	}


}
