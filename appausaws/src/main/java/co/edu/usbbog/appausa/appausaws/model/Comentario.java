/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usbbog.appausa.appausaws.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

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
    @NamedQuery(name = "Comentario.findAll", query = "SELECT c FROM Comentario c")
    , @NamedQuery(name = "Comentario.findByCod", query = "SELECT c FROM Comentario c WHERE c.cod = :cod")
    , @NamedQuery(name = "Comentario.findByLeido", query = "SELECT c FROM Comentario c WHERE c.leido = :leido")
    , @NamedQuery(name = "Comentario.findByRespuesta", query = "SELECT c FROM Comentario c WHERE c.respuesta = :respuesta")
    , @NamedQuery(name = "Comentario.findByFecha", query = "SELECT c FROM Comentario c WHERE c.fecha = :fecha")})
public class Comentario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer cod;
    @Basic(optional = false)
    @Lob
    @Column(nullable = false, length = 65535)
    private String contenido;
    @Basic(optional = false)
    @Column(nullable = false)
    private short leido;
    @Column(length = 45)
    private String respuesta;
    @Basic(optional = false)
    @Column(name = "fecha", nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime fecha;
    @JoinColumn(name = "cuenta_empleado", referencedColumnName = "empleado", nullable = false)
    @ManyToOne(optional = false)
    private Cuenta cuentaEmpleado;

    public Comentario() {
    }

    public Comentario(Integer cod) {
        this.cod = cod;
    }

    public Comentario(Integer cod, String contenido, short leido, LocalDateTime fecha) {
        this.cod = cod;
        this.contenido = contenido;
        this.leido = leido;
        this.fecha = fecha;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public short getLeido() {
        return leido;
    }

    public void setLeido(short leido) {
        this.leido = leido;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Cuenta getCuentaEmpleado() {
        return cuentaEmpleado;
    }

    public void setCuentaEmpleado(Cuenta cuentaEmpleado) {
        this.cuentaEmpleado = cuentaEmpleado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cod != null ? cod.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comentario)) {
            return false;
        }
        Comentario other = (Comentario) object;
        if ((this.cod == null && other.cod != null) || (this.cod != null && !this.cod.equals(other.cod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Comentario: " + toJson().toString();
    }
       //cod,contenido,leido,respuesta,fecha,cuentaEmpleado
    public JSONObject toJson() {
    	JSONObject json = new JSONObject();
    	json.put("codigo", this.getCod());
    	json.put("contenido", this.getContenido());
    	json.put("leido", this.getLeido());
    	json.put("respuesta", this.getRespuesta());
    	json.put("fecha", this.getFecha());
    	json.put("cuentaEmpleado", this.getCuentaEmpleado().toJson());
    	return json;    	
    }
    
    public Comentario fromJson(JSONObject json) throws JSONException, ParseException {
    	this.setCod(json.getInt("codigo"));
    	this.setContenido(json.getString("contenido"));
    	this.setLeido((short) json.get("leido"));
    	this.setRespuesta(json.getString("respuesta"));
    	this.setFecha(LocalDateTime.parse(json.getString("fechaInicio"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    	Cuenta c = this.getCuentaEmpleado().fromJson(json.getJSONObject("cuentaEmpleado"));
    	this.setCuentaEmpleado(c);
    	return this;
    	
    }
    
}
