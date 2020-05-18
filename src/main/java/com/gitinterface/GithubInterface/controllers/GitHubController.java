package com.gitinterface.GithubInterface.controllers;

import com.gitinterface.GithubInterface.model.Repository;
import com.gitinterface.GithubInterface.services.GithubService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
