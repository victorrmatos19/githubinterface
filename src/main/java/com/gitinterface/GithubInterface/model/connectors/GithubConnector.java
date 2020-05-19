package com.gitinterface.GithubInterface.model.connectors;

import com.gitinterface.GithubInterface.model.Branch;
import com.gitinterface.GithubInterface.model.Commit;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

/**
 * Interface connector to access github services.
 *
 * @author Victor Rodrigues de Matos
 */
@FeignClient(name = "GithubConnector",url = "https://api.github.com")
public interface GithubConnector {

    /**
     * Get languages from repository
     *
     * @param ownerName - Repository owner
     * @param repoName - Repository Name
     *
     * @return Map languages>.
     */
    @GetMapping("/repos/{ownerName}/{repoName}/languages")
    Map<String, Object> listLanguagesFromRepository(@PathVariable("ownerName") String ownerName, @PathVariable("repoName") String repoName) throws Exception;

    /**
     * Get branches from repository
     *
     * @param ownerName - Repository owner
     * @param repoName - Repository Name
     *
     * @return List branches.
     */
    @GetMapping("/repos/{ownerName}/{repoName}/branches")
    List<Branch> listBranchesFromRepository(@PathVariable("ownerName") String ownerName, @PathVariable("repoName") String repoName) throws Exception;

    /**
     * Get commits from repository
     *
     * @param ownerName - Repository owner
     * @param repoName - Repository Name
     *
     * @return List commits.
     */
    @GetMapping("/repos/{ownerName}/{repoName}/commits")
    List<Commit> listCommitsFromRepository(@PathVariable("ownerName") String ownerName, @PathVariable("repoName") String repoName) throws Exception;

    /**
     * Get commit from repository by id
     *
     * @param ownerName - Repository owner
     * @param repoName - Repository Name
     * @param commitId - commit identification
     *
     * @return Commit
     */
    @GetMapping("/repos/{ownerName}/{repoName}/commits/{commitId}")
    Commit getCommitFromRepositoryById(@PathVariable("ownerName") String ownerName, @PathVariable("repoName") String repoName, @PathVariable("commitId") String commitId) throws Exception;
}
