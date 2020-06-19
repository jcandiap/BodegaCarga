package TO;

public class PuntoTO {

    private String bod_Descripcion; //Descripcion Bodega
    private String bod_CodBodega; //Codigo Bodega
    private String odp_CodOficina; //Codigo Oficina
    private String odp_Descripcion; //Descripcion Oficina
    private String pun_CodPunto;	//Codigo Punto
    private String pun_Descripcion;	//Punto Descripcion
    private String inm_Ciudad;	//Codigo Ciudad Inmueble
    private String inm_CiudadDescripcion; //Descripcion Ciudad Inmueble
    private String pun_DapCodPunto; //Codigo Punto
    private String dap_Valor;	//MAC punto
    private String inm_Comuna; //Comuna Inmueble
    private String inm_CiudadDescripcion2; //Descripcion Ciudad Inmueble
    private String tp_PunCodTipoPunto; //Codigo tipo de punto
    private String inm_codinmueble; //Codigo Inmueble
    private String codAgencia; //Codigo de agencia
    private String codInmueble;	//Codigo de inmueble
    private String codOficina;	//Codigo de oficina
    private String descOficina; //Descripcion oficina
    private String codPunto; //Codigo Punto
    private String descPunto; //Descripcion Punto
    private String codCiudad; //Codigo Ciudad
    private String descCiudad; //Descripcion Ciudad
    private String codComuna; //Codigo Comuna
    private String descComuna;	//Descripcion Comuna
    private String codBodega; //Codigo de Bodega
    private String descBodega; //Descripcion bodega

    public PuntoTO() {
    }

    public PuntoTO(String bod_Descripcion, String bod_CodBodega, String odp_CodOficina, String odp_Descripcion, String pun_CodPunto,
                   String pun_Descripcion, String inm_Ciudad, String inm_CiudadDescripcion, String pun_DapCodPunto, String dap_Valor,
                   String inm_Comuna, String inm_CiudadDescripcion2, String tp_PunCodTipoPunto, String inm_codinmueble, String codAgencia,
                   String codInmueble, String codOficina, String descOficina, String codPunto, String descPunto, String codCiudad,
                   String descCiudad, String codComuna, String descComuna, String codBodega, String descBodega) {
        this.bod_Descripcion = bod_Descripcion;
        this.bod_CodBodega = bod_CodBodega;
        this.odp_CodOficina = odp_CodOficina;
        this.odp_Descripcion = odp_Descripcion;
        this.pun_CodPunto = pun_CodPunto;
        this.pun_Descripcion = pun_Descripcion;
        this.inm_Ciudad = inm_Ciudad;
        this.inm_CiudadDescripcion = inm_CiudadDescripcion;
        this.pun_DapCodPunto = pun_DapCodPunto;
        this.dap_Valor = dap_Valor;
        this.inm_Comuna = inm_Comuna;
        this.inm_CiudadDescripcion2 = inm_CiudadDescripcion2;
        this.tp_PunCodTipoPunto = tp_PunCodTipoPunto;
        this.inm_codinmueble = inm_codinmueble;
        this.codAgencia = codAgencia;
        this.codInmueble = codInmueble;
        this.codOficina = codOficina;
        this.descOficina = descOficina;
        this.codPunto = codPunto;
        this.descPunto = descPunto;
        this.codCiudad = codCiudad;
        this.descCiudad = descCiudad;
        this.codComuna = codComuna;
        this.descComuna = descComuna;
        this.codBodega = codBodega;
        this.descBodega = descBodega;
    }

    public String getBod_Descripcion() {
        return bod_Descripcion;
    }

    public void setBod_Descripcion(String bod_Descripcion) {
        this.bod_Descripcion = bod_Descripcion;
    }

    public String getBod_CodBodega() {
        return bod_CodBodega;
    }

    public void setBod_CodBodega(String bod_CodBodega) {
        this.bod_CodBodega = bod_CodBodega;
    }

    public String getOdp_CodOficina() {
        return odp_CodOficina;
    }

    public void setOdp_CodOficina(String odp_CodOficina) {
        this.odp_CodOficina = odp_CodOficina;
    }

    public String getOdp_Descripcion() {
        return odp_Descripcion;
    }

    public void setOdp_Descripcion(String odp_Descripcion) {
        this.odp_Descripcion = odp_Descripcion;
    }

    public String getPun_CodPunto() {
        return pun_CodPunto;
    }

    public void setPun_CodPunto(String pun_CodPunto) {
        this.pun_CodPunto = pun_CodPunto;
    }

    public String getPun_Descripcion() {
        return pun_Descripcion;
    }

    public void setPun_Descripcion(String pun_Descripcion) {
        this.pun_Descripcion = pun_Descripcion;
    }

    public String getInm_Ciudad() {
        return inm_Ciudad;
    }

    public void setInm_Ciudad(String inm_Ciudad) {
        this.inm_Ciudad = inm_Ciudad;
    }

    public String getInm_CiudadDescripcion() {
        return inm_CiudadDescripcion;
    }

    public void setInm_CiudadDescripcion(String inm_CiudadDescripcion) {
        this.inm_CiudadDescripcion = inm_CiudadDescripcion;
    }

    public String getPun_DapCodPunto() {
        return pun_DapCodPunto;
    }

    public void setPun_DapCodPunto(String pun_DapCodPunto) {
        this.pun_DapCodPunto = pun_DapCodPunto;
    }

    public String getDap_Valor() {
        return dap_Valor;
    }

    public void setDap_Valor(String dap_Valor) {
        this.dap_Valor = dap_Valor;
    }

    public String getInm_Comuna() {
        return inm_Comuna;
    }

    public void setInm_Comuna(String inm_Comuna) {
        this.inm_Comuna = inm_Comuna;
    }

    public String getInm_CiudadDescripcion2() {
        return inm_CiudadDescripcion2;
    }

    public void setInm_CiudadDescripcion2(String inm_CiudadDescripcion2) {
        this.inm_CiudadDescripcion2 = inm_CiudadDescripcion2;
    }

    public String getTp_PunCodTipoPunto() {
        return tp_PunCodTipoPunto;
    }

    public void setTp_PunCodTipoPunto(String tp_PunCodTipoPunto) {
        this.tp_PunCodTipoPunto = tp_PunCodTipoPunto;
    }

    public String getInm_codinmueble() {
        return inm_codinmueble;
    }

    public void setInm_codinmueble(String inm_codinmueble) {
        this.inm_codinmueble = inm_codinmueble;
    }

    public String getCodAgencia() {
        return codAgencia;
    }

    public void setCodAgencia(String codAgencia) {
        this.codAgencia = codAgencia;
    }

    public String getCodInmueble() {
        return codInmueble;
    }

    public void setCodInmueble(String codInmueble) {
        this.codInmueble = codInmueble;
    }

    public String getCodOficina() {
        return codOficina;
    }

    public void setCodOficina(String codOficina) {
        this.codOficina = codOficina;
    }

    public String getDescOficina() {
        return descOficina;
    }

    public void setDescOficina(String descOficina) {
        this.descOficina = descOficina;
    }

    public String getCodPunto() {
        return codPunto;
    }

    public void setCodPunto(String codPunto) {
        this.codPunto = codPunto;
    }

    public String getDescPunto() {
        return descPunto;
    }

    public void setDescPunto(String descPunto) {
        this.descPunto = descPunto;
    }

    public String getCodCiudad() {
        return codCiudad;
    }

    public void setCodCiudad(String codCiudad) {
        this.codCiudad = codCiudad;
    }

    public String getDescCiudad() {
        return descCiudad;
    }

    public void setDescCiudad(String descCiudad) {
        this.descCiudad = descCiudad;
    }

    public String getCodComuna() {
        return codComuna;
    }

    public void setCodComuna(String codComuna) {
        this.codComuna = codComuna;
    }

    public String getDescComuna() {
        return descComuna;
    }

    public void setDescComuna(String descComuna) {
        this.descComuna = descComuna;
    }

    public String getCodBodega() {
        return codBodega;
    }

    public void setCodBodega(String codBodega) {
        this.codBodega = codBodega;
    }

    public String getDescBodega() {
        return descBodega;
    }

    public void setDescBodega(String descBodega) {
        this.descBodega = descBodega;
    }
}
