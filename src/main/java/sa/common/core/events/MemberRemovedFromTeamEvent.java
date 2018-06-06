package sa.common.core.events;

import lombok.Value;

@Value
public class MemberRemovedFromTeamEvent {

    private String teamId;
    private String memberId;
    private String memberName;
}
