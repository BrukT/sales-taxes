package io.github.brukt;
/*
* calculate total tax
* do rounding of floating point numbers
* */
class Tax {
    static String[] exmptedItems = {"chocolate", "book", "pills"};

    public static boolean isExempted(String name) {
        for (String item : exmptedItems) {
            if (name != null && item != null && item.length() <= name.length() && name.contains(item)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isImported(String name) {
        String item = "imported";
        return name != null && item.length() <= name.length() && name.contains(item);
    }


    public static float roundTax(float input){
        String output = String.format("%.02f",(float)Math.round(input * 100.00) / 100.00);
        char[] digits = output.toCharArray();
        if (digits[digits.length - 1] < '5' && digits[digits.length - 1] != '0'){
            digits[digits.length - 1] = '5';
            output = String.valueOf(digits);
        }else if (digits[digits.length - 1] > '5' && digits[digits.length - 1] != '0'){
            digits[digits.length - 1] = '0';
            output = String.format("%.02f", Float.parseFloat(String.valueOf(digits)) + 0.10f);
        }
        return Float.parseFloat(output);
    }
}
