package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

class WithdrawCommand implements Command{
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH+"withdraw_en", Locale.ENGLISH);

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(ConsoleHelper.askCurrencyCode());
        while (true) {
            try {
                ConsoleHelper.writeMessage(res.getString("specify.amount"));
                int money = Integer.parseInt(ConsoleHelper.readString());
                if (money < 0)
                    throw new NumberFormatException();
                if (manipulator.isAmountAvailable(money)) {
                    for (Map.Entry<Integer, Integer> pair : manipulator.withdrawAmount(money).entrySet())
                        ConsoleHelper.writeMessage("\t" + pair.getKey() + " - " + pair.getValue());

                    ConsoleHelper.writeMessage(String.format(res.getString("success.format"), money, manipulator.getCurrencyCode()));
                    return;
                } else
                    ConsoleHelper.writeMessage(res.getString("not.enough.money"));
            } catch (NumberFormatException e) {
                ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
            } catch (NotEnoughMoneyException e) {
                ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
            }
        }
    }
}
