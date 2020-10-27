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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import com.google.gson.Gson;

import net.minidev.json.JSONObject;

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
    	//FK
    	return json;
    }
    
<<<<<<< HEAD
<<<<<<< HEAD
    public TipoAfeccion fromJson(JSONObject json) throws JSONException, ParseException {
    	this.setNombre(json.getString("nombre"));
    	this.setDescripcion(json.getString("descrpcion"));
    	ArrayList<AfeccionMedica> list = new ArrayList<AfeccionMedica>();     
    	JSONArray jsonArray = json.getJSONArray("afeccionesMedicas"); 
    	int i = 0;
    	while (jsonArray.get(i) != null) {
    		AfeccionMedica ae = null;
    		ae.fromJson(jsonArray.getJSONObject(i));
    	    list.add(ae);
    	   } 
    	this.setAfeccionesMedicas(list);
=======
=======
>>>>>>> parent of 4ce73e8... FKS, List
    public TipoAfeccion fromJson(JSONObject json) {
    	this.setNombre(json.getAsString("nombre"));
    	this.setDescripcion(json.getAsString("descrpcion"));
    	//
<<<<<<< HEAD
>>>>>>> parent of 4ce73e8... FKS, List
=======
>>>>>>> parent of 4ce73e8... FKS, List
    	return this;
    }
}
