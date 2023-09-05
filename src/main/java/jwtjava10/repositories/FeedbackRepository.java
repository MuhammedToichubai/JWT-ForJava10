package jwtjava10.repositories;

import jwtjava10.models.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(path = "feedbacks")
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}