/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.atividade11.classes;

/**
 *
 * @author Joice
 */
public class Main {
    
    public static void main(String[] args) {
        
        Forma vetorForma[] = new Forma[6];
        
        //instancia diferentes formas geometricas e guarda no vetor
        System.out.println("\nFORMAS GEOMETRICAS\n");
        vetorForma[0] = new Piramide(4, 2, 7);
        vetorForma[1] = new Cubo(5.6);
        vetorForma[2] = new Esfera(6);
        vetorForma[3] = new Triangulo(7, 7);
        vetorForma[4] = new Circulo(3.14);
        vetorForma[5] = new Quadrado(5);
       
        // Percorre o vetor de formas
        for(Forma forma: vetorForma) {
            
            // Imprime o nome da forma usando o nome da classe
            System.out.println("Forma: " + forma.getClass().getSimpleName());
            
            System.out.println(forma.toString()); //imprime a representação textual da forma
            
            if(forma instanceof FormaBidimensional) { // Verifica se a forma é bidimensional
                forma.obterArea();
            } else {
                FormaTridimensional f = (FormaTridimensional) forma; // Faz o cast para FormaTridimensional  
                f.obterVolume();
            }
            System.out.println("");
        }
    }
}
