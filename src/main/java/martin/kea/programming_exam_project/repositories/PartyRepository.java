package martin.kea.programming_exam_project.repositories;

import martin.kea.programming_exam_project.models.Party;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartyRepository extends JpaRepository<Party, Long> {
}
