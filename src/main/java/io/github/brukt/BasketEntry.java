package io.github.brukt;

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

/*
* a class representing each line on the receipt
*
* */
class BasketEntry {
    public int quantity;
    public String name;
    public float cost;

    public BasketEntry(){    }

    public BasketEntry(int quantity, String name, float cost) {
        this.quantity = quantity;
        this.name = name;
        this.cost = cost;
    }

    public void parseToBasketEntry(String inputLine){
        String [] entryContent= inputLine.split(" ");
        this.quantity = parseInt(entryContent[0]);
        int indexRange = entryContent.length;
        this.cost = parseFloat(entryContent[indexRange-1]);

        StringBuilder nameBuilder = new StringBuilder();
        for (int i = 1; i < indexRange - 2; i++){
            nameBuilder.append(entryContent[i]);
            nameBuilder.append(" ");
        }
        this.name = nameBuilder.substring(0,nameBuilder.length()-1);
    }

    public boolean equals(BasketEntry basketEntry){
        boolean areIdentical = true;

        if(Math.abs(basketEntry.cost - this.cost) > 0.00f
                || !(basketEntry.name).equals(this.name)
                || basketEntry.quantity != this.quantity){
            areIdentical = false;
        }
        return areIdentical;
    }
}
