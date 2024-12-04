package pe.edu.vallegrande.proyecto.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import pe.edu.vallegrande.proyecto.model.ParticipantsModel;
import pe.edu.vallegrande.proyecto.service.impl.CrudParticipantsService;

@WebServlet({ "/ParticipantsBuscar", "/ParticipantsProcesar", "/ParticipantsActualizar", "/ParticipantsHistorial" })
public class ParticipantsController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private CrudParticipantsService service = new CrudParticipantsService ();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        switch (path) {
            case "/ParticipantsBuscar":
                buscar(request, response);
                break;
            case "/ParticipantsProcesar":
                procesar(request, response);
                break;
            case "/ParticipantsActualizar":
                actualizar(request, response);
                break;
            case "/ParticipantsHistorial":
                historial(request, response);
                break;
        }
    }

    private void procesar(HttpServletRequest request, HttpServletResponse response) {
        // Datos
        String accion = request.getParameter("accion");
        ParticipantsModel bean = new ParticipantsModel();
        bean.setId(Integer.parseInt(request.getParameter("id")));
        bean.setNames(request.getParameter("names"));
        bean.setLast_names(request.getParameter("last_names"));
        bean.setEmail(request.getParameter("email"));
        bean.setCellphone(request.getParameter("cellphone"));
        bean.setType_document(request.getParameter("type_document"));
        bean.setNumber_document(request.getParameter("number_document"));
        bean.setRegistration_date(request.getParameter("registration_date"));
        bean.setUser_name(request.getParameter("user_name"));
        bean.setPassword(request.getParameter("password"));
        bean.setStatus(request.getParameter("status"));
        // Proceso
        try {
            switch (accion) {
                case ControllerUtil.CRUD_NUEVO:
                    service.insert(bean);
                    break;
                case ControllerUtil.CRUD_EDITAR:
                    service.update(bean);
                    break;
                case ControllerUtil.CRUD_RESTAURAR:
                    service.activate(bean.getId());
                    break;
                case ControllerUtil.CRUD_ELIMINAR:
                    service.delete(bean.getId());
                    break;
                default:
                    throw new IllegalArgumentException("Unexpected value: " + accion);
            }
            ControllerUtil.responseJson(response, "Proceso ok.");
        } catch (Exception e) {
            ControllerUtil.responseJson(response, e.getMessage());
        }
    }
    private void buscar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Datos
        String names = request.getParameter("names");
        String last_names = request.getParameter("last_names");
        String number_document = request.getParameter("number_document");
        // Proceso
        ParticipantsModel bean = new ParticipantsModel();
        bean.setNames(names);
        bean.setLast_names(last_names);
        bean.setNumber_document(number_document);
        List<ParticipantsModel> lista = service.get(bean);
        // Preparando el JSON
        Gson gson = new Gson();
        String data = gson.toJson(lista);
        // Reporte
        ControllerUtil.responseJson(response, data);
    }

    private void actualizar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ParticipantsModel> lista = service.getActive();
        Gson gson = new Gson();
        String data = gson.toJson(lista);
        ControllerUtil.responseJson(response, data);
    }

    private void historial(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ParticipantsModel> lista = service.getInactive();
        Gson gson = new Gson();
        String data = gson.toJson(lista);
        ControllerUtil.responseJson(response, data);
    }
}
