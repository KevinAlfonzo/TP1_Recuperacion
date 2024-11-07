package pe.edu.vallegrande.proyecto.prueba;

import pe.edu.vallegrande.proyecto.model.PersonModel;
import pe.edu.vallegrande.proyecto.service.impl.CrudPersonService;

public class CrearPerson {
	
	public static void main(String[] args) {
		try {
			// Datos de consulta
			PersonModel model = new PersonModel();
			model.setType_person("C");
			model.setNames("Jesus");
			model.setLast_names("Canales");
			model.setType_document("DNI");
			model.setNumber_document("79945320");
			model.setAddress("Calle Alfonzo Ugarte Nro. 6578");
			model.setEmail("j.canales@gmail.com");
			model.setCell_phone("989345624");
			model.setUser_name("jesuscanales");
			model.setPassword("jesus12345");
			// Proceso
			CrudPersonService service = new CrudPersonService();
			model = service.insert(model);
			// Reporte
			System.out.println("Registro grabado con id=" + model.getId());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

}
