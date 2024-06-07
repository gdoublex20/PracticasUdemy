package com.jesus.client.client_practica_app;

import com.jesus.client.client_practica_app.services.EncryptionServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.oidc.StandardClaimNames;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc

class ClientPracticaAppApplicationTests {

	@Autowired
	private MockMvc mvc;

	private String subject;
	private Instant expirationDate;
	private List<GrantedAuthority> authorities;
	private EncryptionServiceImp encryptionService;

	@BeforeEach
	public void setUp() throws Exception{

		String  subject = "pepe";
		Instant expirationDate = Instant.now().plusSeconds(3600);
		authorities = Arrays.asList(
				new SimpleGrantedAuthority("SCOPE_read"),
				new SimpleGrantedAuthority("SCOPE_write")
		);
		encryptionService = new EncryptionServiceImp();
	}

	@Test
	public void encriptarCadena() throws Exception {
		this.mvc.perform(get("/api/pokemon/encrypted/ditto").with(
						jwt()
								.jwt(jwt -> jwt.claim(StandardClaimNames.SUB, subject).expiresAt(expirationDate))
								.authorities(authorities)
				))
				.andExpect(status().isOk());
	}

	@Test
	void encontrarTodosConTokenValido() throws Exception {
		this.mvc.perform(get("/catalogos/carros").with(
						jwt()
								.jwt(jwt -> jwt.claim(StandardClaimNames.SUB, subject).expiresAt(expirationDate))
								.authorities(authorities)
				))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.length()").value(4));
	}
	@Test
	void encontrarPorId() throws Exception {
		this.mvc.perform(get("/catalogos/carros/100").with(
						jwt()
								.jwt(jwt -> jwt.claim(StandardClaimNames.SUB, subject).expiresAt(expirationDate))
								.authorities(authorities)
				))
				.andExpect(status().isOk());
	}
	@Test
	void encontrarPorMarca() throws Exception {
		this.mvc.perform(get("/catalogos/carros/marca/Nissan").with(
						jwt()
								.jwt(jwt -> jwt.claim(StandardClaimNames.SUB, subject).expiresAt(expirationDate))
								.authorities(authorities)
				))
				.andExpect(status().isOk());
	}
	@Test
	void borrarCarroPorId() throws Exception {
		this.mvc.perform(delete("/catalogos/carros/delete/20").with(
						jwt()
								.jwt(jwt -> jwt.claim(StandardClaimNames.SUB, subject).expiresAt(expirationDate))
								.authorities(authorities)
				))
				.andExpect(status().isNoContent());
	}
	@Test
	void encontrarTodosSinToken() throws Exception {
		this.mvc.perform(get("/catalogos/carros"))
				.andExpect(status().isUnauthorized());
	}

	@Test
	void encontrarTodosRolIncorrecto() throws Exception {
		authorities = Arrays.asList(
				new SimpleGrantedAuthority("ADMIN")
		); // Múltiples roles
		this.mvc.perform(get("/catalogos/carros").with(
						jwt()
								.jwt(jwt -> jwt.claim(StandardClaimNames.SUB, subject).expiresAt(expirationDate))
								.authorities(authorities)
				))
				.andExpect(status().isForbidden());
	}

	@Test
	void encontrarPokemonSinToken() throws Exception {
		this.mvc.perform(get("/api/pokemon/ditto"))
				.andExpect(status().isUnauthorized());
	}

	@Test
	void encontrarPokemonConTokenValido() throws Exception {
		this.mvc.perform(get("/api/pokemon/ditto").with(
						jwt()
								.jwt(jwt -> jwt.claim(StandardClaimNames.SUB, subject).expiresAt(expirationDate))
								.authorities(authorities)
				))
				.andExpect(status().isOk());
	}

	@Test
	void accesarAOtroEndpointSinToken() throws Exception {
		mvc.perform(get("/otro-endpoint"))
				.andExpect(status().isUnauthorized());
	}

	@Test
	void accesarAOtroEndpointConToken() throws Exception {
		mvc.perform(get("/otro-endpoint")
						.with(
								jwt()
										.jwt(jwt -> jwt.claim(StandardClaimNames.SUB, subject).expiresAt(expirationDate))
										.authorities(authorities)
						))
				.andExpect(status().isNotFound());
	}

	@Test
	@DirtiesContext
	void createNewCarro() throws Exception {
		this.mvc.perform(post("/catalogos/carros/create")
						.with(jwt()
								.jwt(jwt -> jwt.claim(StandardClaimNames.SUB, subject).expiresAt(expirationDate))
								.authorities(authorities)
						)
						.contentType("application/json")
						.content("""
                        {
                            "nombre" : "March",
                            "marca"  : "Nissan",
                            "modelo" : "2021"
                        }
                        """))
				.andExpect(status().isCreated());

	}

	@Test
	void actualizarCarro() throws Exception {
		this.mvc.perform(put("/catalogos/carros/update/100")
						.with(jwt()
								.jwt(jwt -> jwt.claim(StandardClaimNames.SUB, subject).expiresAt(expirationDate))
								.authorities(authorities)
						)
						.contentType("application/json")
						.content("""
                        {
                            "nombre" : "March",
                            "marca"  : "Nissan",
                            "modelo" : "2021"
                        }
                        """))
				.andExpect(status().isOk());

	}


}
