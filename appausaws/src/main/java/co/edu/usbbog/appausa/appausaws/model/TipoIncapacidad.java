/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usbbog.appausa.appausaws.model;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author aasanchez
 */
@Entity
@Table(name = "tipo_incapacidad", catalog = "ingusb_appausa", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoIncapacidad.findAll", query = "SELECT t FROM TipoIncapacidad t")
    , @NamedQuery(name = "TipoIncapacidad.findByNombre", query = "SELECT t FROM TipoIncapacidad t WHERE t.nombre = :nombre")
    , @NamedQuery(name = "TipoIncapacidad.findByPeriodoMax", query = "SELECT t FROM TipoIncapacidad t WHERE t.periodoMax = :periodoMax")})
public class TipoIncapacidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String nombre;
    @Basic(optional = false)
    @Lob
    @Column(nullable = false, length = 65535)
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "periodo_max", nullable = false)
    private int periodoMax;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoIncapacidad")
    private List<Incapacidad> incapacidades;

    public TipoIncapacidad() {
    }

    public TipoIncapacidad(String nombre) {
        this.nombre = nombre;
    }

    public TipoIncapacidad(String nombre, String descripcion, int periodoMax) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.periodoMax = periodoMax;
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

    public int getPeriodoMax() {
        return periodoMax;
    }

    public void setPeriodoMax(int periodoMax) {
        this.periodoMax = periodoMax;
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
        incapacidad.setTipoIncapacidad(this);
        return incapacidad;
    }

    public Incapacidad removeIncapacidad(Incapacidad incapacidad) {
    	getIncapacidades().remove(incapacidad);
        incapacidad.setTipoIncapacidad(null);
        return incapacidad;
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
        if (!(object instanceof TipoIncapacidad)) {
            return false;
        }
        TipoIncapacidad other = (TipoIncapacidad) object;
        if ((this.nombre == null && other.nombre != null) || (this.nombre != null && !this.nombre.equals(other.nombre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TipoIncapacidad: " + toJson().toString();
    }
       
    public JSONObject toJson() {
    	JSONObject json = new JSONObject();
    	json.put("nombre", this.getNombre());
    	json.put("descrpcion", this.getDescripcion());
    	json.put("puntajeMax", this.getPeriodoMax());
    	JSONArray lista = new JSONArray();
    	List<Incapacidad> l = getIncapacidades();
    	int i = 0;
    	while (l.get(i) != null) {
    		lista.put(l.get(i).toJson());
    		i++;
    	}
    	json.put("incapacidades", lista);
    	return json;
    }
    
    public TipoIncapacidad fromJson(JSONObject json) throws JSONException, ParseException {
    	this.setNombre(json.getString("nombre"));
    	this.setDescripcion(json.getString("descrpcion"));
    	this.setPeriodoMax((int) json.getInt("periodoMax"));
    	ArrayList<Incapacidad> list = new ArrayList<Incapacidad>();     
    	JSONArray jsonArray = json.getJSONArray("incapacidades"); 
    	int i = 0;
    	while (jsonArray.get(i) != null) {
    		Incapacidad ae = null;
    		ae.fromJson((JSONObject) jsonArray.get(i));
    	    list.add(ae);
    	    i++;
    	   } 
    	this.setIncapacidades(list);
    	return this;
    }
    
}
