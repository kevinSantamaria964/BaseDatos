/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.basedatos;

/**
 *
 * @author Kevin Santamaria
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Usuario {
    private int idUsuario;
    private String nombreUsuario;
    private String contraseña;

    public Usuario(int idUsuario, String nombreUsuario, String contraseña) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    // Método para insertar un nuevo usuario en la base de datos
    public void insertarUsuario(Connection conexion) throws SQLException {
        try {
            String consulta = "INSERT INTO usuarios (nombreUsuario, contraseña) VALUES (?, ?)";
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setString(1, nombreUsuario);
            statement.setString(2, contraseña);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.cerrarConexion(conexion);
        }
    }

    // Método para autenticar a un usuario
    public static Usuario autenticarUsuario(Connection conexion, String nombreUsuario, String contraseña) throws SQLException {
        try {
            String consulta = "SELECT idUsuario, nombreUsuario, contraseña FROM usuarios WHERE nombreUsuario = ? AND contraseña = ?";
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setString(1, nombreUsuario);
            statement.setString(2, contraseña);
            ResultSet resultado = statement.executeQuery();

            if (resultado.next()) {
                int idUsuario = resultado.getInt("idUsuario");
                String nombreUsuario = resultado.getString("nombreUsuario");
                String contraseña = resultado.getString("contraseña");
                return new Usuario(idUsuario, nombreUsuario, contraseña);
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            Conexion.cerrarConexion(conexion);
        }
    }
}


