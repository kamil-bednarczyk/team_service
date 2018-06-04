package sa.common.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import sa.common.model.Team;

@Repository
public interface TeamRepository extends ReactiveMongoRepository<Team, String> {
}
