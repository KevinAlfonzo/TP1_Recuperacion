package pe.edu.vallegrande.proyecto.prueba.transaccional;

import pe.edu.vallegrande.proyecto.model.ParticipationRegistrationModel;
import pe.edu.vallegrande.proyecto.service.impl.CrudParticipationRegistrationService;

public class CrearRegistroParticipacion {

    public static void main(String[] args) {
        try {
            // Datos de consulta
            ParticipationRegistrationModel model = new ParticipationRegistrationModel();
            model.setCertification("1");
            model.setDate_registration("2024-12-04");
            model.setEvent_id(1);
            model.setPonentes_id(1);
            model.setParticipants_id(10);
            //Proceso
            CrudParticipationRegistrationService service = new CrudParticipationRegistrationService();
            model = service.insert(model);
            //Reporte
            System.out.println("Registro grabado con id=" + model.getId());
        }catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
