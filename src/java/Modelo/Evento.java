/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author Silvio
 */
public class Evento {

    private int id;
    private String Nombre;
    private String Descripcion;
    private String Publico;
    private int Calificacion;
    private Time Duracion;
    private String Foto;
    private Date Fecha;
    private int Voto;
    private String Estado;
    private int idEvento;
    private int idPersona;
    private int idParque;
    

    public Evento() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public String getPublico() {
        return Publico;
    }

    public void setPublico(String Publico) {
        this.Publico = Publico;
    }

    public int getCalificacion() {
        return Calificacion;
    }

    public void setCalificacion(int Calificacion) {
        this.Calificacion = Calificacion;
    }

    public Time getDuracion() {
        return Duracion;
    }

    public void setDuracion(Time Duracion) {
        this.Duracion = Duracion;
    }

    public String getFoto() {
        return Foto;
    }

    public void setFoto(String Foto) {
        this.Foto = Foto;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date Fecha) {
        this.Fecha = Fecha;
    }

    public int getVoto() {
        return Voto;
    }

    public void setVoto(int Voto) {
        this.Voto = Voto;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public int getIdParque() {
        return idParque;
    }

    public void setIdParque(int idParque) {
        this.idParque = idParque;
    }

    
    
}
