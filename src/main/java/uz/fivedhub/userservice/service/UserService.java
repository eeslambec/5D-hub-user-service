package uz.fivedhub.userservice.service;

import org.springframework.stereotype.Service;
import uz.fivedhub.userservice.dto.UserCreateDto;
import uz.fivedhub.userservice.dto.UserUpdateDto;
import uz.fivedhub.userservice.entity.User;

import java.util.List;

@Service
public interface UserService {
    User save(UserCreateDto userCreateDto);
    User getById(Long id);
    List<User> getAll();
    User getByPhoneNumber(String phoneNumber);
    List<User> getByCompanyId(Long companyId);
    User update(UserUpdateDto userUpdateDto);
    void deleteById(Long id);
}
