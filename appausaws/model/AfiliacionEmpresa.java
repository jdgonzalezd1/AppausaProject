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
@Table(name = "afiliacion_empresa", catalog = "ingusb_appausa", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AfiliacionEmpresa.findAll", query = "SELECT a FROM AfiliacionEmpresa a")
    , @NamedQuery(name = "AfiliacionEmpresa.findByEmpresa", query = "SELECT a FROM AfiliacionEmpresa a WHERE a.afiliacionEmpresaPK.empresa = :empresa")
    , @NamedQuery(name = "AfiliacionEmpresa.findByEntidad", query = "SELECT a FROM AfiliacionEmpresa a WHERE a.afiliacionEmpresaPK.entidad = :entidad")
    , @NamedQuery(name = "AfiliacionEmpresa.findByFechaInicio", query = "SELECT a FROM AfiliacionEmpresa a WHERE a.fechaInicio = :fechaInicio")
    , @NamedQuery(name = "AfiliacionEmpresa.findByFechaFin", query = "SELECT a FROM AfiliacionEmpresa a WHERE a.fechaFin = :fechaFin")})
public class AfiliacionEmpresa implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AfiliacionEmpresaPK afiliacionEmpresaPK;
    @Basic(optional = false)
    @Column(name = "fecha_inicio", nullable = false, columnDefinition = "DATE")
    private LocalDate fechaInicio;
    @Column(name = "fecha_fin", nullable = true, columnDefinition = "DATE")
    private LocalDate fechaFin;
    @JoinColumn(name = "empresa", referencedColumnName = "nit", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Empresa empresa1;
    @JoinColumn(name = "entidad", referencedColumnName = "nit", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Entidad entidad1;

    public AfiliacionEmpresa() {
    }

    public AfiliacionEmpresa(AfiliacionEmpresaPK afiliacionEmpresaPK) {
        this.afiliacionEmpresaPK = afiliacionEmpresaPK;
    }

    public AfiliacionEmpresa(AfiliacionEmpresaPK afiliacionEmpresaPK, LocalDate fechaInicio) {
        this.afiliacionEmpresaPK = afiliacionEmpresaPK;
        this.fechaInicio = fechaInicio;
    }

    public AfiliacionEmpresa(String empresa, String entidad) {
        this.afiliacionEmpresaPK = new AfiliacionEmpresaPK(empresa, entidad);
    }

    public AfiliacionEmpresaPK getAfiliacionEmpresaPK() {
        return afiliacionEmpresaPK;
    }

    public void setAfiliacionEmpresaPK(AfiliacionEmpresaPK afiliacionEmpresaPK) {
        this.afiliacionEmpresaPK = afiliacionEmpresaPK;
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

    public Empresa getEmpresa1() {
        return empresa1;
    }

    public void setEmpresa1(Empresa empresa1) {
        this.empresa1 = empresa1;
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
        hash += (afiliacionEmpresaPK != null ? afiliacionEmpresaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AfiliacionEmpresa)) {
            return false;
        }
        AfiliacionEmpresa other = (AfiliacionEmpresa) object;
        if ((this.afiliacionEmpresaPK == null && other.afiliacionEmpresaPK != null) || (this.afiliacionEmpresaPK != null && !this.afiliacionEmpresaPK.equals(other.afiliacionEmpresaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AfiliacionEmpresa: " + toJson().toString();
    }
       //afiliacionEmpresaPK,fechaInicio,fechaFin,empresa1,entidad1
    public JSONObject toJson() {
    	JSONObject json = new JSONObject();
    	json.put("afiliacionEmpresaPK", this.getAfiliacionEmpresaPK().toJson());
    	json.put("fechaInicio", this.getFechaInicio());
    	json.put("fechaFin", this.getFechaFin());
    	json.put("empresa", this.getEmpresa1().toJson());
    	json.put("entidad", this.getEntidad1().toJson());
    	return json;
    }
    
    public AfiliacionEmpresa fromJson(JSONObject json) throws JSONException, ParseException {
    	this.setAfiliacionEmpresaPK((AfiliacionEmpresaPK) json.get("afiliacionEmpresaPK"));
    	this.setFechaInicio(LocalDate.parse(json.getString("fechaInicio"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    	if (json.getString("fechaInicio") != "" && json.getString("fechaInicio") != null){
    		this.setFechaFin(LocalDate.parse(json.getString("fechaFin"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    	}
    	Empresa em = this.getEmpresa1().fromJson(json.getJSONObject("empresa"));
    	this.setEmpresa1(em);
    	Entidad en = this.getEntidad1().fromJson(json.getJSONObject("entidad"));
    	this.setEntidad1(en);
    	return this;
    }
    
}
