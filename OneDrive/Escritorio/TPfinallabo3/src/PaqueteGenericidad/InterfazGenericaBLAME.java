package PaqueteGenericidad;

public interface InterfazGenericaBLAME <T> {

    ///CONSULTA POR UN ITEM-------------------------------------------------------------
    public T buscar(String var);
    ///CONSULTA POR UN ITEM-------------------------------------------------------------

    ///LISTADO---------------------------------------------------------------------------
    public StringBuilder listado();
    ///LISTADO---------------------------------------------------------------------------

    ///AGREGAR UN ITEM------------------------------------------------------------------
    public void agregar(T generico);
    ///AGREGAR UN ITEM------------------------------------------------------------------

    ///MODIFICAR UN ITEM (SIRVE TAMBIEN PARA DAR ALTA Y BAJA LÒGICA)--------------------
    public boolean modificar(T generico, String var);
    ///MODIFICAR UN ITEM (SIRVE TAMBIEN PARA DAR ALTA Y NAJA LÒGICA)--------------------

    ///ELIMINAR UN ITEM-----------------------------------------------------------------
    public boolean eliminar(String var);
    ///ELIMINAR UN ITEM-----------------------------------------------------------------



}
