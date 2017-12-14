/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.betaTransporte.clienteTest;

import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import util.Util;

/**
 *
 * @author Henrique
 */
@RunWith(value = Parameterized.class)
public class ValidarCpfCnpjTest {

    private String value;
    private Boolean expected;
    
    // teste

    @Parameters
    public static Collection data() {
        return Arrays.asList(new Object[][]{{false, "fwe4342"}, {false, "055.820.631-01"}, {false, "555555555555555"},
        {false, ""}, {false, "75.123.831/0001-5"}, {false, "7512383100010"}, {false, "*(4343"}, {false, " "}, {false, "75.123.831"},
        {false, "000.000.000-00"}, {true, "77.149.888/0001-53"},{true,"87.894.458/0001-10"},
        {true,"395.012.417-93"},{true,"848.873.663-02"},{false,"848.873.663"},{true,"84887366302"}
        });
    }

    public ValidarCpfCnpjTest(Boolean expected, String value) {
        this.value = value;
        this.expected = expected;
    }

    @Test
    public void validarCpf() {
        Util util = new Util();
        assertEquals(expected, util.ValidarCPFCNPJ(value));
    }

}
