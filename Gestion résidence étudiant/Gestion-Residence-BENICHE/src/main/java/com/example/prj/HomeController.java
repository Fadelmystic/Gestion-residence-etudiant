package com.example.prj;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index"; // Renvoie index.html dans /templates/
    }

    @GetMapping("/chambres")
    public String chambres() {
        return "chambres"; // Renvoie chambres.html dans /templates/
    }

    @GetMapping("/resident")
    public String resident() {
        return "resident"; // Renvoie resident.html dans /templates/
    }

    @GetMapping("/maintenance")
    public String maintenance() {
        return "maintenance"; // Renvoie maintenance.html dans /templates/
    }

    @GetMapping("/admin")
    public String admin() {
        return "Admin/Dashboard";
    }

    @GetMapping("/admin/edit-room")
    public String EditRoom(@RequestParam Long id, Model model) {
        model.addAttribute("roomId", id);
        return "Admin/edit-room";
    }

    @GetMapping("/reservation-room")
    public String ReserveRoom(@RequestParam("roomId") Long id, Model model) {
        model.addAttribute("roomId", id);  // Ajouter l'ID de la chambre au modèle
        return "reservation-form";  // Retourner la vue 'reservation-form'
    }

    @GetMapping("/profile")
    public String profile() {
        return "profile";
    }

    @GetMapping("/admin/add-room")
    public String addRoom() {
        return "Admin/add-room";
    }

    @GetMapping("/admin/add-student")
    public String addStudent() {
        return "Admin/add-student";
    }
    @GetMapping("/admin/edit-student")
    public String editStudent() {
        return "Admin/edit-student";
    }
    @GetMapping("/admin/add-technician")
    public String addTechnician() {
        return "Admin/add-technician";
    }
    @GetMapping("/admin/edit-technician")
    public String editTechnician() {
        return "Admin/edit-technician";
    }

    @GetMapping("/admin/add-maintenance")
    public String addMaitenance() {
        return "Admin/add-maintenance";
    }
    @GetMapping("/admin/edit-maintenance")
    public String editMaitenance() {
        return "Admin/edit-maintenance";
    }

    @GetMapping("/admin/add-payment")
    public String addPayment() {
        return "Admin/add-payment";
    }
    @GetMapping("/admin/edit-payment")
    public String editPayment() {
        return "Admin/edit-payment";
    }
}