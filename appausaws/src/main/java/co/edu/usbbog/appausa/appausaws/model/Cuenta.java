/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usbbog.appausa.appausaws.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.google.gson.Gson;

/**
 *
 * @author aasanchez
 */
@Entity
@Table(catalog = "ingusb_appausa", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"username"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cuenta.findAll", query = "SELECT c FROM Cuenta c")
    , @NamedQuery(name = "Cuenta.findByEmpleado", query = "SELECT c FROM Cuenta c WHERE c.empleado = :empleado")
    , @NamedQuery(name = "Cuenta.findByUsername", query = "SELECT c FROM Cuenta c WHERE c.username = :username")
    , @NamedQuery(name = "Cuenta.findByContrasenia", query = "SELECT c FROM Cuenta c WHERE c.contrasenia = :contrasenia")
    , @NamedQuery(name = "Cuenta.findByUltimoAcceso", query = "SELECT c FROM Cuenta c WHERE c.ultimoAcceso = :ultimoAcceso")
    , @NamedQuery(name = "Cuenta.findByPuntajeTotal", query = "SELECT c FROM Cuenta c WHERE c.puntajeTotal = :puntajeTotal")
    , @NamedQuery(name = "Cuenta.findByPuntajeMes", query = "SELECT c FROM Cuenta c WHERE c.puntajeMes = :puntajeMes")
    , @NamedQuery(name = "Cuenta.findByTiempoTotal", query = "SELECT c FROM Cuenta c WHERE c.tiempoTotal = :tiempoTotal")
    , @NamedQuery(name = "Cuenta.findByTiempoMes", query = "SELECT c FROM Cuenta c WHERE c.tiempoMes = :tiempoMes")})
public class Cuenta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer empleado;
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String username;
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String contrasenia;
    @Basic(optional = false)
    @Column(name = "ultimo_acceso", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimoAcceso;
    @Basic(optional = false)
    @Column(name = "puntaje_total", nullable = false, length = 45)
    private String puntajeTotal;
    @Basic(optional = false)
    @Column(name = "puntaje_mes", nullable = false, length = 45)
    private String puntajeMes;
    @Basic(optional = false)
    @Column(name = "tiempo_total", nullable = false)
    @Temporal(TemporalType.TIME)
    private Date tiempoTotal;
    @Basic(optional = false)
    @Column(name = "tiempo_mes", nullable = false)
    @Temporal(TemporalType.TIME)
    private Date tiempoMes;
    @JoinTable(name = "rol_cuenta", joinColumns = {
        @JoinColumn(name = "cuenta_empleado", referencedColumnName = "empleado", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "rol", referencedColumnName = "nombre", nullable = false)})
    @ManyToMany
    private List<Rol> roles;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cuenta")
    private List<Log> logs;
    @JoinColumn(name = "empleado", referencedColumnName = "num_documento", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Empleado empleado1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cuentaEmpleado")
    private List<Comentario> comentarioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cuenta")
    private List<Partida> partidas;

    public Cuenta() {
    }

    public Cuenta(Integer empleado) {
        this.empleado = empleado;
    }

    public Cuenta(Integer empleado, String username, String contrasenia, Date ultimoAcceso, String puntajeTotal, String puntajeMes, Date tiempoTotal, Date tiempoMes) {
        this.empleado = empleado;
        this.username = username;
        this.contrasenia = contrasenia;
        this.ultimoAcceso = ultimoAcceso;
        this.puntajeTotal = puntajeTotal;
        this.puntajeMes = puntajeMes;
        this.tiempoTotal = tiempoTotal;
        this.tiempoMes = tiempoMes;
    }

    public Integer getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Integer empleado) {
        this.empleado = empleado;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Date getUltimoAcceso() {
        return ultimoAcceso;
    }

    public void setUltimoAcceso(Date ultimoAcceso) {
        this.ultimoAcceso = ultimoAcceso;
    }

    public String getPuntajeTotal() {
        return puntajeTotal;
    }

    public void setPuntajeTotal(String puntajeTotal) {
        this.puntajeTotal = puntajeTotal;
    }

    public String getPuntajeMes() {
        return puntajeMes;
    }

    public void setPuntajeMes(String puntajeMes) {
        this.puntajeMes = puntajeMes;
    }

    public Date getTiempoTotal() {
        return tiempoTotal;
    }

    public void setTiempoTotal(Date tiempoTotal) {
        this.tiempoTotal = tiempoTotal;
    }

    public Date getTiempoMes() {
        return tiempoMes;
    }

    public void setTiempoMes(Date tiempoMes) {
        this.tiempoMes = tiempoMes;
    }

    @XmlTransient
    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }

    @XmlTransient
    public List<Log> getLogs() {
        return logs;
    }

    public void setLogs(List<Log> logs) {
        this.logs = logs;
    }
    
    public Log addLog(Log log) {
    	getLogs().add(log);
        log.setCuenta(this);
        return log;
    }

    public Log removeLog(Log log) {
    	getLogs().remove(log);
        log.setCuenta(null);
        return log;
    }

    public Empleado getEmpleado1() {
        return empleado1;
    }

    public void setEmpleado1(Empleado empleado1) {
        this.empleado1 = empleado1;
    }

    @XmlTransient
    public List<Comentario> getComentarioList() {
        return comentarioList;
    }

    public void setComentarioList(List<Comentario> comentarioList) {
        this.comentarioList = comentarioList;
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
        partida.setCuenta(this);
        return partida;
    }

    public Partida removePartida(Partida partida) {
    	getPartidas().remove(partida);
        partida.setCuenta(null);
        return partida;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (empleado != null ? empleado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cuenta)) {
            return false;
        }
        Cuenta other = (Cuenta) object;
        if ((this.empleado == null && other.empleado != null) || (this.empleado != null && !this.empleado.equals(other.empleado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Cuenta: " + toJson();
    }
       
    public String toJson() {
    	return new Gson().toJson(this,Cuenta.class);
    }
    
}
