package uz.fivedhub.userservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
        return userRepository.save(User.builder()
                        .firstName(userCreateDto.getFirstName())
                        .lastName(userCreateDto.getLastName())
                        .phoneNumber(userCreateDto.getPhoneNumber())
                        .companyId(userCreateDto.getCompanyId())
                .build());
    }

    @Override
    public User getById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User"));
        Company body = companyProxy.getById(user.getCompanyId()).getBody();
        if (body == null) throw new NotFoundException("Company");
        user.setCompany(body);
        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) throw new NotFoundException("User");

        for (User user : users) {
            if (companyProxy.getById(user.getCompanyId()).getBody() == null) throw new NotFoundException("Company");
            Company body = companyProxy.getById(user.getCompanyId()).getBody();
            user.setCompany(body);
        }
        return users;
    }

    /**
     * This method works for only company service. That's why I did not set the companies.
     * */
    @Override
    public List<User> getAllByIds(List<Long> ids) {
        List<User> allByIds = userRepository.findAllByIds(ids);
        if (allByIds.isEmpty()) throw new NotFoundException("Users");
        return allByIds;
    }

    @Override
    public User getByPhoneNumber(String phoneNumber) {
        User user = userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new NotFoundException("User"));
        Company body = companyProxy.getById(user.getCompanyId()).getBody();
        if (body == null) throw new NotFoundException("Company");
        user.setCompany(body);
        return user;
    }

    @Override
    public List<User> getByCompanyId(Long companyId) {
        List<User> byCompanyId = userRepository.findByCompanyId(companyId);
        if (byCompanyId.isEmpty()) throw new NotFoundException("Users");
        Company body = companyProxy.getById(companyId).getBody();
        if (body == null) throw new NotFoundException("Company");

        for (User user : byCompanyId) {
            user.setCompany(body);
        }

        return byCompanyId;
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
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User"));
        Company body = companyProxy.getById(user.getCompanyId()).getBody();
        if (body == null) throw new NotFoundException("Company");
        List<Long> userIds = body.getUserIds();
        List<User> users = body.getUsers();
        if (userIds == null) throw new NotFoundException("User IDs");
        boolean remove = users.remove(user);
        boolean removeId = userIds.remove(user.getId());
        if (!remove && !removeId) throw new NotFoundException("User");

        body.setUsers(users);
        body.setUserIds(userIds);
        companyProxy.update(body);
        userRepository.deleteById(id);
    }
}
