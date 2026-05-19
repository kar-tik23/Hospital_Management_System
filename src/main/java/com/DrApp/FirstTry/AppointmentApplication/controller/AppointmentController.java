package com.DrApp.FirstTry.AppointmentApplication.controller;

import com.DrApp.FirstTry.AppointmentApplication.model.appointment;
import com.DrApp.FirstTry.AppointmentApplication.model.doctor;
import com.DrApp.FirstTry.AppointmentApplication.model.user;
import com.DrApp.FirstTry.AppointmentApplication.reposetries.AppointmentReposetry;
import com.DrApp.FirstTry.AppointmentApplication.reposetries.DoctorReposetry;
import com.DrApp.FirstTry.AppointmentApplication.reposetries.UserReposetry;
import com.DrApp.FirstTry.AppointmentApplication.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/user")

public class AppointmentController {

    @Autowired
    private DoctorReposetry doctorRepo;
    @Autowired
    private AppointmentReposetry appointmentRepo;
    @Autowired
    private AppointmentService service;

    @Autowired
    private UserReposetry userRepo;

    @GetMapping("/book")
    public String bookPage(Model model) {
        model.addAttribute("doctors", doctorRepo.findAll());
        model.addAttribute("appointment", new appointment());
        return "user/book";
    }

    @PostMapping("/book")
    public String book(
            @ModelAttribute appointment appointment,
            Principal principal,
            Model model
    ) {

        try {

            user user = userRepo.findByUsername(principal.getName()).get();

            appointment.setUser(user);

            service.bookAppointment(appointment);

            return "redirect:/user/book?success";

        } catch (Exception e) {

            model.addAttribute("error", e.getMessage());

            model.addAttribute("doctors", doctorRepo.findAll());

            return "user/book";
        }
    }
    @GetMapping("/my")
    public String myAppointments(Model model, Principal principal) {

        user user = userRepo.findByUsername(principal.getName()).get();

        model.addAttribute("appointments",
                appointmentRepo.findByUserOrderByDateDesc(user));

        return "user/my";
    }
    @GetMapping("/dashboard")
    public String userDashboard() {
        return "user/dashboard";
    }




    @GetMapping("/queue/{doctorId}")
    public String queuePage(
            @PathVariable Long doctorId,
            Model model
    ) {

        doctor doctor = doctorRepo.findById(doctorId).orElse(null);

        if (doctor == null) {
            return "redirect:/user/my";
        }

        List<appointment> queueList =
                appointmentRepo
                        .findByDoctorAndDateOrderByTokenNumberAsc(
                                doctor,
                                LocalDate.now()
                        );

        model.addAttribute("doctor", doctor);
        model.addAttribute("queueList", queueList);

        return "user/queue";
    }
}
