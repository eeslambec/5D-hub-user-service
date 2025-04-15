package uz.fivedhub.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.fivedhub.userservice.dto.UserCreateDto;
import uz.fivedhub.userservice.dto.UserUpdateDto;
import uz.fivedhub.userservice.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody UserCreateDto user){
        return ResponseEntity.ok(userService.save(user));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return ResponseEntity.ok(userService.getById(id));
    }

    @GetMapping("/phone-number/{number}")
    public ResponseEntity<?> getByPhoneNumber(@PathVariable String number){
        return ResponseEntity.ok(userService.getByPhoneNumber(number));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/all-id")
    public ResponseEntity<?> getAllById(@RequestBody List<Long> ids){
        return ResponseEntity.ok(userService.getAllByIds(ids));
    }

    @GetMapping("/company-id/{id}")
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
