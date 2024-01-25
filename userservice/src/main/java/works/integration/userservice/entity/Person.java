package works.integration.userservice.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "The Person's unique Id", accessMode = AccessMode.READ_ONLY)
    private long id;

    @NotBlank(message = "First name cannot be blank")
    @NonNull
    @Column(nullable = false)
    @Schema(description = "The Person's first name")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    @NonNull
    @Column(nullable = false)
    @Schema(description = "The Person's last name")
    private String lastName;

    @Min(value = 0, message = "Age cannot be negative")
    @NotBlank(message = "Age cannot be blank")
    @Column(nullable = false)
    @Schema(description = "The Person's age")
    private int age;

    @NotBlank(message = "Sex cannot be blank")
    @NonNull
    @Column(nullable = false)
    @Schema(description = "The Person's sex")
    private String sex;

}
