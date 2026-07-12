/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.biblioteca.exceptions;

/**
 *
 * @author User
 */
public class TextoInvalidoException extends Exception{
    
    public TextoInvalidoException(){
        
    }
    
    public TextoInvalidoException(String mensaje){
        super(mensaje);
    }
    
}
