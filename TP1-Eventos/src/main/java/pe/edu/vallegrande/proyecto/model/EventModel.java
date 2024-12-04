package pe.edu.vallegrande.proyecto.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class EventModel {

    private Integer id;
    private String event_name;
    private String description;
    private String date;
    private String certificate;
    private String status;
}
