/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.atividade11.classes;

/**
 *
 * @author Joice
 */
public class Circulo extends FormaBidimensional{
 
    private final double raio;
    
    //construtor
    public Circulo(double raio) {
        this.raio = raio;
    }
    
    //area do circulo
    @Override
    public void obterArea() {
        System.out.printf("Area do circulo: %.2f%n", Math.pow(getRaio(), 2) * Math.PI);
    }

    private double getRaio() {
        return raio;
    }
}
