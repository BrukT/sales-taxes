package io.github.brukt;

import org.junit.Test;

import static org.junit.Assert.*;

public class TaxTest{

    @Test
    public void roundTaxTest1() {
        float value1 = 2.23f;
        assertEquals(2.25f, Tax.roundTax(value1), 0.001f);
    }

    @Test
    public void roundTaxTest2(){
        float value2 = 2.84f;
        assertEquals(2.85f, Tax.roundTax(value2), 0.001f);
    }

    @Test
    public void roundTaxTest3(){
        float value2 = 1.499f;
        assertEquals(1.50f, Tax.roundTax(value2), 0.001f);
    }

    @Test
    public void roundTaxTest4(){
        float value2 = 1.999f;
        assertEquals(2.00f, Tax.roundTax(value2), 0.001f);
    }
}