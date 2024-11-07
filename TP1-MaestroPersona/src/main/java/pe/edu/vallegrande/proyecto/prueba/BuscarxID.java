package pe.edu.vallegrande.proyecto.prueba;

import pe.edu.vallegrande.proyecto.model.PersonModel;
import pe.edu.vallegrande.proyecto.service.impl.CrudPersonService;

public class BuscarxID {

	public static void main(String[] args) {
		try {
			// Datos de consulta
			Integer id = 19;

			// Proceso
			CrudPersonService service = new CrudPersonService();
			PersonModel rec = service.getById(id);

			// Verificar si rec es null
			if (rec == null) {
				System.out.println("No se encontró una persona con el ID: " + id + "o está inactivo");
			} else {
				// Reporte
				System.out.println(
						rec.getId() + " - " +
								rec.getNames() + " - " +
								rec.getLast_names() + " - " +
								rec.getType_document() + " - " +
								rec.getNumber_document() + " - " +
								rec.getDistrict() + " - " +
								rec.getAddress() + " - " +
								rec.getEmail() + " - " +
								rec.getCell_phone() + " - " +
								rec.getUser_name() + " - " +
								rec.getPassword() + " - " +
								rec.getStatus()
				);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

}
