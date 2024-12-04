package pe.edu.vallegrande.proyecto.service.impl;

import pe.edu.vallegrande.proyecto.db.AccesoDB;
import pe.edu.vallegrande.proyecto.model.ParticipationRegistrationModel;
import pe.edu.vallegrande.proyecto.service.spec.CrudServiceTransac;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CrudParticipationRegistrationService implements CrudServiceTransac<ParticipationRegistrationModel> {

    @Override
    public ParticipationRegistrationModel getById(Integer id) {
        // Preparando los datos
        Connection cn = null;
        ParticipationRegistrationModel bean = null;
        // Proceso
        try {
            cn = AccesoDB.getConnection();
            String sql = "select id, certification, date_registration, event_id, ponentes_id, participants_id from Participation_Registration ";
            sql += "where id = ?";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                bean = new ParticipationRegistrationModel();
                bean.setId(rs.getInt("id"));
                bean.setCertification(rs.getString("certification"));
                bean.setEvent_id(rs.getInt("event_id"));
                bean.setPonentes_id(rs.getInt("ponentes_id"));
                bean.setParticipants_id(rs.getInt("participants_id"));
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
    public ParticipationRegistrationModel insert(ParticipationRegistrationModel bean) {
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
            sql = "INSERT INTO Participation_Registration(certification, date_registration, event_id, ponentes_id, participants_id) VALUES(?,?,?,?,?)";
            pstm = cn.prepareStatement(sql);
            pstm.setString(1, bean.getCertification());
            pstm.setInt(2, bean.getEvent_id());
            pstm.setInt(3, bean.getPonentes_id());
            pstm.setInt(4, bean.getParticipants_id());
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
    public ParticipationRegistrationModel update(ParticipationRegistrationModel bean) {
        try {
            Connection cn = AccesoDB.getConnection();
            String sql = "UPDATE Participation_Registration SET certification = ?, date_registration = ?, event_id = ?, ponentes_id = ?, participants_id = ? WHERE id = ?";
            PreparedStatement pstm = cn.prepareStatement(sql);

            pstm.setString(1, bean.getCertification());
            pstm.setInt(2, bean.getEvent_id());
            pstm.setInt(3, bean.getPonentes_id());
            pstm.setInt(4, bean.getParticipants_id());
            pstm.setInt(5, bean.getId());

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

}
