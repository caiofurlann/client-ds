package caiofurlan.clientdistributedsystems.system.connection;

import caiofurlan.clientdistributedsystems.system.utilities.Validators;

public class IsValidData {
    public static boolean loginIsValid(String email, String password) throws Exception {
        if (email == null || email.isEmpty()) {
            throw new Exception("E-mail é obrigatório.");
        }
        if (password == null || password.isEmpty()) {
            throw new Exception("Senha é obrigatória.");
        }
        if (!Validators.isEmailValid(email)) {
            throw new Exception("E-mail inválido.");
        }
        if (Validators.isPasswordValid(password)) {
            throw new Exception("Senha deve ter 6 dígitos");
        }
        return true;
    }

    public static boolean logoutIsValid(String token) throws Exception {
        if (token == null || token.isEmpty()) {
            throw new Exception("Token é obrigatório.");
        }
        return true;
    }

    public static boolean registerUserIsValid(String name, String email, String password, String type) throws Exception {
        if (name == null || name.isEmpty()) {
            throw  new Exception("Nome é obrigatório");
        }


        if (password == null || password.isEmpty()) {
            throw  new Exception("Senha é obrigatório");
        }


        if (Validators.isPasswordValid(password)) {
            throw new Exception("Senha deve ter 6 dígitos");
        }

        if (type == null || type.isEmpty()) {
            throw new Exception("Tipo é obrigatório");
        }
        return true;
    }
}