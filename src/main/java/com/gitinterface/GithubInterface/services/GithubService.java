package com.gitinterface.GithubInterface.services;

import com.gitinterface.GithubInterface.model.Branch;
import com.gitinterface.GithubInterface.model.Commit;
import com.gitinterface.GithubInterface.model.Repository;
import com.gitinterface.GithubInterface.model.User;
import com.gitinterface.GithubInterface.model.connectors.GithubConnector;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Class to access github services
 *
 * @author Victor Rodrigues de Matos
 */

@Slf4j
public class GithubService {

    @Autowired
    private GithubConnector githubConnector;

    public Map<String, Object> listLanguagesFromRepository(Repository repository) {
        try {
            return githubConnector.listLanguagesFromRepository(getNameFromOwner(repository.getOwner()),getNameFromRepository(repository));
        } catch (Exception e){
            log.error("Error when get languages from repository: {}",e.getMessage());
            return null;
        }
    }

    public List<Branch> listBranchesFromRepository(Repository repository) {
        try {
            return githubConnector.listBranchesFromRepository(getNameFromOwner(repository.getOwner()),getNameFromRepository(repository));
        } catch (Exception e){
            log.error("Error when get branches from repository: {}",e.getMessage());
            return null;
        }
    }

    public List<Commit> listCommitsFromRepository(Repository repository) {
        try {
            return githubConnector.listCommitsFromRepository(getNameFromOwner(repository.getOwner()),getNameFromRepository(repository));
        } catch (Exception e){
            log.error("Error when get commits from repository: {}",e.getMessage());
            return null;
        }
    }

    public Commit getCommitFromRepositoryById(Repository repository, String commitId) {
        try {
            return githubConnector.getCommitFromRepositoryById(getNameFromOwner(repository.getOwner()),getNameFromRepository(repository),commitId);
        } catch (Exception e){
            log.error("Error when get commit from repository: {}",e.getMessage());
            return null;
        }
    }

    public static String getNameFromRepository(Repository repository){ return repository.getName(); }

    public static String getNameFromOwner(User user){
        return user.getName();
    }
}
