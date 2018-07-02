package sa.common.core.commands;

import lombok.Value;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

@Value
public class CreateTeamCommand {

    @TargetAggregateIdentifier
    private String teamId;

    private String name;
    private String description;
    private String ownerName;
}
