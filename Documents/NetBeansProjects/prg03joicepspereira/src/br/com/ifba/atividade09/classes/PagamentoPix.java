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
public class PagamentoPix implements InterfacePagamento {
    private double valor;

    public PagamentoPix(double valor) {
        this.valor = valor;
    }

    @Override
    public double calcularTotal() {
        return valor; // paga valor integral
    }

    @Override
    public void imprimirRecibo() {
        double cashback = valor * 0.02;
        String msg = String.format(
            "=== RECIBO (PIX) ===%n" +
            "Valor original: R$ %.2f%n" +
            "Cashback (2%%): R$ %.2f%n" +
            "Total pago: R$ %.2f (recebe de volta R$ %.2f em até 24h)",
            valor, cashback, calcularTotal(), cashback
        );
        JOptionPane.showMessageDialog(null, msg);
    }
}
