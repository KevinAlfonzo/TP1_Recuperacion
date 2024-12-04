package pe.edu.vallegrande.proyecto.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class PonentesModel {

    private Integer id;
    private String names;
    private String last_names;
    private String email;
    private String cellphone;
    private String type_document;
    private String number_document;
    private String specialty;
    private String status;
}
