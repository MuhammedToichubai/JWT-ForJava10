package jwtjava10.services;

import jwtjava10.dto.feedback.FeedbackRequest;
import jwtjava10.dto.feedback.FeedbackResponse;

/**
 * @author Mukhammed Asantegin
 */
public interface FeedbackService {
    FeedbackResponse save(FeedbackRequest feedbackRequest);
}
