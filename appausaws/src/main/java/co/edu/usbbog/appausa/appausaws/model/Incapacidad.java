/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usbbog.appausa.appausaws.model;

import java.io.Serializable;
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
import com.google.gson.Gson;

/**
 *
 * @author aasanchez
 */
@Entity
@Table(catalog = "ingusb_appausa", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Incapacidad.findAll", query = "SELECT i FROM Incapacidad i")
    , @NamedQuery(name = "Incapacidad.findByCod", query = "SELECT i FROM Incapacidad i WHERE i.incapacidadPK.cod = :cod")
    , @NamedQuery(name = "Incapacidad.findByInicioIncapacidad", query = "SELECT i FROM Incapacidad i WHERE i.inicioIncapacidad = :inicioIncapacidad")
    , @NamedQuery(name = "Incapacidad.findByFinIncapacidad", query = "SELECT i FROM Incapacidad i WHERE i.finIncapacidad = :finIncapacidad")
    , @NamedQuery(name = "Incapacidad.findByConsultaMedica", query = "SELECT i FROM Incapacidad i WHERE i.incapacidadPK.consultaMedica = :consultaMedica")})
public class Incapacidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    protected Integer cod;
    @Basic(optional = false)
    @Column(name = "inicio_incapacidad", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date inicioIncapacidad;
    @Basic(optional = false)
    @Column(name = "fin_incapacidad", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date finIncapacidad;
    @Basic(optional = false)
    @Lob
    @Column(nullable = false, length = 65535)
    private String indicaciones;
    @JoinColumn(name = "consulta_medica", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private ConsultaMedica consultaMedica;
    @JoinColumn(name = "tipo_incapacidad", referencedColumnName = "nombre", nullable = false)
    @ManyToOne(optional = false)
    private TipoIncapacidad tipoIncapacidad;

    public Incapacidad() {
    }

    public Incapacidad(Integer cod) {
        this.cod = cod;
    }

    public Incapacidad(Integer cod, Date inicioIncapacidad, Date finIncapacidad, String indicaciones) {
        this.cod = cod;
        this.inicioIncapacidad = inicioIncapacidad;
        this.finIncapacidad = finIncapacidad;
        this.indicaciones = indicaciones;
    }


    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public Date getInicioIncapacidad() {
        return inicioIncapacidad;
    }

    public void setInicioIncapacidad(Date inicioIncapacidad) {
        this.inicioIncapacidad = inicioIncapacidad;
    }

    public Date getFinIncapacidad() {
        return finIncapacidad;
    }

    public void setFinIncapacidad(Date finIncapacidad) {
        this.finIncapacidad = finIncapacidad;
    }

    public String getIndicaciones() {
        return indicaciones;
    }

    public void setIndicaciones(String indicaciones) {
        this.indicaciones = indicaciones;
    }

    public ConsultaMedica getConsultaMedica() {
        return consultaMedica;
    }

    public void setConsultaMedica(ConsultaMedica consultaMedica) {
        this.consultaMedica = consultaMedica;
    }

    public TipoIncapacidad getTipoIncapacidad() {
        return tipoIncapacidad;
    }

    public void setTipoIncapacidad(TipoIncapacidad tipoIncapacidad) {
        this.tipoIncapacidad = tipoIncapacidad;
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
        if (!(object instanceof Incapacidad)) {
            return false;
        }
        Incapacidad other = (Incapacidad) object;
        if ((this.cod == null && other.cod != null) || (this.cod != null && !this.cod.equals(other.cod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Incapacidad: " + toJson();
    }
       
    public String toJson() {
    	return new Gson().toJson(this,Incapacidad.class);
    }
    
}
