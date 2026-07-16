/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.biblioteca.exceptions;

/**
 *
 * @author User
 */
public class FormatoInvalidoException extends Exception{
    
    public FormatoInvalidoException(){
        
    }
    
    public FormatoInvalidoException(String mensaje){
        super(mensaje);
    }
    
}
