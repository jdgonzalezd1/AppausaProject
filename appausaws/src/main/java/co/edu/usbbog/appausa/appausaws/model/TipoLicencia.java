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
import org.json.JSONArray;
import org.json.JSONObject;


/**
 *
 * @author aasanchez
 */
@Entity
@Table(name = "tipo_licencia", catalog = "ingusb_appausa", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoLicencia.findAll", query = "SELECT t FROM TipoLicencia t")
    , @NamedQuery(name = "TipoLicencia.findByNombre", query = "SELECT t FROM TipoLicencia t WHERE t.nombre = :nombre")
    , @NamedQuery(name = "TipoLicencia.findByDescripcion", query = "SELECT t FROM TipoLicencia t WHERE t.descripcion = :descripcion")
    , @NamedQuery(name = "TipoLicencia.findByRemunerada", query = "SELECT t FROM TipoLicencia t WHERE t.remunerada = :remunerada")
    , @NamedQuery(name = "TipoLicencia.findByMinimoDia", query = "SELECT t FROM TipoLicencia t WHERE t.minimoDia = :minimoDia")})
public class TipoLicencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String nombre;
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String descripcion;
    @Basic(optional = false)
    @Column(nullable = false)
    private short remunerada;
    @Column(name = "minimo_dia")
    private Integer minimoDia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoLicencia")
    private List<ContratoLicencia> contratosLicencias;

    public TipoLicencia() {
    }

    public TipoLicencia(String nombre) {
        this.nombre = nombre;
    }

    public TipoLicencia(String nombre, String descripcion, short remunerada) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.remunerada = remunerada;
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

    public short getRemunerada() {
        return remunerada;
    }

    public void setRemunerada(short remunerada) {
        this.remunerada = remunerada;
    }

    public Integer getMinimoDia() {
        return minimoDia;
    }

    public void setMinimoDia(Integer minimoDia) {
        this.minimoDia = minimoDia;
    }

    @XmlTransient
    public List<ContratoLicencia> getContratosLicencias() {
        return contratosLicencias;
    }

    public void setContratosLicencias(List<ContratoLicencia> contratosLicencias) {
        this.contratosLicencias = contratosLicencias;
    }
    
    public ContratoLicencia addContratoLicencia(ContratoLicencia contratoLicencia) {
    	getContratosLicencias().add(contratoLicencia);
    	contratoLicencia.setTipoLicencia(this);
        return contratoLicencia;
    }

    public ContratoLicencia removeContratoLicencia(ContratoLicencia contratoLicencia) {
    	getContratosLicencias().remove(contratoLicencia);
    	contratoLicencia.setTipoLicencia(null);
        return contratoLicencia;
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
        if (!(object instanceof TipoLicencia)) {
            return false;
        }
        TipoLicencia other = (TipoLicencia) object;
        if ((this.nombre == null && other.nombre != null) || (this.nombre != null && !this.nombre.equals(other.nombre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TipoLicencia: " + toJson().toString();
    }
       
    public JSONObject toJson() {
    	JSONObject json = new JSONObject();
    	json.put("nombre", this.getNombre());
    	json.put("descripcion", this.getDescripcion());
    	json.put("remunerada", this.getRemunerada());
    	JSONArray lista = new JSONArray();
    	List<ContratoLicencia> l = this.getContratosLicencias();
    	l.forEach((elemento) -> lista.put(elemento.toJson().getString("contratoLicenciaPK")));
    	json.put("contratosLicencia", lista);
    	return json;
    }
    
    public TipoLicencia fromJson(JSONObject json){
    	this.setNombre(json.getString("nombre"));
    	this.setDescripcion(json.getString("descripcion"));
    	this.setRemunerada((short) json.getInt("remunerada"));
    	return this;
    }
    
}
