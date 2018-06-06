package sa.common.core;


import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import sa.common.core.commands.AddMemberToTeamCommand;
import sa.common.core.commands.CreateTeamCommand;
import sa.common.core.commands.RemoveMemberFromTeamCommand;
import sa.common.core.events.MemberAddedToTeamEvent;
import sa.common.core.events.MemberRemovedFromTeamEvent;
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

    @CommandHandler
    public void on(AddMemberToTeamCommand cmd){
        apply(new MemberAddedToTeamEvent(cmd.getTeamId(), cmd.getMemberId(), cmd.getMemberName()));
    }

    @CommandHandler
    public void on(RemoveMemberFromTeamCommand cmd){
        apply(new MemberAddedToTeamEvent(cmd.getTeamId(), cmd.getMemberId(), cmd.getMemberName()));
    }

    @EventSourcingHandler
    public void handle(TeamCreatedEvent event) {
        this.teamId = event.getTeamId();
        this.name = event.getName();
        this.description = event.getDescription();
    }

    @EventHandler
    public void on(MemberAddedToTeamEvent event) {
        this.teamId = event.getTeamId();
        this.memberId = event.getMemberId();
        this.memberName = event.getMemberName();
    }

    @EventHandler
    public void on(MemberRemovedFromTeamEvent event) {
        this.teamId = event.getTeamId();
        this.memberId = null;
        this.memberName = null;
    }
}
