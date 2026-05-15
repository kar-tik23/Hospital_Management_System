package com.DrApp.FirstTry.AppointmentApplication.controller;


import com.DrApp.FirstTry.AppointmentApplication.model.appointment;
import com.DrApp.FirstTry.AppointmentApplication.reposetries.AppointmentReposetry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AppointmentReposetry repo;

    // 📊 View all appointments for today
    @GetMapping("/manage")
    public String manage(Model model) {

        model.addAttribute("appointments",
                repo.findByDateOrderByDoctorAscTokenNumberAsc(LocalDate.now()));

        return "admin/manage";
    }
    //complete
    @GetMapping("/complete/{id}")
    public String complete(@PathVariable Long id) {

        appointment appt = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        // ❌ Prevent invalid update
        if(!appt.getStatus().equals("BOOKED")) {
            return "redirect:/admin/manage?error";
        }

        appt.setStatus("COMPLETED");

        repo.save(appt);

        return "redirect:/admin/manage?completed";
    }

    // ❌ Cancel
    @GetMapping("/cancel/{id}")
    public String cancel(@PathVariable Long id) {

        appointment appt = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        // ❌ Prevent invalid update
        if(!appt.getStatus().equals("BOOKED")) {
            return "redirect:/admin/manage?error";
        }

        appt.setStatus("CANCELLED");

        repo.save(appt);

        return "redirect:/admin/manage?cancelled";
    }
    @GetMapping("/dashboard")
    public String adminDashboard() {
        return "admin/dashboard";
    }
}