package uz.fivedhub.userservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateDto {
    @NotBlank
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Long companyId;
}
