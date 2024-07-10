package com.rakesh.reactivespring.github.config.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@Data
public class GithubRepo {
    private Long id;

    private String name;

    @JsonProperty("full_name")
    private String fullName;

    private String description;

    @JsonProperty("private")
    private String isPrivate;

    @JsonProperty("fork")
    private String isFork;

    private String url;

    @JsonProperty("html_url")
    private String htmlUrl;

    @JsonProperty("git_url")
    private String gitUrl;

    @JsonProperty("forks_count")
    private Long forksCount;

    @JsonProperty("stargazers_count")
    private Long stargazersCount;

    @JsonProperty("watchers_count")
    private Long watchersCount;
}
