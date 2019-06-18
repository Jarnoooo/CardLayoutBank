/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardlayout;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author jarno
 */
public class Printen {
    public Printen(int x){
        DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;
        String formattedDate = formatter.format(LocalDate.now());
        System.out.println("Datum: " +formattedDate);
        System.out.println("Nieuw saldo = "+x);
    }
}
