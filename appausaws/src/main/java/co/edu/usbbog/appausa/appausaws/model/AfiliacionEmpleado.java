/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usbbog.appausa.appausaws.model;

import java.io.Serializable;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.json.JSONException;
import org.json.JSONObject;


/**
 *
 * @author aasanchez
 */
@Entity
@Table(name = "afiliacion_empleado", catalog = "ingusb_appausa", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AfiliacionEmpleado.findAll", query = "SELECT a FROM AfiliacionEmpleado a")
    , @NamedQuery(name = "AfiliacionEmpleado.findByEmpleado", query = "SELECT a FROM AfiliacionEmpleado a WHERE a.afiliacionEmpleadoPK.empleado = :empleado")
    , @NamedQuery(name = "AfiliacionEmpleado.findByEntidad", query = "SELECT a FROM AfiliacionEmpleado a WHERE a.afiliacionEmpleadoPK.entidad = :entidad")
    , @NamedQuery(name = "AfiliacionEmpleado.findByTipo", query = "SELECT a FROM AfiliacionEmpleado a WHERE a.tipo = :tipo")
    , @NamedQuery(name = "AfiliacionEmpleado.findByFechaInicio", query = "SELECT a FROM AfiliacionEmpleado a WHERE a.fechaInicio = :fechaInicio")
    , @NamedQuery(name = "AfiliacionEmpleado.findByFechaFin", query = "SELECT a FROM AfiliacionEmpleado a WHERE a.fechaFin = :fechaFin")})
public class AfiliacionEmpleado implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AfiliacionEmpleadoPK afiliacionEmpleadoPK;
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String tipo;
    @Basic(optional = false)
    @Column(name = "fecha_inicio", nullable = false, columnDefinition = "DATE")
    private LocalDate fechaInicio;
    @Column(name = "fecha_fin", nullable = true, columnDefinition = "DATE")
    private LocalDate fechaFin;
    @JoinColumn(name = "empleado", referencedColumnName = "num_documento", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Empleado empleado1;
    @JoinColumn(name = "entidad", referencedColumnName = "nit", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Entidad entidad1;

    public AfiliacionEmpleado() {
    }

    public AfiliacionEmpleado(AfiliacionEmpleadoPK afiliacionEmpleadoPK) {
        this.afiliacionEmpleadoPK = afiliacionEmpleadoPK;
    }

    public AfiliacionEmpleado(AfiliacionEmpleadoPK afiliacionEmpleadoPK, String tipo, LocalDate fechaInicio) {
        this.afiliacionEmpleadoPK = afiliacionEmpleadoPK;
        this.tipo = tipo;
        this.fechaInicio = fechaInicio;
    }

    public AfiliacionEmpleado(int empleado, String entidad) {
        this.afiliacionEmpleadoPK = new AfiliacionEmpleadoPK(empleado, entidad);
    }

    public AfiliacionEmpleadoPK getAfiliacionEmpleadoPK() {
        return afiliacionEmpleadoPK;
    }

    public void setAfiliacionEmpleadoPK(AfiliacionEmpleadoPK afiliacionEmpleadoPK) {
        this.afiliacionEmpleadoPK = afiliacionEmpleadoPK;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public Empleado getEmpleado1() {
        return empleado1;
    }

    public void setEmpleado1(Empleado empleado1) {
        this.empleado1 = empleado1;
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
        hash += (afiliacionEmpleadoPK != null ? afiliacionEmpleadoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AfiliacionEmpleado)) {
            return false;
        }
        AfiliacionEmpleado other = (AfiliacionEmpleado) object;
        if ((this.afiliacionEmpleadoPK == null && other.afiliacionEmpleadoPK != null) || (this.afiliacionEmpleadoPK != null && !this.afiliacionEmpleadoPK.equals(other.afiliacionEmpleadoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AfiliacionEmpleado: " + toJson().toString();
    }
    //afiliacionEmpleadoPK,tipo,fechaInicio,fechaFin,empleado1,entidad1 
    public JSONObject toJson() {
    	JSONObject json = new JSONObject();
    	json.put("afiliacionEmpleadoPK", this.getAfiliacionEmpleadoPK().toJson());
    	json.put("tipo", this.getTipo());
    	json.put("fechaInicio", this.getFechaInicio());
    	json.put("fechaFin", this.getFechaFin());
    	json.put("empleado", this.getEmpleado1().toJson().getString("numDocumento"));
    	json.put("entidad", this.getEntidad1().toJson().getString("nit"));
    	return json;    	
    }
    
    public AfiliacionEmpleado fromJson(JSONObject json) throws JSONException, ParseException {
    	AfiliacionEmpleadoPK pk = this.getAfiliacionEmpleadoPK().fromJson(json.getJSONObject("afiliacionEmpleadoPK"));
    	this.setAfiliacionEmpleadoPK(pk);
    	this.setTipo(json.getString("tipo"));
    	this.setFechaInicio(LocalDate.parse(json.getString("fechaInicio"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    	if (json.getString("fechaInicio") != "" && json.getString("fechaInicio") != null){
    		this.setFechaFin(LocalDate.parse(json.getString("fechaFin"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    	}
    	return this;
    }
    
}
