package com.desafio.concrete.solutions.cadastroservice.entrypoints;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void methodNotAvailableTest_pathBarra() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/",
                String.class)).contains("{\"mensagem\":\"Endpoint não implementado!\"}");
    }

    @Test
    public void methodNotAvailableTest_pathOtherApi() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/api",
                String.class)).contains("{\"mensagem\":\"Endpoint não implementado!\"}");
    }

    @Test
    public void methodNotAvailableTest_pathOtherApiOutro() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/api/outro",
                String.class)).contains("{\"mensagem\":\"Endpoint não implementado!\"}");
    }
}