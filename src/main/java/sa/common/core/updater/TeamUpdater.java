package sa.common.core.updater;

import lombok.extern.log4j.Log4j2;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import sa.common.core.events.TeamCreatedEvent;
import sa.common.model.Team;
import sa.common.repository.TeamRepository;

import java.util.ArrayList;

@Component
@Log4j2
//@ProcessingGroup("newevents")
public class TeamUpdater {

    private final TeamRepository teamRepository;

    @Autowired
    public TeamUpdater(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @EventHandler
    public void on(TeamCreatedEvent event) {
        log.info("Received event: " + event.toString());
        teamRepository.save(new Team(event.getTeamId(), event.getName(), event.getDescription(), new ArrayList<>()));
    }
}

