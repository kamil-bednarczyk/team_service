package sa.common.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sa.common.model.CreateTeamDto;
import sa.common.model.Team;
import sa.common.repository.TeamRepository;
import sa.common.service.TeamService;

import javax.validation.Valid;

@RestController
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    private TeamRepository teamRepository;

    @PostMapping
    public Mono<Team> createTeam(@RequestBody @Valid CreateTeamDto createTeamDto) {
        return Mono.just(createTeamDto)
                .flatMap(TeamService::createEntity)
                .flatMap(team -> teamRepository.save(team));
    }

    @GetMapping("/{id}")
    public Mono<Team> getTeamById(@PathVariable("id") String id) {
        return teamRepository.findById(id);
    }

    @GetMapping
    public Flux<Team> getAllTeams(){
        return teamRepository.findAll();
    }
}
