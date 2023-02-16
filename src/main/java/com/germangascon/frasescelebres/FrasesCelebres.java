package com.germangascon.frasescelebres;

import com.germangascon.frasescelebres.util.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * Requiere que exista una base de datos llamada frases.
 * Las tablas se crean solas con sus correspondientes campos.
 */
@SpringBootApplication
public class FrasesCelebres implements CommandLineRunner {

    public static boolean DEBUG = true;
    public static String CONFIG_FILE =  null;

    public static void main(String[] args) {
        SpringApplication.run(FrasesCelebres.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Log.i("TAG", "Texto cualquiera");
    }
}
