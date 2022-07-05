package servlets.dal;

import java.sql.*;
import java.util.ArrayList;

import servlets.modelos.Coche;

class DaoCocheMysql implements DaoCoche {

	private String url, user, pass;
	
	private static final String SQL_SELECT = "SELECT * FROM coches";
	private static final String SQL_SELECT_ID = "SELECT * FROM coches WHERE id = ?";
	private static final String SQL_INSERT = "INSERT INTO coches (matricula, marca, modelo, color, potencia, cilindrada) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String SQL_UPDATE = "UPDATE coches SET matricula = ?, marca = ?, modelo = ?, color = ?, potencia = ?, cilindrada = ? WHERE id = ?";
	private static final String SQL_DELETE = "DELETE FROM coches WHERE id = ?";
	
	public DaoCocheMysql(String url, String user, String pass, String driver) {
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
	public Iterable<Coche> obtenerTodos() {
		
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(url, user, pass);
			pst = con.prepareStatement(SQL_SELECT);
			rs = pst.executeQuery();
			
			Coche coche;	
			ArrayList<Coche> coches = new ArrayList<Coche>();
			
			while(rs.next()) {
				coche = new Coche(rs.getLong("id"), rs.getString("matricula"), rs.getString("marca"), rs.getString("modelo"), rs.getString("color"), rs.getInt("potencia"), rs.getInt("cilindrada"), rs.getBoolean("reserva"));
				coches.add(coche);
			}
			
			return coches;
			
		} catch (SQLException e) {
			throw new DaoException("No se ha podido obtener el registro", e);
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
	public Coche obtenerPorId(Long id) {
		
		try (Connection con = DriverManager.getConnection(url, user, pass);
				PreparedStatement pst = con.prepareStatement(SQL_SELECT_ID);) {
			pst.setLong(1, id);
			
			try (ResultSet rs = pst.executeQuery()) {
				Coche coche = null;

				if (rs.next()) {
					coche = new Coche(rs.getLong("id"), rs.getString("matricula"), rs.getString("marca"), rs.getString("modelo"), rs.getString("color"), rs.getInt("potencia"), rs.getInt("cilindrada"), rs.getBoolean("reserva"));
				}

				return coche;
			}
		} catch (SQLException e) {
			throw new DaoException("No se ha podido obtener el registro", e);
		}
	}

	@Override
	public void insertar(Coche coche) {
		try (Connection con = DriverManager.getConnection(url, user, pass);
				PreparedStatement pst = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);) {
			
				pst.setString(1, coche.getMatricula());
				pst.setString(2, coche.getMarca());
				pst.setString(3, coche.getModelo());
				pst.setString(4, coche.getColor());
				pst.setInt(5, coche.getPotencia());
				pst.setInt(6, coche.getCilindrada());

				pst.executeUpdate();
				
				ResultSet rs = pst.getGeneratedKeys();
				
				if (rs != null && rs.next()) {
				    coche.setId(rs.getLong(1));
				}
				
			} catch (SQLException e) {
				throw new DaoException("No se ha podido insertar el registro", e);
			}
		
	}

	@Override
	public void modificar(Coche coche) {
		try (Connection con = DriverManager.getConnection(url, user, pass);
				PreparedStatement pst = con.prepareStatement(SQL_UPDATE);) {
				pst.setString(1, coche.getMatricula());
				pst.setString(2, coche.getMarca());
				pst.setString(3, coche.getModelo());
				pst.setString(4, coche.getColor());
				pst.setInt(5, coche.getPotencia());
				pst.setInt(6, coche.getCilindrada());

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
	public boolean obtenerReservaPorId(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setFalseWhenNoReserva(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTrueReserva(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean comprobarMatricula(String matricula) {
		// TODO Auto-generated method stub
		return false;
	}


}
