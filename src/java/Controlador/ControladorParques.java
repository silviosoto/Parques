/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ConexionAdmin;
import Modelo.Parque;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Silvio
 */
public class ControladorParques extends ConexionAdmin {
    
    public boolean Registrar(Parque parque) {
        PreparedStatement pst = null;

        boolean Consulta = false;
        try {
            String consulta = "INSERT INTO `parque`( `Nombre`, `Dirección`, `Disponibilidad`) VALUES (?,?,?)";
            pst = conectar().prepareStatement(consulta);

            pst.setString(1, parque.getNombre());
            pst.setString(2, parque.getDireccion());
            pst.setString(3, parque.getDisponibilidad());
            
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
    
    
    public boolean Disponibilidad (Parque parque) {
        PreparedStatement pst = null;
        ResultSet rs = null;

        boolean Consulta = false;
        try {
            String consulta = "SELECT * FROM `parque` WHERE `id` =?";
            pst = conectar().prepareStatement(consulta);
            pst.setInt(1, parque.getId());

            rs = pst.executeQuery();
            if (rs.absolute(1)) {
                
                if ("Activo".equals(rs.getString("Disponibilidad"))) {
                    Consulta = true;
                }
                if ("Inactivo".equals(rs.getString("Disponibilidad"))) {
                    Consulta = false;
                }
                if ("Eliminado".equals(rs.getString("Disponibilidad"))) {
                    Consulta = false;
                }
            }
            rs.close();
            conectar().close();
        } catch (Exception e) {
            System.err.println("Error " + e);
        }
        return Consulta;
    }
    
    
    public boolean Editar(Parque parque) {
        PreparedStatement pst = null;

        boolean Consulta = false;
        String consulta = "UPDATE `parque` SET `Nombre`=?,"
                + "`Dirección`=?,`Disponibilidad`=? WHERE `id` =?";
        try {
            pst = conectar().prepareStatement(consulta);
            pst.setString(1, parque.getNombre());
            pst.setString(2, parque.getDireccion());
            pst.setString(3, parque.getDisponibilidad());
            pst.setInt(4, parque.getId());
            
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
    
    public boolean Eliminar(Parque parque) {
        PreparedStatement pst = null;
        boolean Consulta = false;
        String consulta = "UPDATE `parque` SET `Disponibilidad`= ? WHERE `id` = ?";

        try {
            pst = conectar().prepareStatement(consulta);
            pst.setString(1, parque.getDisponibilidad());
            pst.setInt(2, parque.getId());
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
    
     public Parque Buscar(Parque parque) {
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            
            String consulta = "SELECT * FROM `parque` WHERE `Nombre` ='" + parque.getNombre() + "'";

            Statement st = conectar().createStatement();
            rs = st.executeQuery(consulta);

            while (rs.next()) {
                parque.setId(rs.getInt("id"));
                parque.setNombre(rs.getString("Nombre"));
                parque.setDireccion(rs.getString("Dirección"));
                parque.setDisponibilidad(rs.getString("Disponibilidad"));
            }
            rs.close();
            st.close();
            conectar().close();
        } catch (Exception e) {
            System.err.println("Error " + e);
        }
        return parque;
    }
     
    public static void main(String[] arg) throws SQLException {
        ConexionAdmin BD = new ConexionAdmin();

        Parque parque = new Parque();
        Parque parqueR = new Parque();

        String date = "1990-02-02"; // YYYY-MM-DD
        java.sql.Date sqlDate = java.sql.Date.valueOf(date);

        ControladorParques Cpersona = new ControladorParques();
        Cpersona.setUser("Admin");
        Cpersona.setPass("Admin");
        Cpersona.conectar();
        
        parque.setId(2);
        
        parque.setDireccion("oscar2@gmail.com");
        parque.setNombre("Parque los robles");
        parque.setDisponibilidad("Eliminado");
        parqueR = Cpersona.Buscar(parque);
        System.out.println(parqueR.getId());
    }
    
}
