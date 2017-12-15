/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ConexionAdmin;
import Modelo.TipoEvento;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Silvio
 */
public class ControladorTipoEvento extends ConexionAdmin{
    
      public boolean Registrar(TipoEvento TEvento) {
        PreparedStatement  pst = null;

        boolean Consulta = false;
        try {
            String consulta = "INSERT INTO `tipoevento`( `Nombre`, `Descripcion`, `Estado`)"
                    + " VALUES (?,?,?)";
            pst = conectar().prepareStatement(consulta);
            pst.setString(1, TEvento.getNombre());
            pst.setString(2, TEvento.getDescripcion());
            pst.setString(3, TEvento.getEstado());
            
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
    
   
    
    public TipoEvento Buscar(TipoEvento TEvento) {
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            String consulta = "SELECT * FROM `tipoevento` WHERE `Nombre`='" + TEvento.getNombre() + "'";

            Statement st = conectar().createStatement();
            rs = st.executeQuery(consulta);

            while (rs.next()) {
                TEvento.setId(rs.getInt("id"));
                TEvento.setNombre(rs.getString("Nombre"));
                TEvento.setDescripcion(rs.getString("Descripcion"));
                TEvento.setEstado(rs.getString("Estado"));
            }
            rs.close();
            st.close();
            conectar().close();
        } catch (Exception e) {
            System.err.println("Error " + e);
        }
        return TEvento;
    }
    
     public boolean Editar(TipoEvento TEvento) {
        PreparedStatement pst = null;
        
        boolean Consulta = false;
        String consulta = "UPDATE `tipoevento` SET `Nombre`=?,`Descripcion`=?, `Estado` =? WHERE `id` =  ?";
        try {
            pst = conectar().prepareStatement(consulta);

            pst.setString(1, TEvento.getNombre());
            pst.setString(2, TEvento.getDescripcion());
            pst.setString(3, TEvento.getEstado());
            pst.setInt(4, TEvento.getId());

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
        ConexionAdmin BD = new ConexionAdmin();

        TipoEvento TEvento = new TipoEvento();
        TipoEvento TEventoR = new TipoEvento();
        ControladorTipoEvento CTEvento = new ControladorTipoEvento();
        CTEvento.setUser("Admin");
        CTEvento.setPass("Admin");
        CTEvento.conectar();
        
        TEvento.setNombre("Concierto");
        TEvento.setId(1);
        TEvento.setEstado("Activo");
        TEvento.setDescripcion("grupon de musicos con alta capacidad musical");
        TEventoR = CTEvento.Buscar(TEvento);
        System.out.println(TEventoR.getId());
    }
}
