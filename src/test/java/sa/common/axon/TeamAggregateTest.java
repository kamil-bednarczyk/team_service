package sa.common.axon;

import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.Before;
import org.junit.Test;
import sa.common.axon.commands.AddMemberToTeamCommand;
import sa.common.axon.commands.CreateTeamCommand;
import sa.common.axon.commands.RemoveMemberFromTeamCommand;
import sa.common.axon.events.MemberAddedToTeamEvent;
import sa.common.axon.events.MemberRemovedFromTeamEvent;
import sa.common.axon.events.TeamCreatedEvent;

import java.util.ArrayList;
import java.util.Collections;

public class TeamAggregateTest {

    private FixtureConfiguration<TeamAggregate> fixture;

    @Before
    public void setUp() {
        fixture = new AggregateTestFixture<>(TeamAggregate.class);
    }


    @Test
    public void testCreateTeam() {
        fixture.givenNoPriorActivity()
                .when(CreateTeamCommand.builder()
                        .teamId("teamId")
                        .name("name")
                        .ownerName("ownerName")
                        .members(new ArrayList<>())
                        .description("description of team")
                        .build())
                .expectEvents(TeamCreatedEvent.builder()
                        .teamId("teamId")
                        .name("name")
                        .ownerName("ownerName")
                        .members(new ArrayList<>())
                        .description("description of team")
                        .build());
    }

    @Test
    public void testAddMemberToTeam() {
        fixture.given(TeamCreatedEvent.builder()
                .teamId("teamId")
                .name("name")
                .ownerName("ownerName")
                .members(new ArrayList<>())
                .description("description of team")
                .build())
                .when(AddMemberToTeamCommand.builder()
                        .memberId("memberId")
                        .memberName("name")
                        .teamId("teamId")
                        .build())
                .expectEvents(MemberAddedToTeamEvent.builder()
                        .memberId("memberId")
                        .memberName("name")
                        .teamId("teamId")
                        .build());
    }

    @Test
    public void testRemoveMemberFromTeam() {
        fixture.given(TeamCreatedEvent.builder()
                .teamId("teamId")
                .name("name")
                .ownerName("ownerName")
                .members(Collections.singletonList("name"))
                .description("description of team")
                .build())
                .when(RemoveMemberFromTeamCommand.builder()
                        .memberId("memberId")
                        .memberName("name")
                        .teamId("teamId")
                        .build())
                .expectEvents(MemberRemovedFromTeamEvent.builder()
                        .memberId("memberId")
                        .memberName("name")
                        .teamId("teamId")
                        .build());
    }
}
