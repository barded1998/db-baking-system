package db7.bankingsystem.controller;

import db7.bankingsystem.domain.History;
import db7.bankingsystem.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequestMapping("/transaction")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @GetMapping
    public String transaction() {
        return "transaction/transaction";
    }

    @GetMapping("/deposit")
    public String deposit() {
        return "transaction/deposit";
    }

    @PostMapping("/deposit")
    public String deposit(@RequestParam long accountId, int money, RedirectAttributes redirectAttributes) {
        History deposit = transactionService.deposit(accountId, money);
        redirectAttributes.addAttribute("historyId", deposit.getId().getHistoryId());
        return "redirect:/histories/{historyId}";
    }

    @GetMapping("/withdrawal")
    public String withdrawal() {
        return "transaction/withdrawal";
    }

    @PostMapping("/withdrawal")
    public String withdrawal(@RequestParam long accountId, @RequestParam int money, RedirectAttributes redirectAttributes) {
        History withdrawal = transactionService.withdrawal(accountId, money);
        redirectAttributes.addAttribute("historyId", withdrawal.getId().getHistoryId());
        return "redirect:/histories/{historyId}";
    }

    @GetMapping("/transfer")
    public String transfer() {
        return "transaction/transfer";
    }

    @PostMapping("/transfer")
    public String transfer(@RequestParam long fromAccountId, @RequestParam long toAccountId, @RequestParam int money, RedirectAttributes redirectAttributes) {
        History transfer = transactionService.transfer(fromAccountId, toAccountId, money);
        redirectAttributes.addAttribute("historyId", transfer.getId().getHistoryId());
        return "redirect:/histories/{historyId}";
    }

    @GetMapping("/cardPayment")
    public String cardPayment() {
        return "transaction/cardPayment";
    }

    @PostMapping("/cardPayment")
    public String cardPayment(@RequestParam long cardId, @RequestParam int money, RedirectAttributes redirectAttributes) {
        History withdrawal = transactionService.cardPayment(cardId, money);
        redirectAttributes.addAttribute("historyId", withdrawal.getId().getHistoryId());
        return "redirect:/histories/{historyId}";
    }

}
