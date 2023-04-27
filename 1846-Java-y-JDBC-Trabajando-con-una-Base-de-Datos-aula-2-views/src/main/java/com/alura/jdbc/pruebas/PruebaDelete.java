package com.alura.jdbc.pruebas;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.alura.jdbc.factory.ConnectionFactory;

public class PruebaDelete {

	/*
	 * Prueba para eliminar campo inexistente, directamente en la declaracion del query
	 */
	public static void main(String[] args) throws SQLException {
		
			Connection con = new ConnectionFactory().recuperarConexion();
			Statement statement =  con.createStatement();
			statement.execute("DELETE FROM PRODUCTO WHERE ID = 99");
			//Consola Indica cero "No hubieron cambios en la tabla"
			System.out.println(statement.getUpdateCount());
			
	}
}
