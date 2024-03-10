package io.kenan.hexagonalarchitecture.infrastructures.controllers;

import io.kenan.hexagonalarchitecture.application.domain.BankAccount;
import io.kenan.hexagonalarchitecture.application.services.BankAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/account")
public class BankAccountController {


    /*
    private final DepositUseCase depositUseCase;
    private final WithdrawUseCase withdrawUseCase;

    public BankAccountController(DepositUseCase depositUseCase, WithdrawUseCase withdrawUseCase) {
        this.depositUseCase = depositUseCase;
        this.withdrawUseCase = withdrawUseCase;
    }

    @PostMapping(value = "/{id}/deposit/{amount}")
    void deposit(@PathVariable final int id, @PathVariable final BigDecimal amount) {
        depositUseCase.deposit(id, amount);
    }

    @PostMapping(value = "/{id}/withdraw/{amount}")
    void withdraw(@PathVariable final int id, @PathVariable final BigDecimal amount) {
        withdrawUseCase.withdraw(id, amount);
    }

     */

    //**********************************************************************************************************
    private final BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankAccount> viewAccount(@PathVariable int id){
        return ok(bankAccountService.getBankAccount(id));
    }

    @PostMapping(value = "/{id}/deposit/{amount}")
    public ResponseEntity<BankAccount> deposit(@PathVariable final int id, @PathVariable final BigDecimal amount) {
        return ok(bankAccountService.deposit(id, amount));
    }

    @PostMapping(value = "/{id}/withdraw/{amount}")
    public ResponseEntity<BankAccount> withdraw(@PathVariable final int id, @PathVariable final BigDecimal amount) {
        return ok(bankAccountService.withdraw(id, amount));
    }





}
