/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ConexionAdmin;
import Modelo.FechaEvento;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Silvio
 */
public class ControladorFechaEvento extends ConexionAdmin{
    
    public boolean Registrar(FechaEvento FEvento) {
        PreparedStatement  pst = null;

        boolean Consulta = false;
        try {
            String consulta = "INSERT INTO `fechaevento`( `Fecha`, `Voto`, "
                    + "`Estado`, `idParque`) VALUES (?,?,?,?)";
            pst = conectar().prepareStatement(consulta);
            pst.setDate(1, FEvento.getFecha());
            pst.setInt(2, FEvento.getVoto());
            pst.setString(3, FEvento.getEstado());
            pst.setInt(4, FEvento.getIdParque());
            
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
    
    
    public boolean Editar(FechaEvento FEvento) {
        PreparedStatement pst = null;
        
        boolean Consulta = false;
        String consulta = "UPDATE `fechaevento` SET `Fecha`="
                + "?,`Voto`=?,`Estado`=?,`idParque`=? WHERE `id`=?";
        try {
            pst = conectar().prepareStatement(consulta);
            pst.setDate(1, FEvento.getFecha());
            pst.setInt(2, FEvento.getVoto());
            pst.setString(3, FEvento.getEstado());
            pst.setInt(4, FEvento.getIdParque());
            pst.setInt(5, FEvento.getId());

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
    
    public boolean Eliminar(FechaEvento FEvento) {
        PreparedStatement pst = null;
        
        boolean Consulta = false;
        String consulta = "UPDATE `fechaevento` SET `Estado`= ? WHERE `id` = ?";
        try {
            pst = conectar().prepareStatement(consulta);
       
            pst.setString(1, FEvento.getEstado());
            pst.setInt(2, FEvento.getId());

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
          
    public static void main(String[] arg) throws SQLException {

           String date = "1990-02-02"; // YYYY-MM-DD
        java.sql.Date sqlDate = java.sql.Date.valueOf(date);


        FechaEvento TEvento = new FechaEvento();
        ControladorFechaEvento CTEvento = new ControladorFechaEvento();
        CTEvento.setUser("Admin");
        CTEvento.setPass("Admin");
        CTEvento.conectar();
        
        TEvento.setFecha(sqlDate);
        TEvento.setId(1);
        TEvento.setVoto(1);
        TEvento.setEstado("Inactivo");
        TEvento.setIdParque(1);
        
       System.out.println(CTEvento.Registrar(TEvento));
    }
}
