/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.biblioteca.exceptions;

/**
 *
 * @author User
 */
public class CampoVacioException extends Exception{
    
    public CampoVacioException(){
        
    }
    
    public CampoVacioException(String mensaje){
        super(mensaje);
    }
    
}
