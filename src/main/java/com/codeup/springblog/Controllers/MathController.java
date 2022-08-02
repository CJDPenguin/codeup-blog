package com.codeup.springblog.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MathController {
    @GetMapping("/{num1}/{num2}/{operator}")
    @ResponseBody
    public double doMath(@PathVariable double num1, @PathVariable double num2, @PathVariable String operator) {
        switch(operator) {
            case "add": return num1 + num2;
            case "subtract": return num1 - num2;
            case "multipy": return num1 * num2;
            case "divide": return num1 / num2;
            default: return 0;
        }
    }

}
