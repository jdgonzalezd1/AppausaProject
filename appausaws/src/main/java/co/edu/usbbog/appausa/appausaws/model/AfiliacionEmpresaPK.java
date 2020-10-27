/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usbbog.appausa.appausaws.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.json.JSONObject;


/**
 *
 * @author aasanchez
 */
@Embeddable
public class AfiliacionEmpresaPK implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String empresa;
    @Basic(optional = false)
    @Column(nullable = false, length = 17)
    private String entidad;

    public AfiliacionEmpresaPK() {
    }

    public AfiliacionEmpresaPK(String empresa, String entidad) {
        this.empresa = empresa;
        this.entidad = entidad;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (empresa != null ? empresa.hashCode() : 0);
        hash += (entidad != null ? entidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AfiliacionEmpresaPK)) {
            return false;
        }
        AfiliacionEmpresaPK other = (AfiliacionEmpresaPK) object;
        if ((this.empresa == null && other.empresa != null) || (this.empresa != null && !this.empresa.equals(other.empresa))) {
            return false;
        }
        if ((this.entidad == null && other.entidad != null) || (this.entidad != null && !this.entidad.equals(other.entidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AfiliacionEmpresaPK: " + toJson().toString();
    }
       //empresa, entidad
    public JSONObject toJson() {
    	JSONObject json = new JSONObject();
    	json.put("empresa", this.getEmpresa());
    	json.put("entidad", this.getEntidad());
    	return json;
    }
    
    public AfiliacionEmpresaPK fromJson(JSONObject json) {
    	this.setEmpresa(json.getString("empresa"));
    	this.setEntidad(json.getString("entidad"));
    	return this;
    }
}
