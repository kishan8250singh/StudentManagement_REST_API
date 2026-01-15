package com.springboot.demoSpring.ai.AI_Controller;

import com.springboot.demoSpring.ai.AI_Service.StudentAIService;
import com.springboot.demoSpring.ai.AI_dto.StudentAIResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



    @RestController
    @RequestMapping("/students")
    @RequiredArgsConstructor
    public class StudentAIController {

        private final StudentAIService studentAIService;

        @GetMapping("/{id}/ai-analysis")
        public StudentAIResponse analyzeStudent(@PathVariable Long id) {
            return studentAIService.analyzeStudent(id);
        }
    }

