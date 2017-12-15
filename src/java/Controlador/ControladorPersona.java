/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ConexionAdmin;
import Modelo.Persona;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Silvio
 */
public class ControladorPersona extends ConexionAdmin {

    public boolean Autenticacion(String usuario, String contraseña) {
        PreparedStatement pst = null;
        ResultSet rs = null;

        boolean Consulta = false;
        try {
            String consulta = "select * from persona where  Usuario  = ? and Contrasena = ? ";
            pst = conectar().prepareStatement(consulta);
            pst.setString(1, usuario);
            pst.setString(2, contraseña);
            rs = pst.executeQuery();
            if (rs.absolute(1)) {
                Consulta = true;
            }
            rs.close();
            pst.close();
            conectar().close();
        } catch (Exception e) {
            System.err.println("Error " + e);
        }
        return Consulta;
    }

    public boolean RegistrarPersona(Persona persona) {
        PreparedStatement pst = null;

        boolean Consulta = false;
        try {
            String consulta = "INSERT INTO `persona`(`id`, `Nombre`, `FNacimiento`, `Sexo`, `Foto`, `Usuario`, `Contrasena`, `Estado`) "
                    + "VALUES (Null,?,?,?,?,?,?,?) ";
            pst = conectar().prepareStatement(consulta);

            pst.setString(1, persona.getNombre());
            pst.setDate(2, persona.getFNacimiento());
            pst.setString(3, persona.getSexo());
            pst.setString(4, persona.getFoto());
            pst.setString(5, persona.getUsuario());
            pst.setString(6, persona.getContrasena());
            pst.setString(7, persona.getEstado());

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

    public boolean Editar(Persona persona) {
        PreparedStatement pst = null;
        ResultSet rs = null;

        boolean Consulta = false;
        String consulta = "UPDATE `persona` SET `Nombre`=?,`FNacimiento`=?,"
                + "`Sexo`=?,`Foto`=?,`Usuario`=?"
                + " WHERE `Usuario` = ?";
        try {
            pst = conectar().prepareStatement(consulta);

            pst.setString(1, persona.getNombre());
            pst.setDate(2, persona.getFNacimiento());
            pst.setString(3, persona.getSexo());
            pst.setString(4, persona.getFoto());
            pst.setString(5, persona.getUsuario());
            pst.setString(6, persona.getUsuario());

            int n = pst.executeUpdate();
            if (n != 0) {
                Consulta = true;
            } else {
                Consulta = false;
            }

            conectar().close();
        } catch (Exception e) {
            System.err.println("Error " + e);
        }
        return Consulta;
    }

    public boolean EliminarCuenta(Persona persona) {
        PreparedStatement pst = null;
        boolean Consulta = false;
        String consulta = "UPDATE `persona` SET `Estado`= 'Inactivo' WHERE `Usuario` = ?";

        try {
            pst = conectar().prepareStatement(consulta);
            pst.setString(1, persona.getUsuario());
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

    public Persona Mostrar(Persona persona) {
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            String consulta = "SELECT * FROM `persona` WHERE `Usuario` = '" + persona.getUsuario() + "'";

            Statement st = conectar().createStatement();
            rs = st.executeQuery(consulta);

            while (rs.next()) {
                persona.setId(rs.getInt("id"));
                persona.setNombre(rs.getString("Nombre"));
                persona.setFNacimiento(rs.getDate("FNacimiento"));
                persona.setSexo(rs.getString("Sexo"));
                persona.setFoto(rs.getString("Foto"));
                persona.setUsuario(rs.getString("Usuario"));
                persona.setContrasena(rs.getString("Contrasena"));
                persona.setEstado(rs.getString("Estado"));
            }
            rs.close();
            st.close();
            conectar().close();
        } catch (Exception e) {
            System.err.println("Error " + e);
        }
        return persona;
    }

    public Persona Buscar(Persona persona) {
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            String consulta = "SELECT * FROM `persona` WHERE `Usuario` = '" + persona.getUsuario() + "'";

            Statement st = conectar().createStatement();
            rs = st.executeQuery(consulta);

            while (rs.next()) {
                persona.setId(rs.getInt("id"));
                persona.setNombre(rs.getString("Nombre"));
                persona.setFNacimiento(rs.getDate("FNacimiento"));
                persona.setSexo(rs.getString("Sexo"));
                persona.setFoto(rs.getString("Foto"));
                persona.setUsuario(rs.getString("Usuario"));
                persona.setContrasena(rs.getString("Contrasena"));
                persona.setEstado(rs.getString("Estado"));
            }
            rs.close();
            st.close();
            conectar().close();
        } catch (Exception e) {
            System.err.println("Error " + e);
        }
        return persona;
    }

    public boolean EstadoCuenta(Persona persona) {
        PreparedStatement pst = null;
        ResultSet rs = null;

        boolean Consulta = false;
        try {
            String consulta = "SELECT `Estado` FROM `persona` WHERE `Usuario` = ?";
            pst = conectar().prepareStatement(consulta);
            pst.setString(1, persona.getUsuario());

            rs = pst.executeQuery();
            if (rs.absolute(1)) {

                if (rs.getString("Estado") == null) {
                    Consulta = false;
                } else {
                    Consulta = true;
                }

            }
            rs.close();
            conectar().close();
        } catch (Exception e) {
            System.err.println("Error " + e);
        }
        return Consulta;
    }

    public static void main(String[] arg) throws SQLException {
        ConexionAdmin BD = new ConexionAdmin();
        Persona personas = new Persona();

        String date = "1990-02-02"; // YYYY-MM-DD
        java.sql.Date sqlDate = java.sql.Date.valueOf(date);

        ControladorPersona Cpersona = new ControladorPersona();
        Cpersona.setUser("Users");
        Cpersona.setPass("Users");
        Cpersona.conectar();

        personas.setNombre("AnaMs");
        personas.setFNacimiento(sqlDate);
        personas.setSexo("H");
        personas.setFoto("");
        personas.setUsuario("ana@gmail.com");
        System.out.println(Cpersona.Editar(personas));
    }
}
