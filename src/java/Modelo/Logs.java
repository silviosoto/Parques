
package Modelo;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author Silvio
 */
public class Logs {
    private int IdUsuario;
    private String Login; 
    private Date Fecha;
    private Time Hora;
    private String ipMaquina;
    private String IpServidor;

    public Logs() {
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int IdUsuario) {
        this.IdUsuario = IdUsuario;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String Login) {
        this.Login = Login;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date Fecha) {
        this.Fecha = Fecha;
    }

    public Time getHora() {
        return Hora;
    }

    public void setHora(Time Hora) {
        this.Hora = Hora;
    }

    public String getIpMaquina() {
        return ipMaquina;
    }

    public void setIpMaquina(String ipMaquina) {
        this.ipMaquina = ipMaquina;
    }

    public String getIpServidor() {
        return IpServidor;
    }

    public void setIpServidor(String IpServidor) {
        this.IpServidor = IpServidor;
    }

  
}
