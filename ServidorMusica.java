/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.basedatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ServidorMusica {
    private static List<Cancion> cancionesDisponibles = new ArrayList<>();
    private static List<Cancion> colaReproduccion = new ArrayList<>();

    public static void cargarCancionesDisponibles() {
        cancionesDisponibles.clear();
        Connection conexion = Conexion.obtenerConexion();
        if (conexion != null) {
            try {
                String consulta = "SELECT idCancion, nombre, artista, archivo FROM canciones";
                PreparedStatement statement = conexion.prepareStatement(consulta);
                ResultSet resultado = statement.executeQuery();
                while (resultado.next()) {
                    int idCancion = resultado.getInt("idCancion");
                    String nombre = resultado.getString("nombre");
                    String artista = resultado.getString("artista");
                    String archivo = resultado.getString("archivo");
                    Cancion cancion = new Cancion(idCancion, nombre, artista, archivo);
                    cancionesDisponibles.add(cancion);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                Conexion.cerrarConexion(conexion);
            }
        }
    }

    public static List<Cancion> obtenerCancionesDisponibles() {
        return cancionesDisponibles;
    }

    public static void agregarCancionAColaReproduccion(Cancion cancion) {
        colaReproduccion.add(cancion);
    }

    public static void eliminarCancionDeColaReproduccion(Cancion cancion) {
        colaReproduccion.remove(cancion);
    }

    public static List<Cancion> obtenerColaReproduccion() {
        return colaReproduccion;
    }

    // Agrega aquí otros métodos relacionados con la base de datos
    public static void agregarCancionABaseDeDatos(Cancion cancion) {
        Connection conexion = Conexion.obtenerConexion();
        if (conexion != null) {
            try {
                String consulta = "INSERT INTO canciones (nombre, artista, archivo) VALUES (?, ?, ?)";
                PreparedStatement statement = conexion.prepareStatement(consulta);
                statement.setString(1, cancion.getNombre());
                statement.setString(2, cancion.getArtista());
                statement.setString(3, cancion.getArchivo());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                Conexion.cerrarConexion(conexion);
            }
        }
    }

    public static void eliminarCancionDeBaseDeDatos(int idCancion) {
        Connection conexion = Conexion.obtenerConexion();
        if (conexion != null) {
            try {
                String consulta = "DELETE FROM canciones WHERE idCancion = ?";
                PreparedStatement statement = conexion.prepareStatement(consulta);
                statement.setInt(1, idCancion);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                Conexion.cerrarConexion(conexion);
            }
        }
    }

    public static void agregarUsuarioABaseDeDatos(Usuario usuario) {
        Connection conexion = Conexion.obtenerConexion();
        if (conexion != null) {
            try {
                String consulta = "INSERT INTO usuarios (nombreUsuario, contraseña) VALUES (?, ?)";
                PreparedStatement statement = conexion.prepareStatement(consulta);
                statement.setString(1, usuario.getNombreUsuario());
                statement.setString(2, usuario.getContraseña());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                Conexion.cerrarConexion(conexion);
            }
        }
    }

    public static Usuario autenticarUsuarioEnBaseDeDatos(String nombreUsuario, String contraseña) {
        Connection conexion = Conexion.obtenerConexion();
        if (conexion != null) {
            try {
                String consulta = "SELECT idUsuario, nombreUsuario FROM usuarios WHERE nombreUsuario = ? AND contraseña = ?";
                PreparedStatement statement = conexion.prepareStatement(consulta);
                statement.setString(1, nombreUsuario);
                statement.setString(2, contraseña);
                ResultSet resultado = statement.executeQuery();
                if (resultado.next()) {
                    int idUsuario = resultado.getInt("idUsuario");
                    return new Usuario(idUsuario, nombreUsuario, contraseña);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                Conexion.cerrarConexion(conexion);
            }
        }
        return null;
    }
}


