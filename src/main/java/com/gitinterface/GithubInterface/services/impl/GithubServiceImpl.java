package com.gitinterface.GithubInterface.services.impl;

import com.gitinterface.GithubInterface.configuration.ApplicationProperties;
import com.gitinterface.GithubInterface.model.Branch;
import com.gitinterface.GithubInterface.model.Commit;
import com.gitinterface.GithubInterface.model.Repository;
import com.gitinterface.GithubInterface.model.User;
import com.gitinterface.GithubInterface.model.requests.GetCommitFromRepositoryByIdRequest;
import com.gitinterface.GithubInterface.model.requests.ListBranchesFromRepositoryRequest;
import com.gitinterface.GithubInterface.model.requests.ListCommitsFromRepositoryRequest;
import com.gitinterface.GithubInterface.model.requests.ListLanguagesFromRepositoryRequest;
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

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ApplicationProperties applicationProperties;

    @Override
    public Map<String, Object> listLanguagesFromRepository(Repository repository) {
        try {
            String url = ListLanguagesFromRepositoryRequest.buildRequest(repository,applicationProperties.getGithubApiUrl());
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
            String url = ListBranchesFromRepositoryRequest.buildRequest(repository,applicationProperties.getGithubApiUrl());
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
            String url = ListCommitsFromRepositoryRequest.buildRequest(repository, applicationProperties.getGithubApiUrl());
            log.info("Accessing that url path: {}", url);
            return restTemplate.getForObject(url,List.class);
        } catch (RestClientException e){
            log.error("Error when get commits from repository: {}",e.getMessage());
            return null;
        }
    }

    @Override
    public Commit getCommitFromRepositoryById(Repository repository, String commitId) {
        try {
            String url = GetCommitFromRepositoryByIdRequest.buildRequest(repository,commitId, applicationProperties.getGithubApiUrl());
            log.info("Accessing that url path: {}", url);
            return restTemplate.getForObject(url,Commit.class);
        } catch (RestClientException e){
            log.error("Error when get commit from repository: {}",e.getMessage());
            return null;
        }
    }
}
