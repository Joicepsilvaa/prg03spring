/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.atividade08.classes;

/**
 *
 * @author Joice
 */
public class Main {
    
        public static void main(String[] args) {
            
        // Criando as contas de Creusa e Jubileu
      ContaBanco p1 = new ContaBanco();
      p1.setNumConta(1111);
      p1.setDono("Creusa");
      p1.abrirConta("CC");
      
      
      ContaBanco p2 = new ContaBanco();
      p2.setNumConta(2222);
      p2.setDono("Jubileu");
      p2.abrirConta("CP");
      
      
      //teste
      System.out.println("\n TESTES \n");
       
      
      // mostra o estado atual das contas
      p1.estadoAtual();
      p2.estadoAtual(); 
      
      //Creusa e Jubileu fazem depositos:
      p1.depositar(100);
      p2.depositar(500);
      p2.sacar(56);
      p1.sacar(25);
      
      // mostra o estado atual das contas
      p1.estadoAtual();
      p2.estadoAtual();
      
      p1.pagarMensalidade();
      p2.pagarMensalidade();
      
      p1.fecharConta();
      p1.sacar(113);
      p1.estadoAtual();
      p1.fecharConta();
      p1.sacar(100);
      p2.sacar(900);
      

      
      
}
}
