package sa.common.core.updater;

import lombok.extern.log4j.Log4j2;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sa.common.core.events.MemberAddedToTeamEvent;
import sa.common.core.events.MemberRemovedFromTeamEvent;
import sa.common.core.events.TeamCreatedEvent;
import sa.common.model.Member;
import sa.common.model.Team;
import sa.common.repository.TeamRepository;

import java.util.ArrayList;

@Log4j2
@Component
public class TeamEventsHandler {

    private final TeamRepository teamRepository;

    @Autowired
    public TeamEventsHandler(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @EventHandler
    public void on(TeamCreatedEvent event) {
        log.info("Received event: " + event.toString());
        teamRepository.save(new Team(event.getTeamId(), event.getName(), event.getDescription(), new ArrayList<>()));
    }

    @EventHandler
    public void on(MemberAddedToTeamEvent event) {
        log.info("Received event: " + event.toString());
        teamRepository.findById(event.getTeamId()).ifPresent(team -> {
            team.getMembers().add(new Member(event.getMemberId(), event.getMemberName()));
            teamRepository.save(team);
        });
    }

    @EventHandler
    public void on(MemberRemovedFromTeamEvent event) {
        log.info("Received event: " + event.toString());
        teamRepository.findById(event.getTeamId()).ifPresent(team -> {
            team.getMembers().removeIf(member -> member.getId().equals(event.getMemberId()));
            teamRepository.save(team);
        });
    }
}