package TO;

public class CoberturaTO {

    private String ciudad_Cobertura;
    private String servicio_Generico;
    private String inmueble;

    public CoberturaTO() {
    }

    public CoberturaTO(String ciudad_Cobertura, String servicio_Generico) {
        this.ciudad_Cobertura = ciudad_Cobertura;
        this.servicio_Generico = servicio_Generico;
    }

    public CoberturaTO(String inmueble) {
        this.inmueble = inmueble;
    }

    public String getCiudad_Cobertura() {
        return ciudad_Cobertura;
    }

    public void setCiudad_Cobertura(String ciudad_Cobertura) {
        this.ciudad_Cobertura = ciudad_Cobertura;
    }

    public String getServicio_Generico() {
        return servicio_Generico;
    }

    public void setServicio_Generico(String servicio_Generico) {
        this.servicio_Generico = servicio_Generico;
    }

    public String getInmueble() {
        return inmueble;
    }

    public void setInmueble(String inmueble) {
        this.inmueble = inmueble;
    }
}

