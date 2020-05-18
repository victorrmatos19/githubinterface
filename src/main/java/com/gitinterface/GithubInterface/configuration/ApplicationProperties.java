package com.gitinterface.GithubInterface.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ApplicationProperties {

    @Value("${githubApi.Url}")
    private String githubApiUrl;
}
