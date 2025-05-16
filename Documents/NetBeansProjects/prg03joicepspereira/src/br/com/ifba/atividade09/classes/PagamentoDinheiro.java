/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.atividade09.classes;
import javax.swing.JOptionPane;

/**
 *
 * @author Joice
 */
public class PagamentoDinheiro implements InterfacePagamento {
    private double valor;

    public PagamentoDinheiro(double valor) {
        this.valor = valor;
    }

    @Override
    public double calcularTotal() {
        return valor * 0.90; // 10% de desconto
    }

    @Override
    public void imprimirRecibo() {
        double desconto = valor * 0.10;
        String msg = String.format(
            "=== RECIBO (DINHEIRO) ===%n" +
            "Valor original: R$ %.2f%n" +
            "Desconto (10%%): R$ %.2f%n" +
            "Total a pagar: R$ %.2f",
            valor, desconto, calcularTotal()
        );
        JOptionPane.showMessageDialog(null, msg);
    }
}