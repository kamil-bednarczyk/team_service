package sa.common.axon;

import org.axonframework.test.saga.FixtureConfiguration;
import org.axonframework.test.saga.SagaTestFixture;
import org.junit.Before;
import org.junit.Test;
import sa.common.core.commands.AddMemberToTeamCommand;
import sa.common.core.events.TeamCreatedEvent;
import sa.common.core.saga.AddMembersToNewTeamSaga;

import java.util.Arrays;

public class AddMemberToNewTeamSagaTest {

    private FixtureConfiguration fixture;

    @Before
    public void setup() {
        fixture = new SagaTestFixture<>(AddMembersToNewTeamSaga.class);
    }

    @Test
    public void testActivateSagaWhenNewTeamCreated() {
        fixture.givenNoPriorActivity()
                .whenAggregate("team1").publishes(TeamCreatedEvent.builder()
                .teamId("team1")
                .name("name")
                .ownerName("Adam")
                .members(Arrays.asList("Adam", "Tomek", "Alicja"))
                .description("description of team")
                .build())
                .expectActiveSagas(1)
                .expectDispatchedCommands(AddMemberToTeamCommand.builder()
                        .memberId("")
                        .memberName("Adam")
                        .teamId("team1")
                        .build(), AddMemberToTeamCommand.builder()
                        .memberId("")
                        .memberName("Tomek")
                        .teamId("team1")
                        .build(), AddMemberToTeamCommand.builder()
                        .memberId("")
                        .memberName("Alicja")
                        .teamId("team1")
                        .build());
    }
}
