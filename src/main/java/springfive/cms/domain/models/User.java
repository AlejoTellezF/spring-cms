package springfive.cms.domain.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collation = "users")
public class User {

    @Id
    String id;
    String identity;
    String name;
    Role role;
}