package sa.common.model;

import lombok.Data;

@Data
public class MemberStatusDto {

    private String teamId;
    private String memberId;
    private String memberName;
    private String action;
}
