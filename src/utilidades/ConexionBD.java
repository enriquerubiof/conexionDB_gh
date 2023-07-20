package utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD
{
	//Datos de la conexión
	private static String database="tienda";
	private static String user="tienda";
	private static String pass="1234";
	private static String url="jdbc:mysql://localhost:3306/" + database;
	
	//Objeto conection
	private Connection conexion = null;
	
	public Connection getConexion()
	{
		if (conexion != null)
			return this.conexion;
		//Registramos el driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			//Solicitar objeto conexion
			this.conexion = DriverManager.getConnection(url, user, pass);
		} catch (ClassNotFoundException e) {
			System.out.println("No se ha podido registrar la conexión");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("No se ha podido conectar. " + e.getMessage());
			e.printStackTrace();
		}
		return this.conexion;
	}
	
	public void desconectar()
	{
		try {
			this.conexion.close();
		} catch (SQLException e)
		{
			System.out.println("Error liberando la conexión");
			e.printStackTrace();
		}
	}
	
}
