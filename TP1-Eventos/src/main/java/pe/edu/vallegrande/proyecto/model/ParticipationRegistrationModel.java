package pe.edu.vallegrande.proyecto.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class ParticipationRegistrationModel {

    private Integer id;
    private String certification;
    private String date_registration;
    private Integer event_id;
    private Integer ponentes_id;
    private Integer participants_id;
}
