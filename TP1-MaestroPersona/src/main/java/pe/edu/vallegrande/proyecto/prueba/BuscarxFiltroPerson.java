package pe.edu.vallegrande.proyecto.prueba;

import java.util.List;

import pe.edu.vallegrande.proyecto.model.PersonModel;
import pe.edu.vallegrande.proyecto.service.impl.CrudPersonService;

public class BuscarxFiltroPerson {
	
	public static void main(String[] args) {
		try {
			// Datos de consulta
			PersonModel model = new PersonModel();
			model.setNames("");
			model.setLast_names("l");
			model.setNumber_document("1");
			// Proceso
			CrudPersonService service = new CrudPersonService();
			List<PersonModel> lista = service.get(model);
			// Reporte
			System.out.println("LISTADO");
			System.out.println("Registros: " + lista.size());
			for (PersonModel rec : lista) {
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