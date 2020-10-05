/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usbbog.appausa.appausaws.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.Gson;

/**
 *
 * @author aasanchez
 */
@Entity
@Table(name = "contrato_licencia", catalog = "ingusb_appausa", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContratoLicencia.findAll", query = "SELECT c FROM ContratoLicencia c")
    , @NamedQuery(name = "ContratoLicencia.findByContrato", query = "SELECT c FROM ContratoLicencia c WHERE c.contratoLicenciaPK.contrato = :contrato")
    , @NamedQuery(name = "ContratoLicencia.findByContratoEmpresa", query = "SELECT c FROM ContratoLicencia c WHERE c.contratoLicenciaPK.contratoEmpresa = :contratoEmpresa")
    , @NamedQuery(name = "ContratoLicencia.findByContratoUsuario", query = "SELECT c FROM ContratoLicencia c WHERE c.contratoLicenciaPK.contratoUsuario = :contratoUsuario")
    , @NamedQuery(name = "ContratoLicencia.findByTipoLicencia", query = "SELECT c FROM ContratoLicencia c WHERE c.contratoLicenciaPK.tipoLicencia = :tipoLicencia")
    , @NamedQuery(name = "ContratoLicencia.findByFechaInicio", query = "SELECT c FROM ContratoLicencia c WHERE c.fechaInicio = :fechaInicio")
    , @NamedQuery(name = "ContratoLicencia.findByFechaFin", query = "SELECT c FROM ContratoLicencia c WHERE c.fechaFin = :fechaFin")})
public class ContratoLicencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ContratoLicenciaPK contratoLicenciaPK;
    @Basic(optional = false)
    @Column(name = "fecha_inicio", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @JoinColumns({
        @JoinColumn(name = "contrato", referencedColumnName = "num_contrato", nullable = false, insertable = false, updatable = false)
        , @JoinColumn(name = "contrato_empresa", referencedColumnName = "empresa", nullable = false, insertable = false, updatable = false)
        , @JoinColumn(name = "contrato_usuario", referencedColumnName = "empleado", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Contrato contrato1;
    @JoinColumn(name = "tipo_licencia", referencedColumnName = "nombre", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TipoLicencia tipoLicencia;

    public ContratoLicencia() {
    }

    public ContratoLicencia(ContratoLicenciaPK contratoLicenciaPK) {
        this.contratoLicenciaPK = contratoLicenciaPK;
    }

    public ContratoLicencia(ContratoLicenciaPK contratoLicenciaPK, Date fechaInicio) {
        this.contratoLicenciaPK = contratoLicenciaPK;
        this.fechaInicio = fechaInicio;
    }

    public ContratoLicencia(int contrato, String contratoEmpresa, int contratoUsuario, String tipoLicencia) {
        this.contratoLicenciaPK = new ContratoLicenciaPK(contrato, contratoEmpresa, contratoUsuario, tipoLicencia);
    }

    public ContratoLicenciaPK getContratoLicenciaPK() {
        return contratoLicenciaPK;
    }

    public void setContratoLicenciaPK(ContratoLicenciaPK contratoLicenciaPK) {
        this.contratoLicenciaPK = contratoLicenciaPK;
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

    public Contrato getContrato1() {
        return contrato1;
    }

    public void setContrato1(Contrato contrato1) {
        this.contrato1 = contrato1;
    }

    public TipoLicencia getTipoLicencia() {
        return tipoLicencia;
    }

    public void setTipoLicencia(TipoLicencia tipoLicencia) {
        this.tipoLicencia = tipoLicencia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (contratoLicenciaPK != null ? contratoLicenciaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContratoLicencia)) {
            return false;
        }
        ContratoLicencia other = (ContratoLicencia) object;
        if ((this.contratoLicenciaPK == null && other.contratoLicenciaPK != null) || (this.contratoLicenciaPK != null && !this.contratoLicenciaPK.equals(other.contratoLicenciaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ContratoLicencia: " + toJson();
    }
       
    public String toJson() {
    	return new Gson().toJson(this,ContratoLicencia.class);
    }
    
}
