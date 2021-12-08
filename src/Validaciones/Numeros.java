/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Validaciones;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
/**
 *
 * @author JENNY
 */
public class Numeros {
    public static void esNumeroEntero(java.awt.event.KeyEvent evt){
        char c = evt.getKeyChar();
        if((c < '0')||(c > '9')){
            evt.consume();//que no hará nada
             
            JOptionPane.showMessageDialog(null, "Ingresar solo números");
        }
}
    public static boolean ValidarMail(String email) {
        // Patron para validar el email
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
 
        Matcher mather = pattern.matcher(email);
        return mather.find();
    }
}

