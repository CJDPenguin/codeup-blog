package com.codeup.springblog.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class RollDiceController {
    @GetMapping("/rolldice")
    public String rollDice(){
        return "rolldice";
    }

    @GetMapping("rolldice/{n}")
    public String diceRolled(@PathVariable int n, Model model) {
        int roll1 = (int) Math.floor((Math.random()*6)+1);
        boolean match1 = n == roll1;
        int roll2 = (int) Math.floor((Math.random()*6)+1);
        boolean match2 = n == roll2;
        int roll3 = (int) Math.floor((Math.random()*6)+1);
        boolean match3 = n == roll3;
        int roll4 = (int) Math.floor((Math.random()*6)+1);
        boolean match4 = n == roll4;
        int roll5 = (int) Math.floor((Math.random()*6)+1);
        boolean match5 = n == roll5;
        model.addAttribute("num", n);
        model.addAttribute("roll1", roll1);
        model.addAttribute("roll2", roll2);
        model.addAttribute("roll3", roll3);
        model.addAttribute("roll4", roll4);
        model.addAttribute("roll5", roll5);
        model.addAttribute("match1", match1);
        model.addAttribute("match2", match2);
        model.addAttribute("match3", match3);
        model.addAttribute("match4", match4);
        model.addAttribute("match5", match5);
        return "dicerolled";
    }
}
