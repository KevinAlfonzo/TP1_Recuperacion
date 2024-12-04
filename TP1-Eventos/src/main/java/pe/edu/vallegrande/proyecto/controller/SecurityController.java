package pe.edu.vallegrande.proyecto.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.edu.vallegrande.proyecto.model.ParticipantsModel;
import pe.edu.vallegrande.proyecto.service.impl.CrudParticipantsService;
import pe.edu.vallegrande.proyecto.service.impl.SeguridadService;
import pe.edu.vallegrande.proyecto.service.spec.SeguridadServiceSpec;

@WebServlet({ "/InicioSession", "/CerrarSesion" })
public class SecurityController extends HttpServlet {
    /** Instanciamos la lógica del CRUD **/
    CrudParticipantsService dao = new CrudParticipantsService();
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();
        switch (path) {
            case "/CerrarSesion":
                cerrarSesion(request, response);
                break;

        }
    }

    private void cerrarSesion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getSession().invalidate();
        String destino = "index.jsp";
        // Forward
        RequestDispatcher rd = request.getRequestDispatcher(destino);
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();
        switch (path) {
            case "/InicioSession":
                inicioSession(request, response);
                break;

        }
    }

    private void inicioSession(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Variables
        String destino, usuario, clave;
        ParticipantsModel Participants;
        try {
            // Datos
            usuario = request.getParameter("usuario");
            clave = request.getParameter("clave");
            // Proceso
            SeguridadServiceSpec seguridad = new SeguridadService();
            Participants = seguridad.validar(usuario, clave);
            if (Participants == null) {
                request.setAttribute("error", "Usuario o clave incorrectos");
                destino = "index.jsp";
            } else {
                request.getSession().setAttribute("usuario", Participants);
                destino = "participantes.jsp";
            }
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            destino = "index.jsp";
        }
        // Forward
        RequestDispatcher rd = request.getRequestDispatcher(destino);
        rd.forward(request, response);
    }
}
