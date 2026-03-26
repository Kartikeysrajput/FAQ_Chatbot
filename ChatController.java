package com.faq.chatbot;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ChatController {

    private final FaqService faqService;

    public ChatController(FaqService faqService) {
        this.faqService = faqService;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/chat")
    public ResponseEntity<Map<String, String>> chat(@RequestBody Map<String, String> body) {
        String message = body.get("message");
        String answer = faqService.getAnswer(message);
        return ResponseEntity.ok()
            .header("Access-Control-Allow-Origin", "*")
            .body(Map.of("reply", answer));
    }
}