/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.atividade11.classes;

/**
 *
 * @author Joice
 */
public abstract class FormaTridimensional extends Forma {
        
    //metodo obrigatorio nas classes que herdarem FormaTridimensional
        public abstract void obterVolume();
    
    //metodo para retornar o tipo do objeto
    @Override
    public String toString() {
        return "Forma Tridimensional";
    }
}
