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
public class PagamentoCartao implements InterfacePagamento {
    private double valor;

    public PagamentoCartao(double valor) {
        this.valor = valor;
    }

    @Override
    public double calcularTotal() {
        return valor * 1.05; // taxa de 5%
    }

    @Override
    public void imprimirRecibo() {
        double taxa = valor * 0.05;
        String msg = String.format(
            "=== RECIBO (CARTÃO) ===%n" +
            "Valor original: R$ %.2f%n" +
            "Taxa (5%%): R$ %.2f%n" +
            "Total a pagar: R$ %.2f",
            valor, taxa, calcularTotal()
        );
        JOptionPane.showMessageDialog(null, msg);
    }
}