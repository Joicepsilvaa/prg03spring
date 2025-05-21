/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.atividade11.classes;

/**
 *
 * @author Joice
 */
public class Quadrado extends FormaBidimensional{

    private final double lado;

    //metodo construtor
    public Quadrado(double lado) {
        this.lado = lado;
    }
    
    //area do quadrado
    public void obterArea() {
        System.out.printf("Area do quadrado: %.2f%n", Math.pow(getLado(), 2));
    }

    //metodo getter
    private double getLado() {
        return lado;
    }    
}
