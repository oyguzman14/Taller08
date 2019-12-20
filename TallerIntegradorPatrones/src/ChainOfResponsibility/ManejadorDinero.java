/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

 
package ChainOfResponsibility;


public class ManejadorDinero implements Manejador
{
    private Manejador next;
    protected int cantidad;
    protected double denominacion;

    public ManejadorDinero(int cantidad, double denominacion) {
        this.cantidad = cantidad; // Total de billetes
        this.denominacion = denominacion; // Valor de cada billete
    }

    @Override
    public int getCantidad() {
        return this.cantidad;
    }

    @Override
    public double getDenominacion() {
        return this.denominacion;
    }

    @Override
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public boolean retirar(double monto) {
        if(cantidad*denominacion>monto){
            cantidad-=(int)(monto/denominacion);
        }
       return false;
    }

    @Override
    public boolean depositar(double cant, double denominacion) {
        cantidad+=cant;
        return false;
    }

    @Override
    public void setNext(ChainOfResponsibility.Manejador m) {
        this.next = m;
    }

}
