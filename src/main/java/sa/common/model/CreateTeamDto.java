package sa.common.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateTeamDto {

    @NotEmpty
    private String name;
    private String description;
    @NotEmpty
    private String ownerName;
    private List<Member> members;
}