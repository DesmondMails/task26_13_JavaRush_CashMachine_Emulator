package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.*;

import java.util.Collection;
import java.util.ResourceBundle;

class InfoCommand implements Command{
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH+"info_en");
    @Override
    public void execute() {
        ConsoleHelper.writeMessage(res.getString("before"));
        Collection<CurrencyManipulator> currencyManipulators = CurrencyManipulatorFactory.getAllCurrencyManipulators();
        if (currencyManipulators.isEmpty()) {
            ConsoleHelper.writeMessage(res.getString("no.money"));
        }
        for (CurrencyManipulator currencyManipulator : currencyManipulators) {
            if (currencyManipulator.hasMoney()) {
                ConsoleHelper.writeMessage(currencyManipulator.getCurrencyCode().toUpperCase() + " - " + currencyManipulator.getTotalAmount());
            } else {
                ConsoleHelper.writeMessage(res.getString("no.money"));
            }
        }
    }
}
