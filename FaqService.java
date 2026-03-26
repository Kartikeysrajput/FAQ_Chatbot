package com.faq.chatbot;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import java.io.InputStream;
import java.util.*;

@Service
public class FaqService {
    private List<Map<String, Object>> faqs = new ArrayList<>();

    public FaqService() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        InputStream is = getClass().getResourceAsStream("/faqs.json");
        faqs = mapper.readValue(is, List.class);
    }

    public String getAnswer(String userMessage) {
        String lower = userMessage.toLowerCase();
        for (Map<String, Object> faq : faqs) {
            List<String> keywords = (List<String>) faq.get("keywords");
            for (String keyword : keywords) {
                if (lower.contains(keyword)) {
                    return (String) faq.get("answer");
                }
            }
        }
        return "Sorry, I didn't understand that. Try asking about admissions, hostel, exams, or transcripts.";
    }
}