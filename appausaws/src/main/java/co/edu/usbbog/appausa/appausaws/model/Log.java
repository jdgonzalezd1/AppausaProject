/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usbbog.appausa.appausaws.model;

import java.io.Serializable;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;



import org.json.JSONObject;

/**
 *
 * @author aasanchez
 */
@Entity
@Table(catalog = "ingusb_appausa", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Log.findAll", query = "SELECT l FROM Log l")
    , @NamedQuery(name = "Log.findById", query = "SELECT l FROM Log l WHERE l.id = :id")
    , @NamedQuery(name = "Log.findByFecha", query = "SELECT l FROM Log l WHERE l.fecha = :fecha")
    , @NamedQuery(name = "Log.findByMensaje", query = "SELECT l FROM Log l WHERE l.mensaje = :mensaje")
    , @NamedQuery(name = "Log.findByEventoNombre", query = "SELECT l FROM Log l WHERE l.eventoNombre = :eventoNombre")})
public class Log implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(nullable = false, name = "fecha", columnDefinition = "DATETIME")
    private LocalDateTime fecha;
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String mensaje;
    @Basic(optional = false)
    @Column(name = "evento_nombre", nullable = false, length = 45)
    private String eventoNombre;
    @JoinColumn(name = "cuenta", referencedColumnName = "empleado", nullable = false)
    @ManyToOne(optional = false)
    private Cuenta cuenta;
    @JoinColumn(name = "tipo_evento", referencedColumnName = "nombre", nullable = false)
    @ManyToOne(optional = false)
    private TipoEvento tipoEvento;

    public Log() {
    }

    public Log(Integer id) {
        this.id = id;
    }

    public Log(Integer id, LocalDateTime fecha, String mensaje, String eventoNombre) {
        this.id = id;
        this.fecha = fecha;
        this.mensaje = mensaje;
        this.eventoNombre = eventoNombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getEventoNombre() {
        return eventoNombre;
    }

    public void setEventoNombre(String eventoNombre) {
        this.eventoNombre = eventoNombre;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public TipoEvento getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(TipoEvento tipoEvento) {
        this.tipoEvento = tipoEvento;
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
        if (!(object instanceof Log)) {
            return false;
        }
        Log other = (Log) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Log: " + toJson().toString();
    }
       
    public JSONObject toJson() {
    	JSONObject json = new JSONObject();
    	json.put("id",this.getId());
    	json.put("fecha",this.getFecha());
    	json.put("mensaje",this.getMensaje());
    	json.put("eventoNombre",this.getEventoNombre());
    	json.put("cuenta",this.getCuenta().toJson().getString("empleado"));
    	json.put("tipoEvento", this.getTipoEvento().toJson().getString("nombre"));
    	return json;
    }
    
    public Log fromJson(JSONObject json) throws ParseException {
    	this.setId((Integer) json.getInt("id"));
    	this.setFecha(LocalDateTime.parse(json.getString("fecha"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    	this.setMensaje(json.getString("mensaje"));
    	this.setEventoNombre(json.getString("eventoNombre"));
    	return this;
    }
    
}
