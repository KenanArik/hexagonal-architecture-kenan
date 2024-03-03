package io.kenan.hexagonalarchitecture.adapters.persistence.entities;


import io.kenan.hexagonalarchitecture.application.domain.BankAccount;

import java.math.BigDecimal;
import javax.persistence.*;
import javax.persistence.Id;


@Entity
public class BankAccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(nullable = false)
    private BigDecimal balance;

    public BankAccountEntity() { }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
    public BankAccount toDomain() {
        return new BankAccount(id,balance);
    }
}

