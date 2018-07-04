package sa.common.core.events;

import lombok.Value;

import java.util.List;

@Value
public class TeamCreatedEvent {

    String teamId;
    String name;
    String description;
    String ownerName;
    List<String> members;
}
