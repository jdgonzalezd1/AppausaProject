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
public class PartidaPK implements Serializable {

	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
    @Column(nullable = false)
    private int cuenta;
    @Basic(optional = false)
    @Column(nullable = false)
    private int juego;

    public PartidaPK() {
    }

    public PartidaPK(int cuenta, int juego) {
        this.cuenta = cuenta;
        this.juego = juego;
    }

    public int getCuenta() {
        return cuenta;
    }

    public void setCuenta(int cuenta) {
        this.cuenta = cuenta;
    }

    public int getJuego() {
        return juego;
    }

    public void setJuego(int juego) {
        this.juego = juego;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) cuenta;
        hash += (int) juego;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PartidaPK)) {
            return false;
        }
        PartidaPK other = (PartidaPK) object;
        if (this.cuenta != other.cuenta) {
            return false;
        }
        if (this.juego != other.juego) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PartidaPK: " + toJson().toString();
    }
       
    public JSONObject toJson() {
    	JSONObject json = new JSONObject();
    	json.put("cuenta", this.getCuenta());
    	json.put("juego", this.getJuego());
    	return json;
    }
    
    public PartidaPK fromJson(JSONObject json) {
    	this.setCuenta((int) json.getInt("cuenta"));
    	this.setJuego((int) json.getInt("juego"));
    	return this;
    }
    
}
