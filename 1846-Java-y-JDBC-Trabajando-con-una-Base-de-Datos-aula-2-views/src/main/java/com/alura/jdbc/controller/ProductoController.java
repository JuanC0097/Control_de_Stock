package com.alura.jdbc.controller;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alura.jdbc.factory.ConnectionFactory;


public class ProductoController {

	public void modificar(String nombre, String descripcion, Integer id) {
		// TODO
	}

	public void eliminar(Integer id) {
		// TODO
	}

	public List<Map<String, String>> listar() throws SQLException {
		Connection con = new ConnectionFactory().recuperarConexion();
		
		
		//Statement= comandos de SQl
		java.sql.Statement statement = con.createStatement();
		
		//nos devuelve boolean, si devuelve un listado sera: true
		statement.execute("SELECT ID,NOMBRE, DESCRIPCION, CANTIDAD FROM PRODUCTO");
		
		ResultSet resultSet = statement.getResultSet();
		
		List<Map<String, String>> resultado = new ArrayList<>();
		
		
		
		//leer el contenido y agregar a un listado de resultado y devolverlo a la app
		while (resultSet.next()) {
			Map<String, String> fila = new HashMap<>();
			fila.put("ID", String.valueOf(resultSet.getInt("ID")));
			fila.put("NOMBRE", resultSet.getString("NOMBRE"));
			fila.put("DESCRIPCION", resultSet.getString("DESCRIPCION"));
			fila.put("CANTIDAD", resultSet.getString("CANTIDAD"));
			
			resultado.add(fila);
		}
		
		con.close();
		return resultado;
		
	}

	/*
	 * 1.Solicitara como parametro el map
	 * 2.Creara una nueva conexion y traera el metodo recuperarConexion
	 * 3.Se crea un nuevo objeto Statement
	 * 4.Se llama al metodo execute de Statement y se agregan las acciones que realizara,en 
	 *   este caso INSERT
	 * 5.El metodo execute devuelte un valor booleano True si el resultado de la query es un 
	 *   result.set'Listado' o False si no es un resultset. EN CASO DE INSERT EL RETORNO NO SERA 
	 *   UN LISTADO, sera False. ESTE VALOR NO ES UTIL PARA ESTE CASO
	 * 6.En la declaracion del query'consulta'. Solicitamos NOMBRE Y DESCRIPCION, estos son valores
	 *   tipos STRING. SE DEBEN ENCERRAR EN COMILLAS SIMPLES, para señalar que es un string dentro de
	 *   otro String dentro de sql
	 *   EN JAVA "" = String
	 *   en SQL '' = String
	 * 7.CON JDBC podemos saber cual es el producto que fue insertado, segun su id. 
	 *   el metodo execute tiene un parametro adicional para señalar si una clave autogenerable fue
	 *   creada, en este caso el ID
	 * 8.Utilizamos las CONSTANTES de la clase Statement y llamamos RETURN_GENERATED_KEYS.
	 *   nos permite tomar como valor el id de la query de insert.
	 * 9.Obtenemos el listado de id creados durante la query, con ayuda de Resultset y el metodo de
	 *   Statement getGeneratedKeys.
	 *10.Recorremos la lista con un loop y utilizamos el metodo next para ir al siguiente lugar de la
	 *   fila. Obtenemos el id generado con el metodo getInt y  En este caso deseamos saber solo 
	 *   el id generado en la consulta
	 */
    public void guardar(Map<String, String> producto) throws SQLException {
		Connection con = new ConnectionFactory().recuperarConexion();
		
		Statement statement = con.createStatement();
		statement.execute("INSERT INTO PRODUCTO (nombre, descripcion, cantidad) " 
				+ " VALUES ('" + producto.get("NOMBRE") + "' , '" 
				+ producto.get("DESCRIPCION") + " ', "
				+ producto.get("CANTIDAD") + ")", Statement.RETURN_GENERATED_KEYS);
		
		ResultSet resultSet = statement.getGeneratedKeys();
		
		while (resultSet.next()) {
			System.out.println(
					String.format("Fue insertado el producto de ID %d", resultSet.getInt(1)));
		}
		
	}

}
