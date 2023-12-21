package com.PatientDetails.service.serviceimp;

import com.PatientDetails.dao.PatientDetailsDAO;
import com.PatientDetails.dto.MedicalRecordDTO;
import com.PatientDetails.service.PatientService;

public class PatientDetailsServiceImp implements PatientService {
	PatientDetailsDAO patientDetailsDAO;

	@Override
	public MedicalRecordDTO addPatientDetails(MedicalRecordDTO medicalRecordDTO) {
		patientDetailsDAO = new PatientDetailsDAO();
		medicalRecordDTO = patientDetailsDAO.addPatient(medicalRecordDTO);
		return medicalRecordDTO;
	}

}
