package sa.common.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;


@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateTeamDto {

    @NotEmpty
    private String name;
    private String description;
    @NotEmpty
    private String ownerName;
}