package martin.kea.programming_exam_project.repositories;

import martin.kea.programming_exam_project.models.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
}
