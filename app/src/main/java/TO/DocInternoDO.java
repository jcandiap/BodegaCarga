package TO;

public class DocInternoDO {

    private String numeroDocumento;
    private String agencia;
    private String sumaPiezas;
    private String sumaKilos;
    private String sumaMetrosCubicos;
    private String sumaPrecio;
    private String numeroOT;
    private String numeroRecepcion;

    public DocInternoDO(String numeroDocumento, String agencia, String sumaPiezas, String sumaKilos, String sumaMetrosCubicos, String sumaPrecio) {
        this.numeroDocumento = numeroDocumento;
        this.agencia = agencia;
        this.sumaPiezas = sumaPiezas;
        this.sumaKilos = sumaKilos;
        this.sumaMetrosCubicos = sumaMetrosCubicos;
        this.sumaPrecio = sumaPrecio;
    }

    public DocInternoDO(String numeroOT, String numeroRecepcion) {
        this.numeroOT = numeroOT;
        this.numeroRecepcion = numeroRecepcion;
    }

    public DocInternoDO() {
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getSumaPiezas() {
        return sumaPiezas;
    }

    public void setSumaPiezas(String sumaPiezas) {
        this.sumaPiezas = sumaPiezas;
    }

    public String getSumaKilos() {
        return sumaKilos;
    }

    public void setSumaKilos(String sumaKilos) {
        this.sumaKilos = sumaKilos;
    }

    public String getSumaMetrosCubicos() {
        return sumaMetrosCubicos;
    }

    public void setSumaMetrosCubicos(String sumaMetrosCubicos) {
        this.sumaMetrosCubicos = sumaMetrosCubicos;
    }

    public String getSumaPrecio() {
        return sumaPrecio;
    }

    public void setSumaPrecio(String sumaPrecio) {
        this.sumaPrecio = sumaPrecio;
    }

    public String getNumeroOT() {
        return numeroOT;
    }

    public void setNumeroOT(String numeroOT) {
        this.numeroOT = numeroOT;
    }

    public String getNumeroRecepcion() {
        return numeroRecepcion;
    }

    public void setNumeroRecepcion(String numeroRecepcion) {
        this.numeroRecepcion = numeroRecepcion;
    }
}
