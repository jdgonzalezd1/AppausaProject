/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usbbog.appausa.appausaws.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

/**
 *
 * @author aasanchez
 */
@Entity
@Table(catalog = "ingusb_appausa", schema = "")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Contrato.findAll", query = "SELECT c FROM Contrato c"),
		@NamedQuery(name = "Contrato.findByNumContrato", query = "SELECT c FROM Contrato c WHERE c.contratoPK.numContrato = :numContrato"),
		@NamedQuery(name = "Contrato.findByFechaInicio", query = "SELECT c FROM Contrato c WHERE c.fechaInicio = :fechaInicio"),
		@NamedQuery(name = "Contrato.findByFechaFin", query = "SELECT c FROM Contrato c WHERE c.fechaFin = :fechaFin"),
		@NamedQuery(name = "Contrato.findBySueldo", query = "SELECT c FROM Contrato c WHERE c.sueldo = :sueldo"),
		@NamedQuery(name = "Contrato.findByCargo", query = "SELECT c FROM Contrato c WHERE c.cargo = :cargo"),
		@NamedQuery(name = "Contrato.findByRepresentante", query = "SELECT c FROM Contrato c WHERE c.representante = :representante"),
		@NamedQuery(name = "Contrato.findByEmpresa", query = "SELECT c FROM Contrato c WHERE c.contratoPK.empresa = :empresa"),
		@NamedQuery(name = "Contrato.findByEmpleado", query = "SELECT c FROM Contrato c WHERE c.contratoPK.empleado = :empleado") })
public class Contrato implements Serializable {

	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected ContratoPK contratoPK;
	@Basic(optional = false)
	@Column(name = "fecha_inicio", nullable = false, columnDefinition = "DATE")
	private LocalDate fechaInicio;
	@Column(name = "fecha_fin", nullable = true, columnDefinition = "DATE")
	private LocalDate fechaFin;
	@Basic(optional = false)
	@Column(nullable = false)
	private long sueldo;
	@Basic(optional = false)
	@Lob
	@Column(nullable = false, length = 65535)
	private String funciones;
	@Basic(optional = false)
	@Column(nullable = false, length = 45)
	private String cargo;
	@Basic(optional = false)
	@Column(nullable = false)
	private short representante;
	@JoinColumn(name = "empleado", referencedColumnName = "num_documento", nullable = false, insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private Empleado empleado1;
	@JoinColumn(name = "empresa", referencedColumnName = "nit", nullable = false, insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private Empresa empresa1;
	@JoinColumn(name = "nivel_riesgo", referencedColumnName = "nombre", nullable = false)
	@ManyToOne(optional = false)
	private NivelRiesgo nivelRiesgo;
	@JoinColumn(name = "tipo_contrato", referencedColumnName = "nombre", nullable = false)
	@ManyToOne(optional = false)
	private TipoContrato tipoContrato;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "contrato")
	private List<AfiliacionContrato> afiliacionContratoList;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "contrato1")
	private List<ContratoLicencia> contratoLicenciaList;

	public Contrato() {
	}

	public Contrato(ContratoPK contratoPK) {
		this.contratoPK = contratoPK;
	}

	public Contrato(ContratoPK contratoPK, LocalDate fechaInicio, long sueldo, String funciones, String cargo,
			short representante) {
		this.contratoPK = contratoPK;
		this.fechaInicio = fechaInicio;
		this.sueldo = sueldo;
		this.funciones = funciones;
		this.cargo = cargo;
		this.representante = representante;
	}

	public Contrato(int numContrato, String empresa, int empleado) {
		this.contratoPK = new ContratoPK(numContrato, empresa, empleado);
	}

	public ContratoPK getContratoPK() {
		return contratoPK;
	}

	public void setContratoPK(ContratoPK contratoPK) {
		this.contratoPK = contratoPK;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

	public long getSueldo() {
		return sueldo;
	}

	public void setSueldo(long sueldo) {
		this.sueldo = sueldo;
	}

	public String getFunciones() {
		return funciones;
	}

	public void setFunciones(String funciones) {
		this.funciones = funciones;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public short getRepresentante() {
		return representante;
	}

	public void setRepresentante(short representante) {
		this.representante = representante;
	}

	public Empleado getEmpleado1() {
		return empleado1;
	}

	public void setEmpleado1(Empleado empleado1) {
		this.empleado1 = empleado1;
	}

	public Empresa getEmpresa1() {
		return empresa1;
	}

	public void setEmpresa1(Empresa empresa1) {
		this.empresa1 = empresa1;
	}

	public NivelRiesgo getNivelRiesgo() {
		return nivelRiesgo;
	}

	public void setNivelRiesgo(NivelRiesgo nivelRiesgo) {
		this.nivelRiesgo = nivelRiesgo;
	}

	public TipoContrato getTipoContrato() {
		return tipoContrato;
	}

	public void setTipoContrato(TipoContrato tipoContrato) {
		this.tipoContrato = tipoContrato;
	}

	@XmlTransient
	public List<AfiliacionContrato> getAfiliacionContratoList() {
		return afiliacionContratoList;
	}

	public void setAfiliacionContratoList(List<AfiliacionContrato> afiliacionContratoList) {
		this.afiliacionContratoList = afiliacionContratoList;
	}

	@XmlTransient
	public List<ContratoLicencia> getContratoLicenciaList() {
		return contratoLicenciaList;
	}

	public void setContratoLicenciaList(List<ContratoLicencia> contratoLicenciaList) {
		this.contratoLicenciaList = contratoLicenciaList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (contratoPK != null ? contratoPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Contrato)) {
			return false;
		}
		Contrato other = (Contrato) object;
		if ((this.contratoPK == null && other.contratoPK != null)
				|| (this.contratoPK != null && !this.contratoPK.equals(other.contratoPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Contrato: " + toJson().toString();
	}

	// contratoPK,fechaInicio,fechaFin,sueldo,funciones,cargo,representante,empleado1,empresa1,nivelRiesgo,
	// tipoContrato,afiliacionContratoList,contratoLicenciaList
	public JSONObject toJson() {
		JSONObject json = new JSONObject();
		json.put("contratoPK", this.getContratoPK().toJson());
		json.put("fechaInicio", this.getFechaInicio());
		json.put("fechaFin", this.getFechaFin());
		json.put("sueldo", this.getSueldo());
		json.put("funciones", this.getFunciones());
		json.put("cargo", this.getCargo());
		json.put("representantes", this.getRepresentante());
		json.put("empleado", this.getEmpleado1().toJson());
		json.put("empresa", this.getEmpresa1().toJson());
		json.put("nivelRiesgo", this.getNivelRiesgo().toJson());
		json.put("tipoContrato", this.getTipoContrato().toJson());
		JSONArray lista = new JSONArray();
		List<AfiliacionContrato> l = getAfiliacionContratoList();
		int i = 0;
		while (l.get(i) != null) {
			lista.put(l.get(i).toJson().getString("afiliacionContratoPK"));
			i++;
		}
		json.put("listaAfiliacionContrato", lista);
		lista = null;
		List<ContratoLicencia> l1 = getContratoLicenciaList();
		i = 0;
		while (l1.get(i) != null) {
			lista.put(l.get(i).toJson().getString("contratoLicenciaPK"));
			i++;
		}
		json.put("listaContratoLicencia", lista);
		return json;
	}

	public Contrato fromJson(JSONObject json) throws JSONException, ParseException {
		ContratoPK pk = this.getContratoPK().fromJson(json.getJSONObject("contratoPK"));
		this.setContratoPK(pk);
		this.setFechaInicio(LocalDate.parse(json.getString("fechaInicio"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		if (json.getString("fechaInicio") != "" && json.getString("fechaInicio") != null) {
			this.setFechaFin(LocalDate.parse(json.getString("fechaFin"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		}
		this.setSueldo(json.getLong("sueldo"));
		this.setFunciones(json.getString("funciones"));
		this.setCargo(json.getString("cargo"));
		this.setRepresentante((short) json.get("representantes"));
		Empleado emp = this.getEmpleado1().fromJson(json.getJSONObject("empleado"));
		this.setEmpleado1(emp);
		Empresa em = this.getEmpresa1().fromJson(json.getJSONObject("empresa"));
		this.setEmpresa1((Empresa) json.get("empresa"));
		NivelRiesgo nr = this.getNivelRiesgo().fromJson(json.getJSONObject("nivelRiesgo"));
		this.setNivelRiesgo(nr);
		TipoContrato tp = this.getTipoContrato().fromJson(json.getJSONObject("tipoContrato"));
		this.setTipoContrato(tp);
		return this;
	}

}
