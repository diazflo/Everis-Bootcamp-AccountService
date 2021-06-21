package com.everis.account;

import com.everis.account.dao.entity.Associated;
import com.everis.account.dao.entity.common.AccountFixedTermProduct;
import com.everis.account.dao.entity.common.personal.ClientPersonal;
import com.everis.account.dao.entity.personal.AccountPersonalFixedTerm;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class AccountFixedTermApplicationTests {

	private int port = 9001;

	@Test
	public void postAccount(){
		ArrayList<Associated> list = new ArrayList<>();
		list.add(new Associated("Bruno", ""));
		list.add(new Associated("Mathias", ""));

		AccountPersonalFixedTerm account = AccountPersonalFixedTerm.builder()
				.accountNumber("12345678")
				.accountCardNumber("123456789")
				.client(ClientPersonal.builder()
						.idClient(UUID.fromString("c010cafd-051a-432d-9d52-ec578a37b708"))
						.build()
				)
				.accountFixedTermProduct(AccountFixedTermProduct.builder()
						.idProduct(UUID.fromString("a27eb62-13dd-453f-bb12-d7ec1c00e377"))
						.status("Activa")
						.dateMovement(new Date())
						.typeProduct("Cuenta Plazo Fijo")
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
					.uri("/bank/account/fxt/personal")
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
