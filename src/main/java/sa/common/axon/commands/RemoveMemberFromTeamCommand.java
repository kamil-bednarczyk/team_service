package sa.common.axon.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

@Value
@Builder
@AllArgsConstructor
public class RemoveMemberFromTeamCommand {

    @TargetAggregateIdentifier
    private String teamId;

    private String memberId;
    private String memberName;
}