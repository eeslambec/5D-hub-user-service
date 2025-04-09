package uz.fivedhub.userservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import uz.fivedhub.userservice.dto.CustomResponseEntity;
import uz.fivedhub.userservice.entity.Company;
import uz.fivedhub.userservice.entity.User;

@FeignClient(value = "fivedhub-company-service")
public interface CompanyProxy {
    @GetMapping("/company/{id}")
    CustomResponseEntity<Company> getById(@PathVariable("id") Long id);

    @PutMapping("/company/add-user")
    CustomResponseEntity<Company> addUser(@RequestBody User user);
}
