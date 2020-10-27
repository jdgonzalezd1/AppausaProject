/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usbbog.appausa.appausaws.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

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
    @NamedQuery(name = "Partida.findAll", query = "SELECT p FROM Partida p")
    , @NamedQuery(name = "Partida.findByCuenta", query = "SELECT p FROM Partida p WHERE p.partidaPK.cuenta = :cuenta")
    , @NamedQuery(name = "Partida.findByJuego", query = "SELECT p FROM Partida p WHERE p.partidaPK.juego = :juego")
    , @NamedQuery(name = "Partida.findByInicio", query = "SELECT p FROM Partida p WHERE p.inicio = :inicio")
    , @NamedQuery(name = "Partida.findByFin", query = "SELECT p FROM Partida p WHERE p.fin = :fin")
    , @NamedQuery(name = "Partida.findByPuntaje", query = "SELECT p FROM Partida p WHERE p.puntaje = :puntaje")
    , @NamedQuery(name = "Partida.findByDuracion", query = "SELECT p FROM Partida p WHERE p.duracion = :duracion")})
public class Partida implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PartidaPK partidaPK;
    @Basic(optional = false)
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date inicio;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fin;
    @Basic(optional = false)
    @Column(nullable = false)
    private int puntaje;
    @Temporal(TemporalType.TIME)
    private Date duracion;
    @JoinColumn(name = "cuenta", referencedColumnName = "empleado", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Cuenta cuenta;
    @JoinColumn(name = "juego", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Juego juego;

    public Partida() {
    }

    public Partida(PartidaPK partidaPK) {
        this.partidaPK = partidaPK;
    }

    public Partida(PartidaPK partidaPK, Date inicio, int puntaje) {
        this.partidaPK = partidaPK;
        this.inicio = inicio;
        this.puntaje = puntaje;
    }

    public Partida(int cuenta, int juego) {
        this.partidaPK = new PartidaPK(cuenta, juego);
    }

    public PartidaPK getPartidaPK() {
        return partidaPK;
    }

    public void setPartidaPK(PartidaPK partidaPK) {
        this.partidaPK = partidaPK;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public Date getDuracion() {
        return duracion;
    }

    public void setDuracion(Date duracion) {
        this.duracion = duracion;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public Juego getJuego() {
        return juego;
    }

    public void setJuego(Juego juego) {
        this.juego = juego;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (partidaPK != null ? partidaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Partida)) {
            return false;
        }
        Partida other = (Partida) object;
        if ((this.partidaPK == null && other.partidaPK != null) || (this.partidaPK != null && !this.partidaPK.equals(other.partidaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Partida: " + toJson().toString();
    }
       
    public JSONObject toJson() {
    	JSONObject json = new JSONObject();
    	json.put("partidaPk", this.getPartidaPK().toJson());
    	json.put("inicio", this.getInicio());
    	json.put("fin", this.getFin());
    	json.put("puntaje", this.getPuntaje());
    	json.put("duracion", this.getDuracion());
    	json.put("cuenta", this.getCuenta().toJson());
    	json.put("juego", this.getJuego().toJson());
    	return json;
    }
    
    public Partida fromJson(JSONObject json) throws ParseException {
    	PartidaPK pk = this.getPartidaPK().fromJson(json.getJSONObject("partidaPK"));
    	this.setPartidaPK(pk);
    	Date f = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(json.getString("inicio"));
    	this.setInicio(f);
    	f = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(json.getString("fin"));
    	this.setFin(f);
    	this.setPuntaje((int) json.getInt("puntaje"));
    	f = new SimpleDateFormat("HH:mm:ss").parse(json.getString("duracion"));
    	this.setDuracion(f);
    	Cuenta c = this.getCuenta().fromJson(json.getJSONObject("cuenta"));
    	this.setCuenta(c);
    	Juego j = this.getJuego().fromJson(json.getJSONObject("juego"));
    	this.setJuego(j);
    	return this;
    }
    
}
