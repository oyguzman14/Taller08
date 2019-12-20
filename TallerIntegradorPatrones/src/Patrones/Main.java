/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Patrones;

import Adapter.CuentaAdapter;
import ChainOfResponsibility.Manejador;
import ChainOfResponsibility.ManejadorDinero;
import Singleton.AtmUK;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Crear un único cajero Automático de dólares con 100 billetes de 20, 100 de 10, 
        // 10 monedas de 0.50, 10 de 0.25 y 1000 de 0.05

        List<CuentaAdapter> lista = new LinkedList<>();
        Manejador manejador20 = new ManejadorDinero(100, 20.0);
        Manejador manejador10 = new ManejadorDinero(100, 10.0);
        Manejador moneda050 = new ManejadorDinero(10, 0.50);
        Manejador moneda025 = new ManejadorDinero(10, 0.25);
        Manejador moneda005 = new ManejadorDinero(1000, 0.05);
        AtmUK atm = AtmUK.getInstancia();
        atm.addManejador(moneda005);
        atm.addManejador(moneda025);
        atm.addManejador(moneda050);
        atm.addManejador(manejador20);
        atm.addManejador(manejador10);

        // Crear 10 cuentas nuevas en dólares locale.US con un saldo inicial entre 100.00 y 1000.00 USD cada una.
        CuentaAdapter cuenta1 = new CuentaAdapter(1, 100);
        CuentaAdapter cuenta2 = new CuentaAdapter(2, 660);
        CuentaAdapter cuenta3 = new CuentaAdapter(3, 300);
        CuentaAdapter cuenta4 = new CuentaAdapter(4, 250);
        CuentaAdapter cuenta5 = new CuentaAdapter(5, 360);
        CuentaAdapter cuenta6 = new CuentaAdapter(6, 450);
        CuentaAdapter cuenta7 = new CuentaAdapter(7, 560);
        CuentaAdapter cuenta8 = new CuentaAdapter(8, 200);
        CuentaAdapter cuenta9 = new CuentaAdapter(9, 850);
        CuentaAdapter cuenta10 = new CuentaAdapter(10, 320);
        lista.add(cuenta1);
        lista.add(cuenta2);
        lista.add(cuenta3);
        lista.add(cuenta4);
        lista.add(cuenta5);
        lista.add(cuenta6);
        lista.add(cuenta7);
        lista.add(cuenta8);
        lista.add(cuenta9);
        lista.add(cuenta10);

        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el id de su cuenta:");
        int ingreso = sc.nextInt();
        for (CuentaAdapter cuenta : lista) {
            if (cuenta.getCuenta().getId() == ingreso) {
                AtmUK.transaction(cuenta);
            }
        }
    }
}
