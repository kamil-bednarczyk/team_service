package sa.common.core;


import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import sa.common.core.commands.CreateTeamCommand;
import sa.common.core.events.TeamCreatedEvent;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate
public class TeamAggregate {

    @AggregateIdentifier
    private String teamId;
    private String name;
    private String description;

    private String memberId;
    private String memberName;

    @CommandHandler
    public TeamAggregate(CreateTeamCommand cmd) {
        apply(new TeamCreatedEvent(cmd.getTeamId(), cmd.getName(), cmd.getDescription()));
    }

    @EventSourcingHandler
    private void handle(TeamCreatedEvent event) {
        this.teamId = event.getTeamId();
        this.name = event.getName();
        this.description = event.getDescription();
    }
}
