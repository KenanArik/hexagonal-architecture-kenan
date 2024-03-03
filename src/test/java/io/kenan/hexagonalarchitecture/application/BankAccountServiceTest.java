package io.kenan.hexagonalarchitecture.application;

import io.kenan.hexagonalarchitecture.application.domain.BankAccount;
import io.kenan.hexagonalarchitecture.application.exceptions.InsufficientBalanceException;
import io.kenan.hexagonalarchitecture.application.parameterResolvers.BankAccountServiceParameterResolver;
import io.kenan.hexagonalarchitecture.application.services.BankAccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(BankAccountServiceParameterResolver.class)
public class BankAccountServiceTest {
    @Test
    public void should_create_and_store_account(BankAccountService accountService){
        BankAccount newAccount = accountService.openAccount(1,BigDecimal.valueOf(1500));
        assertNotNull(newAccount.getId());
        assertEquals(newAccount.getBalance(),BigDecimal.valueOf(1500));

        BankAccount storedAccount = accountService.getBankAccount(newAccount.getId());
        assertEquals(newAccount.getId(), storedAccount.getId());
        assertEquals(newAccount.getBalance(),storedAccount.getBalance());
    }

    @Test
    public void should_deposit_money_successfully(BankAccountService accountService) {
        BankAccount account = accountService.openAccount(1,BigDecimal.valueOf(5000));
        accountService.deposit(account.getId(), BigDecimal.valueOf(1000));
        assertEquals(BigDecimal.valueOf(6000), accountService.getBankAccount(account.getId()).getBalance());

    }

    @Test
    public void should_fail_withdraw_because_insufficient_balance(BankAccountService accountService) {
        BankAccount account = accountService.openAccount(1,BigDecimal.valueOf(2000));
        assertThrows(InsufficientBalanceException.class,
                () -> accountService.withdraw(account.getId(), BigDecimal.valueOf(4000))
        );
    }
}
