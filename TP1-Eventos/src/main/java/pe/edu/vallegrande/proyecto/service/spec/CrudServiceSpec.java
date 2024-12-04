package pe.edu.vallegrande.proyecto.service.spec;

import java.util.List;

public interface CrudServiceSpec<T> {

    /**
     * Consulta todos los registros de la tabla.
     * @return Retorna una lista de objetos.
     */
    List<T> getActive();
    List<T> getInactive();


    /**
     * Permite consultar un registro especifico de la tabla en base de su ID.
     * @param id El ID del registro a consultas.
     * @return Retorna el registro se gun el ID.
     */
    T getById(Integer id);

    /**
     * Consulta registros en base a un filtro.
     * @param bean
     * @return
     */
    List<T> get(T bean);

    /**
     *
     * @param bean
     * @return
     */
    T insert(T bean);

    /**
     *
     * @param bean
     * @return
     */
    T update(T bean);

    /**
     *
     * @param id
     */
    void delete(Integer id);
}
