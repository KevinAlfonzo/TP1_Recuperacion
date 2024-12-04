package pe.edu.vallegrande.proyecto.controller;

import com.google.gson.Gson;
import pe.edu.vallegrande.proyecto.model.PonentesModel;
import pe.edu.vallegrande.proyecto.service.impl.CrudPonentesService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet({ "/PonentesBuscar", "/PonentesProcesar", "/PonentesActualizar", "/PonentesHistorial" })
public class PonentesController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private CrudPonentesService service = new CrudPonentesService ();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        switch (path) {
            case "/PonentesBuscar":
                buscar(request, response);
                break;
            case "/PonentesProcesar":
                procesar(request, response);
                break;
            case "/PonentesActualizar":
                actualizar(request, response);
                break;
            case "/PonentesHistorial":
                historial(request, response);
                break;
        }
    }

    private void procesar(HttpServletRequest request, HttpServletResponse response) {
        // Datos
        String accion = request.getParameter("accion");
        PonentesModel bean = new PonentesModel();
        bean.setId(Integer.parseInt(request.getParameter("id")));
        bean.setNames(request.getParameter("names"));
        bean.setLast_names(request.getParameter("last_names"));
        bean.setEmail(request.getParameter("email"));
        bean.setCellphone(request.getParameter("cellphone"));
        bean.setType_document(request.getParameter("type_document"));
        bean.setNumber_document(request.getParameter("number_document"));
        bean.setSpecialty(request.getParameter("specialty"));
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
        PonentesModel bean = new PonentesModel();
        bean.setNames(names);
        bean.setLast_names(last_names);
        bean.setNumber_document(number_document);
        List<PonentesModel> lista = service.get(bean);
        // Preparando el JSON
        Gson gson = new Gson();
        String data = gson.toJson(lista);
        // Reporte
        ControllerUtil.responseJson(response, data);
    }

    private void actualizar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<PonentesModel> lista = service.getActive();
        Gson gson = new Gson();
        String data = gson.toJson(lista);
        ControllerUtil.responseJson(response, data);
    }

    private void historial(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<PonentesModel> lista = service.getInactive();
        Gson gson = new Gson();
        String data = gson.toJson(lista);
        ControllerUtil.responseJson(response, data);
    }
}
