package  org.com.User_Location.User_Location.repo;


import org.com.User_Location.User_Location.models.Users;
import java.util.Optional;

public interface UserRepository{
    public Optional<Users> findByUsername(String userName);
}
