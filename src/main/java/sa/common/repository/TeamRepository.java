package sa.common.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import sa.common.model.Team;

@Repository
public interface TeamRepository extends MongoRepository<Team, String> {
}
