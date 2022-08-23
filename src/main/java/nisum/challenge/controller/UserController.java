package nisum.challenge.controller;

import com.google.gson.Gson;
import net.bytebuddy.implementation.bytecode.Throw;
import nisum.challenge.DTO.UserDTO;
import nisum.challenge.model.MessageError;
import nisum.challenge.model.User;
import nisum.challenge.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST})
@Controller
@RequestMapping()
public class UserController {

    @Autowired
    private IUserService userService;
    @Value("${token}")
    private UUID token;

    @PostMapping("/user")
    public ResponseEntity addUser(@RequestHeader("token") UUID tokenUser, @RequestBody UserDTO user) throws Exception {
        Gson gson = new Gson();
        if (!token.equals(tokenUser)) {
            return new ResponseEntity<>(gson.toJson(new MessageError("Invalid Token")), HttpStatus.FORBIDDEN);
        }
        try {
            Optional<UserDTO> userSaved = userService.add(user);
            return userSaved.map(userFound -> new ResponseEntity<>(gson.toJson(userFound), HttpStatus.CREATED))
                    .orElseGet(() -> new ResponseEntity<>(gson.toJson(new MessageError("User Not saved")), HttpStatus.INTERNAL_SERVER_ERROR));
        } catch (Exception e) {
            return new ResponseEntity<>(gson.toJson(new MessageError(e.getMessage())), HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/user/{mail}/{password}")
    public ResponseEntity getUser(@RequestHeader("token") UUID tokenUser, @PathVariable String mail, @PathVariable String password) {
        Gson gson = new Gson();
        if (!token.equals(tokenUser)) {
            return new ResponseEntity<>(gson.toJson(new MessageError("Invalid Token")), HttpStatus.FORBIDDEN);
        }
        try {
            Optional<UserDTO> user = userService.getUserByEmail(mail, password);
            return user.map(userFound -> new ResponseEntity<>(gson.toJson(userFound), HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(gson.toJson(new MessageError("User Not Fount")), HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(gson.toJson(new MessageError(e.getMessage())), HttpStatus.FORBIDDEN);
        }
    }


    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getUsers();
    }


    @PutMapping("/user/{mail}/{password}")
    public ResponseEntity updateUser(@RequestHeader("token") UUID tokenUser, @PathVariable String mail, @PathVariable String password, @RequestBody UserDTO userDTO) {
        Gson gson = new Gson();
        if (!token.equals(tokenUser)) {
            return new ResponseEntity<>(gson.toJson(new MessageError("Invalid Token")), HttpStatus.FORBIDDEN);
        }
        try {
            Optional<UserDTO> userFound = userService.getUserByEmailToUpdate(mail, password);
            return userFound.map(user -> {
                        try {
                            userService.edit(userDTO, user);
                            return new ResponseEntity<>(gson.toJson(new MessageError("User updated")), HttpStatus.OK);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .orElseGet(() -> new ResponseEntity<>(gson.toJson(new MessageError("User Not Fount")), HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(gson.toJson(new MessageError(e.getMessage())), HttpStatus.FORBIDDEN);
        }
    }


    @DeleteMapping("user/{mail}/{password}")
    public ResponseEntity deleteUser(@RequestHeader("token") UUID tokenUser, @PathVariable String mail, @PathVariable String password) {
        Gson gson = new Gson();
        if (!token.equals(tokenUser)) {
            return new ResponseEntity<>(gson.toJson(new MessageError("Invalid Token")), HttpStatus.FORBIDDEN);
        }
        try {
            Optional<UserDTO> user = userService.getUserByEmail(mail, password);
            return user.flatMap(userFound ->
                            userService.delete(user.get().getId().get())
                                    .map(deleted ->
                                            deleted ? new ResponseEntity<>(gson.toJson(new MessageError("User with  id " + user.get().getId().get() + " was deleted")), HttpStatus.OK)
                                                    :
                                                    new ResponseEntity<>(gson.toJson(new MessageError("internal Server Error")), HttpStatus.INTERNAL_SERVER_ERROR))
                    )
                            .
                    orElseGet(() -> new ResponseEntity<>(gson.toJson(new MessageError("User Not Fount")), HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(gson.toJson(new MessageError(e.getMessage())), HttpStatus.FORBIDDEN);
        }
    }
}
