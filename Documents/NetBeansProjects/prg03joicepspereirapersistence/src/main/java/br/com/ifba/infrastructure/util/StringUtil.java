/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.infrastructure.util;

/**
 *
 * @author Joice
 */
public class StringUtil {
    
    // Verifica se uma string está nula ou vazia
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    // Valida se uma string contém apenas letras
    public static boolean isOnlyLetters(String str) {
        return str != null && str.matches("[a-zA-Z]+");
    }

    // Valida se uma string contém apenas letras e números
    public static boolean isAlphanumeric(String str) {
        return str != null && str.matches("[a-zA-Z0-9]+");
    }

    // Valida se uma string contém apenas letras, números e espaços
    public static boolean isAlphanumericWithSpaces(String str) {
        return str != null && str.matches("[a-zA-Z0-9 ]+");
    }

    // Valida se uma string contém caracteres especiais
    public static boolean containsSpecialCharacters(String str) {
        return str != null && str.matches(".*[^a-zA-Z0-9 ].*");
    }

    // Remove espaços extras de uma string
    public static String trimExtraSpaces(String str) {
        if (str == null) return null;
        return str.trim().replaceAll("\\s+", " ");
    }

}
