package sa.common.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import sa.common.model.CreateTeamDto;
import sa.common.model.Member;
import sa.common.model.MemberStatusDto;
import sa.common.model.Team;
import sa.common.model.enums.ActionType;

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

    public static Mono<Team> updateMembers(Team team, MemberStatusDto memberStatusDto) {
        if (memberStatusDto.getAction().equals(ActionType.REMOVE.toString())) {
            team.getMembers().removeIf(member -> memberStatusDto.getMemberId().equals(member.getId()));
        } else {
            team.getMembers().add(new Member(memberStatusDto.getMemberId(), memberStatusDto.getMemberName()));
        }
        return Mono.just(team);
    }
}