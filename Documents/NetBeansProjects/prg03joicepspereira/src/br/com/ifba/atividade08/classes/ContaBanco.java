/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.atividade08.classes;

/**
 *
 * @author Joice
 */
public class ContaBanco {
    
    public int numConta;
    protected String tipo; // tipo da conta (CC = corrente, CP = poupança)
    private String dono;
    private float saldo;
    private boolean status; // true = conta aberta, false = conta fechada
    
   //metodos personalizados:
   //Metodo para exibir o estado atual da conta
    public void estadoAtual() {
        System.out.println("\n------------------------------");
        System.out.println("conta: " +  this.getNumConta());
        System.out.println("tipo: " +  this.getTipo());
        System.out.println("Dono: " +  this.getDono());
        System.out.println("Saldo: " +  this.getSaldo());
        System.out.println("Status: "  +  this.getStatus());
        
    }
    
   // Método para abrir a conta; define o tipo, marca como ativa e adiciona saldo inicial
   public void abrirConta(String t){
    this.setTipo(t);
    this.setStatus(true);
    
    // Define o saldo inicial com base no tipo da conta
    if("CC".equals(t)){
        this.setSaldo(50);
    } else if ("CP".equals(t)){
        this.setSaldo(150);
    }
   } 
   
   // Método para fechar a conta, desde que não tenah saldo positivo ou negativo
   public void fecharConta(){
       if(this.getSaldo() > 0){
           System.out.println("\n A conta não pode ser fechada, pois ainda tem dinheiro.");
       } else if (this.getSaldo() < 0){
           System.out.println("\nA conta não pode ser fechada pois há debitos com o banco.");
       } else{
           this.setStatus(false);
           System.out.println("\nA conta foi fechada com sucesso.");
       }
   } 
   // Método para depositar um valor na conta, desde que ela esteja ativa
   public void depositar(float v){
       if(this.getStatus()){
        this.setSaldo(this.getSaldo() + v);
           System.out.println("\nDeposito realizado com sucesso na conta de  "  +  this.getDono());
       } else{
           System.out.println("\nNão é possível depositar em uma conta fechada");
       }
   } 
   
   // Método para saque, com verificação de status e saldo suficiente
   public void sacar(float valor) {
    if (!this.getStatus()) {
        System.out.println("\nImpossível realizar saque: a conta está fechada.");
    } else if (this.getSaldo() < valor) {
        System.out.println("\nSaldo insuficiente para saque.");
    } else {
        this.setSaldo(this.getSaldo() - valor);
        System.out.println("\nSaque de R$" + valor + " realizado com sucesso na conta de " + this.getDono());
    }
}

   // Método que cobra a mensalidade com base no tipo da conta
   public void pagarMensalidade(){
       int v = 0;
       if("CC".equals(this.getTipo())) {
           v = 12;
       } else  if("CP".equals(this.getTipo())) {
           v = 20;
       } 
       if(this.getStatus()){
        this.setSaldo(this.getSaldo() - v);
           System.out.println("\nMensalidade paga com sucesso por" + this.getDono());
       } else{
           System.out.println("\nImpossivel pagar uma conta fechada");
       }
       
   } 

   //metodos especiais: 
   
   // Construtor
   public ContaBanco(){
      this.saldo = 0;
      this.status = false;            
   }
   
   // Getters e setters
    public int getNumConta() {
        return numConta;
    }

    public void setNumConta(int numConta) {
        this.numConta = numConta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDono() {
        return dono;
    }

    public void setDono(String dono) {
        this.dono = dono;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
