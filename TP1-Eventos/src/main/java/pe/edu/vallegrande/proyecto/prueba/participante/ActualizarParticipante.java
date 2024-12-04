package pe.edu.vallegrande.proyecto.prueba.participante;


import pe.edu.vallegrande.proyecto.model.ParticipantsModel;
import pe.edu.vallegrande.proyecto.service.impl.CrudParticipantsService;

public class ActualizarParticipante {

    public static void main(String[] args) {
        try {
            // Obtener datos actualizados o modificar un objeto existente
            ParticipantsModel person = new ParticipantsModel();
            person.setId(9); // ID del registro que se quiere actualizar

            // Establecer los datos actualizados
            person.setNames("Lauriana");
            person.setLast_names("Rodriguez");
            person.setEmail("l.rodriguez@gmail.com");
            person.setCellphone("934268098");
            person.setType_document("DNI");
            person.setNumber_document("71085882");
            person.setRegistration_date("2024-11-25");
            person.setUser_name("l.rodriguez");
            person.setPassword("campos18");
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
