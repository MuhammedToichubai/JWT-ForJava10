package jwtjava10.services.iml;

import jwtjava10.dto.feedback.FeedbackRequest;
import jwtjava10.dto.feedback.FeedbackResponse;
import jwtjava10.models.Feedback;
import jwtjava10.models.User;
import jwtjava10.repositories.FeedbackRepository;
import jwtjava10.repositories.UserRepository;
import jwtjava10.services.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @author Mukhammed Asantegin
 */
@Service
@RequiredArgsConstructor
public class FeedbackServiceImp implements FeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final UserRepository userRepository;
    @Override
    public FeedbackResponse save(FeedbackRequest feedbackRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.getUserByEmail(email).orElseThrow(() ->
                new RuntimeException("User with email: " + email + " not found"));

        Feedback feedback = feedbackRequest.build();
        user.addFeedback(feedback);
        feedbackRepository.save(feedback);

        return new FeedbackResponse(
                feedback.getId(),
                feedback.getDescription(),
                feedback.getImage(),
                user.getId(),
                user.getEmail()
        );
    }
}
