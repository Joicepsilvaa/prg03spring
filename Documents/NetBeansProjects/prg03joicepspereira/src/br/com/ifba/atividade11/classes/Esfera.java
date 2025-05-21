/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.atividade11.classes;

/**
 *
 * @author Joice
 */
public class Esfera extends FormaTridimensional{
    
     private final double raio;

    //construtor
    public Esfera(double raio) {
        this.raio = raio;
    }
    
    //volume da esfera
    @Override
    public void obterVolume() {
        System.out.printf("Volume da esfera: %.2f%n", (4 * Math.PI * Math.pow(getRaio(), 3)) / 3);
    }

    //area da esfera
     @Override
    public void obterArea() {
        System.out.printf("Area da esfera: %.2f%n", 4 * Math.PI * Math.pow(getRaio(), 2));
    }

    private double getRaio() {
        return raio;
    }

}
