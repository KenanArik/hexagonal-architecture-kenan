package io.kenan.hexagonalarchitecture.application.domain;

import io.kenan.hexagonalarchitecture.application.exceptions.InsufficientBalanceException;

import java.math.BigDecimal;

public class BankAccount {

    private Integer id;
    private BigDecimal balance;

    public BankAccount(Integer id, BigDecimal balance) {
        this.id = id;
        this.balance = balance;
    }


    public BankAccount withdraw(BigDecimal amount) {
        if(balance.compareTo(amount) < 0) {
            throw new InsufficientBalanceException("Insufficient balance for withdrawal.");
        }

        return new BankAccount(id, balance.subtract(amount));
    }

    public void deposit(BigDecimal amount) {
        balance = balance.add(amount);
    }

    public Integer getId(){
        return this.id;
    }
    public void setId(Integer pId){
        this.id = pId;
    }

    public BigDecimal getBalance(){
        return this.balance;
    }
    public void setBalance(BigDecimal p){
        this.balance = p;
    }


}
