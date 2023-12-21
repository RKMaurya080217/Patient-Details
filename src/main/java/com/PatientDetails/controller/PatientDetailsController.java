package com.PatientDetails.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PatientDetails.dto.MedicalRecordDTO;
import com.PatientDetails.service.serviceimp.PatientDetailsServiceImp;

@RestController
@RequestMapping("/medicalrecord")
public class PatientDetailsController {
	PatientDetailsServiceImp patientDetailsServiceImp;
	@PostMapping
	public ResponseEntity<MedicalRecordDTO> addPatient(@RequestBody MedicalRecordDTO medicalRecordDTO) {
		patientDetailsServiceImp = new PatientDetailsServiceImp();
		medicalRecordDTO = patientDetailsServiceImp.addPatientDetails(medicalRecordDTO);

		return ResponseEntity.status(HttpStatus.CREATED).body(medicalRecordDTO);

	}

}
