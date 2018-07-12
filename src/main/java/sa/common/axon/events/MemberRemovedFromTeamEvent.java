package sa.common.axon.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class MemberRemovedFromTeamEvent {

    private String teamId;
    private String memberId;
    private String memberName;
}
