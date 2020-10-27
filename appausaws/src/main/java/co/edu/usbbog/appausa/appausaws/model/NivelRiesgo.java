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
@Table(name = "nivel_riesgo", catalog = "ingusb_appausa", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NivelRiesgo.findAll", query = "SELECT n FROM NivelRiesgo n")
    , @NamedQuery(name = "NivelRiesgo.findByNombre", query = "SELECT n FROM NivelRiesgo n WHERE n.nombre = :nombre")
    , @NamedQuery(name = "NivelRiesgo.findByDescripcion", query = "SELECT n FROM NivelRiesgo n WHERE n.descripcion = :descripcion")})
public class NivelRiesgo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(nullable = false, length = 8)
    private String nombre;
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nivelRiesgo")
    private List<Contrato> contratos;

    public NivelRiesgo() {
    }

    public NivelRiesgo(String nombre) {
        this.nombre = nombre;
    }

    public NivelRiesgo(String nombre, String descripcion) {
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
        contrato.setNivelRiesgo(this);
        return contrato;
    }

    public Contrato removeContrato(Contrato contrato) {
    	getContratos().remove(contrato);
        contrato.setNivelRiesgo(null);
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
        if (!(object instanceof NivelRiesgo)) {
            return false;
        }
        NivelRiesgo other = (NivelRiesgo) object;
        if ((this.nombre == null && other.nombre != null) || (this.nombre != null && !this.nombre.equals(other.nombre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "NivelRiesgo: " + toJson().toString();
    }
       
    public JSONObject toJson() {
    	JSONObject json = new JSONObject();
    	json.put("nombre", this.getNombre());
    	json.put("descricion",this.getDescripcion());
    	
    	return json;
    }
    
    
<<<<<<< HEAD
<<<<<<< HEAD
    public NivelRiesgo fromJson(JSONObject json) throws JSONException, ParseException {
    	this.setNombre(json.getString("nombre"));
    	this.setDescripcion(json.getString("descripcion"));
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
=======
=======
>>>>>>> parent of 4ce73e8... FKS, List
    public NivelRiesgo fromJson(JSONObject json) {
    	this.setNombre(json.getAsString("nombre"));
    	this.setDescripcion(json.getAsString("descripcion"));
    	
<<<<<<< HEAD
>>>>>>> parent of 4ce73e8... FKS, List
=======
>>>>>>> parent of 4ce73e8... FKS, List
    	return this;
    }
}
