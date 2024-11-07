package pe.edu.vallegrande.proyecto.prueba;

import pe.edu.vallegrande.proyecto.service.impl.CrudPersonService;

public class EliminadoLógicoPerson {
	
	public static void main(String[] args) {
		try {
			// Datos de consulta
			Integer id = 19;
			// Proceso
			CrudPersonService service = new CrudPersonService();
			service.delete(id);
			// Reporte
			System.out.println("Registro eliminado logicamente.");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

}
