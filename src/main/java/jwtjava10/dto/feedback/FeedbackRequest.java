package jwtjava10.dto.feedback;

import jwtjava10.models.Feedback;

/**
 * @author Mukhammed Asantegin
 */
public record FeedbackRequest(String description, String image) {
    public Feedback build(){
        Feedback feedback = new Feedback();
        feedback.setDescription(this.description);
        feedback.setImage(this.image);
        return feedback;
    }
}
