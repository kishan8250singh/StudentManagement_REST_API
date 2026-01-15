package com.springboot.demoSpring.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {


    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;  // 👈 visible only during POST/PUT, hidden in GET


    private String username;
    private String email;

    private String role;

    private int attendance;
    private Map<String, Integer> marks;

    public StudentDto(String username, String email) {
    }
}
