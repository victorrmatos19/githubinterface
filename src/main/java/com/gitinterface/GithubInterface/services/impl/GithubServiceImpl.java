package com.gitinterface.GithubInterface.services.impl;

import com.gitinterface.GithubInterface.configuration.ApplicationProperties;
import com.gitinterface.GithubInterface.model.Branch;
import com.gitinterface.GithubInterface.model.Commit;
import com.gitinterface.GithubInterface.model.Repository;
import com.gitinterface.GithubInterface.model.User;
import com.gitinterface.GithubInterface.services.GithubService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;


/**
 * Class to access github services
 *
 * @author Victor Rodrigues de Matos
 */
@Slf4j
@Setter
public class GithubServiceImpl implements GithubService {

    private static final String REPOS = "repos";
    private static final String LANGUAGES = "languages";
    private static final String BRANCHES = "branches";
    private static final String COMMITS = "commits";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ApplicationProperties applicationProperties;

    @Override
    public Map<String, Object> listLanguagesFromRepository(Repository repository) {
        try {
            String url = buildListLanguagesFromRepositoryUrl(repository);
            log.info("Accessing that url path: {}", url);
            return restTemplate.getForObject(url,Map.class);
        } catch (RestClientException e){
            log.error("Error when get languages from repository: {}",e.getMessage());
            return null;
        }
    }

    @Override
    public List<Branch> listBranchesFromRepository(Repository repository) {
        try {
            String url = buildListBranchesFromRepositoryUrl(repository);
            log.info("Accessing that url path: {}", url);
            return restTemplate.getForObject(url,List.class);
        } catch (RestClientException e){
            log.error("Error when get branches from repository: {}",e.getMessage());
            return null;
        }
    }

    @Override
    public List<Commit> listCommitsFromRepository(Repository repository) {
        try {
            String url = buildListCommitsFromRepositoryUrl(repository);
            log.info("Accessing that url path: {}", url);
            return restTemplate.getForObject(url,List.class);
        } catch (RestClientException e){
            log.error("Error when get commits from repository: {}",e.getMessage());
            return null;
        }
    }

    private String buildListCommitsFromRepositoryUrl(Repository repository){
        return applicationProperties.getGithubApiUrl() +
                REPOS + "/" +
                getNameFromOwner(repository.getOwner()) + "/" +
                getNameFromRepository(repository) + "/" +
                COMMITS;
    }

    private String buildListLanguagesFromRepositoryUrl(Repository repository){
        return applicationProperties.getGithubApiUrl() +
                REPOS + "/" +
                getNameFromOwner(repository.getOwner()) + "/" +
                getNameFromRepository(repository) + "/" +
                LANGUAGES;
    }

    private String buildListBranchesFromRepositoryUrl(Repository repository){
        return applicationProperties.getGithubApiUrl() +
                REPOS + "/" +
                getNameFromOwner(repository.getOwner()) + "/" +
                getNameFromRepository(repository) + "/" +
                BRANCHES;
    }

    private String getNameFromRepository(Repository repository){ return repository.getName(); }

    private String getNameFromOwner(User user){
        return user.getName();
    }
}
