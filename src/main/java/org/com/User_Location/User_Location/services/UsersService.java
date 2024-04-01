package  org.com.User_Location.User_Location.services;


import org.com.User_Location.User_Location.models.Users;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UsersService {
    List<Users> users = new ArrayList<>();

    public void userService(){
        users.add(new Users(UUID.randomUUID().toString(), "admin", "admin", "ADMIN"));
        users.add(new Users(UUID.randomUUID().toString(), "customer", "customer", "CUSTOMER"));
    }

    public List<Users> getUsers(){
        return this.users;
    }
}
