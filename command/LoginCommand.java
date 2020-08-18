package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.Locale;
import java.util.ResourceBundle;

public class LoginCommand implements Command {
    private ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.class.getPackage().getName() + ".resources.verifiedCards", Locale.ENGLISH);
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH+"login_en", Locale.ENGLISH);

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        ConsoleHelper.writeMessage(res.getString("specify.data"));
        while (true) {
            ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
            ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
            String number = ConsoleHelper.readString();
            if (validCreditCards.containsKey(number) && validCreditCards.getString(number).equals(ConsoleHelper.readString())) {
                ConsoleHelper.writeMessage(res.getString("success.format"));
                break;
            }
            ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), number));
        }
    }

}
