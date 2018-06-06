package sa.common.model;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Team implements Serializable {

    @Id
    private String id;
    private String name;
    private String description;
    private List<Member> members;
}