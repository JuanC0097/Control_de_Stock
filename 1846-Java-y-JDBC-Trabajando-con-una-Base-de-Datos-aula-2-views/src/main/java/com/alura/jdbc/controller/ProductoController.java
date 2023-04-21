package com.alura.jdbc.controller;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoController {

	public void modificar(String nombre, String descripcion, Integer id) {
		// TODO
	}

	public void eliminar(Integer id) {
		// TODO
	}

	public List<?> listar() throws SQLException {
		Connection con = DriverManager.getConnection(
				"jdbc:mysql://localhost/control_de_stock?useTimeZone=true&serverTimeZone=UTC",
				"root",
				"Encaminoaserdesarrollador");
		//Statement= comandos de SQl
		java.sql.Statement statement = con.createStatement();
		//nos devuelve boolean, si devuelve un listado sera: true
		boolean result = statement.execute("SELECT ID,NOMBRE, DESCRIPCION, CANTIDAD FROM PRODUCTO");
		
		System.out.println(result);
		
		con.close();
		return new ArrayList<>();
	}

    public void guardar(Object producto) {
		// TODO
	}

}
