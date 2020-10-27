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
public class ContratoLicenciaPK implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
    @Column(nullable = false)
    private int contrato;
    @Basic(optional = false)
    @Column(name = "contrato_empresa", nullable = false, length = 45)
    private String contratoEmpresa;
    @Basic(optional = false)
    @Column(name = "contrato_usuario", nullable = false)
    private int contratoUsuario;
    @Basic(optional = false)
    @Column(name = "tipo_licencia", nullable = false, length = 45)
    private String tipoLicencia;

    public ContratoLicenciaPK() {
    }

    public ContratoLicenciaPK(int contrato, String contratoEmpresa, int contratoUsuario, String tipoLicencia) {
        this.contrato = contrato;
        this.contratoEmpresa = contratoEmpresa;
        this.contratoUsuario = contratoUsuario;
        this.tipoLicencia = tipoLicencia;
    }

    public int getContrato() {
        return contrato;
    }

    public void setContrato(int contrato) {
        this.contrato = contrato;
    }

    public String getContratoEmpresa() {
        return contratoEmpresa;
    }

    public void setContratoEmpresa(String contratoEmpresa) {
        this.contratoEmpresa = contratoEmpresa;
    }

    public int getContratoUsuario() {
        return contratoUsuario;
    }

    public void setContratoUsuario(int contratoUsuario) {
        this.contratoUsuario = contratoUsuario;
    }

    public String getTipoLicencia() {
        return tipoLicencia;
    }

    public void setTipoLicencia(String tipoLicencia) {
        this.tipoLicencia = tipoLicencia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) contrato;
        hash += (contratoEmpresa != null ? contratoEmpresa.hashCode() : 0);
        hash += (int) contratoUsuario;
        hash += (tipoLicencia != null ? tipoLicencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContratoLicenciaPK)) {
            return false;
        }
        ContratoLicenciaPK other = (ContratoLicenciaPK) object;
        if (this.contrato != other.contrato) {
            return false;
        }
        if ((this.contratoEmpresa == null && other.contratoEmpresa != null) || (this.contratoEmpresa != null && !this.contratoEmpresa.equals(other.contratoEmpresa))) {
            return false;
        }
        if (this.contratoUsuario != other.contratoUsuario) {
            return false;
        }
        if ((this.tipoLicencia == null && other.tipoLicencia != null) || (this.tipoLicencia != null && !this.tipoLicencia.equals(other.tipoLicencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ContratoLicenciaPK: " + toJson().toString();
    }
       //contrato, contratoEmpresa,contratoUsuario,tipoLicencia
    public JSONObject toJson() {
    	JSONObject json = new JSONObject();
    	json.put("contrato", this.getContrato());
    	json.put("contratoEmpresa", this.getContratoEmpresa());
    	json.put("contratoUsuario", this.getContratoUsuario());
    	json.put("tipoLicencia", this.getTipoLicencia());
    	return json;
    }
    
    public ContratoLicenciaPK fromJson(JSONObject json) {
    	this.setContrato(json.getInt("contrato"));
    	this.setContratoEmpresa(json.getString("contratoEmpresa"));
    	this.setContratoUsuario(json.getInt("contratoUsuario"));
    	this.setTipoLicencia(json.getString("tipoLicencia"));
    	return this;
    }
    
}
