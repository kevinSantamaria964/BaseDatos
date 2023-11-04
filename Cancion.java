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

public class Cancion {
    private int idCancion;
    private String nombre;
    private String artista;
    private String archivo;

    public Cancion(int idCancion, String nombre, String artista, String archivo) {
        this.idCancion = idCancion;
        this.nombre = nombre;
        this.artista = artista;
        this.archivo = archivo;
    }

    public int getIdCancion() {
        return idCancion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    // Método para insertar una canción en la base de datos
    public void insertarCancion(Connection conexion) throws SQLException {
        try {
            String consulta = "INSERT INTO canciones (nombre, artista, archivo) VALUES (?, ?, ?)";
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setString(1, nombre);
            statement.setString(2, artista);
            statement.setString(3, archivo);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.cerrarConexion(conexion);
        }
    }

    // Método para recuperar una canción de la base de datos por su ID
    public static Cancion obtenerCancionPorID(Connection conexion, int id) throws SQLException {
        try {
            String consulta = "SELECT idCancion, nombre, artista, archivo FROM canciones WHERE idCancion = ?";
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setInt(1, id);
            ResultSet resultado = statement.executeQuery();

            if (resultado.next()) {
                int idCancion = resultado.getInt("idCancion");
                String nombre = resultado.getString("nombre");
                String artista = resultado.getString("artista");
                String archivo = resultado.getString("archivo");
                return new Cancion(idCancion, nombre, artista, archivo);
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.cerrarConexion(conexion);
        }
        return null;
    }
}
 




