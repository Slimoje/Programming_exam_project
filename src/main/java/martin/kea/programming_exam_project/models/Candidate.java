package martin.kea.programming_exam_project.models;


import lombok.Data;


import javax.persistence.*;


@Data
@Table(name="candidates")
@Entity
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private int votes;

    @ManyToOne
    @JoinColumn(name="party_id")
    private Party party;
}
