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
public class AfeccionEmpleadoPK implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
    @Column(name = "afeccion_medica", nullable = false, length = 45)
    private String afeccionMedica;
    @Basic(optional = false)
    @Column(nullable = false)
    private int empleado;

    public AfeccionEmpleadoPK() {
    }

    public AfeccionEmpleadoPK(String afeccionMedica, int empleado) {
        this.afeccionMedica = afeccionMedica;
        this.empleado = empleado;
    }

    public String getAfeccionMedica() {
        return afeccionMedica;
    }

    public void setAfeccionMedica(String afeccionMedica) {
        this.afeccionMedica = afeccionMedica;
    }

    public int getEmpleado() {
        return empleado;
    }

    public void setEmpleado(int empleado) {
        this.empleado = empleado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (afeccionMedica != null ? afeccionMedica.hashCode() : 0);
        hash += (int) empleado;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AfeccionEmpleadoPK)) {
            return false;
        }
        AfeccionEmpleadoPK other = (AfeccionEmpleadoPK) object;
        if ((this.afeccionMedica == null && other.afeccionMedica != null) || (this.afeccionMedica != null && !this.afeccionMedica.equals(other.afeccionMedica))) {
            return false;
        }
        if (this.empleado != other.empleado) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AfeccionEmpleadoPK: " + toJson().toString();
    }
    //afeccionMedica, empleado   
    public JSONObject toJson() {
    	JSONObject json = new JSONObject();
    	json.put("afeccionMedica", this.getAfeccionMedica());
    	json.put("empleado", this.getEmpleado());
    	return json;
    }
    
    public AfeccionEmpleadoPK fromJson(JSONObject json) {
    	this.setAfeccionMedica(json.getString("afeccionMedica"));
    	this.setEmpleado(json.getInt("empleado"));
    	return this;
    }
    
}
