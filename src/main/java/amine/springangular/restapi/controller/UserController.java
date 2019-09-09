package amine.springangular.restapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import amine.springangular.restapi.model.User;
import amine.springangular.restapi.repository.UserRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
     
    private UserRepository userRepository;
 
    public UserController(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@GetMapping("/users")
    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }
	
	@GetMapping(path = "/users/{id}")
	public Optional<User> getUserById(@PathVariable("id") long id){
		return userRepository.findById(id);
	}
 
    @PostMapping("/users")
    void addUser(@RequestBody User user) {
        userRepository.save(user);
    }
    
    @PutMapping(path = "/users/{id}")
    public ResponseEntity<User> editUser(@PathVariable("id") long id , @RequestBody User user){
    	return userRepository.findById(id)
    			.map(record -> {
    	            record.setName(user.getName());
    	            record.setEmail(user.getEmail());
    	            User updated = userRepository.save(record);
    	            return ResponseEntity.ok().body(updated);
    	        }).orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping(path ="/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") long id, @RequestBody User user){
    	return userRepository.findById(id)
    			.map(record -> {
    				userRepository.deleteById(id);
    				return ResponseEntity.ok().build();
    			}
    			).orElse(ResponseEntity.notFound().build());
    }
}