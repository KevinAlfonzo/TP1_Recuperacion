package pe.edu.vallegrande.proyecto.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data @AllArgsConstructor @NoArgsConstructor
public class PersonModel {

	private Integer id;
	private String type_person;
	private String names;
	private String last_names;
	private String type_document;
	private String number_document;
	private String district;
	private String address;
	private String email;
	private String cell_phone;
	private String user_name;
	private String password;
	private String status;
	
}
