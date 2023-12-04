package com.example.msusers.domain;

import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class User {

    private String userId;
    private String userName;
    private String fistName;
    private String email;
    private List<Bill> bills;

}
