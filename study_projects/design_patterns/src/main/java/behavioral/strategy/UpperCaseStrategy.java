package main.java.behavioral.strategy;

import java.util.Locale;

public class UpperCaseStrategy implements TextFormatterStrategy{

    @Override
    public void formatInputString(String text) {
        System.out.println("Uppercase Formatter: ".concat(text.toUpperCase()));
    }
}
