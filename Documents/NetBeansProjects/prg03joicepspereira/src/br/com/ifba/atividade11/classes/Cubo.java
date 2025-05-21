/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.atividade11.classes;

/**
 *
 * @author Joice
 */
public class Cubo extends FormaTridimensional{
    
    private final double aresta;

    //construtor
    public Cubo(double aresta) {
        this.aresta = aresta;
    }
    
    //volume do cubo
    @Override
    public void obterVolume() {
        System.out.printf("Volume do cubo: %.2f%n", Math.pow(getAresta(), 3));
    }

    //area do cubo
    @Override
    public void obterArea() {
        System.out.printf("Area do cubo: %.2f%n", 6 * Math.pow(aresta, 2));
    }

    private double getAresta() {
        return aresta;
    }
    
}
