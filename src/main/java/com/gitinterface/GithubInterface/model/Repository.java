package com.gitinterface.GithubInterface.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@Getter
@Builder
@ToString
public class Repository {

    private String name;
    private User owner;
    private Map<String,Object> languages;
}
