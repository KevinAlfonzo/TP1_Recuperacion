package pe.edu.vallegrande.proyecto.controller;

import com.google.gson.Gson;
import pe.edu.vallegrande.proyecto.model.EventModel;
import pe.edu.vallegrande.proyecto.service.impl.CrudEventService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet({ "/EventoBuscar", "/EventoProcesar", "/EventoActualizar", "/EventoHistorial" })
public class EventoController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private CrudEventService service = new CrudEventService ();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        switch (path) {
            case "/EventoBuscar":
                buscar(request, response);
                break;
            case "/EventoProcesar":
                procesar(request, response);
                break;
            case "/EventoActualizar":
                actualizar(request, response);
                break;
            case "/EventoHistorial":
                historial(request, response);
                break;
        }
    }

    private void procesar(HttpServletRequest request, HttpServletResponse response) {
        // Datos
        String accion = request.getParameter("accion");
        EventModel bean = new EventModel();
        bean.setId(Integer.parseInt(request.getParameter("id")));
        bean.setEvent_name(request.getParameter("event_name"));
        bean.setDescription(request.getParameter("description"));
        bean.setDate(request.getParameter("date"));
        bean.setCertificate(request.getParameter("certificate"));
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
        String event_name = request.getParameter("event_name");
        String date = request.getParameter("date");
        // Proceso
        EventModel bean = new EventModel();
        bean.setEvent_name(event_name);
        bean.setDate(date);
        List<EventModel> lista = service.get(bean);
        // Preparando el JSON
        Gson gson = new Gson();
        String data = gson.toJson(lista);
        // Reporte
        ControllerUtil.responseJson(response, data);
    }

    private void actualizar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<EventModel> lista = service.getActive();
        Gson gson = new Gson();
        String data = gson.toJson(lista);
        ControllerUtil.responseJson(response, data);
    }

    private void historial(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<EventModel> lista = service.getInactive();
        Gson gson = new Gson();
        String data = gson.toJson(lista);
        ControllerUtil.responseJson(response, data);
    }
}
