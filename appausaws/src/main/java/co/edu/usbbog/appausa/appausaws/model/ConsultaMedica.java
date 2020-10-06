/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usbbog.appausa.appausaws.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.json.JSONObject;

import com.google.gson.Gson;

/**
 *
 * @author aasanchez
 */
@Entity
@Table(name = "consulta_medica", catalog = "ingusb_appausa", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConsultaMedica.findAll", query = "SELECT c FROM ConsultaMedica c")
    , @NamedQuery(name = "ConsultaMedica.findById", query = "SELECT c FROM ConsultaMedica c WHERE c.id = :id")
    , @NamedQuery(name = "ConsultaMedica.findByObservaciones", query = "SELECT c FROM ConsultaMedica c WHERE c.observaciones = :observaciones")
    , @NamedQuery(name = "ConsultaMedica.findByFecha", query = "SELECT c FROM ConsultaMedica c WHERE c.fecha = :fecha")
    , @NamedQuery(name = "ConsultaMedica.findByPeso", query = "SELECT c FROM ConsultaMedica c WHERE c.peso = :peso")
    , @NamedQuery(name = "ConsultaMedica.findByEstatura", query = "SELECT c FROM ConsultaMedica c WHERE c.estatura = :estatura")
    , @NamedQuery(name = "ConsultaMedica.findByMedico", query = "SELECT c FROM ConsultaMedica c WHERE c.medico = :medico")
    , @NamedQuery(name = "ConsultaMedica.findByTipo", query = "SELECT c FROM ConsultaMedica c WHERE c.tipo = :tipo")})
public class ConsultaMedica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String observaciones;
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String fecha;
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String peso;
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String estatura;
    @Column(length = 45)
    private String medico;
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String tipo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "consultaMedica")
    private List<Incapacidad> incapacidades;
    @JoinColumn(name = "empleado", referencedColumnName = "num_documento", nullable = false)
    @ManyToOne(optional = false)
    private Empleado empleado;
    @JoinColumn(name = "entidad", referencedColumnName = "nit", nullable = false)
    @ManyToOne(optional = false)
    private Entidad entidad;

    public ConsultaMedica() {
    }

    public ConsultaMedica(Integer id) {
        this.id = id;
    }

    public ConsultaMedica(Integer id, String observaciones, String fecha, String peso, String estatura, String tipo) {
        this.id = id;
        this.observaciones = observaciones;
        this.fecha = fecha;
        this.peso = peso;
        this.estatura = estatura;
        this.tipo = tipo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getEstatura() {
        return estatura;
    }

    public void setEstatura(String estatura) {
        this.estatura = estatura;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @XmlTransient
    public List<Incapacidad> getIncapacidades() {
        return incapacidades;
    }

    public void setIncapacidades(List<Incapacidad> incapacidades) {
        this.incapacidades = incapacidades;
    }
    
    public Incapacidad addIncapacidad(Incapacidad incapacidad) {
    	getIncapacidades().add(incapacidad);
        incapacidad.setConsultaMedica(this);
        return incapacidad;
    }

    public Incapacidad removeIncapacidad(Incapacidad incapacidad) {
    	getIncapacidades().remove(incapacidad);
        incapacidad.setConsultaMedica(null);
        return incapacidad;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConsultaMedica)) {
            return false;
        }
        ConsultaMedica other = (ConsultaMedica) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ConsultaMedica: " + toJson().toString();
    }
       //id, observaciones, fecha, peso, estatura, medico, tipo, incapacidades, empleado, entidad
    public JSONObject toJson() {
    	JSONObject json = new JSONObject();
    	json.put("id", this.getId());
    	json.put("observaciones", this.getObservaciones());
    	json.put("fecha", this.getFecha());
    	json.put("peso", this.getPeso());
    	json.put("estatura", this.getEstatura());
    	json.put("medico", this.getMedico());
    	json.put("tipo", this.getTipo());
    	json.put("incapacidades", this.getIncapacidades());
    	json.put("empleado", this.getEmpleado());
    	json.put("entidad", this.getEntidad());
    	return json;  
    }
    
    public ConsultaMedica fromJson(JSONObject json) {
    	this.setId(json.getInt("id"));
    	this.setObservaciones(json.getString("observaciones"));
    	this.setFecha(json.getString("fecha"));
    	this.setPeso(json.getString("peso"));
    	this.setEstatura(json.getString("estatura"));
    	this.setMedico(json.getString("medico"));
    	this.setTipo(json.getString("tipo"));
    	this.setIncapacidades((List<Incapacidad>) json.get("incapacidades"));
    	this.setEmpleado((Empleado) json.get("empleado"));
    	this.setEntidad((Entidad) json.get("entidad"));
    	return this;
    }
    
}
