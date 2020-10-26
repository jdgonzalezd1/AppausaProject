/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usbbog.appausa.appausaws.model;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.json.*;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

/**
 *
 * @author aasanchez
 */
@Entity
@Table(name = "afeccion_medica", catalog = "ingusb_appausa", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AfeccionMedica.findAll", query = "SELECT a FROM AfeccionMedica a")
    , @NamedQuery(name = "AfeccionMedica.findByNombre", query = "SELECT a FROM AfeccionMedica a WHERE a.nombre = :nombre")
    , @NamedQuery(name = "AfeccionMedica.findByDescrip", query = "SELECT a FROM AfeccionMedica a WHERE a.descrip = :descrip")})
public class AfeccionMedica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String nombre;
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String descrip;
    @Basic(optional = false)
    @Lob
    @Column(nullable = false, length = 65535)
    private String indicaciones;
    @JoinColumn(name = "tipo", referencedColumnName = "nombre", nullable = false)
    @ManyToOne(optional = false)
    private TipoAfeccion tipo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "afeccionMedica1")
    private List<AfeccionEmpleado> afeccionEmpleadoList;

    public AfeccionMedica() {
    }

    public AfeccionMedica(String nombre) {
        this.nombre = nombre;
    }

    public AfeccionMedica(String nombre, String descrip, String indicaciones) {
        this.nombre = nombre;
        this.descrip = descrip;
        this.indicaciones = indicaciones;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public String getIndicaciones() {
        return indicaciones;
    }

    public void setIndicaciones(String indicaciones) {
        this.indicaciones = indicaciones;
    }

    public TipoAfeccion getTipo() {
        return tipo;
    }

    public void setTipo(TipoAfeccion tipo) {
        this.tipo = tipo;
    }

    @XmlTransient
    public List<AfeccionEmpleado> getAfeccionEmpleadoList() {
        return afeccionEmpleadoList;
    }

    public void setAfeccionEmpleadoList(List<AfeccionEmpleado> afeccionEmpleadoList) {
        this.afeccionEmpleadoList = afeccionEmpleadoList;
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
        if (!(object instanceof AfeccionMedica)) {
            return false;
        }
        AfeccionMedica other = (AfeccionMedica) object;
        if ((this.nombre == null && other.nombre != null) || (this.nombre != null && !this.nombre.equals(other.nombre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AfeccionMedica: " + toJson().toString();
    }
    //nombre, descrip, indicaciones, tipo, afeccionEmpleadoList   
    public JSONObject toJson() {
    	JSONObject json = new JSONObject();
    	json.put("nombre", this.getNombre());
    	json.put("descripcion", this.getDescrip());
    	json.put("indicaciones", this.getIndicaciones());
    	json.put("tipo", this.getTipo().toJson());
    	JSONArray lista = new JSONArray();
    	List<AfeccionEmpleado> l = getAfeccionEmpleadoList();
    	int i = 0;
    	while (l.get(i) != null) {
    		JSONObject obj = l.get(i).toJson();
    		lista.put(obj);
    		i++;
    	}
    	json.put("listaAfeccionEmpleado", lista);
    	return json;
    }
    
    public AfeccionMedica fromJson(JSONObject json) throws JSONException, ParseException {
    	this.setNombre(json.getString("nombre"));
    	this.setDescrip(json.getString("descripcion"));
    	this.setIndicaciones(json.getString("indicaciones"));
    	TipoAfeccion ta = this.getTipo().fromJson(json.getJSONObject("tipo"));
    	this.setTipo(ta);
    	ArrayList<AfeccionEmpleado> list = new ArrayList<AfeccionEmpleado>();     
    	JSONArray jsonArray = json.getJSONArray("listaAfeccionEmpleado"); 
    	int i = 0;
    	while (jsonArray.get(i) != null) {
    		AfeccionEmpleado ae = null;
    		ae.fromJson((JSONObject) jsonArray.get(i));
    	    list.add(ae);
    	    i++;
    	   } 
    	this.setAfeccionEmpleadoList(list);
    	return this;
    }
    
}
