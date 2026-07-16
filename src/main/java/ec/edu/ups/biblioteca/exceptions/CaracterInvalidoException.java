/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.biblioteca.exceptions;

/**
 *
 * @author USUARIO
 */
public class CaracterInvalidoException extends Exception{
    public CaracterInvalidoException(){
        
    }
    
    public CaracterInvalidoException(String mensaje){
        super(mensaje);
    }
}
