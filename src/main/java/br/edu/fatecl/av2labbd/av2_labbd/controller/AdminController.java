package br.edu.fatecl.av2labbd.av2_labbd.controller;

import br.edu.fatecl.av2labbd.av2_labbd.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/admin/login")
    public String login(@RequestParam String login, @RequestParam String senha, Model model) {

        try {
            adminService.autenticar(login, senha);
            return "redirect:/admin/menu";
        } catch (RuntimeException e) {
            model.addAttribute("erro", e.getMessage());
            return "loginAdmin";
        }

    }

}
