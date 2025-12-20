package com.health.integrated.Health_Management.service;

import com.health.integrated.Health_Management.dto.request.DoctorRequest;
import com.health.integrated.Health_Management.dto.response.DoctorResponse;
import com.health.integrated.Health_Management.enums.Role;
import com.health.integrated.Health_Management.exception.BadRequestException;
import com.health.integrated.Health_Management.model.Doctor;
import com.health.integrated.Health_Management.model.User;
import com.health.integrated.Health_Management.repository.DoctorRepository;
import com.health.integrated.Health_Management.repository.UserRepository;
import com.health.integrated.Health_Management.transformers.DoctorTransformer;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;

    public DoctorService(DoctorRepository doctorRepository,UserRepository userRepository){
        this.doctorRepository = doctorRepository;
        this.userRepository = userRepository;
    }
    //create new Doctor profile
    public DoctorResponse createDoctorProfile(String userId, DoctorRequest request){

        User user = userRepository.findById(userId)
                .orElseThrow(()->new BadRequestException("User with userId " + userId + " not found"));
        if(user.getRole() != Role.DOCTOR){
            throw new BadRequestException("Only Doctors user Profiles can create Doctor profile");
        }
        if(doctorRepository.existsByUserId(userId)){
            throw new BadRequestException("Doctor with Userid " + userId + " already Present");
        }
        if(doctorRepository.existsByLicenseNumber(request.getLicenseNumber())){
            throw new BadRequestException("License Number " + request.getLicenseNumber() + " already presetn");
        }

        Doctor doctor = DoctorTransformer.doctorRequestToDoctor(request);
        doctor.setUserId(userId);

        Doctor savedDoctor = doctorRepository.save(doctor);
        return DoctorTransformer.doctorToDoctorResponse(savedDoctor);

    }
    // GET own doctor profile
    public DoctorResponse getMyDoctorProfile(String userId) {

        Doctor doctor = doctorRepository.findByUserId(userId)
                .orElseThrow(() -> new BadRequestException("Doctor profile not found"));

        return DoctorTransformer.doctorToDoctorResponse(doctor);
    }

    // PATCH – limited update
    public DoctorResponse updateDoctorProfile(String userId, DoctorRequest request) {

        Doctor doctor = doctorRepository.findByUserId(userId)
                .orElseThrow(() -> new BadRequestException("Doctor profile not found"));

        if (request.getSpecialization() != null) {
            doctor.setSpecialization(request.getSpecialization());
        }

        if (request.getYearsOfExperience() != null) {
            doctor.setYearsOfExperience(request.getYearsOfExperience());
        }

        if (request.getConsultationFee() != null) {
            doctor.setConsultationFee(request.getConsultationFee());
        }

        Doctor savedDoctor = doctorRepository.save(doctor);
        return DoctorTransformer.doctorToDoctorResponse(savedDoctor);
    }
}
