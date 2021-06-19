package com.everis.account;

import com.everis.account.dao.entity.Account;
import com.everis.account.dao.entity.Client;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.UUID;
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class AccountApplicationTests {

	private int port = 9001;

	@Test
	public void postAccount(){
		Account account = Account.builder()
				.accountNumber("12345678")
				.accountCardNumber("123456789")
				.client(Client.builder()
						.idClient(UUID.fromString("a27eb62-13dd-453f-bb12-d7ec1c00e377"))
						.dni("73620522")
						.typeClient("Persona")
						.build()
				)
				.build();

		try {
			WebTestClient.bindToServer()
					.baseUrl("http://localhost:" + port)
					.build()
					.post()
					.uri("/bank")
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
