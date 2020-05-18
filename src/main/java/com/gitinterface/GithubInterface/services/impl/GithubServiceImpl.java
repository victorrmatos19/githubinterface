package com.gitinterface.GithubInterface.services.impl;

import com.gitinterface.GithubInterface.configuration.ApplicationProperties;
import com.gitinterface.GithubInterface.model.Repository;
import com.gitinterface.GithubInterface.model.User;
import com.gitinterface.GithubInterface.services.GithubService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;


/**
 * Class to access github services
 *
 * @author Victor Rodrigues de Matos
 */
@Slf4j
@Setter
@Service
public class GithubServiceImpl implements GithubService {

    private static final String REPOS = "repos";
    private static final String LANGUAGES = "languages";

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

    private String buildListLanguagesFromRepositoryUrl(Repository repository){
        return applicationProperties.getGithubApiUrl() +
                REPOS + "/" +
                getNameFromOwner(repository.getOwner()) + "/" +
                getNameFromRepository(repository) + "/" +
                LANGUAGES;
    }

    private String getNameFromRepository(Repository repository){ return repository.getName(); }

    private String getNameFromOwner(User user){
        return user.getName();
    }
}
