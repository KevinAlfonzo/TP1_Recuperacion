package pe.edu.vallegrande.proyecto.prueba;

import pe.edu.vallegrande.proyecto.model.PersonModel;
import pe.edu.vallegrande.proyecto.service.impl.CrudPersonService;

public class ActualizarPerson {
	
	public static void main(String[] args) {
	    try {
	        // Obtener datos actualizados o modificar un objeto existente
	        PersonModel person = new PersonModel();
	        person.setId(19); // ID del registro que se quiere actualizar

	        // Establecer los datos actualizados
	        person.setType_person("C");
	        person.setNames("Aquilino");
	        person.setLast_names("Candela");
	        person.setType_document("DNI");
	        person.setNumber_document("79945320");
//	        person.setDistrict("Nuevo Imperial");
	        person.setAddress("Calle Alfonzo Ugarte");
	        person.setEmail("a.candela@gmail.com");
	        person.setCell_phone("989345624");
	        person.setUser_name("l.candela");
	        person.setPassword("manzo123");
//	        person.setStatus("A"); 

	        // Proceso
	        CrudPersonService service = new CrudPersonService();
	        service.update(person);

	        // Reporte
	        System.out.println("Registro actualizado exitosamente.");
	    } catch (Exception e) {
	        System.err.println(e.getMessage());
	    }
	}
}
