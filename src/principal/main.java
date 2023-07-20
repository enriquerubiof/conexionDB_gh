package principal;

import java.sql.Connection;

import utilidades.ConexionBD;

public class main {

	public static void main(String[] args)
	{
		ConexionBD conexionBD = new ConexionBD();
		
		System.out.println("Conectando a la base de datos tienda...");
		
		Connection con = conexionBD.getConexion();
		System.out.println("Conexión realizada correctamente");
		
	}

}
