/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 *
 * @author Henrique
 */
public class Util {
    
    public Date LocalDate_To_Date(LocalDate localDate){
       Instant instant = localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
       return Date.from(instant);
    }
    
}
