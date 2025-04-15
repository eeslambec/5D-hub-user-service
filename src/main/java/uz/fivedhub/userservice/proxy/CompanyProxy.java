package uz.fivedhub.userservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import uz.fivedhub.userservice.dto.CustomResponseEntity;
import uz.fivedhub.userservice.entity.Company;

@FeignClient(value = "fivedhub-company-service", url = "localhost:2341")
public interface CompanyProxy {
    @GetMapping("/company/id/{id}")
    CustomResponseEntity<Company> getById(@PathVariable("id") Long id);

    @PutMapping("/company/update")
    CustomResponseEntity<Company> update(@RequestBody Company company);

}
