package pe.edu.vallegrande.proyecto.prueba;

import pe.edu.vallegrande.proyecto.service.impl.CrudPersonService;

public class ActivarPerson {
	
	    public static void main(String[] args) {
	        try {
	            // Datos de consulta
	            int id = 19; // ID del registro inactivo que se quiere activar

	            // Proceso
	            CrudPersonService service = new CrudPersonService();
	            service.activate(id);

	            // Reporte
	            System.out.println("Registro activado nuevamente.");
	        } catch (Exception e) {
	            System.err.println(e.getMessage());
	        }
	    }
	}

