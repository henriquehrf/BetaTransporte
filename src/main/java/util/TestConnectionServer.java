/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.concurrent.Callable;

/**
 *
 * @author Henrique
 */

/** Classe realiza o teste se o servidor esta disponível **/
public class TestConnectionServer implements Callable<Integer> {

    public static Boolean CONNECTION_SERVER;

    @Override
    public Integer call() {

        try {

            java.net.URL isOnline;

            isOnline = new java.net.URL("https://www.google.com.br");
            java.net.URLConnection conn = isOnline.openConnection();
            java.net.HttpURLConnection httpConn = (java.net.HttpURLConnection) conn;
            httpConn.connect();
            int x = httpConn.getResponseCode();
            if (x == 200) {
                CONNECTION_SERVER = true;
            } else {
                CONNECTION_SERVER = false;
            }

            return 0;

        } catch (Exception ex) {
            CONNECTION_SERVER = false;
            return 0;
        }
    }

}
