/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.basedatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ClienteMusica {
    private Usuario usuarioActual;

    public ClienteMusica(Usuario usuario) {
        this.usuarioActual = usuario;
    }

    public void verCancionesDisponibles() {
        List<Cancion> canciones = ServidorMusica.obtenerCancionesDisponibles();
        for (Cancion cancion : canciones) {
            System.out.println("ID: " + cancion.getIdCancion() + ", Nombre: " + cancion.getNombre() + ", Artista: " + cancion.getArtista());
        }
    }

    public void agregarCancionAColaReproduccion(int idCancion) {
        List<Cancion> canciones = ServidorMusica.obtenerCancionesDisponibles();
        for (Cancion cancion : canciones) {
            if (cancion.getIdCancion() == idCancion) {
                ServidorMusica.agregarCancionAColaReproduccion(cancion);
                System.out.println("Canción agregada a la cola de reproducción: " + cancion.getNombre());
                return;
            }
        }
        System.out.println("Canción no encontrada.");
    }

    public void verColaReproduccion() {
        List<Cancion> cola = ServidorMusica.obtenerColaReproduccion();
        System.out.println("Cola de Reproducción:");
        for (Cancion cancion : cola) {
            System.out.println("ID: " + cancion.getIdCancion() + ", Nombre: " + cancion.getNombre() + ", Artista: " + cancion.getArtista());
        }
    }

    public void agregarCancionAPlaylist(String nombrePlaylist, int idCancion) {
        Connection conexion = Conexion.obtenerConexion();
        if (conexion != null) {
            try {
                String consulta = "INSERT INTO playlists (nombrePlaylist, idCancion) VALUES (?, ?)";
                PreparedStatement statement = conexion.prepareStatement(consulta);
                statement.setString(1, nombrePlaylist);
                statement.setInt(2, idCancion);
                statement.executeUpdate();
                System.out.println("Canción agregada a la playlist: " + nombrePlaylist);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                Conexion.cerrarConexion(conexion);
            }
        }
    }

    public void crearPlaylist(String nombrePlaylist) {
        Connection conexion = Conexion.obtenerConexion();
        if (conexion != null) {
            try {
                String consulta = "INSERT INTO playlists (nombrePlaylist) VALUES (?)";
                PreparedStatement statement = conexion.prepareStatement(consulta);
                statement.setString(1, nombrePlaylist);
                statement.executeUpdate();
                System.out.println("Playlist creada: " + nombrePlaylist);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                Conexion.cerrarConexion(conexion);
            }
        }
    }

    public void eliminarCancionDePlaylist(String nombrePlaylist, int idCancion) {
        Connection conexion = Conexion.obtenerConexion();
        if (conexion != null) {
            try {
                String consulta = "DELETE FROM playlists WHERE nombrePlaylist = ? AND idCancion = ?";
                PreparedStatement statement = conexion.prepareStatement(consulta);
                statement.setString(1, nombrePlaylist);
                statement.setInt(2, idCancion);
                statement.executeUpdate();
                System.out.println("Canción eliminada de la playlist: " + nombrePlaylist);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                Conexion.cerrarConexion(conexion);
            }
        }
    }

    public void eliminarPlaylist(String nombrePlaylist) {
        Connection conexion = Conexion.obtenerConexion();
        if (conexion != null) {
            try {
                String consulta = "DELETE FROM playlists WHERE nombrePlaylist = ?";
                PreparedStatement statement = conexion.prepareStatement(consulta);
                statement.setString(1, nombrePlaylist);
                statement.executeUpdate();
                System.out.println("Playlist eliminada: " + nombrePlaylist);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                Conexion.cerrarConexion(conexion);
            }
        }
    }
}

