package sa.common.core.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.util.List;

@Value
@Builder
@AllArgsConstructor
public class CreateTeamCommand {

    @TargetAggregateIdentifier
    private String teamId;

    private String name;
    private String description;
    private String ownerName;
    private List<String> members;
}
