/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usbbog.appausa.appausaws.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

/**
 *
 * @author aasanchez
 */
@Entity
@Table(name = "afiliacion_contrato", catalog = "ingusb_appausa", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AfiliacionContrato.findAll", query = "SELECT a FROM AfiliacionContrato a")
    , @NamedQuery(name = "AfiliacionContrato.findByNumContrato", query = "SELECT a FROM AfiliacionContrato a WHERE a.afiliacionContratoPK.numContrato = :numContrato")
    , @NamedQuery(name = "AfiliacionContrato.findByEmpresa", query = "SELECT a FROM AfiliacionContrato a WHERE a.afiliacionContratoPK.empresa = :empresa")
    , @NamedQuery(name = "AfiliacionContrato.findByEmpleado", query = "SELECT a FROM AfiliacionContrato a WHERE a.afiliacionContratoPK.empleado = :empleado")
    , @NamedQuery(name = "AfiliacionContrato.findByEntidad", query = "SELECT a FROM AfiliacionContrato a WHERE a.afiliacionContratoPK.entidad = :entidad")
    , @NamedQuery(name = "AfiliacionContrato.findByFechaInicio", query = "SELECT a FROM AfiliacionContrato a WHERE a.fechaInicio = :fechaInicio")
    , @NamedQuery(name = "AfiliacionContrato.findByFechaFin", query = "SELECT a FROM AfiliacionContrato a WHERE a.fechaFin = :fechaFin")})
public class AfiliacionContrato implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AfiliacionContratoPK afiliacionContratoPK;
    @Basic(optional = false)
    @Column(name = "fecha_inicio", nullable = false, columnDefinition = "DATE")
    private LocalDate fechaInicio;
    @Column(name = "fecha_fin", nullable = true, columnDefinition = "DATE")
    private LocalDate fechaFin;
    @JoinColumns({
        @JoinColumn(name = "num_contrato", referencedColumnName = "num_contrato", nullable = false, insertable = false, updatable = false)
        , @JoinColumn(name = "empresa", referencedColumnName = "empresa", nullable = false, insertable = false, updatable = false)
        , @JoinColumn(name = "empleado", referencedColumnName = "empleado", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Contrato contrato;
    @JoinColumn(name = "entidad", referencedColumnName = "nit", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Entidad entidad1;

    public AfiliacionContrato() {
    }

    public AfiliacionContrato(AfiliacionContratoPK afiliacionContratoPK) {
        this.afiliacionContratoPK = afiliacionContratoPK;
    }

    public AfiliacionContrato(AfiliacionContratoPK afiliacionContratoPK, LocalDate fechaInicio) {
        this.afiliacionContratoPK = afiliacionContratoPK;
        this.fechaInicio = fechaInicio;
    }

    public AfiliacionContrato(int numContrato, String empresa, int empleado, String entidad) {
        this.afiliacionContratoPK = new AfiliacionContratoPK(numContrato, empresa, empleado, entidad);
    }

    public AfiliacionContratoPK getAfiliacionContratoPK() {
        return afiliacionContratoPK;
    }

    public void setAfiliacionContratoPK(AfiliacionContratoPK afiliacionContratoPK) {
        this.afiliacionContratoPK = afiliacionContratoPK;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    public Entidad getEntidad1() {
        return entidad1;
    }

    public void setEntidad1(Entidad entidad1) {
        this.entidad1 = entidad1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (afiliacionContratoPK != null ? afiliacionContratoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AfiliacionContrato)) {
            return false;
        }
        AfiliacionContrato other = (AfiliacionContrato) object;
        if ((this.afiliacionContratoPK == null && other.afiliacionContratoPK != null) || (this.afiliacionContratoPK != null && !this.afiliacionContratoPK.equals(other.afiliacionContratoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AfiliacionContrato: " + toJson().toString();
    }
    //afiliacionContratoPK, fechaInicio, fechaFin, contrato, entidad1 
    public JSONObject toJson() {
    	JSONObject json = new JSONObject();
    	json.put("afiliacionContratoPK", this.getAfiliacionContratoPK().toJson());
    	json.put("fechaInicio", this.getFechaInicio());
    	json.put("fechaFin", this.getFechaFin());
    	json.put("contrato", this.getContrato().toJson().getString("contratoPK"));
    	json.put("entidad", this.getEntidad1().toJson().getString("nit"));
    	return json;
    }
    
    public AfiliacionContrato fromJson(JSONObject json) throws JSONException, ParseException {
    	AfiliacionContratoPK pk = this.getAfiliacionContratoPK().fromJson(json.getJSONObject("afiliacionContratoPK"));
    	this.setAfiliacionContratoPK(pk);
    	this.setFechaInicio(LocalDate.parse(json.getString("fechaInicio"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    	if (json.getString("fechaInicio") != "" && json.getString("fechaInicio") != null){
    		this.setFechaFin(LocalDate.parse(json.getString("fechaFin"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    	}   	
    	return this;
    }
    
}
