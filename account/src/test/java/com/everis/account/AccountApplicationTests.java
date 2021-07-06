package com.everis.account;

import com.everis.account.dao.entity.Associated;
import com.everis.account.dao.entity.common.AccountCurrentProduct;
import com.everis.account.dao.entity.personal.AccountPersonalCurrent;
import com.everis.account.dao.entity.common.personal.ClientPersonal;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.UUID;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class AccountApplicationTests {

    @Value("${server.port}")
    String port;

    @Test
    public void createAccountPersonalCurrent() {
        ArrayList<Associated> list = new ArrayList<>();
        list.add(new Associated("Bruno", ""));
        list.add(new Associated("Mathias", ""));

        AccountPersonalCurrent account = AccountPersonalCurrent.builder()
                .accountNumber("12345678")
                .accountCardNumber("123456789")
                .client(ClientPersonal.builder()
                            .idClient(UUID.fromString("17f8cf85-5331-4c31-84eb-75098686b111"))
                        .build()
                )
                .accountCurrentProduct(AccountCurrentProduct.builder()
                        .idPCheckingAccount(UUID.fromString("bce4c653-14bf-4b45-9d1a-125cc803fc26"))
                        .build()
                )
                .amountAvailable(BigDecimal.valueOf(0))
                .associates(list)
                .build();

        try {
            WebTestClient.bindToServer()
                    .baseUrl("http://localhost:" + port)
                    .build()
                    .post()
                    .uri("/bank/account/current/personal")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .bodyValue(account)
                    .exchange()
                    .expectStatus().isCreated();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findAccountByAccountNumber() {
        String accountnumber = "12345678";
        try {
            WebTestClient.bindToServer()
                    .baseUrl("http://localhost:" + port)
                    .build()
                    .get()
                    .uri("/bank/account/current/personal/accountnumber/" + accountnumber)
                    .accept(MediaType.APPLICATION_JSON)
                    .exchange()
                    .expectStatus().isOk();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findAccountByDni() {
        String accountnumber = "12345678";
        try {

           val result = WebTestClient.bindToServer()
                    .baseUrl("http://localhost:" + port)
                    .build()
                    .get()
                    .uri("/bank/account/current/personal/dni/" + accountnumber)
                    .exchange()
                    .expectStatus();


            StepVerifier.create(s -> result.isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
