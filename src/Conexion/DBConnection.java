/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Isi
 */
public class DBConnection {

    public static Connection getConnection() {

        Connection con = null;
        try {
            Class.forName("org.postgresql.Driver");
            try {
//                con = DriverManager.getConnection("jdbc:postgresql://190.128.149.222:5432/mca", "postgres", "postgres");
                con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bascula", "postgres", "postgres");
            } catch (SQLException ex) {
                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

        return con;
    }

    public static String getHoy_format2() throws ClassNotFoundException, SQLException {
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String hoy = ft.format(dNow);
        return hoy;
    }
    
    public static String getFecha_ddMMMyyyy() throws ClassNotFoundException, SQLException {
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("dd-MMM-yyyy");
        String hoy = ft.format(dNow);
        return hoy;
    }
    
    public static String Obtener_Fecha() {
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        String hoy = ft.format(dNow);
        return hoy;
    }

    public static int RecepcionMax() {
        int max = 0;
        try {
            try (Connection conexion = getConnection()) {
                Statement ST = conexion.createStatement();
                ResultSet RS = ST.executeQuery("SELECT max(id_recepcion) FROM recepcion ");
                if (RS.next()) {
                    max = RS.getInt("max");
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return max;
    }
    public static int ClienteMax() {
        int max = 0;
        try {
            try (Connection conexion = getConnection()) {
                Statement ST = conexion.createStatement();
                ResultSet RS = ST.executeQuery("SELECT max(id_cliente) FROM cliente ");
                if (RS.next()) {
                    max = RS.getInt("max");
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return max;
    }
    public static int ChoferMax() {
        int max = 0;
        try {
            try (Connection conexion = getConnection()) {
                Statement ST = conexion.createStatement();
                ResultSet RS = ST.executeQuery("SELECT max(id_chofer) FROM chofer ");
                if (RS.next()) {
                    max = RS.getInt("max");
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return max;
    }
    
    public static int CamionMax() {
        int max = 0;
        try {
            try (Connection conexion = getConnection()) {
                Statement ST = conexion.createStatement();
                ResultSet RS = ST.executeQuery("SELECT max(id_camion) FROM camion ");
                if (RS.next()) {
                    max = RS.getInt("max");
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return max;
    }
    
   

    public static String getSepararMiles(String txtprec) {
        String valor = txtprec;

        int largo = valor.length();
        if (largo > 8) {
            valor = valor.substring(largo - 9, largo - 6) + "." + valor.substring(largo - 6, largo - 3) + "." + valor.substring(largo - 3, largo);
        } else if (largo > 7) {
            valor = valor.substring(largo - 8, largo - 6) + "." + valor.substring(largo - 6, largo - 3) + "." + valor.substring(largo - 3, largo);
        } else if (largo > 6) {
            valor = valor.substring(largo - 7, largo - 6) + "." + valor.substring(largo - 6, largo - 3) + "." + valor.substring(largo - 3, largo);
        } else if (largo > 5) {
            valor = valor.substring(largo - 6, largo - 3) + "." + valor.substring(largo - 3, largo);
        } else if (largo > 4) {
            valor = valor.substring(largo - 5, largo - 3) + "." + valor.substring(largo - 3, largo);
        } else if (largo > 3) {
            valor = valor.substring(largo - 4, largo - 3) + "." + valor.substring(largo - 3, largo);
        }
        txtprec = valor;
        return valor;
    }
}
