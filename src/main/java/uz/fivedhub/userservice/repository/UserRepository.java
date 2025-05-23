package uz.fivedhub.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.fivedhub.userservice.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByPhoneNumber(String phoneNumber);
    List<User> findByCompanyId(Long companyId);
    @Query("SELECT u FROM User u WHERE u.id IN :ids")
    List<User> findAllByIds(List<Long> ids);
}
