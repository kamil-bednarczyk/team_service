package sa.common.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class MemberStatusDto {

    @NotEmpty
    private String teamId;

    private String memberId;
    private String memberName;
}