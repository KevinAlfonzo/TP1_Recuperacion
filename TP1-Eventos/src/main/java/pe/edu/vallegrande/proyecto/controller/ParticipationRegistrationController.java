package pe.edu.vallegrande.proyecto.controller;

import pe.edu.vallegrande.proyecto.model.ParticipantsModel;
import pe.edu.vallegrande.proyecto.model.ParticipationRegistrationModel;
import pe.edu.vallegrande.proyecto.service.impl.CrudParticipationRegistrationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/ParticipantsProcesar"})
public class ParticipationRegistrationController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private CrudParticipationRegistrationService service = new CrudParticipationRegistrationService ();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        switch (path) {
            case "/ParticipantsProcesar":
                procesar(request, response);
                break;
        }
    }

    private void procesar(HttpServletRequest request, HttpServletResponse response) {
        // Datos
        String accion = request.getParameter("accion");
        ParticipationRegistrationModel bean = new ParticipationRegistrationModel();
        bean.setId(Integer.parseInt(request.getParameter("id")));
        bean.setCertification(request.getParameter("certification"));
        bean.setEvent_id(Integer.parseInt(request.getParameter("event_id")));
        bean.setPonentes_id(Integer.parseInt(request.getParameter("ponentes_id")));
        bean.setParticipants_id(Integer.parseInt(request.getParameter("participants_id")));
        // Proceso
        try {
            switch (accion) {
                case ControllerUtil.CRUD_NUEVO:
                    service.insert(bean);
                    break;
                case ControllerUtil.CRUD_EDITAR:
                    service.update(bean);
                    break;
                default:
                    throw new IllegalArgumentException("Unexpected value: " + accion);
            }
            ControllerUtil.responseJson(response, "Proceso ok.");
        } catch (Exception e) {
            ControllerUtil.responseJson(response, e.getMessage());
        }
    }
}
