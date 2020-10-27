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
@Table(name = "tipo_contrato", catalog = "ingusb_appausa", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoContrato.findAll", query = "SELECT t FROM TipoContrato t")
    , @NamedQuery(name = "TipoContrato.findByNombre", query = "SELECT t FROM TipoContrato t WHERE t.nombre = :nombre")
    , @NamedQuery(name = "TipoContrato.findByDescripcion", query = "SELECT t FROM TipoContrato t WHERE t.descripcion = :descripcion")})
public class TipoContrato implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String nombre;
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoContrato")
    private List<Contrato> contratos;

    public TipoContrato() {
    }

    public TipoContrato(String nombre) {
        this.nombre = nombre;
    }

    public TipoContrato(String nombre, String descripcion) {
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
    public List<Contrato> getContratos() {
        return contratos;
    }

    public void setContratos(List<Contrato> contratos) {
        this.contratos = contratos;
    }
    
    public Contrato addContrato(Contrato contrato) {
    	getContratos().add(contrato);
        contrato.setTipoContrato(this);
        return contrato;
    }

    public Contrato removeContrato(Contrato contrato) {
    	getContratos().remove(contrato);
        contrato.setTipoContrato(null);
        return contrato;
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
        if (!(object instanceof TipoContrato)) {
            return false;
        }
        TipoContrato other = (TipoContrato) object;
        if ((this.nombre == null && other.nombre != null) || (this.nombre != null && !this.nombre.equals(other.nombre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TipoContrato: " + toJson().toString();
    }
       
    public JSONObject toJson() {
    	JSONObject json = new JSONObject();
    	json.put("nombre", this.getNombre());
    	json.put("descrpcion", this.getDescripcion());
    	JSONArray lista = new JSONArray();
    	List<Contrato> l = this.getContratos();
    	int i = 0;
    	while (l.get(i) != null) {
    		lista.put(l.get(i).toJson());
    		i++;
    	}
    	json.put("contratos", lista);
    	return json;
    }
    
    public TipoContrato fromJson(JSONObject json) throws JSONException, ParseException {
    	this.setNombre(json.getString("nombre"));
    	this.setDescripcion(json.getString("descrpcion"));
    	ArrayList<Contrato> list = new ArrayList<Contrato>();     
    	JSONArray jsonArray = json.getJSONArray("contratos"); 
    	int i = 0;
    	while (jsonArray.get(i) != null) {
    		Contrato ae = null;
    		ae.fromJson(jsonArray.getJSONObject(i));
    	    list.add(ae);
    	    i++;
    	} 
    	this.setContratos(list);
    	return this;
    }
    
}
