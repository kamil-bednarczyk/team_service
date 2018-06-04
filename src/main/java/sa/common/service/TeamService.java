package sa.common.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import sa.common.model.CreateTeamDto;
import sa.common.model.Team;

import java.util.UUID;

@Service
public class TeamService {

    public static Mono<Team> createEntity(CreateTeamDto createTeamDto) {
        return Mono.just(Team.builder()
                .id(UUID.randomUUID().toString())
                .name(createTeamDto.getName())
                .description(createTeamDto.getDescription())
                .build());
    }
}