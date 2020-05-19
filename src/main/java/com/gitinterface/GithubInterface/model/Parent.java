package com.gitinterface.GithubInterface.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Parent {

    private String sha;
    private String url;
    private String html_url;

}
