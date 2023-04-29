package pojo.endPointS.ListOfUsers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class User {

        private int id;
        private String email;
        private String first_name;
        private String last_name;
        private String avatar;
}
