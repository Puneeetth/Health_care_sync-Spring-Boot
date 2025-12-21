package com.health.integrated.Health_Management.transformers;

import com.health.integrated.Health_Management.dto.request.AppointmentRequest;
import com.health.integrated.Health_Management.dto.response.AppointmentResponse;
import com.health.integrated.Health_Management.enums.AppointmentStatus;
import com.health.integrated.Health_Management.model.Appointment;

public class AppointmentTransformer {
    public static Appointment appointmentRequestToAppointment(String patentId,AppointmentRequest request){
        Appointment a = new Appointment();
        a.setPatientId(patentId);
        a.setDoctorId(request.getDoctorId());
        a.setAppointmentTime(request.getAppointmentTime());
        a.setStatus(AppointmentStatus.REQUESTED);
        return a;
    }

    public static AppointmentResponse appointmentToAppointmentResponse(Appointment appointment){
        return AppointmentResponse.builder()
                .id(appointment.getId())
                .patientId(appointment.getPatientId())
                .doctorId(appointment.getDoctorId())
                .appointmentTime(appointment.getAppointmentTime())
                .status(appointment.getStatus())
                .build();
    }
}
