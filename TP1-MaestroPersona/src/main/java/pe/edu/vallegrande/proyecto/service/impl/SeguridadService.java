package pe.edu.vallegrande.proyecto.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import pe.edu.vallegrande.proyecto.db.AccesoDB;
import pe.edu.vallegrande.proyecto.model.PersonModel;
import pe.edu.vallegrande.proyecto.service.spec.SeguridadServiceSpec;

public class SeguridadService implements SeguridadServiceSpec {

	@Override
	public PersonModel validar(String user_name, String password) {
		// Preparando los datos
		Connection cn = null;
		PersonModel bean = null;
		// Proceso
		try {
			cn = AccesoDB.getConnection();
			String sql = "select id, type_person, names, last_names, type_document, number_document, district='Cerro Azul', address, email, cell_phone, user_name, '*****' password, status='A' from PERSON ";
			sql += "where user_name=? and password=?";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setString(1, user_name);
			pstm.setString(2, password);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				bean = new PersonModel();
				bean.setId(rs.getInt("id"));
				bean.setType_person(rs.getString("type_person"));
				bean.setNames(rs.getString("names"));
				bean.setLast_names(rs.getString("last_names"));
				bean.setType_document(rs.getString("type_document"));
				bean.setNumber_document(rs.getString("number_document"));
				bean.setDistrict(rs.getString("district"));
				bean.setAddress(rs.getString("address"));
				bean.setEmail(rs.getString("email"));
				bean.setCell_phone(rs.getString("cell_phone"));
				bean.setUser_name(rs.getString("user_name"));
				bean.setPassword(rs.getString("password"));
				bean.setStatus(rs.getString("status"));
			}
			rs.close();
			pstm.close();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} catch (Exception e) {
			throw new RuntimeException("Error en el proceso");
		} finally {
			try {
				cn.close();
			} catch (Exception e) {
			}
		}
		return bean;
	}

}
