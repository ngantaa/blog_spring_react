package com.example.restcrud.user;

import com.example.restcrud.config.ImageResourceHttpRequestHandler;
import com.example.restcrud.util.FileUploadUtil;
import com.example.restcrud.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@CrossOrigin("http://localhost:3000/")
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    ImageResourceHttpRequestHandler imageResourceHttpRequestHandler;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomUserDetailsService userService;

    @Autowired
    JwtUtil jwtUtil;

    @GetMapping("/authenticated")
    public User getPrincipal() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return userRepository.findByUserName(username);
    }

    @GetMapping("/image/{userId}")
    public void download(@PathVariable String userId, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {
        String fileName = userRepository.findById(Long.parseLong(userId)).orElse(null).getProfilePicture();
        File file = new File("user_profile_pictures/" + fileName);
        httpServletRequest.setAttribute(ImageResourceHttpRequestHandler.ATTRIBUTE_FILE, file);
        imageResourceHttpRequestHandler.handleRequest(httpServletRequest, httpServletResponse);
    }

    @GetMapping("/user/{userId}")
    public User getUser(@PathVariable Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @PostMapping(value = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void newUserForm(@RequestBody User user) {
        userService.registerNewUser(user);
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @DeleteMapping("/user/{userId}")
    public void deleteUser(@PathVariable String userId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUserName(auth.getName());
        if (user.getUserId() != Long.parseLong(userId)) {
            throw new IllegalStateException("Impossible to delete someone else's account");
        }
        userRepository.deleteById(Long.parseLong(userId));
    }

    @PatchMapping("/user/{userId}")
    public void modifyUser(@PathVariable String userId, @RequestBody User user) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User userPrincipal = userRepository.findByUserName(auth.getName());
        if (userPrincipal.getUserId() != Long.parseLong(userId)) {
            throw new IllegalStateException("Impossible to modify someone else's account");
        }
        userService.updateUser(user, userPrincipal);
    }

    @PostMapping("/user/{userId}/picture")
    public void addProfilePicture(@PathVariable String userId, @RequestParam("image") MultipartFile multipartFile) throws  IOException{
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUserName(auth.getName());
        if (!user.getProfilePicture().equals("blank-avatar.jpg")) {
            Files.delete(Path.of("user_profile_pictures/" + user.getProfilePicture()));
        }
        String fileName = StringUtils.cleanPath(userId + multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().length() - 4));
        user.setProfilePicture(fileName);
        userRepository.save(user);
        String uploadDir = "user_profile_pictures/";
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
    }
}
