package com.gitinterface.GithubInterface.services;

import com.gitinterface.GithubInterface.model.Branch;
import com.gitinterface.GithubInterface.model.Commit;
import com.gitinterface.GithubInterface.model.Repository;

import java.util.List;
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

    /**
     * Get branchs from repository
     *
     * @param repository - Repository data
     *
     * @return List branches.
     */
    List<Branch> listBranchesFromRepository(Repository repository);

    /**
     * Get commits from repository
     *
     * @param repository - Repository data
     *
     * @return List commits.
     */
    List<Commit> listCommitsFromRepository(Repository repository);

}
