package sa.common.model;

import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Team {
    @Id
    private String id;
    private String name;
    private String description;
}