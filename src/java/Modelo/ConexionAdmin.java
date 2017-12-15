/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Silvio
 */
public class ConexionAdmin {
    
    
    public String db = "parques";
    public String url = "jdbc:mysql://localhost/" + db;
    public String user = "";
    public String pass = "";
    private Connection Enlace;

    public ConexionAdmin() {
    }
    
    public Connection conectar() throws SQLException {
        Connection link = null;
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            link = DriverManager.getConnection(this.url, this.getUser(), this.getPass());
        } catch (ClassNotFoundException | SQLException e){
         System.out.println(e);
        }
        return link;
    }

    public Connection getEnlace() {
        return Enlace;
    }

    public void setEnlace(Connection Enlace) {
        this.Enlace = Enlace;
    }

    
    public void CerrarConexionBD( Connection link ) throws SQLException {
        
        try {
            link.close();
        } catch (SQLException e) {
             System.out.println(e);
        }
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    
    
   
    public static void main(String[] args ) throws SQLException{
        ConexionAdmin conz  =  new ConexionAdmin();
        conz.setEnlace(conz.conectar());
        System.out.println(conz.getEnlace());
        conz.CerrarConexionBD(conz.getEnlace());
    }
    
}
