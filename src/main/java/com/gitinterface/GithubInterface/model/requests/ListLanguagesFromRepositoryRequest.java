package com.gitinterface.GithubInterface.model.requests;

import com.gitinterface.GithubInterface.model.Repository;
import lombok.Getter;

@Getter
public class ListLanguagesFromRepositoryRequest extends RepositoryRequest{

    private static final String LANGUAGES = "languages";

    public static String buildRequest(Repository repository, String urlPath){
        return  urlPath +
                REPOS + "/" +
                getNameFromOwner(repository.getOwner()) + "/" +
                getNameFromRepository(repository) + "/" +
                LANGUAGES;
    }
}

