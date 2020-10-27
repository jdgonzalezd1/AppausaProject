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
public class AfiliacionContratoPK implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
    @Column(name = "num_contrato", nullable = false)
    private int numContrato;
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String empresa;
    @Basic(optional = false)
    @Column(nullable = false)
    private int empleado;
    @Basic(optional = false)
    @Column(nullable = false, length = 17)
    private String entidad;

    public AfiliacionContratoPK() {
    }

    public AfiliacionContratoPK(int numContrato, String empresa, int empleado, String entidad) {
        this.numContrato = numContrato;
        this.empresa = empresa;
        this.empleado = empleado;
        this.entidad = entidad;
    }

    public int getNumContrato() {
        return numContrato;
    }

    public void setNumContrato(int numContrato) {
        this.numContrato = numContrato;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public int getEmpleado() {
        return empleado;
    }

    public void setEmpleado(int empleado) {
        this.empleado = empleado;
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
        hash += (int) numContrato;
        hash += (empresa != null ? empresa.hashCode() : 0);
        hash += (int) empleado;
        hash += (entidad != null ? entidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AfiliacionContratoPK)) {
            return false;
        }
        AfiliacionContratoPK other = (AfiliacionContratoPK) object;
        if (this.numContrato != other.numContrato) {
            return false;
        }
        if ((this.empresa == null && other.empresa != null) || (this.empresa != null && !this.empresa.equals(other.empresa))) {
            return false;
        }
        if (this.empleado != other.empleado) {
            return false;
        }
        if ((this.entidad == null && other.entidad != null) || (this.entidad != null && !this.entidad.equals(other.entidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AfiliacionContratoPK: " + toJson().toString();
    }
    //numContrato, empresa, empleado, entidad
    public JSONObject toJson() {
    	JSONObject json = new JSONObject();
    	json.put("numContrato", this.getNumContrato());
    	json.put("empresa", this.getEmpresa());
    	json.put("empleado", this.getEmpleado());
    	json.put("entidad", this.getEntidad());
    	return json;
    }
    
    public AfiliacionContratoPK fromJson(JSONObject json) {
    	this.setNumContrato(json.getInt("numContrato"));
    	this.setEmpresa(json.getString("empresa"));
    	this.setEmpleado(json.getInt("empleado"));
    	this.setEntidad(json.getString("entidad"));
    	return this;
    }
    
}
