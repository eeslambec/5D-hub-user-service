package uz.fivedhub.userservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.fivedhub.userservice.dto.CustomResponseEntity;
import uz.fivedhub.userservice.dto.UserCreateDto;
import uz.fivedhub.userservice.dto.UserUpdateDto;
import uz.fivedhub.userservice.entity.Company;
import uz.fivedhub.userservice.entity.User;
import uz.fivedhub.userservice.exception.NotFoundException;
import uz.fivedhub.userservice.proxy.CompanyProxy;
import uz.fivedhub.userservice.repository.UserRepository;
import uz.fivedhub.userservice.service.UserService;
import uz.fivedhub.userservice.util.Validation;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final CompanyProxy companyProxy;


    @Override
    public User save(UserCreateDto userCreateDto) {

        if (companyProxy.getById(userCreateDto.getCompanyId()).getBody() == null) {
            throw new NotFoundException("Company");
        }
        return userRepository.save(User.builder()
                        .firstName(userCreateDto.getFirstName())
                        .lastName(userCreateDto.getLastName())
                        .phoneNumber(userCreateDto.getPhoneNumber())
                        .companyId(userCreateDto.getCompanyId())
                .build());
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User"));
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new NotFoundException("User"));
    }

    @Override
    public List<User> getByCompanyId(Long companyId) {
        CustomResponseEntity<Company> byId = companyProxy.getById(companyId);
        if (byId == null) {
            throw new NotFoundException("Company");
        }
        return byId.getBody().getUsers();
    }

    @Override
    public User update(UserUpdateDto userUpdateDto) {
        User byId = userRepository.findById(userUpdateDto.getId())
                .orElseThrow(() -> new NotFoundException("User"));
        if (companyProxy.getById(userUpdateDto.getCompanyId()).getBody() == null) {
            throw new NotFoundException("Company");
        }
        return userRepository.save(
                User.builder()
                        .id(userUpdateDto.getId())
                        .firstName(Validation.requireNonNullElse(userUpdateDto.getFirstName(), byId.getFirstName()))
                        .lastName(Validation.requireNonNullElse(userUpdateDto.getLastName(), byId.getLastName()))
                        .phoneNumber(Validation.requireNonNullElse(userUpdateDto.getPhoneNumber(), byId.getPhoneNumber()))
                        .companyId(Validation.requireNonNullElse(userUpdateDto.getCompanyId(), byId.getCompanyId()))
                        .build()
        );
    }

    @Override
    public void deleteById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new NotFoundException("User");
        }
        userRepository.deleteById(id);
    }
}
