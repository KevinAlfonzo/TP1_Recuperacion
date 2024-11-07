package pe.edu.vallegrande.proyecto.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pe.edu.vallegrande.proyecto.db.AccesoDB;
import pe.edu.vallegrande.proyecto.model.PersonModel;
import pe.edu.vallegrande.proyecto.service.spec.CrudServiceSpec;

public class CrudPersonService implements CrudServiceSpec<PersonModel> {

	@Override
	public List<PersonModel> getActive() {
		List<PersonModel> lista = new ArrayList<>();
		Connection cn = null;
		PersonModel rec = null;
		try {
			cn = AccesoDB.getConnection();
			String sql = "select id, type_person, names, last_names, type_document, number_document, district, address, email, cell_phone, user_name, password, status from PERSON where status='A'";
			PreparedStatement pstm = cn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				rec = new PersonModel();
				rec.setId(rs.getInt("id"));
				rec.setType_person(rs.getString("type_person"));
				rec.setNames(rs.getString("names"));
				rec.setLast_names(rs.getString("last_names"));
				rec.setType_document(rs.getString("type_document"));
				rec.setNumber_document(rs.getString("number_document"));
				rec.setDistrict(rs.getString("district"));
				rec.setAddress(rs.getString("address"));
				rec.setEmail(rs.getString("email"));
				rec.setCell_phone(rs.getString("cell_phone"));
				rec.setUser_name(rs.getString("user_name"));
				rec.setPassword(rs.getString("password"));
				rec.setStatus(rs.getString("status"));
				lista.add(rec);
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
		return lista;
	}

	@Override
	public List<PersonModel> getInactive() {
		List<PersonModel> lista = new ArrayList<>();
		Connection cn = null;
		PersonModel rec = null;
		try {
			cn = AccesoDB.getConnection();
			String sql = "select id, type_person, names, last_names, type_document, number_document, district, address, email, cell_phone, user_name, password, status from PERSON where status='I'";
			PreparedStatement pstm = cn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				rec = new PersonModel();
				rec.setId(rs.getInt("id"));
				rec.setType_person(rs.getString("type_person"));
				rec.setNames(rs.getString("names"));
				rec.setLast_names(rs.getString("last_names"));
				rec.setType_document(rs.getString("type_document"));
				rec.setNumber_document(rs.getString("number_document"));
				rec.setDistrict(rs.getString("district"));
				rec.setAddress(rs.getString("address"));
				rec.setEmail(rs.getString("email"));
				rec.setCell_phone(rs.getString("cell_phone"));
				rec.setUser_name(rs.getString("user_name"));
				rec.setPassword(rs.getString("password"));
				rec.setStatus(rs.getString("status"));
				lista.add(rec);
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
				// Manejo de excepciones
			}
		}
		return lista;
	}

	@Override
	public PersonModel getById(Integer id) {
		// Preparando los datos
		Connection cn = null;
		PersonModel bean = null;
		// Proceso
		try {
			cn = AccesoDB.getConnection();
			String sql = "select id, type_person, names, last_names, type_document, number_document, district, address, email, cell_phone, user_name, password, status from PERSON ";
			sql += "where status='A' and district='Nuevo Imperial'and id = ?";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setInt(1, id);
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

	@Override
	public List<PersonModel> get(PersonModel bean) {
		// Preparando los datos
		String names = bean.getNames().trim() + "%";
		String last_names = bean.getLast_names().trim() + "%";
		String number_document = bean.getNumber_document().trim() + "%";
		List<PersonModel> lista = new ArrayList<>();
		Connection cn = null;
		PersonModel rec = null;
		// Proceso
		try {
			cn = AccesoDB.getConnection();
			String sql = "select id, type_person, names, last_names, type_document, number_document, district, address, email, cell_phone, user_name, password, status from PERSON ";
			sql += "where names like ? and last_names like ? and number_document like ? ";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setString(1, names);
			pstm.setString(2, last_names);
			pstm.setString(3, number_document);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				rec = new PersonModel();
				rec.setId(rs.getInt("id"));
				rec.setType_person(rs.getString("type_person"));
				rec.setNames(rs.getString("names"));
				rec.setLast_names(rs.getString("last_names"));
				rec.setType_document(rs.getString("type_document"));
				rec.setNumber_document(rs.getString("number_document"));
				rec.setDistrict(rs.getString("district"));
				rec.setAddress(rs.getString("address"));
				rec.setEmail(rs.getString("email"));
				rec.setCell_phone(rs.getString("cell_phone"));
				rec.setUser_name(rs.getString("user_name"));
				rec.setPassword(rs.getString("password"));
				rec.setStatus(rs.getString("status"));
				lista.add(rec);
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
		return lista;
	}

	@Override
	public PersonModel insert(PersonModel bean) {
		// Variables
		Integer id;
		Connection cn = null;
		PreparedStatement pstm;
		ResultSet rs;
		String sql;
		// Proceso
		try {
			// Inicio de la TX
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
			// Insertar registro
			sql = "INSERT INTO PERSON(type_person, names, last_names, type_document, number_document, address, email, cell_phone, user_name, password) VALUES(?,?,?,?,?,?,?,?,?,?)";
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, bean.getType_person());
			pstm.setString(2, bean.getNames());
			pstm.setString(3, bean.getLast_names());
			pstm.setString(4, bean.getType_document());
			pstm.setString(5, bean.getNumber_document());
			pstm.setString(6, bean.getAddress());
			pstm.setString(7, bean.getEmail());
			pstm.setString(8, bean.getCell_phone());
			pstm.setString(9, bean.getUser_name());
			pstm.setString(10, bean.getPassword());
			pstm.executeUpdate();
			// Onteniendo el id
			sql = "SELECT @@IDENTITY id";
			pstm = cn.prepareStatement(sql);
			rs = pstm.executeQuery();
			rs.next();
			id = rs.getInt("id");
			bean.setId(id);
			// Fun de la TX
			cn.commit();
		} catch (SQLException e) {
			try {
				cn.rollback();
			} catch (Exception e2) {
			}
			throw new RuntimeException(e.getMessage());
		} catch (Exception e) {
			try {
				cn.rollback();
			} catch (Exception e2) {
			}
			throw new RuntimeException("Error en el proceso");
		} finally {
			try {
				cn.close();
			} catch (Exception e) {
			}
		}
		// Reporte
		return bean;
	}

	@Override
	public PersonModel update(PersonModel bean) {
		try {
			Connection cn = AccesoDB.getConnection();
			String sql = "UPDATE PERSON SET type_person = ?, names = ?, last_names = ?, type_document = ?, number_document = ?, address = ?, email = ?, cell_phone = ?, user_name = ?, password = ? WHERE id = ?";
			PreparedStatement pstm = cn.prepareStatement(sql);

			pstm.setString(1, bean.getType_person());
			pstm.setString(2, bean.getNames());
			pstm.setString(3, bean.getLast_names());
			pstm.setString(4, bean.getType_document());
			pstm.setString(5, bean.getNumber_document());
//			pstm.setString(6, bean.getDistrict());
			pstm.setString(6, bean.getAddress());
			pstm.setString(7, bean.getEmail());
			pstm.setString(8, bean.getCell_phone());
			pstm.setString(9, bean.getUser_name());
			pstm.setString(10, bean.getPassword());
//			pstm.setString(12, bean.getStatus());
			pstm.setInt(11, bean.getId());

			int affectedRows = pstm.executeUpdate();
			if (affectedRows == 0) {
				throw new RuntimeException("No se encontró el registro con ID: " + bean.getId());
			}

			pstm.close();
			cn.close();

			return bean; // Devolver el objeto actualizado si es necesario.
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public void activate(int id) {
		try {
			Connection cn = AccesoDB.getConnection();
			String sql = "UPDATE PERSON SET status = 'A' WHERE id = ?";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setInt(1, id);
			int affectedRows = pstm.executeUpdate();
			if (affectedRows == 0) {
				throw new RuntimeException("No se encontró el registro con ID: " + id);
			}
			pstm.close();
			cn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public void delete(Integer id) {
		// Variables
		Connection cn = null;
		PreparedStatement pstm;
		ResultSet rs;
		String sql;
		int filas;
		// Proceso
		try {
			// Inicio de la TX
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
			// Insertar registro
			sql = "UPDATE PERSON SET status='I' WHERE id=?";
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, id);
			filas = pstm.executeUpdate();
			pstm.close();
			if (filas == 0) {
				throw new SQLException("ID no existe");
			}
			// Fun de la TX
			cn.commit();
		} catch (SQLException e) {
			try {
				cn.rollback();
			} catch (Exception e2) {
			}
			throw new RuntimeException(e.getMessage());
		} catch (Exception e) {
			try {
				cn.rollback();
			} catch (Exception e2) {
			}
			throw new RuntimeException("Error en el proceso");
		} finally {
			try {
				cn.close();
			} catch (Exception e) {
			}
		}
	}

	public PersonModel validateLogin(String user_name, String password) {
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		PersonModel loggedInUser = null;

		try {
			cn = AccesoDB.getConnection();
			String sql = "SELECT id, type_person, names, last_names, type_document, number_document, district, address, email, cell_phone, user_name, password, status FROM PERSON WHERE user_name = ? AND password = ?";
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, user_name);
			pstm.setString(2, password);
			rs = pstm.executeQuery();

			if (rs.next()) {
				loggedInUser = new PersonModel();
				loggedInUser.setId(rs.getInt("id"));
				loggedInUser.setType_person(rs.getString("type_person"));
				loggedInUser.setNames(rs.getString("names"));
				loggedInUser.setLast_names(rs.getString("last_names"));
				loggedInUser.setType_document(rs.getString("type_document"));
				loggedInUser.setNumber_document(rs.getString("number_document"));
				loggedInUser.setDistrict(rs.getString("district"));
				loggedInUser.setAddress(rs.getString("address"));
				loggedInUser.setEmail(rs.getString("email"));
				loggedInUser.setCell_phone(rs.getString("cell_phone"));
				loggedInUser.setUser_name(rs.getString("user_name"));
				loggedInUser.setPassword(rs.getString("password"));
				loggedInUser.setStatus(rs.getString("status"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstm != null)
					pstm.close();
				if (cn != null)
					cn.close();
			} catch (SQLException e) {
				throw new RuntimeException("Error al cerrar recursos de base de datos: " + e.getMessage());
			}
		}
		return loggedInUser;
	}
}