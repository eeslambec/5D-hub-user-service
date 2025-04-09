package uz.fivedhub.userservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
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
@Embeddable
@ToString
@Builder
@Getter
@Setter
public class Company {
    @Column(name = "company_id")
    private Long id;
    private String name;
    private BigDecimal budget;
    @Transient
    private List<User> users;
}
