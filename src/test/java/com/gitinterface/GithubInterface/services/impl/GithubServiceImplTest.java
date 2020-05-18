package com.gitinterface.GithubInterface.services.impl;

import com.gitinterface.GithubInterface.configuration.ApplicationProperties;
import com.gitinterface.GithubInterface.model.Branch;
import com.gitinterface.GithubInterface.model.Commit;
import com.gitinterface.GithubInterface.model.Repository;
import com.gitinterface.GithubInterface.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

/**
 * Class to test the {@link GithubServiceImpl} class.
 *
 * @author Victor Rodrigues de Matos
 */
@DisplayName("Test: GithubServiceImpl")
public class GithubServiceImplTest {

    private static final String REPOSITORY_NAME = "repositoryName";
    private static final String USER_NAME = "userName";
    private static final String GIT_PATH = "gitPath";
    private static final String BRANCH_NAME = "branchName";

    @InjectMocks
    private GithubServiceImpl githubServiceImpl;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ApplicationProperties applicationProperties;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        githubServiceImpl.setRestTemplate(restTemplate);
    }

    @Test
    @DisplayName("1.listLanguagesFromRepository: Should return a language map with success")
    public void listLanguagesFromRepositoryWithSuccess(){
        when(applicationProperties.getGithubApiUrl()).thenReturn(GIT_PATH);
        when(restTemplate.getForObject(anyString(), any())).thenReturn(createLanguagesMap());
        Map<String,Object> languages = githubServiceImpl.listLanguagesFromRepository(createRepository());
        assertEquals(languages.size(),2);
        assertFalse(languages.isEmpty());
    }

    @Test
    @DisplayName("2.listLanguagesFromRepository: Should return a language map with error")
    public void listLanguagesFromRepositoryWithError(){
        when(applicationProperties.getGithubApiUrl()).thenReturn(GIT_PATH);
        when(restTemplate.getForObject(anyString(), any())).thenThrow(RestClientException.class);
        Map<String,Object> languages = githubServiceImpl.listLanguagesFromRepository(createRepository());
        assertNull(languages);
    }

    @Test
    @DisplayName("3.listBranchesFromRepository: Should return a branch list with success")
    public void listBranchesFromRepositoryWithSuccess(){
        when(applicationProperties.getGithubApiUrl()).thenReturn(GIT_PATH);
        when(restTemplate.getForObject(anyString(), any())).thenReturn(createBranchList());
        List<Branch> branches = githubServiceImpl.listBranchesFromRepository(createRepository());
        assertEquals(branches.size(),2);
        assertFalse(branches.isEmpty());
    }

    @Test
    @DisplayName("4.listBranchesFromRepository: Should return a branch list with error")
    public void listBranchesFromRepositoryWithError(){
        when(applicationProperties.getGithubApiUrl()).thenReturn(GIT_PATH);
        when(restTemplate.getForObject(anyString(), any())).thenThrow(RestClientException.class);
        List<Branch> branches  = githubServiceImpl.listBranchesFromRepository(createRepository());
        assertNull(branches);
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
