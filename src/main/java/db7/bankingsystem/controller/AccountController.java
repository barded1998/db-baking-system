package db7.bankingsystem.controller;

import db7.bankingsystem.domain.Account;
import db7.bankingsystem.domain.User;
import db7.bankingsystem.service.AccountDto;
import db7.bankingsystem.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    public String accounts(@RequestParam(required = false) Long userId, Model model) {
        if (userId != null) {
            List<Account> result = accountService.findByUserId(userId);
            model.addAttribute("userId", userId);
            model.addAttribute("accounts", result);
            return "accounts/accounts";
        }
        List<Account> result = accountService.findAll();
        model.addAttribute("accounts", result);
        return "accounts/accounts";
    }


    @GetMapping("/{accountId}")
    public String account(@PathVariable long accountId, Model model) {
        Account account = accountService.findById(accountId).get();
        model.addAttribute("account", account);
        return "accounts/account";
    }

//    @GetMapping("/{userId}")
//    public String accountByUserId(@PathVariable long userId, Model model) {
//        List<Account> result = accountService.findByUserId(userId);
//        model.addAttribute("accounts", result);
//        return "accounts/account";
//    }

    @GetMapping("/add")
    public String addForm(@RequestParam(required = false) Long userId, Model model) {
        model.addAttribute("userId", userId);
        return "accounts/addForm";
    }

    @PostMapping("/add")
    public String addAccount(@ModelAttribute AccountDto accountDto, RedirectAttributes redirectAttributes) {
        Account savedAccount = accountService.save(accountDto);
        redirectAttributes.addAttribute("accountId", savedAccount.getAccountId());
        return "redirect:/accounts/{accountId}";
    }

}
