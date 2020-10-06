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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(catalog = "ingusb_appausa", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Juego.findAll", query = "SELECT j FROM Juego j")
    , @NamedQuery(name = "Juego.findById", query = "SELECT j FROM Juego j WHERE j.id = :id")
    , @NamedQuery(name = "Juego.findByNombre", query = "SELECT j FROM Juego j WHERE j.nombre = :nombre")
    , @NamedQuery(name = "Juego.findByPuntaje", query = "SELECT j FROM Juego j WHERE j.puntaje = :puntaje")
    , @NamedQuery(name = "Juego.findByUrlDescarga", query = "SELECT j FROM Juego j WHERE j.urlDescarga = :urlDescarga")
    , @NamedQuery(name = "Juego.findByDispositivo", query = "SELECT j FROM Juego j WHERE j.dispositivo = :dispositivo")
    , @NamedQuery(name = "Juego.findByLinkApp", query = "SELECT j FROM Juego j WHERE j.linkApp = :linkApp")
    , @NamedQuery(name = "Juego.findByTipoJuegoId", query = "SELECT j FROM Juego j WHERE j.tipoJuego = :tipoJuego")})
public class Juego implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    protected Integer id;
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String nombre;
    @Basic(optional = false)
    @Column(nullable = false)
    private int puntaje;
    @Basic(optional = false)
    @Lob
    @Column(nullable = false, length = 65535)
    private String mecanica;
    @Basic(optional = false)
    @Column(name = "url_descarga", nullable = false, length = 45)
    private String urlDescarga;
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String dispositivo;
    @Column(name = "link_app", length = 45)
    private String linkApp;
    @JoinColumn(name = "tipo_juego_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private TipoJuego tipoJuego;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "juego")
    private List<Partida> partidas;

    public Juego() {
    }

    public Juego(Integer id) {
        this.id = id;
    }

    public Juego(Integer id, String nombre, int puntaje, String mecanica, String urlDescarga, String dispositivo) {
        this.id = id;
        this.nombre = nombre;
        this.puntaje = puntaje;
        this.mecanica = mecanica;
        this.urlDescarga = urlDescarga;
        this.dispositivo = dispositivo;
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

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public String getMecanica() {
        return mecanica;
    }

    public void setMecanica(String mecanica) {
        this.mecanica = mecanica;
    }

    public String getUrlDescarga() {
        return urlDescarga;
    }

    public void setUrlDescarga(String urlDescarga) {
        this.urlDescarga = urlDescarga;
    }

    public String getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(String dispositivo) {
        this.dispositivo = dispositivo;
    }

    public String getLinkApp() {
        return linkApp;
    }

    public void setLinkApp(String linkApp) {
        this.linkApp = linkApp;
    }

    public TipoJuego getTipoJuego() {
        return tipoJuego;
    }

    public void setTipoJuego(TipoJuego tipoJuego) {
        this.tipoJuego = tipoJuego;
    }

    @XmlTransient
    public List<Partida> getPartidas() {
        return partidas;
    }

    public void setPartidas(List<Partida> partidas) {
        this.partidas = partidas;
    }
    
    public Partida addPartida(Partida partida) {
    	getPartidas().add(partida);
        partida.setJuego(this);
        return partida;
    }

    public Partida removePartida(Partida partida) {
    	getPartidas().remove(partida);
        partida.setJuego(null);
        return partida;
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
        if (!(object instanceof Juego)) {
            return false;
        }
        Juego other = (Juego) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Juego: " + toJson().toString();
    }
       
    public JSONObject toJson() {
    	JSONObject json = new JSONObject();
    	json.put("id", this.getId());
    	json.put("nombre", this.getNombre());
    	json.put("puntaje", this.getPuntaje());
    	json.put("mecanica", this.getMecanica());
    	json.put("urlDescarga", this.getUrlDescarga());
    	json.put("dispositivo", this.getDispositivo());
    	json.put("tipoJuego", this.getTipoJuego());
    	return json;
    }
    
    public Juego fromJson(JSONObject json){
    	this.setId((Integer) json.getAsNumber("id"));
    	this.setNombre(json.getAsString("nombre"));
    	this.setPuntaje((int) json.getAsNumber("puntaje"));
    	this.setMecanica(json.getAsString("mecanica"));
    	this.setLinkApp(json.getAsString("linkApp"));
    	this.setTipoJuego((TipoJuego) json.get("tipoJuego"));
    	return this;
    }
    
}
