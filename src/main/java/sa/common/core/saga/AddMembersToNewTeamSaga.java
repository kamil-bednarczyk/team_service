package sa.common.core.saga;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.saga.EndSaga;
import org.axonframework.eventhandling.saga.SagaEventHandler;
import org.axonframework.eventhandling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;
import sa.common.core.commands.AddMemberToTeamCommand;
import sa.common.core.events.TeamCreatedEvent;

@Saga
public class AddMembersToNewTeamSaga {

    @Autowired
    private transient CommandGateway commandGateway;

    @StartSaga
    @EndSaga
    @SagaEventHandler(associationProperty = "teamId")
    public void handle(TeamCreatedEvent event) {
        event.getMembers().stream().map(name -> new AddMemberToTeamCommand(event.getTeamId(), "", name))
                .forEach(command -> commandGateway.send(command));
    }

}
