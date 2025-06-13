package com.yuri.login.test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordHashGenerator {

    public static void main(String[] args) {
        // A senha que você deseja encriptar
        String adminPassword = "admin";

        // Cria uma instância do bcrypt encoder
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        // Gera o hash (senha encriptada)
        String hashedPassword = encoder.encode(adminPassword);

        // Exibe o hash da senha
        System.out.println("Hash da senha 'admin': " + hashedPassword);
    }
}