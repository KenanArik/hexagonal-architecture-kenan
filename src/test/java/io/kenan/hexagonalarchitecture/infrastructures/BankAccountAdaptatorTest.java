package io.kenan.hexagonalarchitecture.infrastructures;

import io.kenan.hexagonalarchitecture.application.domain.BankAccount;
import io.kenan.hexagonalarchitecture.application.parameterResolvers.BankAccountServiceParameterResolver;
import io.kenan.hexagonalarchitecture.application.port.outgoing.BankAccountPort;
import io.kenan.hexagonalarchitecture.application.services.BankAccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc
public class BankAccountAdaptatorTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BankAccountService bankAccountService;

    @SpyBean
    private BankAccountPort bankAccountPort;

    @Test
    public void givenAccount_whenGetAccounts_thenStatus200() throws Exception {
        BankAccount newAccount = bankAccountService.openAccount(1,BigDecimal.valueOf(1000));

        mockMvc.perform(get("/account/" + newAccount.getId())
                .contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(newAccount.getId().toString()))
                .andExpect(jsonPath("$.balance").value(1000));
    }


    @Test
    public void whenPostTransfer_thenStatus200() throws Exception {
        BankAccount account = bankAccountService.openAccount(1,BigDecimal.valueOf(2000));

        mockMvc.perform(post("/account/"+account.getId()+"/deposit/"+500)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Assert that transfer has been done
        assertThat(bankAccountService.getBankAccount(account.getId()).getBalance())
                .isEqualByComparingTo(BigDecimal.valueOf(2500));


    }

    @Test
    public void whenPostTransfer_andInsufficientBalance_thenStatus400_andProblemDetail() throws Exception {
        BankAccount account = bankAccountService.openAccount(1,BigDecimal.valueOf(100));

        mockMvc.perform(post("/account/"+account.getId()+"/withdraw/"+300)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError())
                .andExpect(jsonPath("$.detail").value("RuntimeException:io.kenan.hexagonalarchitecture.application.exceptions.InsufficientBalanceException: Insufficient balance for withdrawal."));
    }

}
