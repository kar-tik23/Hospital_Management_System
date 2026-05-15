package com.DrApp.FirstTry.AppointmentApplication.services;

import com.DrApp.FirstTry.AppointmentApplication.model.appointment;
import com.DrApp.FirstTry.AppointmentApplication.reposetries.AppointmentReposetry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentReposetry repo;

    public appointment bookAppointment(appointment appointment) {

        // ❌ Prevent past booking
        if (appointment.getDate().isBefore(LocalDate.now())) {
            throw new RuntimeException("Cannot book past dates");
        }

        // ❌ Prevent duplicate slot
        boolean exists = repo.existsByDoctorAndDateAndTime(
                appointment.getDoctor(),
                appointment.getDate(),
                appointment.getTime()
        );

        if (exists) {
            throw new RuntimeException("Slot already booked");
        }

        // ✅ Token generation
        int token = repo.findByDoctorAndDateOrderByTokenNumberAsc(
                appointment.getDoctor(),
                appointment.getDate()
        ).size() + 1;

        appointment.setTokenNumber(token);

        appointment.setStatus("BOOKED");

        return repo.save(appointment);
    }
}
