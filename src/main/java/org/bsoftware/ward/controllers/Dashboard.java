package org.bsoftware.ward.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class Dashboard
{
    @GetMapping
    public String getDashboard(Model model)
    {
        return "dashboard";
    }
}