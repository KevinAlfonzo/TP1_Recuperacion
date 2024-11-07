package pe.edu.vallegrande.proyecto.prueba;

import java.sql.Connection;
import java.sql.SQLException;

import pe.edu.vallegrande.proyecto.db.AccesoDB;

public class Conexion {

	public static void main(String[] args) {
		try {
			Connection cn = AccesoDB.getConnection();
			System.out.println("Conexión ok");
			cn.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
}
