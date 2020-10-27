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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

/**
 *
 * @author aasanchez
 */
@Entity
@Table(catalog = "ingusb_appausa", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empresa.findAll", query = "SELECT e FROM Empresa e")
    , @NamedQuery(name = "Empresa.findByNit", query = "SELECT e FROM Empresa e WHERE e.nit = :nit")
    , @NamedQuery(name = "Empresa.findByNombre", query = "SELECT e FROM Empresa e WHERE e.nombre = :nombre")
    , @NamedQuery(name = "Empresa.findByDireccion", query = "SELECT e FROM Empresa e WHERE e.direccion = :direccion")
    , @NamedQuery(name = "Empresa.findByCiudad", query = "SELECT e FROM Empresa e WHERE e.ciudad = :ciudad")
    , @NamedQuery(name = "Empresa.findByTelefono", query = "SELECT e FROM Empresa e WHERE e.telefono = :telefono")
    , @NamedQuery(name = "Empresa.findByCorreoElectronico", query = "SELECT e FROM Empresa e WHERE e.correoElectronico = :correoElectronico")
    , @NamedQuery(name = "Empresa.findByNumEmpleados", query = "SELECT e FROM Empresa e WHERE e.numEmpleados = :numEmpleados")
    , @NamedQuery(name = "Empresa.findByTipo", query = "SELECT e FROM Empresa e WHERE e.tipo = :tipo")})
public class Empresa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String nit;
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String nombre;
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String direccion;
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String ciudad;
    @Basic(optional = false)
    @Column(nullable = false, length = 20)
    private String telefono;
    @Basic(optional = false)
    @Column(name = "correo_electronico", nullable = false, length = 45)
    private String correoElectronico;
    @Basic(optional = false)
    @Lob
    @Column(name = "descrip_empresa", nullable = false, length = 65535)
    private String descripEmpresa;
    @Basic(optional = false)
    @Column(name = "num_empleados", nullable = false)
    private int numEmpleados;
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String tipo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empresa1")
    private List<AfiliacionEmpresa> afiliacionEmpresaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empresa1")
    private List<Contrato> contratoList;

    public Empresa() {
    }

    public Empresa(String nit) {
        this.nit = nit;
    }

    public Empresa(String nit, String nombre, String direccion, String ciudad, String telefono, String correoElectronico, String descripEmpresa, int numEmpleados, String tipo) {
        this.nit = nit;
        this.nombre = nombre;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.descripEmpresa = descripEmpresa;
        this.numEmpleados = numEmpleados;
        this.tipo = tipo;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getDescripEmpresa() {
        return descripEmpresa;
    }

    public void setDescripEmpresa(String descripEmpresa) {
        this.descripEmpresa = descripEmpresa;
    }

    public int getNumEmpleados() {
        return numEmpleados;
    }

    public void setNumEmpleados(int numEmpleados) {
        this.numEmpleados = numEmpleados;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @XmlTransient
    public List<AfiliacionEmpresa> getAfiliacionEmpresaList() {
        return afiliacionEmpresaList;
    }

    public void setAfiliacionEmpresaList(List<AfiliacionEmpresa> afiliacionEmpresaList) {
        this.afiliacionEmpresaList = afiliacionEmpresaList;
    }

    @XmlTransient
    public List<Contrato> getContratoList() {
        return contratoList;
    }

    public void setContratoList(List<Contrato> contratoList) {
        this.contratoList = contratoList;
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
        if (!(object instanceof Empresa)) {
            return false;
        }
        Empresa other = (Empresa) object;
        if ((this.nit == null && other.nit != null) || (this.nit != null && !this.nit.equals(other.nit))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Empresa: " + toJson().toString();
    }
       
    public JSONObject toJson() {
    	JSONObject json = new JSONObject();
    	json.put("nit", this.getNit());
    	json.put("nombre", this.getNombre());
    	json.put("direccion", this.getDireccion());
    	json.put("ciudad", this.getCiudad());
    	json.put("telefono", this.getTelefono());
    	json.put("correoElectronico", this.getCorreoElectronico());
    	json.put("descripEmpresa", this.getDescripEmpresa());
    	json.put("numEmpleados", this.getNumEmpleados());
    	json.put("tipo", this.getTipo());
    	JSONArray lista = new JSONArray();
    	List<AfiliacionEmpresa> l = getAfiliacionEmpresaList();
    	int i = 0;
    	while (l.get(i) != null) {
    		lista.put(l.get(i).toJson().getString("afiliacionEmpresaPK"));
    		i++;
    	}
    	json.put("afiliacionEmpresaist", lista);
    	lista = null;
    	List<Contrato> l1 = getContratoList();
    	i = 0;
    	while (l1.get(i) != null) {
    		lista.put(l1.get(i).toJson().getString("contratoPK"));
    		i++;
    	}
    	json.put("contratoList", lista);
    	return json;
    }
    
    public Empresa fromJson(JSONObject json) throws JSONException, ParseException {
    	this.setNit(json.getString("nit"));
    	this.setNombre(json.getString("nombre"));
    	this.setDireccion(json.getString("direccion"));
    	this.setCiudad(json.getString("ciudad"));
    	this.setTelefono(json.getString("telefono"));
    	this.setCorreoElectronico(json.getString("correoElectronico"));
    	this.setDescripEmpresa(json.getString("descripEmpresa"));
    	this.setNumEmpleados(json.getInt("numEmpleados"));
    	this.setNombre(json.getString("tipo"));
    	return this;
    }
    
}
