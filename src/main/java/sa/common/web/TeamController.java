package sa.common.web;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sa.common.core.commands.CreateTeamCommand;
import sa.common.model.CreateTeamDto;
import sa.common.model.Member;
import sa.common.model.Team;
import sa.common.repository.TeamRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private CommandGateway commandGateway;

    @PostMapping
    public ResponseEntity createTeam(@RequestBody @Valid CreateTeamDto dto) {
        commandGateway.send(
                new CreateTeamCommand(UUID.randomUUID().toString(),
                        dto.getName(),
                        dto.getDescription(),
                        dto.getOwnerName(),
                        dto.getMembers().stream().map(Member::getName).collect(Collectors.toList())));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public Optional<Team> getTeamById(@PathVariable("id") String id) {
        return teamRepository.findById(id);
    }

    @GetMapping
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    @GetMapping("/name/{name}")
    public List<Team> getTeamsByMember(@PathVariable("name") String name) {
        return new ArrayList<>(teamRepository.findByMembersName(name));
    }
}