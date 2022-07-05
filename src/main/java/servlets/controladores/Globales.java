package servlets.controladores;

import java.io.IOException;
import java.util.Properties;

import servlets.dal.DaoCoche;
import servlets.dal.DaoException;
import servlets.dal.DaoFabrica;
import servlets.dal.DaoReserva;
import servlets.dal.DaoUsuario;

public class Globales {
	
	static final DaoCoche DAO_COCHE;
	static final DaoUsuario DAO_USUARIO;
	static final DaoReserva DAO_RESERVA;
	private static final String CONFIGURACION = "configuracion.properties"; 
	
	static {
		try {
			Properties props = new Properties();
			props.load(Globales.class.getClassLoader().getResourceAsStream(CONFIGURACION));
			
			String tipo = props.getProperty("dal.tipodao");
			String url = props.getProperty("dal.url");
			String user = props.getProperty("dal.user");
			String pass = props.getProperty("dal.pass");
			String driver = props.getProperty("dal.driver");
			
			DaoFabrica fabrica = new DaoFabrica(tipo, url, user, pass, driver);
			
			DAO_COCHE = fabrica.getDaoCoche();
			DAO_USUARIO = fabrica.getDaoUsuario();
			DAO_RESERVA = fabrica.getDaoReserva();
		} catch (IOException e) {
			throw new DaoException("No se ha podido obtener la configuración");
		}
	}
}
