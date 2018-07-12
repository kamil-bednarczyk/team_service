package sa.common.axon.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
@AllArgsConstructor
public class TeamCreatedEvent {

    String teamId;
    String name;
    String description;
    String ownerName;
    List<String> members;
}