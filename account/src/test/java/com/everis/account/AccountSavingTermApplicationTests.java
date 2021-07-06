package com.everis.account;

import com.everis.account.dao.entity.Associated;
import com.everis.account.dao.entity.common.AccountFixedTermProduct;
import com.everis.account.dao.entity.common.AccountSavingProduct;
import com.everis.account.dao.entity.common.personal.ClientPersonal;
import com.everis.account.dao.entity.personal.AccountPersonalFixedTerm;
import com.everis.account.dao.entity.personal.AccountPersonalSaving;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class AccountSavingTermApplicationTests {

	@Value("${server.port}")
	String port;

	@Test
	public void postAccount(){
		ArrayList<Associated> list = new ArrayList<>();
		list.add(new Associated("Bruno", ""));
		list.add(new Associated("Mathias", ""));

		AccountPersonalSaving account = AccountPersonalSaving.builder()
				.accountNumber("12345678")
				.accountNumberCard("123456789")
				.client(ClientPersonal.builder()
						.idClient(UUID.fromString("c010cafd-051a-432d-9d52-ec578a37b708"))
						.build()
				)
				.product(AccountSavingProduct.builder()
						.idProduct(UUID.fromString("a27eb62-13dd-453f-bb12-d7ec1c00e377"))
						.status("Activa")
						.limitMovements(1)
						.currency("SOLES")
						.status("Activo")
						.build()
				)
				.amountAvailable(BigDecimal.valueOf(0))
				.associated(list)
				.build();

		try {
			WebTestClient.bindToServer()
					.baseUrl("http://localhost:" + port)
					.build()
					.post()
					.uri("/bank/account/saving/personal")
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					.bodyValue(account)
					.exchange()
					.expectStatus().isCreated();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
