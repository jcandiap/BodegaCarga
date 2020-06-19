package TO;

public class BultosTO {

    private String bultos_Totales;
    private String bultos_Recepcionados;
    private String bultos_No_Recepcionados;

    public BultosTO() {
    }

    public BultosTO(String bultos_Totales, String bultos_Recepcionados, String bultos_No_Recepcionados) {
        this.bultos_Totales = bultos_Totales;
        this.bultos_Recepcionados = bultos_Recepcionados;
        this.bultos_No_Recepcionados = bultos_No_Recepcionados;
    }

    public String getBultos_Totales() {
        return bultos_Totales;
    }

    public void setBultos_Totales(String bultos_Totales) {
        this.bultos_Totales = bultos_Totales;
    }

    public String getBultos_Recepcionados() {
        return bultos_Recepcionados;
    }

    public void setBultos_Recepcionados(String bultos_Recepcionados) {
        this.bultos_Recepcionados = bultos_Recepcionados;
    }

    public String getBultos_No_Recepcionados() {
        return bultos_No_Recepcionados;
    }

    public void setBultos_No_Recepcionados(String bultos_No_Recepcionados) {
        this.bultos_No_Recepcionados = bultos_No_Recepcionados;
    }
}
