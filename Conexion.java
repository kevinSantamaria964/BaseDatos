/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.basedatos;

/**
 *
 * @author Kevin Santamaria
 */
//import com.mysql.jdbc.Connection;
import java.sql.*;

public class Conexion {

    static Connection obtenerConexion() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    static void cerrarConexion(Connection conexion) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
   private Connection conexion;
   
   public Conexion obtener(){
      String cadcon="jdbc:mysql://localhost:3306/dbconta_1";
      String user="root";
      String password="";
      try {
            Class.forName("com.mysql.jdbc.Driver");
            setConexion(DriverManager.getConnection(cadcon, user, password));
            
            if(getConexion() != null){
                System.out.println("Conexion Exitosa!");
            }else{
                System.out.println("Conexion Fallida!");                
            }
            
      } catch (Exception e) {
              e.printStackTrace();
  }
   
      return this;
   }
   public Connection getConexion() {
  return conexion;
   }    
   public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }   
   
   public ResultSet consultar(String sql) {
        ResultSet resultado;
        try {
            Statement sentencia = getConexion().createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            resultado = sentencia.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }        return resultado;
    }

}
