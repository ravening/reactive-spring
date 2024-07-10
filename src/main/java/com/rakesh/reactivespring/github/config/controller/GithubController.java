package com.rakesh.reactivespring.github.config.controller;

import com.rakesh.reactivespring.github.config.AppProperties;
import com.rakesh.reactivespring.github.config.model.GithubRepo;
import com.rakesh.reactivespring.github.config.model.RepoRequest;
import com.rakesh.reactivespring.github.config.service.GithubClient;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
@Slf4j
public class GithubController {

    @Autowired
    private GithubClient githubClient;

    @Autowired
    private AppProperties appProperties;

    @GetMapping("/repos")
    public Flux<GithubRepo> getRepos() {
        return githubClient.listGithubRepo();
    }

    @GetMapping("/repos/{name}")
    public Mono<GithubRepo> getRepo(@PathVariable String name) {
        return githubClient.getRepoByName(appProperties.getGithub().getUsername(), name);
    }

    @PostMapping("/repos")
    public Mono<GithubRepo> createRepo(@RequestBody RepoRequest repoRequest) {
        return githubClient.createGithubRepo(repoRequest);
    }

    @PatchMapping("/repos/{repo}")
    public Mono<GithubRepo> editGithubRepository(@PathVariable String repo, @Valid @RequestBody RepoRequest repoRequest) {
        return githubClient.editGithubRepository(appProperties.getGithub().getUsername(), repo, repoRequest);
    }

    @DeleteMapping("/repos/{repo}")
    public Mono<Void> deleteGithubRepository(@PathVariable String repo) {
        return githubClient.deleteGithubRepository(appProperties.getGithub().getUsername(), repo);
    }
}
