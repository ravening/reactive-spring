package com.rakesh.reactivespring.github.config.service;

import com.rakesh.reactivespring.github.config.AppProperties;
import com.rakesh.reactivespring.github.config.model.GithubRepo;
import com.rakesh.reactivespring.github.config.model.RepoRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeFilterFunctions;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class GithubClient {
    private static final String GITHUB_V3_MIME_TYPE = "application/vnd.github.v3+json";
    private static final String GITHUB_API_BASE_URL = "https://api.github.com";
    private static final String USER_AGENT = "Spring 5 WebClient";
    private static final Logger logger = LoggerFactory.getLogger(GithubClient.class);

    private final WebClient webClient;

    @Autowired
    public GithubClient(AppProperties appProperties) {
        this.webClient = WebClient.builder()
                .baseUrl(GITHUB_API_BASE_URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, GITHUB_V3_MIME_TYPE)
                .defaultHeader(HttpHeaders.USER_AGENT, USER_AGENT)
                .filter(ExchangeFilterFunctions
                        .basicAuthentication(appProperties.getGithub().getUsername(),
                                appProperties.getGithub().getToken()))
                .filter(logRequest())
                .filter(logResponseStatus())
                .build();
    }

    public Flux<GithubRepo> listGithubRepo() {
        return this.webClient
                .get()
                .uri("/user/repos?sort={sortField}&direction={sortDirection}",
                        "updated", "desc")
                .exchangeToFlux(clientResponse ->
                        clientResponse.bodyToFlux(GithubRepo.class));
    }

    public Flux<GithubRepo> listGithubrepositories() {
        return this.webClient
                .get()
                .uri(uriBuilder -> uriBuilder.path("/user/repos")
                        .queryParam("sort", "updated")
                        .queryParam("direction", "desc")
                        .build())
                .exchangeToFlux(clientResponse ->
                        clientResponse.bodyToFlux(GithubRepo.class));
    }

    public Mono<GithubRepo> createGithubRepo(RepoRequest repoRequest) {
        return this.webClient
                .post()
                .uri("/user/repos")
                .body(Mono.just(repoRequest), RepoRequest.class)
                .exchangeToMono(clientResponse -> clientResponse.bodyToMono(GithubRepo.class));
    }

    public Mono<GithubRepo> getRepoByName(String owner, String repoName) {
        return this.webClient
                .get()
                .uri("/repos/{owner}/{repo}", owner, repoName)
                .exchangeToMono(clientResponse -> clientResponse.bodyToMono(GithubRepo.class));
    }

    private ExchangeFilterFunction logRequest() {
        return (clientRequest, next) -> {
            logger.info("Request: {} {}", clientRequest.method(), clientRequest.url());
            clientRequest.headers()
                    .forEach((name, values) -> values.forEach(value -> logger.info("{}={}", name, value)));
            return next.exchange(clientRequest);
        };
    }

    private ExchangeFilterFunction logResponseStatus() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            logger.info("Status: {}", clientResponse.statusCode());
            return Mono.just(clientResponse);
        });
    }

    public Mono<GithubRepo> editGithubRepository(String owner, String repo, RepoRequest editRepoRequest) {
        return webClient.patch()
                .uri("/repos/{owner}/{repo}", owner, repo)
                .body(BodyInserters.fromObject(editRepoRequest))
                .retrieve()
                .bodyToMono(GithubRepo.class);
    }

    public Mono<Void> deleteGithubRepository(String owner, String repo) {
        return webClient.delete()
                .uri("/repos/{owner}/{repo}", owner, repo)
                .retrieve()
                .bodyToMono(Void.class);
    }
}
