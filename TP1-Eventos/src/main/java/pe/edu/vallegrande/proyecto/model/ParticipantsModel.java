package pe.edu.vallegrande.proyecto.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data @AllArgsConstructor @NoArgsConstructor
public class ParticipantsModel {

    private Integer id;
    private String names;
    private String last_names;
    private String email;
    private String cellphone;
    private String type_document;
    private String number_document;
    private String registration_date;
    private String user_name;
    private String password;
    private String status;
}
