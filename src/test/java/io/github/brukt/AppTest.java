package io.github.brukt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    static String inputPathPrefix = "data/input";
    static String outputPathPrefix = "data/output";
    static String expectedOutputPathPrefix = "data/expected_output";
    /**
     * Rigorous Test :-)
     */
    @Test
    public void mainTest1() {
        testByCase("1");
    }

    @Test
    public void mainTest2() {
        testByCase("2");
    }

    @Test
    public void mainTest3() {
        testByCase("3");
    }

    @Test
    public void calculateReceiptEntryTest() {
        BasketEntry inputEntry = new BasketEntry();
        inputEntry.parseToBasketEntry("1 chocolate bar at 0.85");
        BasketEntry receiptEntry = new BasketEntry(1, "chocolate bar", 0.85f);
        assertTrue(inputEntry.equals(receiptEntry));
    }

    private void testByCase(String caseNo){
        String pathSuffix = caseNo + ".txt";
        String inputPath = inputPathPrefix + pathSuffix;
        String outputPath = outputPathPrefix + pathSuffix;
        String expectedOutputPath = expectedOutputPathPrefix + pathSuffix;
        String fileNames1 = inputPath + "\n" + outputPath + "\n";

        System.setIn(new ByteArrayInputStream(fileNames1.getBytes()));
        App.main(null);
        assertTrue(haveSimilarContent(outputPath, expectedOutputPath));
    }

    private boolean haveSimilarContent(String fileName1, String fileName2){
        boolean areEqual = true;
        try {
            BufferedReader reader1 = new BufferedReader(new FileReader(fileName1));
            BufferedReader reader2 = new BufferedReader(new FileReader(fileName2));
            String line1 = reader1.readLine();
            String line2 = reader2.readLine();

            int lineNum = 1;
            while (line1 != null || line2 != null) {
                if (line1 == null || line2 == null) {
                    areEqual = false;
                    break;
                }
                else if (!line1.equals(line2)) {
                    System.out.println(line1 +" == " +line2);
                    areEqual = false;
                    break;
                }
                line1 = reader1.readLine();
                line2 = reader2.readLine();

                lineNum++;
            }
            reader1.close();
            reader2.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return areEqual;
    }
}
