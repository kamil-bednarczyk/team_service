package sa.common.axon.processor;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sa.common.axon.events.MemberAddedToTeamEvent;
import sa.common.axon.events.MemberRemovedFromTeamEvent;
import sa.common.axon.events.TeamCreatedEvent;
import sa.common.model.Member;
import sa.common.model.Team;
import sa.common.repository.TeamRepository;

import java.util.ArrayList;

@Component
public class TeamEventsProcessor {

    private final TeamRepository teamRepository;

    @Autowired
    public TeamEventsProcessor(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @EventHandler
    public void on(TeamCreatedEvent event) {
        teamRepository.save(Team.builder()
                .id(event.getTeamId())
                .name(event.getName())
                .ownerName(event.getOwnerName())
                .description(event.getDescription())
                .members(new ArrayList<>())
                .build());
    }

    @EventHandler
    public void on(MemberAddedToTeamEvent event) {
        teamRepository.findById(event.getTeamId()).ifPresent(team -> {
            team.getMembers().add(new Member(event.getMemberName(), event.getMemberName()));
            teamRepository.save(team);
        });
    }

    @EventHandler
    public void on(MemberRemovedFromTeamEvent event) {
        teamRepository.findById(event.getTeamId()).ifPresent(team -> {
            team.getMembers().removeIf(member -> member.getName().equals(event.getMemberName()));
            teamRepository.save(team);
        });
    }
}