/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.sql.Statement;
import Modelo.ConexionAdmin;
import Modelo.Evento;
import Modelo.FechaEvento;
import Modelo.Parque;
import Modelo.Persona;
import Modelo.TipoEvento;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author Silvio
 */
public class ControladorEventos extends ConexionAdmin {

    public boolean Registrar(Evento evento) {
        PreparedStatement pst = null;

        boolean Consulta = false;

        try {
            String consulta = "INSERT INTO `evento`( `Nombre`, "
                    + "`Descripcion`, `Publico`, `Duracion`, `Foto`, `Fecha`, "
                    + " `Estado`, `idEvento`, `idPersona`, `Idparque` ,`Voto`) VALUES (?,?,?,?,?,?,?,?,?,?,?) ";
            pst = conectar().prepareStatement(consulta);
            pst.setString(1, evento.getNombre());
            pst.setString(2, evento.getDescripcion());
            pst.setString(3, evento.getPublico());
            pst.setTime(4, evento.getDuracion());
            pst.setString(5, evento.getFoto());
            pst.setDate(6, evento.getFecha());
            pst.setString(7, evento.getEstado());
            pst.setInt(8, evento.getIdEvento());
            pst.setInt(9, evento.getIdPersona());
            pst.setInt(10, evento.getIdParque());
            pst.setInt(11, evento.getVoto());

            int n = pst.executeUpdate();
            if (n != 0) {
                Consulta = true;
            } else {
                Consulta = false;
            }
            pst.close();
            conectar().close();
        } catch (Exception e) {
            System.err.println("Error " + e);
        }
        return Consulta;
    }

    public Evento Buscar(Evento evento) {
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            String consulta = "SELECT * FROM `evento` WHERE `id` ='" + evento.getId() + "'";
            Statement st = conectar().createStatement();
            rs = st.executeQuery(consulta);

            while (rs.next()) {
        
               evento.setNombre(rs.getString("Nombre"));
                evento.setDescripcion(rs.getString("Descripcion"));
                evento.setPublico(rs.getString("Publico"));
                evento.setDuracion(rs.getTime("Duracion"));
                evento.setFoto(rs.getString("Foto"));
                evento.setFecha(rs.getDate("Fecha"));
                evento.setEstado(rs.getString("Estado"));
                evento.setVoto(rs.getInt("Voto"));
                evento.setIdEvento(rs.getInt("idEvento"));
                evento.setIdPersona(rs.getInt("idPersona"));
                evento.setIdParque(rs.getInt("Idparque"));
            }
            rs.close();
            st.close();
            conectar().close();
        } catch (Exception e) {
            System.err.println("Error " + e);
        }
        return evento;
    }

    public LinkedList<Evento> Mostrar() {
        LinkedList<Evento> listaEventos = new LinkedList<Evento>();
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            String consulta = "SELECT * FROM `evento`";

            Statement st = conectar().createStatement();
            rs = st.executeQuery(consulta);

            while (rs.next()) {
                Evento evento = new Evento();
                evento.setId(rs.getInt("id"));
                evento.setNombre(rs.getString("Nombre"));
                evento.setDescripcion(rs.getString("Descripcion"));
                evento.setPublico(rs.getString("Publico"));
                evento.setDuracion(rs.getTime("Duracion"));
                evento.setFoto(rs.getString("Foto"));
                evento.setFecha(rs.getDate("Fecha"));
                evento.setEstado(rs.getString("Estado"));
                evento.setVoto(rs.getInt("Voto"));
                evento.setIdEvento(rs.getInt("idEvento"));
                evento.setIdPersona(rs.getInt("idPersona"));
                evento.setIdParque(rs.getInt("Idparque"));
                listaEventos.add(evento);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaEventos;
    }

   public static void main(String[] arg) throws SQLException {
       
        ControladorEventos CEvento = new ControladorEventos();
        CEvento.setUser("Users");
        CEvento.setPass("Users");
        CEvento.conectar();
        Evento evento = new Evento();
        Evento Revento = new Evento();
        evento.setId(1);
        
        Revento =CEvento.Buscar(evento);
        
      
       
        
        
        
   }
   /* 
    public static void main(String[] arg) throws SQLException {
        ConexionAdmin BD = new ConexionAdmin();

        Evento evento = new Evento();
        ControladorEventos CEvento = new ControladorEventos();

        String dates = "1990-02-02"; // YYYY-MM-DD
        java.sql.Date sqlDate = java.sql.Date.valueOf(dates);

        java.util.Date date = new java.util.Date();
        long t = date.getTime();
        java.sql.Time sqlTime = java.sql.Time.valueOf("20:30:20");
        System.out.println(sqlTime);

        CEvento.setUser("Users");
        CEvento.setPass("Users");
        CEvento.conectar();

        evento.setNombre("Dominica");
        evento.setDescripcion("Es un evento de varias personas");
        evento.setPublico("Todo Publico");
        evento.setDuracion(sqlTime);
        evento.setFoto("");
        evento.setFecha(sqlDate);
        evento.setEstado("Activo");
        evento.setVoto(0);

        //id del usuario
        Persona persona = new Persona();
        Persona Rpersona = new Persona();
        persona.setUsuario("silviojsoto@hotmail.com");
        ControladorPersona cpersona = new ControladorPersona();
        cpersona.setUser("Users");
        cpersona.setPass("Users");
        cpersona.conectar();
        Rpersona = cpersona.Mostrar(persona);
        evento.setIdPersona(persona.getId());

        //Tipo de Evento
        TipoEvento TEvento = new TipoEvento();
        TipoEvento TEventoR = new TipoEvento();
        ControladorTipoEvento CTEvento = new ControladorTipoEvento();
        CTEvento.setUser("Users");
        CTEvento.setPass("Users");
        CTEvento.conectar();
        TEvento.setNombre("Concierto");
        TEventoR = CTEvento.Buscar(TEvento);
        evento.setIdEvento(TEventoR.getId());

        //obtener el id del parque
        Parque parque = new Parque();
        Parque parqueR = new Parque();
        ControladorParques Cpersona = new ControladorParques();
        Cpersona.setUser("Users");
        Cpersona.setPass("Users");
        Cpersona.conectar();
        parque.setNombre("Parque los laureles");
        parqueR = Cpersona.Buscar(parque);
        evento.setIdParque(parqueR.getId());
        System.out.println(CEvento.Registrar(evento));
    }*/
}
