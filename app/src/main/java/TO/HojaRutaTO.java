package TO;

public class HojaRutaTO {

    private String ot;
    private String formaPago;
    private String destino;
    private String piezas;
    private String metros;
    private String kilos;
    private String valorFlete;
    private String ciudadDestino;
    private String destinoIata;
    private String tipoDocumento;
    private String numDocumento;
    private String patente;
    private String estadoOdt;
    private String empaque;
    private String codBarra;
    private String estado;
    private String estadoDos;
    private String pallet;
    private String fecha;
    private String servicioGeneral;
    private String cobertura;
    private String cantidad;
    private String agencia;
    private String usuario;

    public HojaRutaTO() {
    }

    public HojaRutaTO(String ot, String formaPago, String destino, String piezas, String metros, String kilos, String valorFlete, String ciudadDestino,
                      String destinoIata, String tipoDocumento, String numDocumento, String patente, String estadoOdt, String empaque, String codBarra,
                      String estado, String estadoDos, String pallet, String fecha, String servicioGeneral, String cobertura) {
        this.ot = ot;
        this.formaPago = formaPago;
        this.destino = destino;
        this.piezas = piezas;
        this.metros = metros;
        this.kilos = kilos;
        this.valorFlete = valorFlete;
        this.ciudadDestino = ciudadDestino;
        this.destinoIata = destinoIata;
        this.tipoDocumento = tipoDocumento;
        this.numDocumento = numDocumento;
        this.patente = patente;
        this.estadoOdt = estadoOdt;
        this.empaque = empaque;
        this.codBarra = codBarra;
        this.estado = estado;
        this.estadoDos = estadoDos;
        this.pallet = pallet;
        this.fecha = fecha;
        this.servicioGeneral = servicioGeneral;
        this.cobertura = cobertura;
    }

    public HojaRutaTO(String ot, String cantidad) {
        this.ot = ot;
        this.cantidad = cantidad;
    }

    public HojaRutaTO(String patente, String agencia, String usuario) {
        this.patente = patente;
        this.agencia = agencia;
        this.usuario = usuario;
    }

    public String getOt() {
        return ot;
    }

    public void setOt(String ot) {
        this.ot = ot;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getPiezas() {
        return piezas;
    }

    public void setPiezas(String piezas) {
        this.piezas = piezas;
    }

    public String getMetros() {
        return metros;
    }

    public void setMetros(String metros) {
        this.metros = metros;
    }

    public String getKilos() {
        return kilos;
    }

    public void setKilos(String kilos) {
        this.kilos = kilos;
    }

    public String getValorFlete() {
        return valorFlete;
    }

    public void setValorFlete(String valorFlete) {
        this.valorFlete = valorFlete;
    }

    public String getCiudadDestino() {
        return ciudadDestino;
    }

    public void setCiudadDestino(String ciudadDestino) {
        this.ciudadDestino = ciudadDestino;
    }

    public String getDestinoIata() {
        return destinoIata;
    }

    public void setDestinoIata(String destinoIata) {
        this.destinoIata = destinoIata;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public String getEstadoOdt() {
        return estadoOdt;
    }

    public void setEstadoOdt(String estadoOdt) {
        this.estadoOdt = estadoOdt;
    }

    public String getEmpaque() {
        return empaque;
    }

    public void setEmpaque(String empaque) {
        this.empaque = empaque;
    }

    public String getCodBarra() {
        return codBarra;
    }

    public void setCodBarra(String codBarra) {
        this.codBarra = codBarra;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstadoDos() {
        return estadoDos;
    }

    public void setEstadoDos(String estadoDos) {
        this.estadoDos = estadoDos;
    }

    public String getPallet() {
        return pallet;
    }

    public void setPallet(String pallet) {
        this.pallet = pallet;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getServicioGeneral() {
        return servicioGeneral;
    }

    public void setServicioGeneral(String servicioGeneral) {
        this.servicioGeneral = servicioGeneral;
    }

    public String getCobertura() {
        return cobertura;
    }

    public void setCobertura(String cobertura) {
        this.cobertura = cobertura;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
