package db7.bankingsystem.controller;

import db7.bankingsystem.domain.Card;
import db7.bankingsystem.domain.History;
import db7.bankingsystem.service.HistoryDto;
import db7.bankingsystem.service.HistoryService;
import db7.bankingsystem.service.HistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/histories")
@RequiredArgsConstructor
public class HistoryController {

    private final HistoryService historyService;

    @GetMapping
    public String histories(@RequestParam(required = false) Long userId, Model model) {
        if (userId != null) {
            List<History> result = historyService.findByUserId(userId);
            model.addAttribute("userId", userId);
            model.addAttribute("histories", result);
            return "histories/histories";
        }
        List<History> result = historyService.findAll();
        model.addAttribute("histories", result);
        return "histories/histories";
    }


    @GetMapping("/{historyId}")
    public String history(@PathVariable long historyId, Model model) {
        History history = historyService.findByHistoryId(historyId).get();
        model.addAttribute("history", history);
        return "histories/history";
    }


    @GetMapping("/add")
    public String addForm() {
        return "histories/addForm";
    }

    @PostMapping("/add")
    public String addHistory(@ModelAttribute HistoryDto historyDto, RedirectAttributes redirectAttributes) {
        History savedHistory = historyService.save(historyDto);
        redirectAttributes.addAttribute("historyId", savedHistory.getId().getHistoryId());
        return "redirect:/histories/{historyId}";
    }

}
