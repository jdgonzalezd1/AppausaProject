/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usbbog.appausa.appausaws.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.google.gson.Gson;

/**
 *
 * @author aasanchez
 */
@Entity
@Table(catalog = "ingusb_appausa", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contrato.findAll", query = "SELECT c FROM Contrato c")
    , @NamedQuery(name = "Contrato.findByNumContrato", query = "SELECT c FROM Contrato c WHERE c.contratoPK.numContrato = :numContrato")
    , @NamedQuery(name = "Contrato.findByFechaInicio", query = "SELECT c FROM Contrato c WHERE c.fechaInicio = :fechaInicio")
    , @NamedQuery(name = "Contrato.findByFechaFin", query = "SELECT c FROM Contrato c WHERE c.fechaFin = :fechaFin")
    , @NamedQuery(name = "Contrato.findBySueldo", query = "SELECT c FROM Contrato c WHERE c.sueldo = :sueldo")
    , @NamedQuery(name = "Contrato.findByCargo", query = "SELECT c FROM Contrato c WHERE c.cargo = :cargo")
    , @NamedQuery(name = "Contrato.findByRepresentante", query = "SELECT c FROM Contrato c WHERE c.representante = :representante")
    , @NamedQuery(name = "Contrato.findByEmpresa", query = "SELECT c FROM Contrato c WHERE c.contratoPK.empresa = :empresa")
    , @NamedQuery(name = "Contrato.findByEmpleado", query = "SELECT c FROM Contrato c WHERE c.contratoPK.empleado = :empleado")})
public class Contrato implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ContratoPK contratoPK;
    @Basic(optional = false)
    @Column(name = "fecha_inicio", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @Basic(optional = false)
    @Column(nullable = false)
    private long sueldo;
    @Basic(optional = false)
    @Lob
    @Column(nullable = false, length = 65535)
    private String funciones;
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String cargo;
    @Basic(optional = false)
    @Column(nullable = false)
    private short representante;
    @JoinColumn(name = "empleado", referencedColumnName = "num_documento", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Empleado empleado1;
    @JoinColumn(name = "empresa", referencedColumnName = "nit", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Empresa empresa1;
    @JoinColumn(name = "nivel_riesgo", referencedColumnName = "nombre", nullable = false)
    @ManyToOne(optional = false)
    private NivelRiesgo nivelRiesgo;
    @JoinColumn(name = "tipo_contrato", referencedColumnName = "nombre", nullable = false)
    @ManyToOne(optional = false)
    private TipoContrato tipoContrato;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contrato")
    private List<AfiliacionContrato> afiliacionContratoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contrato1")
    private List<ContratoLicencia> contratoLicenciaList;

    public Contrato() {
    }

    public Contrato(ContratoPK contratoPK) {
        this.contratoPK = contratoPK;
    }

    public Contrato(ContratoPK contratoPK, Date fechaInicio, long sueldo, String funciones, String cargo, short representante) {
        this.contratoPK = contratoPK;
        this.fechaInicio = fechaInicio;
        this.sueldo = sueldo;
        this.funciones = funciones;
        this.cargo = cargo;
        this.representante = representante;
    }

    public Contrato(int numContrato, String empresa, int empleado) {
        this.contratoPK = new ContratoPK(numContrato, empresa, empleado);
    }

    public ContratoPK getContratoPK() {
        return contratoPK;
    }

    public void setContratoPK(ContratoPK contratoPK) {
        this.contratoPK = contratoPK;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public long getSueldo() {
        return sueldo;
    }

    public void setSueldo(long sueldo) {
        this.sueldo = sueldo;
    }

    public String getFunciones() {
        return funciones;
    }

    public void setFunciones(String funciones) {
        this.funciones = funciones;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public short getRepresentante() {
        return representante;
    }

    public void setRepresentante(short representante) {
        this.representante = representante;
    }

    public Empleado getEmpleado1() {
        return empleado1;
    }

    public void setEmpleado1(Empleado empleado1) {
        this.empleado1 = empleado1;
    }

    public Empresa getEmpresa1() {
        return empresa1;
    }

    public void setEmpresa1(Empresa empresa1) {
        this.empresa1 = empresa1;
    }

    public NivelRiesgo getNivelRiesgo() {
        return nivelRiesgo;
    }

    public void setNivelRiesgo(NivelRiesgo nivelRiesgo) {
        this.nivelRiesgo = nivelRiesgo;
    }

    public TipoContrato getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(TipoContrato tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    @XmlTransient
    public List<AfiliacionContrato> getAfiliacionContratoList() {
        return afiliacionContratoList;
    }

    public void setAfiliacionContratoList(List<AfiliacionContrato> afiliacionContratoList) {
        this.afiliacionContratoList = afiliacionContratoList;
    }

    @XmlTransient
    public List<ContratoLicencia> getContratoLicenciaList() {
        return contratoLicenciaList;
    }

    public void setContratoLicenciaList(List<ContratoLicencia> contratoLicenciaList) {
        this.contratoLicenciaList = contratoLicenciaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (contratoPK != null ? contratoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contrato)) {
            return false;
        }
        Contrato other = (Contrato) object;
        if ((this.contratoPK == null && other.contratoPK != null) || (this.contratoPK != null && !this.contratoPK.equals(other.contratoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Contrato: " + toJson();
    }
       
    public String toJson() {
    	return new Gson().toJson(this,Contrato.class);
    }
    
}
