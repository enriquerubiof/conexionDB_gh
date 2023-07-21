package principal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import utilidades.ConexionBD;

public class main2 {

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduzca el precio: ");
		double precio = sc.nextInt();
		sc.nextLine();
		System.out.println("Introduzca el nombre: ");
		String nombre = sc.nextLine();
		
		ConexionBD conexionBD = new ConexionBD();
		
		System.out.println("Conectando a la base de datos tienda...");
		
		Connection con = conexionBD.getConexion();
		System.out.println("Liberando la conexion");
		
		PreparedStatement stmt = null;
		ResultSet resultado = null;
		
		try {
			String consulta = "select codigo, nombre, precio, codigo_fabricante from producto"
					+ " where precio > ? and nombre like concat('%',?,'%')";
			stmt = con.prepareStatement(consulta);
			
			//Establecemos los parámetros
			stmt.setDouble(1, precio);
			stmt.setString(2, nombre);
			resultado = stmt.executeQuery();
			/*resultado = stmt.executeQuery(
					"select codigo, nombre, precio, codigo_fabricante from producto"
					+ " where precio > 210 and nombre like '%monitor%'");*/
			System.out.println("Código\tNombre\tPrecio\tCódigo_fabricante");
			System.out.println("    ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~    ");
			while (resultado.next())
			{
				int codigo = resultado.getInt("codigo");    //----- Mediante nombre de la columna
				// int codigo = resultado.getInt(1)    ----- Mediante número de la columna
						// int codigo = resultado.getInt("cod")    ----- Mediante alias
				nombre = resultado.getString("nombre");
				precio = resultado.getDouble("precio");
				int codigoFabricante = resultado.getInt("codigo_fabricante");
				System.out.printf("%d\t%s\t%.2f\t%d\n",
						codigo, nombre, precio, codigoFabricante);
			}
		} catch (SQLException e) {
			System.out.println("Error en la base de datos " + e.getMessage());
		} finally {
			try {
				resultado.close();
				stmt.close();
			} catch (SQLException e) {
				System.out.println("Error liberando recursos");
			}
		}
		
		conexionBD.desconectar();
		
	}

}
