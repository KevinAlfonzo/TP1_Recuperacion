package pe.edu.vallegrande.proyecto.prueba.participante;

import pe.edu.vallegrande.proyecto.model.ParticipantsModel;
import pe.edu.vallegrande.proyecto.service.impl.CrudParticipantsService;

import java.util.List;

public class BuscarxFiltroParticipante {

    public static void main(String[] args) {
        try {
            // Datos de consulta
            ParticipantsModel model = new ParticipantsModel();
            model.setNames("Kevin");
            model.setLast_names("");
            model.setNumber_document("");
            // Proceso
            CrudParticipantsService service = new CrudParticipantsService();
            List<ParticipantsModel> lista = service.get(model);
            // Reporte
            System.out.println("LISTADO");
            System.out.println("Registros: " + lista.size());
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
