/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Henrique
 */
public class Util {

    public Date LocalDate_To_Date(LocalDate localDate) {
        Instant instant = localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    public String Date_To_String(Date date) {
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        return f.format(date);
    }

    public Boolean ValidarCPFCNPJ(String value) {
        Mask mask = new Mask();
        value = mask.Desmascarar(value);
        if (value.length() < 11) {
            return false;
        }
        if (value.length() > 11 && value.length() < 14) {
            return false;
        }

        String v;
        if (value.length() == 11) {
            if (value.equals("00000000000") || value.equals("11111111111")
                    || value.equals("22222222222") || value.equals("33333333333")
                    || value.equals("44444444444") || value.equals("55555555555")
                    || value.equals("66666666666") || value.equals("77777777777")
                    || value.equals("88888888888") || value.equals("99999999999")
                    || (value.length() != 11)) {
                return false;
            }
            int sum1 = 0;
            int sum2 = 0;
            int resto1 = 0;
            int resto2 = 0;

            for (int i = 0; i < 9; i++) {
                v = value.substring(i, i + 1);
                sum1 += (Integer.parseInt(v)) * (10 - i);
                sum2 += (Integer.parseInt(v)) * (11 - i);
            }
            v = value.substring(9, 10);
            sum2 += (Integer.parseInt(v)) * (11 - 9);

            resto1 = 11 - (sum1 % 11);
            resto2 = 11 - (sum2 % 11);

            if (resto1 > 9) {
                resto1 = 0;
            }
            if (resto2 > 9) {
                resto2 = 0;
            }

            if (Integer.parseInt(value.substring(9, 10)) != resto1) {
                return false;
            }
            if (Integer.parseInt(value.substring(10, 11)) != resto2) {
                return false;
            }

            return true;

        }
        if (value.length() == 14) {

            if (value.equals("00000000000000") || value.equals("11111111111111")
                    || value.equals("22222222222222") || value.equals("33333333333333")
                    || value.equals("44444444444444") || value.equals("55555555555555")
                    || value.equals("66666666666666") || value.equals("77777777777777")
                    || value.equals("88888888888888") || value.equals("99999999999999")
                    || (value.length() != 14)) {
                return false;
            }

            int sum1 = 0;
            int sum2 = 0;
            int resto1 = 0;
            int resto2 = 0;
            int j = 2;
            int k = 3;
            for (int i = 12; i > 0; i--) {
                v = value.substring(i - 1, i);
                sum1 += (Integer.parseInt(v)) * j;
                sum2 += (Integer.parseInt(v)) * k;
                j++;
                k++;
                if (j == 10) {
                    j = 2;
                }
                if (k == 10) {
                    k = 2;
                }
            }

            v = value.substring(12, 13);

            sum2 += (Integer.parseInt(v)) * 2;

            resto1 = (sum1 % 11);
            resto2 = (sum2 % 11);

            if (resto1 == 0 || resto1 == 1) {
                resto1 = 0;
            }
            if (resto2 == 0 || resto2 == 1) {
                resto2 = 0;
            }

            if (resto1 > 1) {
                resto1 = 11 - resto1;
            }
            if (resto2 > 1) {
                resto2 = 11 - resto2;
            }

            if (Integer.parseInt(value.substring(12, 13)) != resto1) {
                return false;
            }
            if (Integer.parseInt(value.substring(13, 14)) != resto2) {
                return false;
            }

            return true;

        }

        return false;
    }

    public Boolean ValidarIdade(Date dt_nascimento) {

        Calendar dataNasc = Calendar.getInstance();
        Calendar dataAtual = Calendar.getInstance();
        try {
            dataNasc.setTime(new SimpleDateFormat("dd/MM/yyyy").parse(Date_To_String(dt_nascimento)));
            dataNasc.add(Calendar.YEAR, 18);
            if (dataNasc.before(dataAtual)) {
                return true;
            } else {
                return false;
            }
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
