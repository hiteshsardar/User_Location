package  org.com.User_Location.User_Location.models;

import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Users {

    @Id
    private String userID;
    private String userName;
    private String userRole;
    private String password;
}
