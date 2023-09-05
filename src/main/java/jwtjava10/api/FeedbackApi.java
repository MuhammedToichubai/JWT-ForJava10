package jwtjava10.api;

import jwtjava10.dto.feedback.FeedbackRequest;
import jwtjava10.dto.feedback.FeedbackResponse;
import jwtjava10.models.Feedback;
import jwtjava10.services.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author Mukhammed Asantegin
 */
@RestController
@RequestMapping("/api/feedbacks")
@RequiredArgsConstructor
public class FeedbackApi {
    private final FeedbackService feedbackService;

    @PostMapping
    @Secured("USER")
    public ResponseEntity<FeedbackResponse> saveFeedback(@RequestBody FeedbackRequest feedbackRequest){
       return ResponseEntity.ok( feedbackService.save(feedbackRequest));
    }
}
