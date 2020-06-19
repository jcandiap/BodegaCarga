package TO;

public class RegistroTO {

    /*Registrar contenido*/
    private String rnumeroOt;
    private String rempaqueOt;
    private String rcodigoBarra;
    private String ritemCodigo;
    private String ragencia;
    private String rbodega;
    private String restado;
    private String restadoOperacional;

    /*Registrar contenedor*/
    private String codigoBarraBulto;
    private String estadoOperacional;

    /*Registrar tracking*/
    private String odt;
    private String codigoBarra;
    private String itemCodigo;
    private String agencia;
    private String bodega;
    private String estado;
    private String usuario;
    private String mac;
    private String ip;

    /*Registrar OT*/
    private String numeroOt;
    //private String agencia;
    //private String bodega;
    //private String estadoOperacional;
    //private String ip;
    //private String mac;
    //private String usuario;
    private String estadoOperacion;
    private String patente;
    private String tipoDoc;
    private String numDoc;

    /*Registra pegaso*/
    private String documento;
    //private String usuario;
    //private String estadoOperacional;
    //private String agencia;
    //private String ip;
    //private String mac;
    private String movil;


    public RegistroTO(String rnumeroOt, String rempaqueOt, String rcodigoBarra, String ritemCodigo, String ragencia, String rbodega, String restado, String restadoOperacional) {
        this.rnumeroOt = rnumeroOt;
        this.rempaqueOt = rempaqueOt;
        this.rcodigoBarra = rcodigoBarra;
        this.ritemCodigo = ritemCodigo;
        this.ragencia = ragencia;
        this.rbodega = rbodega;
        this.restado = restado;
        this.restadoOperacional = restadoOperacional;
    }

    public RegistroTO(String numeroOt, String agencia, String bodega, String estadoOperacion, String ip, String mac, String usuario, String patente, String tipoDoc, String numDoc) {
        this.estadoOperacion = estadoOperacion;
        this.agencia = agencia;
        this.bodega = bodega;
        this.usuario = usuario;
        this.mac = mac;
        this.ip = ip;
        this.numeroOt = numeroOt;
        this.patente = patente;
        this.tipoDoc = tipoDoc;
        this.numDoc = numDoc;
    }

    public RegistroTO(String agencia, String usuario, String mac, String ip, String estadoOperacion, String documento, String movil) {
        this.agencia = agencia;
        this.usuario = usuario;
        this.mac = mac;
        this.ip = ip;
        this.estadoOperacion = estadoOperacion;
        this.documento = documento;
        this.movil = movil;
    }

    public String getRnumeroOt() {
        return rnumeroOt;
    }

    public void setRnumeroOt(String rnumeroOt) {
        this.rnumeroOt = rnumeroOt;
    }

    public String getRempaqueOt() {
        return rempaqueOt;
    }

    public void setRempaqueOt(String rempaqueOt) {
        this.rempaqueOt = rempaqueOt;
    }

    public String getRcodigoBarra() {
        return rcodigoBarra;
    }

    public void setRcodigoBarra(String rcodigoBarra) {
        this.rcodigoBarra = rcodigoBarra;
    }

    public String getRitemCodigo() {
        return ritemCodigo;
    }

    public void setRitemCodigo(String ritemCodigo) {
        this.ritemCodigo = ritemCodigo;
    }

    public String getRagencia() {
        return ragencia;
    }

    public void setRagencia(String ragencia) {
        this.ragencia = ragencia;
    }

    public String getRbodega() {
        return rbodega;
    }

    public void setRbodega(String rbodega) {
        this.rbodega = rbodega;
    }

    public String getRestado() {
        return restado;
    }

    public void setRestado(String restado) {
        this.restado = restado;
    }

    public String getRestadoOperacional() {
        return restadoOperacional;
    }

    public void setRestadoOperacional(String restadoOperacional) {
        this.restadoOperacional = restadoOperacional;
    }

    public RegistroTO(String codigoBarraBulto, String estadoOperacional) {
        this.codigoBarraBulto = codigoBarraBulto;
        this.estadoOperacional = estadoOperacional;
    }

    public RegistroTO(String odt, String codigoBarra, String itemCodigo, String agencia, String bodega, String estado, String usuario, String mac, String ip) {
        this.odt = odt;
        this.codigoBarra = codigoBarra;
        this.itemCodigo = itemCodigo;
        this.agencia = agencia;
        this.bodega = bodega;
        this.estado = estado;
        this.usuario = usuario;
        this.mac = mac;
        this.ip = ip;
    }

    public RegistroTO() {
    }

    public String getCodigoBarraBulto() {
        return codigoBarraBulto;
    }

    public void setCodigoBarraBulto(String codigoBarraBulto) {
        this.codigoBarraBulto = codigoBarraBulto;
    }

    public String getEstadoOperacional() {
        return estadoOperacional;
    }

    public void setEstadoOperacional(String estadoOperacional) {
        this.estadoOperacional = estadoOperacional;
    }

    public String getOdt() {
        return odt;
    }

    public void setOdt(String odt) {
        this.odt = odt;
    }

    public String getCodigoBarra() {
        return codigoBarra;
    }

    public void setCodigoBarra(String codigoBarra) {
        this.codigoBarra = codigoBarra;
    }

    public String getItemCodigo() {
        return itemCodigo;
    }

    public void setItemCodigo(String itemCodigo) {
        this.itemCodigo = itemCodigo;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getNumeroOt() {
        return numeroOt;
    }

    public void setNumeroOt(String numeroOt) {
        this.numeroOt = numeroOt;
    }

    public String getEstadoOperacion() {
        return estadoOperacion;
    }

    public void setEstadoOperacion(String estadoOperacion) {
        this.estadoOperacion = estadoOperacion;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public String getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public String getNumDoc() {
        return numDoc;
    }

    public void setNumDoc(String numDoc) {
        this.numDoc = numDoc;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getMovil() {
        return movil;
    }

    public void setMovil(String movil) {
        this.movil = movil;
    }
}
