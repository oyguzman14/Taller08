/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Singleton;

import Adapter.Cuenta;
import Adapter.CuentaAdapter;
import ChainOfResponsibility.Manejador;
import Patrones.Account;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;
import java.util.Scanner;

    public class AtmUK {
    private static AtmUK instance=null;
    private static Currency moneda=null;
    private static double dinero = 3057.5;
    private Cuenta cuenta;
    static Scanner in;
    protected static ArrayList <Manejador> manejadores;
    
    // -----------------
    private AtmUK() {
        
    }
    
    public static AtmUK getInstancia()
    {
         if (instance == null) {
             instance = new AtmUK();
             moneda= Currency.getInstance(Locale.US);
             manejadores=new ArrayList<>();
            System.out.println("Su cajero ha sido creado");
        }
        else {
            System.out.println("Ya existe el cajero");
        }
        return instance;
    }
    
    public boolean sacarDinero(double amount) { 
        int contador=0;
        if(dinero>=amount){
           while(contador<amount){
               for(Manejador manejadores: manejadores){
                   manejadores.retirar(amount);
                   contador+=amount;
           } 
        }
        }else{
            System.out.println("Insufficient funds at our ATM, Sorry.. Try later ");
        }
        dinero =dinero - amount;
        return true;
    }
    
    public static double getTotal() {
        return dinero;
    }
    
    public void ingresarDinero(double amount, double denominacion) {
        for(Manejador manejadores: manejadores){
            if(manejadores.getDenominacion()==denominacion){
                manejadores.depositar(amount,denominacion);
            }
        
        }
    dinero += amount;
    }

    public void addManejador(Manejador m){
        manejadores.add(m);
    }
    public Manejador removeManejador(int i){
        return manejadores.remove(i);
    }
    
    

   
    public static void transaction(CuentaAdapter cuenta){
       
        in=new Scanner(System.in);
        int choice; 
        System.out.println("Please select an option"); 
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Balance");
        System.out.println("4. Balance ATM");
        choice= in.nextInt();
        switch(choice){
            case 1:
                float amount; 
                System.out.println("Please enter amount to withdraw: "); 
                amount = in.nextFloat();
                if(amount > cuenta.getCuenta().getAmount() || amount == 0){
                    System.out.println("You have insufficient funds\n\n"); 
                    anotherTransaction(cuenta); // ask if they want another transaction
                } else {
                    if(amount<=cuenta.getCuenta().getAmount()){
                        cuenta.getCuenta().withdraw(amount);
                        instance.sacarDinero(amount);
                        System.out.println("You have withdrawn "+amount+" and your new balance is "+cuenta.Balance());
                        anotherTransaction(cuenta); 
                    }
                   
                }
            break; 
            case 2:
                   
                    float deposit; 
                    System.out.println("Please enter amount you would wish to deposit: "); 
                    deposit = in.nextFloat();
                    System.out.println("Please enter the denomintion dollars you'd wish deposit it: ");
                    int denomination;
                    denomination=in.nextInt();
                    for(Manejador manejador: manejadores){
                        if(manejador.getDenominacion()==denomination){
                            instance.ingresarDinero(deposit, denomination);
                            cuenta.getCuenta().deposit(deposit);
                            System.out.println("You have withdrawn "+deposit+" and your new balance is "+cuenta.Balance());
                            anotherTransaction(cuenta); 
                        }   
                    }
                   
            break; 
            case 3:
                 
                    System.out.println("Your balance is "+cuenta.Balance());
                    anotherTransaction(cuenta); 
            break;
            case 4:
                    
                    System.out.println("The ATM's balance according with our system is :" +getTotal());
                    anotherTransaction(cuenta); 
            break;
            default:
                    System.out.println("Invalid option:\n\n"); 
                    anotherTransaction(cuenta);
            break;
        }
    }
    public static void anotherTransaction(CuentaAdapter cuenta){
        System.out.println("Do you want another transaction?\n\n Press 1 for another transaction\n2 To exit");
        int anotherTransaction = in.nextInt();
        switch (anotherTransaction) {
            case 1:
                transaction(cuenta);
                break;
            case 2:
                System.out.println("Thanks for choosing us. Good Bye!");
                break;
            default:
                System.out.println("Invalid choice\n\n");
                anotherTransaction(cuenta);
                break;
        }
    }
}