package sa.common.web;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sa.common.core.commands.CreateTeamCommand;
import sa.common.model.CreateTeamDto;
import sa.common.model.Team;
import sa.common.repository.TeamRepository;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private CommandGateway commandGateway;

    @PostMapping
    public ResponseEntity createTeam(@RequestBody @Valid CreateTeamDto dto) {
      /*  return Mono.just(createTeamDto)
                .flatMap(TeamService::createEntity)
                .flatMap(team -> teamRepository.save(team));*/
        commandGateway.send(new CreateTeamCommand(UUID.randomUUID().toString(), dto.getName(), dto.getDescription()));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public Mono<Team> getTeamById(@PathVariable("id") String id) {
        return teamRepository.findById(id);
    }

    @GetMapping
    public Flux<Team> getAllTeams() {
        return teamRepository.findAll();
    }
}
