/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.atividade11.classes;

/**
 *
 * @author Joice
 */
public class Piramide extends FormaTridimensional {

    private final double areaBase;
    private final double altura;
    private final double areaLateral;

    //construtor
    public Piramide(double areaBase, double altura, double areaLateral) {
        this.areaBase = areaBase;
        this.altura = altura;
        this.areaLateral = areaLateral;
    }
    
    //volume da piramide
    @Override
    public void obterVolume() {
        System.out.printf("Volume da piramide: %.2f%n", (getAreaBase() * getAltura()) / 3);
    }

    //área da piramide
    public void obterArea() {
        System.out.printf("Area da piramide: %.2f%n", getAreaBase() + getAreaLateral());    
    }

    //metodos getters
    public double getAreaBase() {
        return areaBase;
    }

    public double getAltura() {
        return altura;
    }

    private double getAreaLateral() {
        return areaLateral;
    }
}
