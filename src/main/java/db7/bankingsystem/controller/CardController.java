package db7.bankingsystem.controller;

import db7.bankingsystem.domain.Account;
import db7.bankingsystem.domain.Card;
import db7.bankingsystem.service.AccountService;
import db7.bankingsystem.service.CardDto;
import db7.bankingsystem.service.CardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/cards")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @GetMapping
    public String cards(@RequestParam(required = false) Long accountId, Model model) {
        if (accountId != null) {
            List<Card> result = cardService.findByAccountId(accountId);
            model.addAttribute("accountId", accountId);
            model.addAttribute("cards", result);
            return "cards/cards";
        }
        List<Card> result = cardService.findAll();
        model.addAttribute("cards", result);
        return "cards/cards";
    }


    @GetMapping("/{cardId}")
    public String cardByCardId(@PathVariable long cardId, Model model) {
        Card card = cardService.findById(cardId).get();
        model.addAttribute("card", card);
        return "cards/card";
    }

//    @GetMapping("/{userId}")
//    public String cardByUserId(@PathVariable long userId, Model model) {
//        List<Card> result = cardService.findByUserId(userId);
//        model.addAttribute("cards", result);
//        return "cards";
//    }

//    @GetMapping("/{accountId}")
//    public String cardByAccountId(@PathVariable long accountId, Model model) {
//        List<Card> result = cardService.findByAccountId(accountId);
//        model.addAttribute("cards", result);
//        return "cards/ards";
//    }

    @GetMapping("/add")
    public String addForm(@RequestParam(required = false) Long accountId, Model model) {
        model.addAttribute("accountId", accountId);
        return "cards/addForm";
    }

    @PostMapping("/add")
    public String addCard(@ModelAttribute CardDto cardDto, RedirectAttributes redirectAttributes) {
        Card savedCard = cardService.save(cardDto);
        redirectAttributes.addAttribute("cardId", savedCard.getCardId());
        return "redirect:/cards/{cardId}";
    }

}
