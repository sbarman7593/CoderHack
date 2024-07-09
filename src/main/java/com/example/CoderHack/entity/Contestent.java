package com.example.CoderHack.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "contestents")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contestent {
    @Id
    private String userId;
    private String name;
    private int score;
    private List<String> badges = new ArrayList<>();
}
