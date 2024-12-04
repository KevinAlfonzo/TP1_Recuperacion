package pe.edu.vallegrande.proyecto.service.impl;

import pe.edu.vallegrande.proyecto.db.AccesoDB;
import pe.edu.vallegrande.proyecto.model.EventModel;
import pe.edu.vallegrande.proyecto.service.spec.CrudServiceSpec;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CrudEventService implements CrudServiceSpec<EventModel> {

    @Override
    public List<EventModel> getActive() {
        List<EventModel> lista = new ArrayList<>();
        Connection cn = null;
        EventModel rec = null;
        try {
            cn = AccesoDB.getConnection();
            String sql = "select id, event_name, description, date, certificate, status from Evento where status='A'";
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                rec = new EventModel();
                rec.setId(rs.getInt("id"));
                rec.setEvent_name(rs.getString("event_name"));
                rec.setDescription(rs.getString("description"));
                rec.setDate(String.valueOf(rs.getDate("date")));
                rec.setCertificate(rs.getString("certificate"));
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
    public List<EventModel> getInactive() {
        List<EventModel> lista = new ArrayList<>();
        Connection cn = null;
        EventModel rec = null;
        try {
            cn = AccesoDB.getConnection();
            String sql = "select id, event_name, description, date, certificate, status from Evento where status='I'";
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                rec = new EventModel();
                rec.setId(rs.getInt("id"));
                rec.setEvent_name(rs.getString("event_name"));
                rec.setDescription(rs.getString("description"));
                rec.setDate(String.valueOf(rs.getDate("date")));
                rec.setCertificate(rs.getString("certificate"));
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
    public EventModel getById(Integer id) {
        // Preparando los datos
        Connection cn = null;
        EventModel bean = null;
        // Proceso
        try {
            cn = AccesoDB.getConnection();
            String sql = "select id, event_name, description, date, certificate, status from Evento ";
            sql += "where status='A' and id = ?";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                bean = new EventModel();
                bean.setId(rs.getInt("id"));
                bean.setEvent_name(rs.getString("event_name"));
                bean.setDescription(rs.getString("description"));
                bean.setDate(String.valueOf(rs.getDate("date")));
                bean.setCertificate(rs.getString("certificate"));
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
    public List<EventModel> get(EventModel bean) {
        // Preparando los datos
        String Event_name = bean.getEvent_name().trim() + "%";
        String Date = bean.getDate().trim() + "%";
        List<EventModel> lista = new ArrayList<>();
        Connection cn = null;
        EventModel rec = null;
        // Proceso
        try {
            cn = AccesoDB.getConnection();
            String sql = "select id, event_name, description, date, certificate, status from Evento ";
            sql += "where event_name like ? and date like ? ";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, Event_name);
            pstm.setString(2, Date);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                rec = new EventModel();
                rec.setId(rs.getInt("id"));
                rec.setEvent_name(rs.getString("event_name"));
                rec.setDescription(rs.getString("description"));
                rec.setDate(String.valueOf(rs.getDate("date")));
                rec.setCertificate(rs.getString("certificate"));
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
    public EventModel insert(EventModel bean) {
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
            sql = "INSERT INTO Evento (event_name, description, date, certificate, status) VALUES(?,?,?,?,?)";
            pstm = cn.prepareStatement(sql);
            pstm.setString(1, bean.getEvent_name());
            pstm.setString(2, bean.getDescription());
            pstm.setString(3, bean.getDate());
            pstm.setString(4, bean.getCertificate());
            pstm.setString(5, bean.getStatus());
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
    public EventModel update(EventModel bean) {
        try {
            Connection cn = AccesoDB.getConnection();
            String sql = "UPDATE Evento SET event_name = ?, description = ?, date = ?, certificate = ?, status = ? WHERE id = ?";
            PreparedStatement pstm = cn.prepareStatement(sql);

            pstm.setString(1, bean.getEvent_name());
            pstm.setString(2, bean.getDescription());
            pstm.setString(3, bean.getDate());
            pstm.setString(4, bean.getCertificate());
            pstm.setString(5, bean.getStatus());
            pstm.setInt(6, bean.getId());

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
            String sql = "UPDATE Evento SET status = 'A' WHERE id = ?";
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
            sql = "UPDATE Evento SET status='I' WHERE id=?";
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

}
