package ParteCategoria;

import java.util.ArrayList;

public class Categoria{
    private String nombreCateogria;
    private boolean asientoCuero;
    private boolean jacuzzi;
    private boolean wifi;


    public Categoria(String nombreCateogria, boolean asientoCuero, boolean jacuzzi, boolean wifi) {
        this.nombreCateogria = nombreCateogria;
        this.asientoCuero = asientoCuero;
        this.jacuzzi = jacuzzi;
        this.wifi = wifi;
    }

    public String getNombreCateogria() {
        return nombreCateogria;
    }

    public void setNombreCateogria(String nombreCateogria) {
        this.nombreCateogria = nombreCateogria;
    }







}
