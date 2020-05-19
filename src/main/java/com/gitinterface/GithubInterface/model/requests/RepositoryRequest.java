package com.gitinterface.GithubInterface.model.requests;

import com.gitinterface.GithubInterface.configuration.ApplicationProperties;
import com.gitinterface.GithubInterface.model.Repository;
import com.gitinterface.GithubInterface.model.User;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@Getter
public abstract class RepositoryRequest {

    protected static final String REPOS = "repos";

    public static String getNameFromRepository(Repository repository){ return repository.getName(); }

    public static String getNameFromOwner(User user){
        return user.getName();
    }
}
