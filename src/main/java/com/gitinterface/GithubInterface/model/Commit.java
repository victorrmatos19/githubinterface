package com.gitinterface.GithubInterface.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Commit {

    private String sha;
    private String url;
    private String node_id;
    private CommitDTO commit;
    private String html_url;
    private String comments_url;
    private Author author;
    private Commiter committer;
    private List<Parent> parents;

}
