package pe.edu.vallegrande.proyecto.service.spec;

import pe.edu.vallegrande.proyecto.model.ParticipantsModel;

public interface SeguridadServiceSpec {

    /**
     * Valida el usuario y clave de una persona.
     * Puede generar una excepción en caso de algun error en la ejecución del proceso.
     * @param usuario El usuario de la persona.
     * @param clave   La clave del usuario.
     * @return        Retorna un objeto con los datos de la persona o un null si los datos no son correctos.
     */
    ParticipantsModel validar(String user_Name, String password);
}
