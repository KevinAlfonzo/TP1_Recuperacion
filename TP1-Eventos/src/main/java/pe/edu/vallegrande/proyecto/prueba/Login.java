package pe.edu.vallegrande.proyecto.prueba;

import pe.edu.vallegrande.proyecto.model.ParticipantsModel;
import pe.edu.vallegrande.proyecto.service.impl.SeguridadService;
import pe.edu.vallegrande.proyecto.service.spec.SeguridadServiceSpec;

public class Login {

    public static void main(String[] args) {
        try {
            // Datos de consulta
            String user_name = "ksilva";
            String password = "2!p!vaILP";
            // Proceso
            SeguridadServiceSpec service = new SeguridadService();
            ParticipantsModel bean = service.validar(user_name, password);
            String reporte = "Datos incorrectos.";
            if(bean != null) {
                reporte = "Datos correctos. Hola " + bean.getNames() + ".";
            }
            // Reporte
            System.out.println(reporte);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}
