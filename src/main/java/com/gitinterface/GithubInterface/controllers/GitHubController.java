package com.gitinterface.GithubInterface.controllers;

import com.gitinterface.GithubInterface.model.Branch;
import com.gitinterface.GithubInterface.model.Commit;
import com.gitinterface.GithubInterface.model.Repository;
import com.gitinterface.GithubInterface.services.GithubService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * GitHubController
 *
 * @author Victor Rodrigues de Matos
 */

@Slf4j
@RestController
@RequestMapping("/github")
public class GitHubController {

    @Autowired
    private GithubService githubService;

    @GetMapping("/listLanguagesFromRepository")
    public ResponseEntity<Map<String,Object>> listLanguagesFromRepository (@RequestBody Repository repository){
        log.info("Accepting listLanguagesFromRepository method: {}");
        Map<String, Object> languages = githubService.listLanguagesFromRepository(repository);
        if(!Objects.isNull(languages)){
            log.info("Get language(s) with success: {}");
            return ResponseEntity.ok(languages);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/listBranchsFromRepository")
    public ResponseEntity<List<Branch>> listBranchsFromRepository(@RequestBody Repository repository) {
        List<Branch> branches = githubService.listBranchesFromRepository(repository);
        if(!Objects.isNull(branches)){
            log.info("Get branch(s) with success: {}");
            return ResponseEntity.ok(branches);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/listCommitsFromRepository")
    public ResponseEntity<List<Commit>> listCommitsFromRepository(@RequestBody Repository repository) {
        List<Commit> commits = githubService.listCommitsFromRepository(repository);
        if(!Objects.isNull(commits)){
            log.info("Get commit(s) with success: {}");
            return ResponseEntity.ok(commits);
        }
        return ResponseEntity.notFound().build();
    }
}
