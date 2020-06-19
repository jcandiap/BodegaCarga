package TO;

public class TotalesTO {

    private String sumaPiezas;
    private String sumaKilos;
    private String sumaMetroCubicos;
    private String sumaPrecio;

    public TotalesTO(String sumaPiezas, String sumaKilos, String sumaMetroCubicos, String sumaPrecio) {
        this.sumaPiezas = sumaPiezas;
        this.sumaKilos = sumaKilos;
        this.sumaMetroCubicos = sumaMetroCubicos;
        this.sumaPrecio = sumaPrecio;
    }

    public TotalesTO() {
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

    public String getSumaMetroCubicos() {
        return sumaMetroCubicos;
    }

    public void setSumaMetroCubicos(String sumaMetroCubicos) {
        this.sumaMetroCubicos = sumaMetroCubicos;
    }

    public String getSumaPrecio() {
        return sumaPrecio;
    }

    public void setSumaPrecio(String sumaPrecio) {
        this.sumaPrecio = sumaPrecio;
    }
}
