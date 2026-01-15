package com.springboot.demoSpring.ai.AI_Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.demoSpring.ai.AI_dto.StudentAIResponse;
import com.springboot.demoSpring.entity.StudentUser;
import com.springboot.demoSpring.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;

import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentAIService {

    private final ChatClient chatClient;
    private final StudentRepository studentRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public StudentAIResponse analyzeStudent(Long studentId) {

        StudentUser student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Map<String, Integer> marks = student.getMarks();

        String marksData = marks.entrySet()
                .stream()
                .map(e -> e.getKey() + " = " + e.getValue())
                .collect(Collectors.joining(", "));

        String prompt = """
                You are an academic advisor.
                Analyze the student and respond ONLY in JSON:

                {
                  "performanceSummary": "",
                  "weakAreas": "",
                  "improvementSuggestions": "",
                  "careerRecommendation": ""
                }

                Student Details:
                Name: %s
                Attendance: %d%%
                Role: %s
                Marks: %s
                """.formatted(
                student.getUsername(),
                student.getAttendance(),
                student.getRole(),
                marksData
        );

        try {
            PromptTemplate promptTemplate = new PromptTemplate(prompt);
            Prompt aiPrompt = promptTemplate.create();

            String aiResponse = chatClient
                    .prompt(aiPrompt)
                    .call()
                    .content();

            return objectMapper.readValue(aiResponse, StudentAIResponse.class);

        } catch (Exception e) {
            return StudentAIResponse.builder()
                    .performanceSummary("Student performance analysis currently unavailable.")
                    .weakAreas("Needs further evaluation.")
                    .improvementSuggestions("Focus on weak subjects and attendance.")
                    .careerRecommendation("Explore suitable career paths based on interests.")
                    .build();
        }
    }
}