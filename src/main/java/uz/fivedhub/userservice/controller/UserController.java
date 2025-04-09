package uz.fivedhub.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.fivedhub.userservice.dto.UserCreateDto;
import uz.fivedhub.userservice.dto.UserUpdateDto;
import uz.fivedhub.userservice.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody UserCreateDto user){
        return ResponseEntity.ok(userService.save(user));
    }

    @GetMapping("/get/id/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return ResponseEntity.ok(userService.getById(id));
    }

    @GetMapping("/get/phone-number/{number}")
    public ResponseEntity<?> getByPhoneNumber(@PathVariable String number){
        return ResponseEntity.ok(userService.getByPhoneNumber(number));
    }

    @GetMapping("/get/company-id/{id}")
    public ResponseEntity<?> getByCompanyId(@PathVariable Long id){
        return ResponseEntity.ok(userService.getByCompanyId(id));
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody UserUpdateDto userUpdateDto){
        return ResponseEntity.ok(userService.update(userUpdateDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        userService.deleteById(id);
        return ResponseEntity.ok("User deleted");
    }
}
