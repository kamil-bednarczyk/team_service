package sa.common.axon.saga;

import lombok.extern.log4j.Log4j2;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.saga.EndSaga;
import org.axonframework.eventhandling.saga.SagaEventHandler;
import org.axonframework.eventhandling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;
import sa.common.axon.commands.AddMemberToTeamCommand;
import sa.common.axon.events.MemberAddedToTeamEvent;
import sa.common.axon.events.TeamCreatedEvent;

@Saga
@Log4j2
public class AddMembersToNewTeamSaga {

    @Autowired
    private transient CommandGateway commandGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "teamId")
    public void handle(TeamCreatedEvent event) {
        event.getMembers().stream().map(name -> new AddMemberToTeamCommand(event.getTeamId(), "", name))
                .forEach(command ->
                {
                    commandGateway.send(command);
                    log.info("Command dispatched: " + command.toString());
                });
    }

    @EndSaga
    @SagaEventHandler(associationProperty = "teamId")
    public void handle(MemberAddedToTeamEvent event){
    }

}
