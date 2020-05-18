package com.gitinterface.GithubInterface.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Repository {

    private String name;
    private User owner;
    private Map<String,Object> languages;
}
