package pe.edu.vallegrande.proyecto.service.impl;

import pe.edu.vallegrande.proyecto.db.AccesoDB;
import pe.edu.vallegrande.proyecto.model.ParticipantsModel;
import pe.edu.vallegrande.proyecto.model.PonentesModel;
import pe.edu.vallegrande.proyecto.service.spec.CrudServiceSpec;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CrudPonentesService implements CrudServiceSpec<PonentesModel> {

    @Override
    public List<PonentesModel> getActive() {
        List<PonentesModel> lista = new ArrayList<>();
        Connection cn = null;
        PonentesModel rec = null;
        try {
            cn = AccesoDB.getConnection();
            String sql = "select id, names, last_names, email, cellphone, type_document, number_document, specialty, status from Ponentes where status='A'";
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                rec = new PonentesModel();
                rec.setId(rs.getInt("id"));
                rec.setNames(rs.getString("names"));
                rec.setLast_names(rs.getString("last_names"));
                rec.setEmail(rs.getString("email"));
                rec.setCellphone(rs.getString("cellphone"));
                rec.setType_document(rs.getString("type_document"));
                rec.setNumber_document(rs.getString("number_document"));
                rec.setSpecialty(rs.getString("specialty"));
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
    public List<PonentesModel> getInactive() {
        List<PonentesModel> lista = new ArrayList<>();
        Connection cn = null;
        PonentesModel rec = null;
        try {
            cn = AccesoDB.getConnection();
            String sql = "select id, names, last_names, email, cellphone, type_document, number_document, specialty, status from Ponentes where status='I'";
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                rec = new PonentesModel();
                rec.setId(rs.getInt("id"));
                rec.setNames(rs.getString("names"));
                rec.setLast_names(rs.getString("last_names"));
                rec.setEmail(rs.getString("email"));
                rec.setCellphone(rs.getString("cellphone"));
                rec.setType_document(rs.getString("type_document"));
                rec.setNumber_document(rs.getString("number_document"));
                rec.setSpecialty(rs.getString("specialty"));
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
    public PonentesModel getById(Integer id) {
        // Preparando los datos
        Connection cn = null;
        PonentesModel bean = null;
        // Proceso
        try {
            cn = AccesoDB.getConnection();
            String sql = "select id, names, last_names, email, cellphone, type_document, number_document, specialty, status from Ponentes ";
            sql += "where status='A' and id = ?";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                bean = new PonentesModel();
                bean.setId(rs.getInt("id"));
                bean.setNames(rs.getString("names"));
                bean.setLast_names(rs.getString("last_names"));
                bean.setEmail(rs.getString("email"));
                bean.setCellphone(rs.getString("cellphone"));
                bean.setType_document(rs.getString("type_document"));
                bean.setNumber_document(rs.getString("number_document"));
                bean.setSpecialty(rs.getString("specialty"));
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
    public List<PonentesModel> get(PonentesModel bean) {
        // Preparando los datos
        String Names = bean.getNames().trim() + "%";
        String Last_Names = bean.getLast_names().trim() + "%";
        String Number_Document = bean.getNumber_document().trim() + "%";
        List<PonentesModel> lista = new ArrayList<>();
        Connection cn = null;
        PonentesModel rec = null;
        // Proceso
        try {
            cn = AccesoDB.getConnection();
            String sql = "select id, names, last_names, email, cellphone, type_document, number_document, specialty, status from Ponentes ";
            sql += "where names like ? and last_names like ? and number_document like ? ";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, Names);
            pstm.setString(2, Last_Names);
            pstm.setString(3, Number_Document);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                rec = new PonentesModel();
                rec.setId(rs.getInt("id"));
                rec.setNames(rs.getString("names"));
                rec.setLast_names(rs.getString("last_names"));
                rec.setEmail(rs.getString("email"));
                rec.setCellphone(rs.getString("cellphone"));
                rec.setType_document(rs.getString("type_document"));
                rec.setNumber_document(rs.getString("number_document"));
                rec.setSpecialty(rs.getString("specialty"));
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
    public PonentesModel insert(PonentesModel bean) {
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
            sql = "INSERT INTO Ponentes(names, last_names, email, cellphone, type_document, number_document, specialty, status) VALUES(?,?,?,?,?,?,?,?)";
            pstm = cn.prepareStatement(sql);
            pstm.setString(1, bean.getNames());
            pstm.setString(2, bean.getLast_names());
            pstm.setString(3, bean.getEmail());
            pstm.setString(4, bean.getCellphone());
            pstm.setString(5, bean.getType_document());
            pstm.setString(6, bean.getNumber_document());
            pstm.setString(7, bean.getSpecialty());
            pstm.setString(8, bean.getStatus());
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
    public PonentesModel update(PonentesModel bean) {
        try {
            Connection cn = AccesoDB.getConnection();
            String sql = "UPDATE Ponentes SET names = ?, last_names = ?, email = ?, cellphone = ?, type_document = ?, number_document = ?, specialty = ?, status = ? WHERE id = ?";
            PreparedStatement pstm = cn.prepareStatement(sql);

            pstm.setString(1, bean.getNames());
            pstm.setString(2, bean.getLast_names());
            pstm.setString(3, bean.getEmail());
            pstm.setString(4, bean.getCellphone());
            pstm.setString(5, bean.getType_document());
            pstm.setString(6, bean.getNumber_document());
            pstm.setString(7, bean.getSpecialty());
            pstm.setString(8, bean.getStatus());
            pstm.setInt(9, bean.getId());

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
            String sql = "UPDATE Ponentes SET status = 'A' WHERE id = ?";
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
            sql = "UPDATE Ponentes SET status='I' WHERE id=?";
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
