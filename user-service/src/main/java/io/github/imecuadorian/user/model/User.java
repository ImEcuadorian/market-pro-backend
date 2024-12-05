package io.github.imecuadorian.user.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "users")
public class User {

    @Id
    private String id;

    @Email
    @NotBlank
    @NotNull
    @Indexed(unique = true)
    private String email;

    private String name;

    private String surname;

    @NotNull
    @NotBlank
    private String password;

    private boolean active = true;

    private String phone;

}
