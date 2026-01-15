package com.springboot.demoSpring.ai.AI_dto;

import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentAIResponse {

    private String performanceSummary;
    private String weakAreas;
    private String improvementSuggestions;
    private String careerRecommendation;



}
