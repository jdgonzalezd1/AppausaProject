/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usbbog.appausa.appausaws.model;

import java.io.Serializable;
import java.text.ParseException;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author aasanchez
 */
@Entity
@Table(name = "tipo_afeccion", catalog = "ingusb_appausa", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoAfeccion.findAll", query = "SELECT t FROM TipoAfeccion t")
    , @NamedQuery(name = "TipoAfeccion.findByNombre", query = "SELECT t FROM TipoAfeccion t WHERE t.nombre = :nombre")
    , @NamedQuery(name = "TipoAfeccion.findByDescripcion", query = "SELECT t FROM TipoAfeccion t WHERE t.descripcion = :descripcion")})
public class TipoAfeccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String nombre;
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipo")
    private List<AfeccionMedica> afeccionesMedicas;

    public TipoAfeccion() {
    }

    public TipoAfeccion(String nombre) {
        this.nombre = nombre;
    }

    public TipoAfeccion(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<AfeccionMedica> getAfeccionesMedicas() {
        return afeccionesMedicas;
    }

    public void setAfeccionesMedicas(List<AfeccionMedica> afeccionesMedicas) {
        this.afeccionesMedicas = afeccionesMedicas;
    }
    
    public AfeccionMedica addAfeccionMedica(AfeccionMedica afeccionMedica) {
        getAfeccionesMedicas().add(afeccionMedica);
        afeccionMedica.setTipo(this);
        return afeccionMedica;
    }

    public AfeccionMedica removeAfeccionMedica(AfeccionMedica afeccionMedica) {
        getAfeccionesMedicas().remove(afeccionMedica);
        afeccionMedica.setTipo(null);
        return afeccionMedica;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nombre != null ? nombre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoAfeccion)) {
            return false;
        }
        TipoAfeccion other = (TipoAfeccion) object;
        if ((this.nombre == null && other.nombre != null) || (this.nombre != null && !this.nombre.equals(other.nombre))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "TipoAfeccion: " + toJson().toString();
    }
       
    public JSONObject toJson() {
    	JSONObject json = new JSONObject();
    	json.put("nombre", this.getNombre());
    	json.put("descrpcion", this.getDescripcion());
    	final JSONArray lista = new JSONArray();
    	List<AfeccionMedica> l = this.getAfeccionesMedicas();
    	l.forEach((elemento) -> lista.put(elemento.toJson().getString("afeccionMedicaPK")));
    	json.put("afeccionesMedicas", lista);
    	return json;
    }
    
    public TipoAfeccion fromJson(JSONObject json) throws JSONException, ParseException {
    	this.setNombre(json.getString("nombre"));
    	this.setDescripcion(json.getString("descrpcion"));
    	return this;
    }
}
