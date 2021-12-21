package martin.kea.programming_exam_project.controllers;

import martin.kea.programming_exam_project.models.Candidate;
import martin.kea.programming_exam_project.models.Party;
import martin.kea.programming_exam_project.repositories.CandidateRepository;
import martin.kea.programming_exam_project.repositories.PartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Parties {

    @Autowired
    PartyRepository parties;

    @Autowired
    CandidateRepository candidates;

    @GetMapping("/parties")
    public Iterable<Party> getAllParties() {
        return parties.findAll();
    }

    @GetMapping("/parties/{id}")
    public Party getPartyById(@PathVariable Long id) {
        return parties.findById(id).get();
    }

    @GetMapping("/parties/{id}/candidates")
    public Iterable<Candidate> getCandidatesByPartyId(@PathVariable Long id) {
        List<Candidate> allCandidates = candidates.findAll();
        List<Candidate> candidatesFromParty = new ArrayList<>();
        for (int i = 0; i < allCandidates.size(); i++) {
            if (allCandidates.get(i).getParty().getId() == id) {
                candidatesFromParty.add(allCandidates.get(i));
            }
        }
        return candidatesFromParty;
    }

    @PostMapping("/parties")
    public Party addParty(@RequestBody Party newParty) {
        newParty.setId(null);
        return parties.save(newParty);
    }

    @DeleteMapping("/parties/{id}")
    public String deletePartyById(@PathVariable Long id) {
        Party foundParty = parties.findById(id).get();
        String partyName = "";
        partyName += foundParty.getName();
        parties.deleteById(id);
        return (partyName + " was deleted.");
    }
}
