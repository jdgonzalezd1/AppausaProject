/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usbbog.appausa.appausaws.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

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
@NamedQueries({
    @NamedQuery(name = "Empleado.findAll", query = "SELECT e FROM Empleado e")
    , @NamedQuery(name = "Empleado.findByNumDocumento", query = "SELECT e FROM Empleado e WHERE e.numDocumento = :numDocumento")
    , @NamedQuery(name = "Empleado.findByTipoDocumento", query = "SELECT e FROM Empleado e WHERE e.tipoDocumento = :tipoDocumento")
    , @NamedQuery(name = "Empleado.findByNombres", query = "SELECT e FROM Empleado e WHERE e.nombres = :nombres")
    , @NamedQuery(name = "Empleado.findByApellidos", query = "SELECT e FROM Empleado e WHERE e.apellidos = :apellidos")
    , @NamedQuery(name = "Empleado.findByFechaNacimiento", query = "SELECT e FROM Empleado e WHERE e.fechaNacimiento = :fechaNacimiento")
    , @NamedQuery(name = "Empleado.findByEdad", query = "SELECT e FROM Empleado e WHERE e.edad = :edad")
    , @NamedQuery(name = "Empleado.findByCorreoElectronico", query = "SELECT e FROM Empleado e WHERE e.correoElectronico = :correoElectronico")
    , @NamedQuery(name = "Empleado.findByTelefono", query = "SELECT e FROM Empleado e WHERE e.telefono = :telefono")
    , @NamedQuery(name = "Empleado.findByDireccion", query = "SELECT e FROM Empleado e WHERE e.direccion = :direccion")
    , @NamedQuery(name = "Empleado.findByCiudad", query = "SELECT e FROM Empleado e WHERE e.ciudad = :ciudad")
    , @NamedQuery(name = "Empleado.findByNacionalidad", query = "SELECT e FROM Empleado e WHERE e.nacionalidad = :nacionalidad")})
public class Empleado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "num_documento", nullable = false)
    private Integer numDocumento;
    @Basic(optional = false)
    @Column(name = "tipo_documento", nullable = false, length = 45)
    private String tipoDocumento;
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String nombres;
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String apellidos;
    @Basic(optional = false)
    @Column(name = "fecha_nacimiento", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Basic(optional = false)
    @Column(nullable = false)
    private int edad;
    @Basic(optional = false)
    @Column(name = "correo_electronico", nullable = false, length = 45)
    private String correoElectronico;
    @Basic(optional = false)
    @Column(nullable = false)
    private int telefono;
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String direccion;
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String ciudad;
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String nacionalidad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleado1")
    private List<AfiliacionEmpleado> afiliacionEmpleadoList;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "empleado1")
    private Cuenta cuenta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleado")
    private List<ConsultaMedica> consultaMedicaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleado1")
    private List<Contrato> contratoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleado1")
    private List<AfeccionEmpleado> afeccionEmpleadoList;

    public Empleado() {
    }

    public Empleado(Integer numDocumento) {
        this.numDocumento = numDocumento;
    }

    public Empleado(Integer numDocumento, String tipoDocumento, String nombres, String apellidos, Date fechaNacimiento, int edad, String correoElectronico, int telefono, String direccion, String ciudad, String nacionalidad) {
        this.numDocumento = numDocumento;
        this.tipoDocumento = tipoDocumento;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.edad = edad;
        this.correoElectronico = correoElectronico;
        this.telefono = telefono;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.nacionalidad = nacionalidad;
    }

    public Integer getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(Integer numDocumento) {
        this.numDocumento = numDocumento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    @XmlTransient
    public List<AfiliacionEmpleado> getAfiliacionEmpleadoList() {
        return afiliacionEmpleadoList;
    }

    public void setAfiliacionEmpleadoList(List<AfiliacionEmpleado> afiliacionEmpleadoList) {
        this.afiliacionEmpleadoList = afiliacionEmpleadoList;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    @XmlTransient
    public List<ConsultaMedica> getConsultaMedicaList() {
        return consultaMedicaList;
    }

    public void setConsultaMedicaList(List<ConsultaMedica> consultaMedicaList) {
        this.consultaMedicaList = consultaMedicaList;
    }

    @XmlTransient
    public List<Contrato> getContratoList() {
        return contratoList;
    }

    public void setContratoList(List<Contrato> contratoList) {
        this.contratoList = contratoList;
    }

    @XmlTransient
    public List<AfeccionEmpleado> getAfeccionEmpleadoList() {
        return afeccionEmpleadoList;
    }

    public void setAfeccionEmpleadoList(List<AfeccionEmpleado> afeccionEmpleadoList) {
        this.afeccionEmpleadoList = afeccionEmpleadoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numDocumento != null ? numDocumento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empleado)) {
            return false;
        }
        Empleado other = (Empleado) object;
        if ((this.numDocumento == null && other.numDocumento != null) || (this.numDocumento != null && !this.numDocumento.equals(other.numDocumento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Empleado: " +  toJson().toString();
    }
       
    public JSONObject toJson() {
    	JSONObject json = new JSONObject();
    	json.put("numDocumento", this.getNumDocumento());
    	json.put("tipoDocumento", this.getTipoDocumento());
    	json.put("nombres", this.getNombres());
    	json.put("apellidos", this.getApellidos());
    	json.put("fechaNacimiento", this.getFechaNacimiento());
    	json.put("edad", this.getEdad());
    	json.put("correoElectronico", this.getCorreoElectronico());
    	json.put("telefono", this.getTelefono());
    	json.put("direccion", this.getDireccion());
    	json.put("ciudad", this.getCiudad());
    	json.put("nacionalidad", this.getNacionalidad());
    	json.put("cuenta", this.getCuenta().toJson());
    	//FK
    	return json;
    }
    
    public Empleado fromJson(JSONObject json) throws JSONException, ParseException {
    	this.setNumDocumento(json.getInt("numDocumento"));
    	this.setTipoDocumento(json.getString("tipoDocumento"));
    	this.setNombres(json.getString("nombres"));
    	this.setApellidos(json.getString("apellidos"));
    	Date f = new SimpleDateFormat("dd/MM/yyyy").parse(json.getString("fechaNacimiento"));
    	this.setFechaNacimiento(f);
    	this.setEdad(json.getInt("edad"));
    	this.setCorreoElectronico(json.getString("correoElectronico"));
    	this.setTelefono(json.getInt("telefono"));
    	this.setDireccion(json.getString("direccion"));
    	this.setCiudad(json.getString("ciudad"));
    	this.setNacionalidad(json.getString("nacionalidad"));
<<<<<<< HEAD
<<<<<<< HEAD
    	Cuenta c = this.getCuenta().fromJson(json.getJSONObject("cuenta"));
    	this.setCuenta(c);
    	ArrayList<AfeccionEmpleado> list = new ArrayList<AfeccionEmpleado>();     
    	JSONArray jsonArray = json.getJSONArray("afeccionEmpleadoList"); 
    	int i = 0;
    	while (jsonArray.get(i) != null) {
    		AfeccionEmpleado ae = null;
    		ae.fromJson(jsonArray.getJSONObject(i));
    	    list.add(ae);
    	    i++;
    	   } 
    	this.setAfeccionEmpleadoList(list);
    	ArrayList<AfiliacionEmpleado> list1 = new ArrayList<AfiliacionEmpleado>();     
    	jsonArray = json.getJSONArray("afiliacionEmpleadoList"); 
    	i = 0;
    	while (jsonArray.get(i) != null) {
    		AfiliacionEmpleado ae = null;
    		ae.fromJson(jsonArray.getJSONObject(i));
    	    list1.add(ae);
    	    i++;
    	   } 
    	this.setAfiliacionEmpleadoList(list1);
    	ArrayList<ConsultaMedica> list2 = new ArrayList<ConsultaMedica>();     
    	jsonArray = json.getJSONArray("consultaMedicaList"); 
    	i = 0;
    	while (jsonArray.get(i) != null) {
    		ConsultaMedica ae = null;
    		ae.fromJson(jsonArray.getJSONObject(i));
    	    list2.add(ae);
    	    i++;
    	   } 
    	this.setConsultaMedicaList(list2);
=======
    	//this.setCuenta(json.);
>>>>>>> parent of 4ce73e8... FKS, List
=======
    	//this.setCuenta(json.);
>>>>>>> parent of 4ce73e8... FKS, List
    	return this;
    }
    
}
