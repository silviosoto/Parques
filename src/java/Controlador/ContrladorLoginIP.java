/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ConexionAdmin;
import Modelo.Logs;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Silvio
 */
public class ContrladorLoginIP extends ConexionAdmin {
    
    public boolean RegistrarLogin(Logs logs) {
        PreparedStatement pst = null;
        ResultSet rs = null;

        boolean Consulta = false;
        try {
            String consulta = "insert into logs (IdUsuario, Login, Fecha, Hora,"
                    + "ipMaquina, IpServidor ) values (?,?,?,?,?,?) ";
            pst = conectar().prepareStatement(consulta);
            pst.setInt(1, logs.getIdUsuario());
            pst.setString(2, logs.getLogin());
            pst.setDate(3, logs.getFecha());
            pst.setTime(4, logs.getHora());
            pst.setString(5, logs.getIpMaquina());
            pst.setString(6, logs.getIpServidor());
            int n = pst.executeUpdate();
            if (n != 0) {
                Consulta = true;
            } else {
                Consulta = false;
            }
        } catch (Exception e) {
            System.err.println("Error " + e);
        }
        return Consulta;
    }
       
    public static void main(String[] arg) throws SQLException {
       //Fecha de hoy 
        Date fecha = new Date();
        java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
        
        
        // obtener la hora y salida por pantalla con formato:
        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
        
        java.sql.Time HoraTimeSql =  java.sql.Time.valueOf(hourFormat.format(fecha));
        System.out.println(HoraTimeSql);
        
        Logs logs = new Logs();
        ContrladorLoginIP cLogin = new ContrladorLoginIP();
        
        
        logs.setIdUsuario(1);
        logs.setLogin("oscar@gmail.com");
        logs.setFecha(fechaSQL);
        logs.setHora(HoraTimeSql);
        logs.setIpMaquina("1213123");
        logs.setIpServidor("1231231");
        
        ContrladorLoginIP cLoginIp = new ContrladorLoginIP();
        cLoginIp.setUser("Users");
        cLoginIp.setPass("Users");
        cLoginIp.RegistrarLogin(logs);
        
    }
}
