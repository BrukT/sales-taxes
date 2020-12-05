package io.github.brukt;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Sales Taxes
 *
 */
public class App 
{

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the input file path");
        String inputFilePath = scanner.nextLine();

        System.out.println("Enter the output file path");
        String outputFilePath = scanner.nextLine();

        ArrayList<BasketEntry> basket;
        basket = readInputFile(inputFilePath);

        printTheReceipt(basket,outputFilePath);
        System.out.println("The receipt is printed in: "+ outputFilePath);
    }

    public static BasketEntry calculateReceiptEntry(BasketEntry entry) {
        BasketEntry newEntry = new BasketEntry();
        newEntry.name = entry.name + ":";
        newEntry.quantity = entry.quantity;
        newEntry.cost = entry.quantity * entry.cost;
        float tax = 0.0f;
        if (!Tax.isExempted(newEntry.name)) {
            tax += entry.cost * 0.10f;
        }
        if (Tax.isImported(newEntry.name)) {
            tax += entry.cost * 0.05f;
        }
        newEntry.cost += Tax.roundTax(tax) * entry.quantity;

        return newEntry;
    }

    private static ArrayList<BasketEntry> readInputFile(String inputFilePath){
        ArrayList<BasketEntry> basket = new ArrayList<>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(inputFilePath));
            String line = reader.readLine();
            while (line != null) {
                BasketEntry newEntry = new BasketEntry();
                newEntry.parseToBasketEntry(line);
                basket.add(newEntry);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return basket;
    }

    public static void printTheReceipt(ArrayList<BasketEntry> basket, String outputFilePath){
        ArrayList<BasketEntry> receipt = new ArrayList<>();
        float taxSum = 0.0f;
        float totalCost = 0.0f;
        for (BasketEntry basketItem : basket) {
            BasketEntry receiptEntry = calculateReceiptEntry(basketItem);
            receipt.add(receiptEntry);
            taxSum += receiptEntry.cost - (basketItem.cost * basketItem.quantity);
            totalCost += receiptEntry.cost;
        }

        /*if the output file exists delete it*/
        File file = new File(outputFilePath);
        if(file.exists()){
            boolean deleted = file.delete();
            if (!deleted){
                throw new RuntimeException("Output file exists and it can't be deleted.");
            }
        }

        FileWriter outputWriter;
        try {
            outputWriter = new FileWriter(outputFilePath);
            String line;
            for (BasketEntry entry : receipt) {
                line = String.format("%d %s %.02f\n", entry.quantity, entry.name, entry.cost);
                outputWriter.write(line);
            }
            line = String.format("Sales Taxes: %.02f\n", taxSum);
            outputWriter.write(line);
            line = String.format("Total: %.02f\n", totalCost);
            outputWriter.write(line);
            outputWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
