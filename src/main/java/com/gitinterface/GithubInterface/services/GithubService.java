package com.gitinterface.GithubInterface.services;

import com.gitinterface.GithubInterface.model.Repository;

import java.util.Map;

/**
 * Interface service to access github services.
 *
 * @author Victor Rodrigues de Matos
 */
public interface GithubService {

    /**
     * Get languages from repository
     *
     * @param repository - Repository data
     *
     * @return Map languages>.
     */
    Map<String,Object> listLanguagesFromRepository(Repository repository);
}
