package com.gitinterface.GithubInterface.services;

import com.gitinterface.GithubInterface.model.Branch;
import com.gitinterface.GithubInterface.model.Commit;
import com.gitinterface.GithubInterface.model.Repository;
import com.gitinterface.GithubInterface.model.User;
import com.gitinterface.GithubInterface.model.connectors.GithubConnector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestClientException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Class to test the {@link GithubService} class.
 *
 * @author Victor Rodrigues de Matos
 */
@DisplayName("Test: GithubService")
public class GithubServiceTest {

    private static final String REPOSITORY_NAME = "repositoryName";
    private static final String USER_NAME = "userName";
    private static final String BRANCH_NAME = "branchName";
    private static final String COMMIT_ID = "commitId";

    @InjectMocks
    private GithubService githubService;

    @Mock
    private GithubConnector githubConnector;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("1.listLanguagesFromRepository: Should return a language map with success")
    public void listLanguagesFromRepositoryWithSuccess() throws Exception {
        when(githubConnector.listLanguagesFromRepository(USER_NAME,REPOSITORY_NAME)).thenReturn(createLanguagesMap());
        Map<String,Object> languages = githubService.listLanguagesFromRepository(createRepository());
        assertEquals(languages.size(),2);
        assertFalse(languages.isEmpty());
    }

    @Test
    @DisplayName("2.listLanguagesFromRepository: Should return a language map with error")
    public void listLanguagesFromRepositoryWithError() throws Exception {
        when(githubConnector.listLanguagesFromRepository(USER_NAME,REPOSITORY_NAME)).thenThrow(Exception.class);
        Map<String,Object> languages = githubService.listLanguagesFromRepository(createRepository());
        assertNull(languages);
    }

    @Test
    @DisplayName("3.listBranchesFromRepository: Should return a branch list with success")
    public void listBranchesFromRepositoryWithSuccess() throws Exception {
        when(githubConnector.listBranchesFromRepository(USER_NAME,REPOSITORY_NAME)).thenReturn(createBranchList());
        List<Branch> branches = githubService.listBranchesFromRepository(createRepository());
        assertEquals(branches.size(),2);
        assertFalse(branches.isEmpty());
    }

    @Test
    @DisplayName("4.listBranchesFromRepository: Should return a branch list with error")
    public void listBranchesFromRepositoryWithError() throws Exception {
        when(githubConnector.listBranchesFromRepository(USER_NAME,REPOSITORY_NAME)).thenThrow(Exception.class);
        List<Branch> branches  = githubService.listBranchesFromRepository(createRepository());
        assertNull(branches);
    }

    @Test
    @DisplayName("5.listCommitsFromRepository: Should return a commits list with success")
    public void listCommitsFromRepositoryWithSuccess() throws Exception {
        when(githubConnector.listCommitsFromRepository(USER_NAME,REPOSITORY_NAME)).thenReturn(createCommitList());
        List<Commit> commits = githubService.listCommitsFromRepository(createRepository());
        assertEquals(commits.size(),2);
        assertFalse(commits.isEmpty());
    }

    @Test
    @DisplayName("6.listCommitsFromRepository: Should return a commits list with error")
    public void listCommitsFromRepositoryWithError() throws Exception {
        when(githubConnector.listCommitsFromRepository(USER_NAME,REPOSITORY_NAME)).thenThrow(Exception.class);
        List<Commit> commits = githubService.listCommitsFromRepository(createRepository());
        assertNull(commits);
    }

    @Test
    @DisplayName("7.getCommitFromRepositoryById: Should return a commit with success")
    public void getCommitFromRepositoryByIdWithSuccess() throws Exception {
        when(githubConnector.getCommitFromRepositoryById(USER_NAME,REPOSITORY_NAME,COMMIT_ID)).thenReturn(createCommit());
        Commit commit = githubService.getCommitFromRepositoryById(createRepository(),COMMIT_ID);
        assertNotNull(commit);
    }

    @Test
    @DisplayName("8.getCommitFromRepositoryById: Should return a commit with error")
    public void getCommitFromRepositoryByIdWithError() throws Exception {
        when(githubConnector.getCommitFromRepositoryById(USER_NAME,REPOSITORY_NAME,COMMIT_ID)).thenThrow(RestClientException.class);
        Commit commit = githubService.getCommitFromRepositoryById(createRepository(),COMMIT_ID);
        assertNull(commit);
    }

    private List<Commit> createCommitList() {
        List<Commit> commits = new ArrayList<>();
        commits.add(createCommit());
        commits.add(createCommit());
        return commits;
    }

    private List<Branch> createBranchList() {
        List<Branch> branchList = new ArrayList<>();
        branchList.add(createBranch());
        branchList.add(createBranch());
        return branchList;
    }

    private Branch createBranch(){
        return Branch.builder()._protected(false).name(BRANCH_NAME).commit(createCommit()).build();
    }

    private Commit createCommit(){
        return Commit.builder().sha("").url("").build();
    }

    private Repository createRepository(){
        return Repository.builder().name(REPOSITORY_NAME).owner(createUser()).build();
    }

    private User createUser(){
        return User.builder().name(USER_NAME).build();
    }

    private Map<String, Object> createLanguagesMap(){
        Map<String,Object> map = new HashMap<>();
        map.put("Java",1233);
        map.put("Phyton",1326);
        return map;
    }
}
