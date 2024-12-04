package pe.edu.vallegrande.proyecto.prueba.participante;

import pe.edu.vallegrande.proyecto.model.ParticipantsModel;
import pe.edu.vallegrande.proyecto.service.impl.CrudParticipantsService;

import java.util.List;

public class ListarActivos {

    public static void main(String[] args) {
        try {
            CrudParticipantsService service = new CrudParticipantsService();
            List<ParticipantsModel> lista = service.getActive();

            System.out.println("Filas: " + lista.size());
            for (ParticipantsModel rec : lista) {
                System.out.println(
                        rec.getId() + " - " +
                                rec.getNames() + " - " +
                                rec.getLast_names() + " - " +
                                rec.getType_document() + " - " +
                                rec.getNumber_document() + " - " +
                                rec.getEmail() + " - " +
                                rec.getCellphone() + " - " +
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
