package pe.edu.vallegrande.proyecto.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import pe.edu.vallegrande.proyecto.model.PersonModel;
import pe.edu.vallegrande.proyecto.service.impl.CrudPersonService;

@WebServlet({ "/PersonBuscar", "/PersonProcesar", "/PersonActualizar", "/PersonHistorial" })
public class PersonController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CrudPersonService service = new CrudPersonService ();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		switch (path) {
		case "/PersonBuscar":
			buscar(request, response);
			break;
		case "/PersonProcesar":
			procesar(request, response);
			break;
		case "/PersonActualizar":
			actualizar(request, response);
			break;
		case "/PersonHistorial":
			historial(request, response);
			break;
		}
	}

	private void procesar(HttpServletRequest request, HttpServletResponse response) {
		// Datos
		String accion = request.getParameter("accion");
		PersonModel bean = new PersonModel();
		bean.setId(Integer.parseInt(request.getParameter("id")));
		bean.setType_person(request.getParameter("type_person"));
		bean.setNames(request.getParameter("names"));
		bean.setLast_names(request.getParameter("last_names"));
		bean.setType_document(request.getParameter("type_document"));
		bean.setNumber_document(request.getParameter("number_document"));
		bean.setDistrict(request.getParameter("district"));
		bean.setAddress(request.getParameter("address"));
		bean.setEmail(request.getParameter("email"));
		bean.setCell_phone(request.getParameter("cell_phone"));
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
		PersonModel bean = new PersonModel();
		bean.setNames(names);
		bean.setLast_names(last_names);
		bean.setNumber_document(number_document);
		List<PersonModel> lista = service.get(bean);
		// Preparando el JSON
		Gson gson = new Gson();
		String data = gson.toJson(lista);
		// Reporte
		ControllerUtil.responseJson(response, data);
	}

	private void actualizar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<PersonModel> lista = service.getActive();
		Gson gson = new Gson();
		String data = gson.toJson(lista);
		ControllerUtil.responseJson(response, data);
	}

	private void historial(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<PersonModel> lista = service.getInactive();
		Gson gson = new Gson();
		String data = gson.toJson(lista);
		ControllerUtil.responseJson(response, data);
	}

}

