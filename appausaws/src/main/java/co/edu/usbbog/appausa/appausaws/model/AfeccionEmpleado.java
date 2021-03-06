/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usbbog.appausa.appausaws.model;

import java.io.Serializable;
import java.text.ParseException;
import java.time.LocalDateTime;
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
@Table(name = "afeccion_empleado", catalog = "ingusb_appausa", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AfeccionEmpleado.findAll", query = "SELECT a FROM AfeccionEmpleado a")
    , @NamedQuery(name = "AfeccionEmpleado.findByAfeccionMedica", query = "SELECT a FROM AfeccionEmpleado a WHERE a.afeccionEmpleadoPK.afeccionMedica = :afeccionMedica")
    , @NamedQuery(name = "AfeccionEmpleado.findByEmpleado", query = "SELECT a FROM AfeccionEmpleado a WHERE a.afeccionEmpleadoPK.empleado = :empleado")
    , @NamedQuery(name = "AfeccionEmpleado.findByFechaDiagnostico", query = "SELECT a FROM AfeccionEmpleado a WHERE a.fechaDiagnostico = :fechaDiagnostico")})
public class AfeccionEmpleado implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AfeccionEmpleadoPK afeccionEmpleadoPK;
    @Basic(optional = false)
    /// CAMBIAR 
    @Column(name = "fecha_diagnostico", nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime fechaDiagnostico;
    @JoinColumn(name = "afeccion_medica", referencedColumnName = "nombre", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private AfeccionMedica afeccionMedica1;
    @JoinColumn(name = "empleado", referencedColumnName = "num_documento", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Empleado empleado1;
    @JoinColumn(name = "entidad", referencedColumnName = "nit", nullable = false)
    @ManyToOne(optional = false)
    private Entidad entidad;

    public AfeccionEmpleado() {
    }

    public AfeccionEmpleado(AfeccionEmpleadoPK afeccionEmpleadoPK) {
        this.afeccionEmpleadoPK = afeccionEmpleadoPK;
    }

    public AfeccionEmpleado(AfeccionEmpleadoPK afeccionEmpleadoPK, LocalDateTime fechaDiagnostico) {
        this.afeccionEmpleadoPK = afeccionEmpleadoPK;
        this.fechaDiagnostico = fechaDiagnostico;
    }

    public AfeccionEmpleado(String afeccionMedica, int empleado) {
        this.afeccionEmpleadoPK = new AfeccionEmpleadoPK(afeccionMedica, empleado);
    }

    public AfeccionEmpleadoPK getAfeccionEmpleadoPK() {
        return afeccionEmpleadoPK;
    }

    public void setAfeccionEmpleadoPK(AfeccionEmpleadoPK afeccionEmpleadoPK) {
        this.afeccionEmpleadoPK = afeccionEmpleadoPK;
    }

    public LocalDateTime getFechaDiagnostico() {
        return fechaDiagnostico;
    }

    public void setFechaDiagnostico(LocalDateTime fechaDiagnostico) {
        this.fechaDiagnostico = fechaDiagnostico;
    }

    public AfeccionMedica getAfeccionMedica1() {
        return afeccionMedica1;
    }

    public void setAfeccionMedica1(AfeccionMedica afeccionMedica1) {
        this.afeccionMedica1 = afeccionMedica1;
    }

    public Empleado getEmpleado1() {
        return empleado1;
    }

    public void setEmpleado1(Empleado empleado1) {
        this.empleado1 = empleado1;
    }

    public Entidad getEntidad() {
        return entidad;
    }

    public void setEntidad(Entidad entidad) {
        this.entidad = entidad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (afeccionEmpleadoPK != null ? afeccionEmpleadoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AfeccionEmpleado)) {
            return false;
        }
        AfeccionEmpleado other = (AfeccionEmpleado) object;
        if ((this.afeccionEmpleadoPK == null && other.afeccionEmpleadoPK != null) || (this.afeccionEmpleadoPK != null && !this.afeccionEmpleadoPK.equals(other.afeccionEmpleadoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AfeccionEmpleado: " + toJson().toString();
    }
    //afeccionEmpleadoPK, fechaDiagnostico, afeccionMedica1,, empleado1, entidad
    public JSONObject toJson() {
    	JSONObject json = new JSONObject();
    	json.put("afeccionEmpleadoPK", this.getAfeccionEmpleadoPK().toJson());
    	json.put("fechaDiagnostico", this.getFechaDiagnostico());
    	json.put("afeccionMedica", this.getAfeccionMedica1().toJson().getString("afeccionMedicaPK"));
    	json.put("empleado",this.getEmpleado1().toJson().getString("numDocumento"));
    	json.put("entidad", this.getEntidad().toJson().getString("nit"));
    	return json;
    }
    
    public AfeccionEmpleado fromJson(JSONObject json) throws JSONException, ParseException {
    	AfeccionEmpleadoPK pk = this.getAfeccionEmpleadoPK().fromJson((JSONObject) json.get("afeccionEmpleadoPK"));
    	this.setAfeccionEmpleadoPK(pk);
    	this.setFechaDiagnostico(LocalDateTime.parse(json.getString("fechaDiagnostico"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    	return this;
    }
    
    
}
