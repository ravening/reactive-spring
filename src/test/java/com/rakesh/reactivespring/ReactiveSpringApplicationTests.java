package com.rakesh.reactivespring;

import com.rakesh.reactivespring.github.config.model.GithubRepo;
import com.rakesh.reactivespring.github.config.model.RepoRequest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import org.assertj.core.api.Assertions;
import reactor.core.publisher.Mono;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ReactiveSpringApplicationTests {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void contextLoads() {
    }

//    @Test
    @Order(1)
    public void testCreateGithubRepository() {
        RepoRequest repoRequest = new RepoRequest("test-webclient-repository", "Repository created for testing WebClient");

        webTestClient.post().uri("/api/repos")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(repoRequest), RepoRequest.class)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.name").isNotEmpty()
                .jsonPath("$.name").isEqualTo("test-webclient-repository");
    }

    @Test
    @Order(2)
    void testGetAllRepositories() {
        webTestClient.get().uri("/api/repos")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(GithubRepo.class)
        ;

    }

    @Test
    @Order(3)
    void testGetSingleRepositories() {
        webTestClient.get().uri("/api/repos/1")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .consumeWith(response ->
                        Assertions.assertThat(response.getResponseBody()).isNotEmpty())
        ;
    }
}
