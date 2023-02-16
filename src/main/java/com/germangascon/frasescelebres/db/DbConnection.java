package com.germangascon.frasescelebres.db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.germangascon.frasescelebres.FrasesCelebres;
import com.germangascon.frasescelebres.util.Log;

/**
 * Clase Singleton para obtener una conexión a la base de datos.
 * Para un servidor real que atendiera varias peticiones de forma simultánea
 * debería crearse un pool de conexiones.
 */
public class DbConnection {
    private final static String TAG = "DbConnection";
    private static DbConnection instance = null;
    private String DB_SERVER = "localhost";
    private String DB_NAME = "frases";
    private String DB_USERNAME = "frases";
    private String DB_PASSWORD = "frases";
    public Connection connection;

    /** Constructor private para evitar que sea instanciado */
    private DbConnection() {
        /** Se deja vacío porque estamos definiendo una clase Singleton */
    }

    /**
     * Obtiene la instancia para la conexión a la base de datos.
     * Hacemos el método synchronized para evitar que 2 hilos puedan crear
     * instancias diferentes.
     * @return DbConnection
     */
    public synchronized static DbConnection getInstance() {
        if(instance == null) {
            instance = new DbConnection();
            if(FrasesCelebres.CONFIG_FILE != null) {
                instance.readConfig(FrasesCelebres.CONFIG_FILE);
            }
            instance.connect();
        }
        return instance;
    }

    /**
     * Conecta a la base de datos
     * @return true si la conexión ha sido satisfactoria, false en caso contrario.
     */
    private boolean connect() {
        boolean success = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://"+DB_SERVER+"/"+DB_NAME;
            connection = DriverManager.getConnection (url,DB_USERNAME,DB_PASSWORD);
            success = true;
        } catch (ClassNotFoundException ex) {
            Log.e(TAG,"No se ha podido cargar el driver de conexión a base de datos");
            Log.e(TAG, ex.getMessage());
            ex.printStackTrace();
        } catch(SQLException sqle) {
            Log.e(TAG,"Error al conectar con la base de datos");
            Log.e(TAG, sqle.getMessage());
        }
        return success;
    }

    public void destroy() {
        try {
            connection.close();
            Log.i(TAG, "Se ha finalizado la conexión con el servidor");
        } catch (SQLException ex) {
            Log.e(TAG, ex.getMessage());
        }
        instance = null;
    }

    private void readConfig(String filename) {
        Properties prop = new Properties();
        InputStream in;
        try {
            in = new FileInputStream(filename);
            prop.load(in);
            DB_SERVER = prop.getProperty("DB_SERVER", "locahost");
            DB_NAME = prop.getProperty("DB_NAME", "frases");
            DB_USERNAME = prop.getProperty("DB_USERNAME", "frases");
            DB_PASSWORD = prop.getProperty("DB_PASSWORD", "frases");
        } catch (FileNotFoundException e) {
            Log.e(TAG, "Archivo " + filename + " no encontrado");
            Log.e(TAG, e.getMessage());
        } catch (IOException io) {
            Log.e(TAG, "Error al leer el archivo configuración " + filename);
        }
    }
}

