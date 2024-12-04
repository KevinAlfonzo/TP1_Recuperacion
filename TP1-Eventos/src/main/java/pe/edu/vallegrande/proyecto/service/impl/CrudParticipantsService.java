package pe.edu.vallegrande.proyecto.service.impl;

import pe.edu.vallegrande.proyecto.db.AccesoDB;
import pe.edu.vallegrande.proyecto.model.ParticipantsModel;
import pe.edu.vallegrande.proyecto.service.spec.CrudServiceSpec;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CrudParticipantsService implements CrudServiceSpec<ParticipantsModel> {

    @Override
    public List<ParticipantsModel> getActive() {
        List<ParticipantsModel> lista = new ArrayList<>();
        Connection cn = null;
        ParticipantsModel rec = null;
        try {
            cn = AccesoDB.getConnection();
            String sql = "select id, names, last_names, email, cellphone, type_document, number_document, registration_date, user_name, password, status from Participantes where status='A'";
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                rec = new ParticipantsModel();
                rec.setId(rs.getInt("id"));
                rec.setNames(rs.getString("names"));
                rec.setLast_names(rs.getString("last_names"));
                rec.setEmail(rs.getString("email"));
                rec.setCellphone(rs.getString("cellphone"));
                rec.setType_document(rs.getString("type_document"));
                rec.setNumber_document(rs.getString("number_document"));
                rec.setRegistration_date(String.valueOf(rs.getDate("registration_date")));
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
    public List<ParticipantsModel> getInactive() {
        List<ParticipantsModel> lista = new ArrayList<>();
        Connection cn = null;
        ParticipantsModel rec = null;
        try {
            cn = AccesoDB.getConnection();
            String sql = "select id, names, last_names, email, cellphone, type_document, number_document, registration_date, user_name, password, status from Participantes where status='I'";
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                rec = new ParticipantsModel();
                rec.setId(rs.getInt("id"));
                rec.setNames(rs.getString("names"));
                rec.setLast_names(rs.getString("last_names"));
                rec.setEmail(rs.getString("email"));
                rec.setCellphone(rs.getString("cellphone"));
                rec.setType_document(rs.getString("type_document"));
                rec.setNumber_document(rs.getString("number_document"));
                rec.setRegistration_date(String.valueOf((rs.getDate("registration_date"))));
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
    public ParticipantsModel getById(Integer id) {
        // Preparando los datos
        Connection cn = null;
        ParticipantsModel bean = null;
        // Proceso
        try {
            cn = AccesoDB.getConnection();
            String sql = "select id, names, last_names, email, cellphone, type_document, number_document, registration_date, user_name, password, status from Participantes ";
            sql += "where status='A' and id = ?";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                bean = new ParticipantsModel();
                bean.setId(rs.getInt("id"));
                bean.setNames(rs.getString("names"));
                bean.setLast_names(rs.getString("last_names"));
                bean.setEmail(rs.getString("email"));
                bean.setCellphone(rs.getString("cellphone"));
                bean.setType_document(rs.getString("type_document"));
                bean.setNumber_document(rs.getString("number_document"));
                bean.setRegistration_date(String.valueOf(rs.getDate("registration_date")));
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
    public List<ParticipantsModel> get(ParticipantsModel bean) {
        // Preparando los datos
        String Names = bean.getNames().trim() + "%";
        String Last_Names = bean.getLast_names().trim() + "%";
        String Number_Document = bean.getNumber_document().trim() + "%";
        List<ParticipantsModel> lista = new ArrayList<>();
        Connection cn = null;
        ParticipantsModel rec = null;
        // Proceso
        try {
            cn = AccesoDB.getConnection();
            String sql = "select id, names, last_names, email, cellphone, type_document, number_document, registration_date, user_name, password, status from Participantes ";
            sql += "where names like ? and last_names like ? and number_document like ? ";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, Names);
            pstm.setString(2, Last_Names);
            pstm.setString(3, Number_Document);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                rec = new ParticipantsModel();
                rec.setId(rs.getInt("id"));
                rec.setNames(rs.getString("names"));
                rec.setLast_names(rs.getString("last_names"));
                rec.setEmail(rs.getString("email"));
                rec.setCellphone(rs.getString("cellphone"));
                rec.setType_document(rs.getString("type_document"));
                rec.setNumber_document(rs.getString("number_document"));
                rec.setRegistration_date(String.valueOf(rs.getDate("registration_date")));
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
    public ParticipantsModel insert(ParticipantsModel bean) {
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
            sql = "INSERT INTO Participantes(names, last_names, email, cellphone, type_document, number_document, registration_date, user_name, password, status) VALUES(?,?,?,?,?,?,?,?,?,?)";
            pstm = cn.prepareStatement(sql);
            pstm.setString(1, bean.getNames());
            pstm.setString(2, bean.getLast_names());
            pstm.setString(3, bean.getEmail());
            pstm.setString(4, bean.getCellphone());
            pstm.setString(5, bean.getType_document());
            pstm.setString(6, bean.getNumber_document());
            pstm.setString(7, bean.getRegistration_date());
            pstm.setString(8, bean.getUser_name());
            pstm.setString(9, bean.getPassword());
            pstm.setString(10, bean.getStatus());
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
    public ParticipantsModel update(ParticipantsModel bean) {
        try {
            Connection cn = AccesoDB.getConnection();
            String sql = "UPDATE Participantes SET names = ?, last_names = ?, email = ?, cellphone = ?, type_document = ?, number_document = ?, registration_date = ?, user_name = ?, password = ?, status = ? WHERE id = ?";
            PreparedStatement pstm = cn.prepareStatement(sql);

            pstm.setString(1, bean.getNames());
            pstm.setString(2, bean.getLast_names());
            pstm.setString(3, bean.getEmail());
            pstm.setString(4, bean.getCellphone());
            pstm.setString(5, bean.getType_document());
            pstm.setString(6, bean.getNumber_document());
            pstm.setString(7, bean.getRegistration_date());
            pstm.setString(8, bean.getUser_name());
            pstm.setString(9, bean.getPassword());
            pstm.setString(10, bean.getStatus());
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
            String sql = "UPDATE Participantes SET status = 'A' WHERE id = ?";
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
            sql = "UPDATE Participantes SET status='I' WHERE id=?";
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

    public ParticipantsModel validateLogin(String user_name, String password) {
        Connection cn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ParticipantsModel loggedInUser = null;

        try {
            cn = AccesoDB.getConnection();
            String sql = "SELECT id, names, last_names, email, cellphone, type_document, number_document, registration_date, user_name, password, status FROM Participantes WHERE user_name = ? AND password = ?";
            pstm = cn.prepareStatement(sql);
            pstm.setString(1, user_name);
            pstm.setString(2, password);
            rs = pstm.executeQuery();

            if (rs.next()) {
                loggedInUser = new ParticipantsModel();
                loggedInUser.setId(rs.getInt("id"));
                loggedInUser.setNames(rs.getString("names"));
                loggedInUser.setLast_names(rs.getString("last_names"));
                loggedInUser.setEmail(rs.getString("email"));
                loggedInUser.setCellphone(rs.getString("cellphone"));
                loggedInUser.setType_document(rs.getString("type_document"));
                loggedInUser.setNumber_document(rs.getString("number_document"));
                loggedInUser.setRegistration_date(rs.getString("registration_date"));
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
