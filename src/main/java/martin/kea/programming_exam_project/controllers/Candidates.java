package martin.kea.programming_exam_project.controllers;

import martin.kea.programming_exam_project.models.Candidate;
import martin.kea.programming_exam_project.models.Party;
import martin.kea.programming_exam_project.repositories.CandidateRepository;
import martin.kea.programming_exam_project.repositories.PartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class Candidates {

    @Autowired
    CandidateRepository candidates;

    @Autowired
    PartyRepository parties;

    @GetMapping("/candidates")
    public Iterable<Candidate> getAllCandidates() {
        return candidates.findAll();
    }

    @GetMapping("/candidates/{id}")
    public Candidate getCandidateById(@PathVariable Long id) {
        return candidates.findById(id).get();
    }


    @PostMapping("/candidates/{partyId}")
    public Candidate addCandidateWithParty(@PathVariable Long partyId, @RequestBody Candidate newCandidate) {
        Party foundParty = parties.findById(partyId).get();
        newCandidate.setId(null);
        newCandidate.setParty(foundParty);
        return candidates.save(newCandidate);
    }

    @PutMapping("/candidates/{id}")
    public String updateCandidateById(@PathVariable Long id, @RequestBody Candidate candidateToUpdateWith) {
        if(candidates.existsById(id)) {
            candidateToUpdateWith.setId(id);
            candidates.save(candidateToUpdateWith);
            return "Candidate was updated.";
        }
        return "Candidate not found.";
    }

    @PatchMapping("/candidates/{id}")
    public String patchCandidateById(@PathVariable Long id, @RequestBody Candidate candidateToUpdateWith) {
        return candidates.findById(id).map(foundCandidate -> {
            if(candidateToUpdateWith.getFirstName() != null) foundCandidate.setFirstName(candidateToUpdateWith.getFirstName());
            if(candidateToUpdateWith.getLastName() != null) foundCandidate.setLastName(candidateToUpdateWith.getLastName());
            if(candidateToUpdateWith.getVotes() != 0) foundCandidate.setVotes(candidateToUpdateWith.getVotes());
            if(candidateToUpdateWith.getParty() != null) foundCandidate.setParty(candidateToUpdateWith.getParty());

            candidates.save(foundCandidate);
            return "Candidate was updated.";
        }).orElse("Candidate not updated.");
    }

    @DeleteMapping("/candidates/{id}")
    public String deleteCandidateById(@PathVariable Long id) {
        Candidate foundCandidate = candidates.findById(id).get();
        String candidateName = "";
        candidateName += foundCandidate.getFirstName();
        candidateName += " ";
        candidateName += foundCandidate.getLastName();
        candidates.deleteById(id);
        return (candidateName + " was deleted.");
    }
}
