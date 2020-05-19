package com.gitinterface.GithubInterface.model.requests;

import com.gitinterface.GithubInterface.model.Repository;
import lombok.Getter;

@Getter
public class ListCommitsFromRepositoryRequest extends RepositoryRequest {

    private static final String COMMITS = "commits";

    public static String buildRequest(Repository repository, String urlPath){
        return  urlPath +
                REPOS + "/" +
                getNameFromOwner(repository.getOwner()) + "/" +
                getNameFromRepository(repository) + "/" +
                COMMITS;
    }
}
