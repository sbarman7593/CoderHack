package com.example.CoderHack.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import lombok.Data;

@Document(collection = "contestents")
@Data
public class Contestent {
    @Id
    private String userId;
    private String name;
    private int score;
    private List<String> badges = new ArrayList<>();
}
