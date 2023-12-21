package com.PatientDetails.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.PatientDetails.dto.MedicalRecordDTO;
import com.PatientDetails.dto.MedicinesDTO;
import com.PatientDetails.dto.TestsDTO;

public class PatientDetailsDAO {

	private String addPatientDetails = "INSERT INTO PATIENTS (EMAIL, NAME, CONTACT) VALUES (?, ?, ?)";
	private String addAppointmentDetails = "INSERT INTO APPOINTMENTS (PATIENT_EMAIL, REASON, HOSPITAL_NAME, ADDRESS, DOCTOR_NAME, FEE, MEDICINES, TESTS) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	//private String addPrescriptionDetails = "INSERT INTO prescriptions (appointment_email) VALUES (?)";
	private String addMedicineDetails = "INSERT INTO MEDICINES (NAME, PRICE) VALUES (?, ?)";
	private String addTestDetails = "INSERT INTO TESTS (NAME, PRICE, RESULT) VALUES (?, ?, ?)";

	private int rowsAffected = 0;
	PreparedStatement pstmt;

	public MedicalRecordDTO addPatient(MedicalRecordDTO medicalRecordDTO) {

		try {
			pstmt = DataBaseConnection.getConnection().prepareStatement(addPatientDetails);
			// medicalRecordDTO.getAppointmentDTO().getPrescriptionDTO().getMedicines()
			pstmt.setString(2, medicalRecordDTO.getPatientDTO().getEmail());
			pstmt.setString(3, medicalRecordDTO.getPatientDTO().getName());
			pstmt.setString(4, medicalRecordDTO.getPatientDTO().getContact());

			rowsAffected = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			pstmt = DataBaseConnection.getConnection().prepareStatement(addAppointmentDetails);

			pstmt.setString(2, medicalRecordDTO.getPatientDTO().getEmail());
			pstmt.setString(3, medicalRecordDTO.getAppointmentDTO().getReason());
			pstmt.setString(4, medicalRecordDTO.getAppointmentDTO().getHospital_name());
			pstmt.setString(5, medicalRecordDTO.getAppointmentDTO().getAddress());
			pstmt.setString(6, medicalRecordDTO.getAppointmentDTO().getDoctor_name());
			pstmt.setDouble(7, medicalRecordDTO.getAppointmentDTO().getFee());

			rowsAffected = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}


		try {
			pstmt = DataBaseConnection.getConnection().prepareStatement(addMedicineDetails);
			List<MedicinesDTO> medi = medicalRecordDTO.getAppointmentDTO().getPrescriptionDTO().getMedicines();

			for (MedicinesDTO m : medi) {
				pstmt.setString(2, m.getName());
				pstmt.setDouble(3, m.getPrice());
				rowsAffected = pstmt.executeUpdate();

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			pstmt = DataBaseConnection.getConnection().prepareStatement(addTestDetails);
			List<TestsDTO> test = medicalRecordDTO.getAppointmentDTO().getPrescriptionDTO().getTests();

			for (TestsDTO t : test) {
				pstmt.setString(2, t.getName());
				pstmt.setDouble(3, t.getPrice());
				pstmt.setString(4, t.getResult());
				rowsAffected = pstmt.executeUpdate();

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (rowsAffected > 0) {
			return medicalRecordDTO;
		} else {
			return null;
		}
	}

}
