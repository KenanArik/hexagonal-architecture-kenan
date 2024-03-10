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
