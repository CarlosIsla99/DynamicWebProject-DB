package servlets.dal;

public class DaoFabrica {
	private DaoCoche daoCoche;
	private DaoReserva daoReserva;
	private DaoUsuario daoUsuario;
	
	public DaoFabrica(String tipoDao) {
		this(tipoDao, null, null, null, null);
	}
	
	public DaoFabrica(String tipoDao, String url, String user, String pass, String driver) {
		switch(tipoDao) {
		case "memoria":
			daoCoche = DaoCocheMemoria.getInstancia();
			daoReserva = DaoReservaMemoria.getInstancia();
			daoUsuario = DaoUsuarioMemoria.getInstancia();
			break;
		case "mysql":
			daoCoche = new DaoCocheMysql(url, user, pass, driver);
			daoReserva = new DaoReservaMysql(url, user, pass, driver);
			daoUsuario = new DaoUsuarioMysql(url, user, pass, driver);
			break;
		default:
			throw new DaoException("No conozco ese tipo " + tipoDao);
		}
	}
	
	public DaoCoche getDaoCoche() {
		return daoCoche;
	}
	
	public DaoReserva getDaoReserva() {
		return daoReserva;
	}
	
	public DaoUsuario getDaoUsuario() {
		return daoUsuario;
	}
}
