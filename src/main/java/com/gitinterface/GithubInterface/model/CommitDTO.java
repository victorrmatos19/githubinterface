package com.gitinterface.GithubInterface.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommitDTO {

    private Author author;
    private Commiter commiter;
    private String message;
    private Tree tree;
    private String url;
    private int comment_count;
    private Verification verification;
}
