package com.javarush.task.task26.task2613;



import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class ConsoleHelper {
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));
    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH+"common_en");

    public static void writeMessage(String message){
        System.out.println(message);
    }
    public static String readString() throws InterruptOperationException {
        String s = "";
        try {
            s = bis.readLine();
            if (s.equalsIgnoreCase("exit"))
                {
                    ConsoleHelper.writeMessage("the.end");
                throw new InterruptOperationException();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }
    public static String askCurrencyCode() throws InterruptOperationException {
        writeMessage(res.getString("choose.currency.code"));
        String s = null;
        while (true) {
            s = readString();
            if (s.matches("[a-z,A-Z]{3,3}")){
               s = s.toUpperCase();
               break;
            }else{
                writeMessage(res.getString("invalid.data"));
                continue;
            }
        }
        return s;
    }
    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        writeMessage(res.getString("choose.denomination.and.count.format"));
        String[] strings;
        while (true){
            String s = readString();
            if (s.matches("\\d+ \\d+")){
                strings = s.split(" ");
                if (Integer.parseInt(strings[0]) <= 0 || Integer.parseInt(strings[1])<= 0){
                    writeMessage(res.getString("invalid.data"));
                    continue;
                }else {
                    break;
                }
            }else {
                writeMessage(res.getString("invalid.data"));
                continue;
            }
        }
        return strings;
    }
    public static Operation askOperation() throws InterruptOperationException {
        writeMessage(res.getString("choose.operation"));
        writeMessage("\t1:"+res.getString("operation.INFO"));
        writeMessage("\t2:"+res.getString("operation.DEPOSIT"));
        writeMessage("\t3:"+res.getString("operation.WITHDRAW"));
        writeMessage("\t4:"+res.getString("operation.EXIT"));
        Operation operation;
        while (true) {
            try {
                String s = readString();
                int i = Integer.parseInt(s);
                operation = Operation.getAllowableOperationByOrdinal(i);
                break;
            } catch (IllegalArgumentException e) {
                writeMessage(res.getString("invalid.data"));
                continue;
            }
        }
        return operation;
    }
    public static void printExitMessage(){
        writeMessage(res.getString("the.end"));
    }

}
