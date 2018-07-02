package sa.common.core.events;

import lombok.Value;

@Value
public class TeamCreatedEvent {

    String teamId;
    String name;
    String description;
    String onwerName;
}
