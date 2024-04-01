package  org.com.User_Location.User_Location.repo;

import org.com.User_Location.User_Location.models.UsersLocation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsersLocationRepository extends MongoRepository<UsersLocation, Integer> {
}
