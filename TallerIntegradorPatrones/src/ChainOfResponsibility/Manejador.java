/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ChainOfResponsibility;

public interface Manejador
{    
    public int getCantidad();
    public double getDenominacion();
    public void setCantidad(int cantidad);

    public void setNext(Manejador m);
    
    public boolean retirar(double monto);
    public boolean depositar(double cantidad, double denominacion);
}

