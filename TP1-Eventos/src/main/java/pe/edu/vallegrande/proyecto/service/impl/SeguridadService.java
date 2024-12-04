package pe.edu.vallegrande.proyecto.service.impl;

import java.sql.*;

import pe.edu.vallegrande.proyecto.db.AccesoDB;
import pe.edu.vallegrande.proyecto.model.ParticipantsModel;
import pe.edu.vallegrande.proyecto.service.spec.SeguridadServiceSpec;

public class SeguridadService implements SeguridadServiceSpec {

    @Override
    public ParticipantsModel validar(String user_name, String password) {
        // Preparando los datos
        Connection cn = null;
        ParticipantsModel bean = null;
        // Proceso
        try {
            cn = AccesoDB.getConnection();
            String sql = "select id, names, last_names, email, cellphone, type_document, number_document, registration_date, user_name, '*****' password, status = 'A' from Participantes ";
            sql += "where user_name=? and password=?";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, user_name);
            pstm.setString(2, password);
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
                bean.setRegistration_date(("registration_date"));
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
