package io.github.brukt;

import org.junit.Assert;
import org.junit.Test;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import static org.junit.Assert.*;

public class TaxTest{
    Tax tax = new Tax();
    BasketEntry entry1 = new BasketEntry(1, "chocolate", 12.25f);
    BasketEntry entry2 = new BasketEntry(2, "shoes",  45.00f);

    private float round(float input){
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        return Float.parseFloat(df.format(input));
    }

    @Test
    public void testSalesTaxExempt1() {
        /*check for the non exempt material*/
        assertFalse(tax.salesTaxExempt(entry1));
    }

    @Test
    public void testSalesTaxExempt2() {
        /*check for exmpted material*/
        assertTrue(tax.salesTaxExempt(entry2));
    }

    @Test
    public void testImportTax1() {
        float tax1 = round(entry1.cost * 0.05f);
        assertEquals(tax1, tax.importTaxPerEntry(entry1), 0.00f);
    }

    @Test
    public void testImportTax2(){
        float tax2 = round(entry2.cost * 0.05f);
        assertEquals(tax2, tax.importTaxPerEntry(entry2), 0.00f);
    }

    @Test
    public void testSalesTax1(){
        float tax1 = 0.00f;
        assertEquals(tax1, tax.importTaxPerEntry(entry1), 0.00f);
    }

    @Test
    public void testSalesTax2(){
        float tax2 = round(0.1f * entry2.cost);
        assertEquals(tax2, tax.salesTaxPerEntry(entry2), 0.00f);
    }
}