package uz.fivedhub.userservice.entity;

import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Getter
@Setter
public class Company {
    private Long id;
    private String name;
    private BigDecimal budget;
    private List<Long> userIds;
    @Transient
    private List<User> users;
}
