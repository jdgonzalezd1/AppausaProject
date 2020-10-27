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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "tipo_juego", catalog = "ingusb_appausa", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoJuego.findAll", query = "SELECT t FROM TipoJuego t")
    , @NamedQuery(name = "TipoJuego.findById", query = "SELECT t FROM TipoJuego t WHERE t.id = :id")
    , @NamedQuery(name = "TipoJuego.findByNombre", query = "SELECT t FROM TipoJuego t WHERE t.nombre = :nombre")
    , @NamedQuery(name = "TipoJuego.findByDescripcion", query = "SELECT t FROM TipoJuego t WHERE t.descripcion = :descripcion")})
public class TipoJuego implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String nombre;
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoJuego")
    private List<Juego> juegos;

    public TipoJuego() {
    }

    public TipoJuego(Integer id) {
        this.id = id;
    }

    public TipoJuego(Integer id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
    public List<Juego> getJuegos() {
        return juegos;
    }

    public void setJuegos(List<Juego> juegos) {
        this.juegos = juegos;
    }
    
    public Juego addJuego(Juego juego) {
    	getJuegos().add(juego);
        juego.setTipoJuego(this);
        return juego;
    }

    public Juego removeJuego(Juego juego) {
    	getJuegos().remove(juego);
        juego.setTipoJuego(null);
        return juego;
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
        if (!(object instanceof TipoJuego)) {
            return false;
        }
        TipoJuego other = (TipoJuego) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TipoJuego: " + toJson().toString();
    }
       
    public JSONObject toJson() {
    	JSONObject json = new JSONObject();
    	json.put("id", this.getId());
    	json.put("nombre", this.getNombre());
    	json.put("descrpcion", this.getDescripcion());
    	//FK
    	return json;
    }
    
<<<<<<< HEAD
<<<<<<< HEAD
    public TipoJuego fromJson(JSONObject json) throws JSONException, ParseException {
    	this.setId((Integer) json.getInt("id"));
    	this.setNombre(json.getString("nombre"));
    	this.setDescripcion(json.getString("descrpcion"));
    	ArrayList<Juego> list = new ArrayList<Juego>();     
    	JSONArray jsonArray = json.getJSONArray("juegos"); 
    	int i = 0;
    	while (jsonArray.get(i) != null) {
    		Juego ae = null;
    		ae.fromJson(jsonArray.getJSONObject(i));
    	    list.add(ae);
    	    i++;
    	} 
    	this.setJuegos(list);
=======
=======
>>>>>>> parent of 4ce73e8... FKS, List
    public TipoJuego fromJson(JSONObject json) {
    	this.setId((Integer) json.getAsNumber("id"));
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
