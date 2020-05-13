package org.bsoftware.ward.controllers;

import org.bsoftware.ward.services.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class IndexController
{
    private InfoService infoService;

    @GetMapping
    public String getDashboard(Model model)
    {
        model.addAllAttributes(infoService.getInfo());
        return "index";
    }

    @Autowired
    public IndexController(InfoService infoService)
    {
        this.infoService = infoService;
    }
}