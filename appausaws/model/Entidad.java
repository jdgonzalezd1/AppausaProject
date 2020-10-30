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

import org.json.JSONArray;
import org.json.JSONException;

import com.google.gson.Gson;

import org.json.JSONObject;

/**
 *
 * @author aasanchez
 */
@Entity
@Table(catalog = "ingusb_appausa", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Entidad.findAll", query = "SELECT e FROM Entidad e")
    , @NamedQuery(name = "Entidad.findByNit", query = "SELECT e FROM Entidad e WHERE e.nit = :nit")
    , @NamedQuery(name = "Entidad.findByNombre", query = "SELECT e FROM Entidad e WHERE e.nombre = :nombre")
    , @NamedQuery(name = "Entidad.findByTipo", query = "SELECT e FROM Entidad e WHERE e.tipo = :tipo")
    , @NamedQuery(name = "Entidad.findByTelefono", query = "SELECT e FROM Entidad e WHERE e.telefono = :telefono")
    , @NamedQuery(name = "Entidad.findByDireccion", query = "SELECT e FROM Entidad e WHERE e.direccion = :direccion")
    , @NamedQuery(name = "Entidad.findByEmail", query = "SELECT e FROM Entidad e WHERE e.email = :email")})
public class Entidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(nullable = false, length = 17)
    private String nit;
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String nombre;
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String tipo;
    @Basic(optional = false)
    @Column(nullable = false)
    private int telefono;
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String direccion;
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String email;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "entidad1")
    private List<AfiliacionEmpresa> afiliacionEmpresaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "entidad1")
    private List<AfiliacionEmpleado> afiliacionEmpleadoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "entidad")
    private List<ConsultaMedica> consultaMedicaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "entidad1")
    private List<AfiliacionContrato> afiliacionContratoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "entidad")
    private List<AfeccionEmpleado> afeccionEmpleadoList;

    public Entidad() {
    }

    public Entidad(String nit) {
        this.nit = nit;
    }

    public Entidad(String nit, String nombre, String tipo, int telefono, String direccion, String email) {
        this.nit = nit;
        this.nombre = nombre;
        this.tipo = tipo;
        this.telefono = telefono;
        this.direccion = direccion;
        this.email = email;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlTransient
    public List<AfiliacionEmpresa> getAfiliacionEmpresaList() {
        return afiliacionEmpresaList;
    }

    public void setAfiliacionEmpresaList(List<AfiliacionEmpresa> afiliacionEmpresaList) {
        this.afiliacionEmpresaList = afiliacionEmpresaList;
    }

    @XmlTransient
    public List<AfiliacionEmpleado> getAfiliacionEmpleadoList() {
        return afiliacionEmpleadoList;
    }

    public void setAfiliacionEmpleadoList(List<AfiliacionEmpleado> afiliacionEmpleadoList) {
        this.afiliacionEmpleadoList = afiliacionEmpleadoList;
    }

    @XmlTransient
    public List<ConsultaMedica> getConsultaMedicaList() {
        return consultaMedicaList;
    }

    public void setConsultaMedicaList(List<ConsultaMedica> consultaMedicaList) {
        this.consultaMedicaList = consultaMedicaList;
    }

    @XmlTransient
    public List<AfiliacionContrato> getAfiliacionContratoList() {
        return afiliacionContratoList;
    }

    public void setAfiliacionContratoList(List<AfiliacionContrato> afiliacionContratoList) {
        this.afiliacionContratoList = afiliacionContratoList;
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
        hash += (nit != null ? nit.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Entidad)) {
            return false;
        }
        Entidad other = (Entidad) object;
        if ((this.nit == null && other.nit != null) || (this.nit != null && !this.nit.equals(other.nit))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidad: " + toJson().toString();
    }
       
    public JSONObject toJson() {
    	JSONObject json = new JSONObject();
    	json.put("nit", this.getNit());
    	json.put("nombre", this.getNombre());
    	json.put("tipo", this.getTipo());
    	json.put("telefono", this.getTelefono());
    	json.put("direccion", this.getDireccion());
    	json.put("email", this.getEmail());
    	JSONArray lista = new JSONArray();
    	List<AfeccionEmpleado> l = this.getAfeccionEmpleadoList();
    	int i = 0;
    	while (l.get(i) != null) {
    		lista.put(l.get(i).toJson().getString("afeccionEmpleadoPK"));
    		i++;
    	}
    	json.put("afeccionEmpleadoList", lista);
    	lista = new JSONArray();
    	List<AfiliacionContrato> l1 = this.getAfiliacionContratoList();
    	i = 0;
    	while (l1.get(i) != null) {
    		lista.put(l1.get(i).toJson().getString("afliacionContratoPK"));
    		i++;
    	}
    	json.put("afiliacionContratoList", lista);
    	lista = new JSONArray();
    	List<AfiliacionEmpleado> l2 = this.getAfiliacionEmpleadoList();
    	i = 0;
    	while (l2.get(i) != null) {
    		lista.put(l2.get(i).toJson().getString("afiliacionEmpleadoPK"));
    		i++;
    	}
    	json.put("afiliacionEmpleadoList", lista);
    	lista = new JSONArray();
    	List<AfiliacionEmpresa> l3 = this.getAfiliacionEmpresaList();
    	i = 0;
    	while (l3.get(i) != null) {
    		lista.put(l3.get(i).toJson().getString("afiliacionEmpresaPK"));
    		i++;
    	}
    	json.put("afiliacionEmpresaList", lista);
    	return json;
    	}
    
    public Entidad fromJson(JSONObject json) throws JSONException, ParseException{
    	this.setNit(json.getString("nit"));
    	this.setNombre(json.getString("nombre"));
    	this.setTipo(json.getString("tipo"));
    	this.setTelefono(json.getInt("telefono"));
    	this.setDireccion(json.getString("direccion"));
    	this.setEmail(json.getString("email"));
    	return this;
    }

    
}