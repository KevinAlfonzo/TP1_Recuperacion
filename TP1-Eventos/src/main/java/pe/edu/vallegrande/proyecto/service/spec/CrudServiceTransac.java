package pe.edu.vallegrande.proyecto.service.spec;

public interface CrudServiceTransac<T> {

    /**
     * Permite consultar un registro especifico de la tabla en base de su ID.
     * @param id El ID del registro a consultas.
     * @return Retorna el registro se gun el ID.
     */
    T getById(Integer id);

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

}
