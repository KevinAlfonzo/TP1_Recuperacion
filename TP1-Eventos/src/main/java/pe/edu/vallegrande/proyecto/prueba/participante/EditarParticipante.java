package pe.edu.vallegrande.proyecto.prueba.participante;

import pe.edu.vallegrande.proyecto.model.ParticipantsModel;
import pe.edu.vallegrande.proyecto.service.impl.CrudParticipantsService;

public class EditarParticipante {

    public static void main(String[] args) {
        try {
            // Obtener datos actualizados o modificar un objeto existente
            ParticipantsModel person = new ParticipantsModel();
            person.setId(10); // ID del registro que se quiere actualizar

            // Establecer los datos actualizados
            person.setNames("Yeremy");
            person.setLast_names("Alfonzo");
            person.setType_document("DNI");
            person.setNumber_document("73828771");
            person.setEmail("k.alfonzo@gmail.com");
            person.setCellphone("904535298");
            person.setUser_name("y.silva");
            person.setPassword("4321");
            person.setRegistration_date("2024-11-16");
	        person.setStatus("A");

            // Proceso
            CrudParticipantsService service = new CrudParticipantsService();
            service.update(person);

            // Reporte
            System.out.println("Registro actualizado exitosamente.");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
