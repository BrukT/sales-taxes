package io.github.brukt;

public class Tax {
    public float salesTaxPerEntry(BasketEntry entry){
        return 0.0f;
    }

    public float importTaxPerEntry(BasketEntry entry){
        return 0.0f;
    }

    private boolean salesTaxExempt(BasketEntry entry){
        return false;
    }
}
