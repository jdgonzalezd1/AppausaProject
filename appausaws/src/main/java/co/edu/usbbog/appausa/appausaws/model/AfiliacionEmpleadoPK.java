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

import com.google.gson.Gson;

/**
 *
 * @author aasanchez
 */
@Embeddable
public class AfiliacionEmpleadoPK implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
    @Column(nullable = false)
    private int empleado;
    @Basic(optional = false)
    @Column(nullable = false, length = 17)
    private String entidad;

    public AfiliacionEmpleadoPK() {
    }

    public AfiliacionEmpleadoPK(int empleado, String entidad) {
        this.empleado = empleado;
        this.entidad = entidad;
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
        hash += (int) empleado;
        hash += (entidad != null ? entidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AfiliacionEmpleadoPK)) {
            return false;
        }
        AfiliacionEmpleadoPK other = (AfiliacionEmpleadoPK) object;
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
        return "AfiliacionEmpleadoPK: " + toJson();
    }
       
    public String toJson() {
    	return new Gson().toJson(this,AfiliacionEmpleadoPK.class);
    }
    
}
