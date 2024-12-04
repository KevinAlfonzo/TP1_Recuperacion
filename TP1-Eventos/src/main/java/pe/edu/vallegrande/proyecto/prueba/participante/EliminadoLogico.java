package pe.edu.vallegrande.proyecto.prueba.participante;

import pe.edu.vallegrande.proyecto.service.impl.CrudParticipantsService;

public class EliminadoLogico    {

    public static void main(String[] args) {
        try {
            // Datos de consulta
            Integer id = 12;
            // Proceso
            CrudParticipantsService service = new CrudParticipantsService();
            service.delete(id);
            // Reporte
            System.out.println("Registro eliminado logicamente.");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
