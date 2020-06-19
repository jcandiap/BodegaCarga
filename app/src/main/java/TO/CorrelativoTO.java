package TO;

public class CorrelativoTO {

    private String fecha;
    private String agencia;
    private String bodega;

    public CorrelativoTO() {
    }

    public CorrelativoTO(String fecha, String agencia, String bodega) {
        this.fecha = fecha;
        this.agencia = agencia;
        this.bodega = bodega;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getBodega() {
        return bodega;
    }

    public void setBodega(String bodega) {
        this.bodega = bodega;
    }
}
