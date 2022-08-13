package com.example.command;

import com.google.common.collect.Lists;

import java.util.List;

public class CommandDemo {

    public static void main(String[] args) {
        BankAccount ba = new BankAccount();
        System.out.println(ba);
        List<BankAccountCommand> bankAccountCommands = List.of(
                new BankAccountCommand(ba, BankAccountCommand.Action.DEPOSIT, 100),
                new BankAccountCommand(ba, BankAccountCommand.Action.WITHDRAW, 1000)
        );
        bankAccountCommands.forEach(command -> {
            command.call();
            System.out.println(ba);
        });
        for (Command c : Lists.reverse(bankAccountCommands)) {
            c.undo();
            System.out.println(ba);
        }
    }
}

interface Command {
    void call();
    void undo();
}

class BankAccountCommand implements Command {
    private final BankAccount bankAccount;
    private boolean succeeded;
    public enum Action { DEPOSIT, WITHDRAW}
    private int amount;
    private Action action;

    public BankAccountCommand(BankAccount account, Action action, int amount) {
        this.amount = amount;
        this.bankAccount = account;
        this.action = action;
    }

    @Override
    public void call() {
        switch (action) {
            case DEPOSIT -> {succeeded = true; bankAccount.deposit(amount);}
            case WITHDRAW -> succeeded = bankAccount.withDraw(amount);
        }
    }

    @Override
    public void undo() {
        if (!succeeded) return;
        switch (action) {
            case DEPOSIT -> bankAccount.withDraw(amount);
            case WITHDRAW -> bankAccount.deposit(amount);
        }
    }
}

class BankAccount {
    private int balance;
    private final int overDraftLimit = -500;
    public void deposit(int amount) {
        balance += amount;
        System.out.println("Deposited " + amount +
                ", balance is now " + balance);
    }
    public boolean withDraw(int amount) {
        if (balance - amount >= overDraftLimit) {
            balance -= amount;
            System.out.println("Withdrew " + amount + ", balance is now " + balance);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "balance=" + balance +
                '}';
    }
}
