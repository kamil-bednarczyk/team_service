package sa.common.core.commands;

import lombok.Value;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

@Value
public class AddMemberToTeamCommand {

    @TargetAggregateIdentifier
    private String teamId;
    private String memberId;
    private String memberName;
}