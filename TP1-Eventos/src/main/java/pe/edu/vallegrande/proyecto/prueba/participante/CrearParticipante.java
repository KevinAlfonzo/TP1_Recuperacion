package pe.edu.vallegrande.proyecto.prueba.participante;

import pe.edu.vallegrande.proyecto.model.ParticipantsModel;
import pe.edu.vallegrande.proyecto.service.impl.CrudParticipantsService;

public class CrearParticipante {

    public static void main(String[] args) {
        try {
            // Datos de consulta
            ParticipantsModel model = new ParticipantsModel();
            model.setNames("Jesus");
            model.setLast_names("Canales");
            model.setEmail("j.canales@gmail.com");
            model.setCellphone("924784282");
            model.setType_document("DNI");
            model.setNumber_document("87654321");
            model.setRegistration_date("2024-11-25");
            model.setUser_name("jesuscanales");
            model.setPassword("jesus12345");
            model.setStatus("A");
            // Proceso
            CrudParticipantsService service = new CrudParticipantsService();
            model = service.insert(model);
            // Reporte
            System.out.println("Registro grabado con id=" + model.getId());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
