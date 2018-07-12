package sa.common.axon;


import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import sa.common.axon.commands.AddMemberToTeamCommand;
import sa.common.axon.commands.CreateTeamCommand;
import sa.common.axon.commands.RemoveMemberFromTeamCommand;
import sa.common.axon.events.MemberAddedToTeamEvent;
import sa.common.axon.events.MemberRemovedFromTeamEvent;
import sa.common.axon.events.TeamCreatedEvent;

import java.util.HashSet;
import java.util.Set;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate
public class TeamAggregate {

    @AggregateIdentifier
    private String teamId;

    private String name;
    private String description;
    private Set<String> members;
    private String ownerName;

    @SuppressWarnings("unused")
    protected TeamAggregate() {
    }

    @CommandHandler
    public TeamAggregate(CreateTeamCommand cmd) {
        apply(new TeamCreatedEvent(cmd.getTeamId(), cmd.getName(), cmd.getDescription(), cmd.getOwnerName(), cmd.getMembers()));
    }

    @CommandHandler
    public void handle(AddMemberToTeamCommand cmd) {
        apply(new MemberAddedToTeamEvent(cmd.getTeamId(), cmd.getMemberId(), cmd.getMemberName()));
    }

    @CommandHandler
    public void handle(RemoveMemberFromTeamCommand cmd) {
        apply(new MemberRemovedFromTeamEvent(cmd.getTeamId(), cmd.getMemberId(), cmd.getMemberName()));
    }

    @EventSourcingHandler
    public void on(TeamCreatedEvent event) {
        this.teamId = event.getTeamId();
        this.name = event.getName();
        this.ownerName = event.getOwnerName();
        this.description = event.getDescription();
        this.members = new HashSet<>(event.getMembers());
    }

    @EventHandler
    public void on(MemberAddedToTeamEvent event) {
        this.teamId = event.getTeamId();
        this.members.add(event.getMemberName());
    }

    @EventHandler
    public void on(MemberRemovedFromTeamEvent event) {
        this.teamId = event.getTeamId();
        this.members.removeIf( name -> name.equals(event.getMemberName()));
    }
}