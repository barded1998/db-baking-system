package db7.bankingsystem.controller;

import db7.bankingsystem.domain.Card;
import db7.bankingsystem.domain.User;
import db7.bankingsystem.repository.CardRepository;
import db7.bankingsystem.repository.UserRepository;
import db7.bankingsystem.service.UserDto;
import db7.bankingsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private static long sequence = 0;

    @GetMapping
    public String users(@RequestParam(required = false) Long userId, Model model) {
        if (userId != null) {
            UserDto userDto = userService.findByUserId(userId).get();
            List<UserDto> result = new ArrayList<>();
            result.add(userDto);
            model.addAttribute("userId", userId);
            model.addAttribute("users", result);
            return "users/users";
        }
        List<UserDto> result = userService.findAll();
        model.addAttribute("users", result);
        return "users/users";
    }


    @GetMapping("/{userId}")
    public String user(@PathVariable long userId, Model model) {
        UserDto user = userService.findByUserId(userId).get();
        model.addAttribute("user", user);
        return "users/user";
    }

    @GetMapping("/add")
    public String addForm() {
        return "users/addForm";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute UserDto userDto, RedirectAttributes redirectAttributes) {
        UserDto savedUser = userService.save(userDto);
        redirectAttributes.addAttribute("userId", savedUser.getUserId());
        return "redirect:/users/{userId}";
    }

}
