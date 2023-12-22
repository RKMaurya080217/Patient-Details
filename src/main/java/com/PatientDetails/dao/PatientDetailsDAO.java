package com.PatientDetails.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import com.PatientDetails.dto.MedicalRecordDTO;
import com.PatientDetails.dto.MedicinesDTO;
import com.PatientDetails.dto.TestsDTO;

public class PatientDetailsDAO {

	private String addPatientDetails = "INSERT INTO PATIENTS (EMAIL, NAME, CONTACT) VALUES (?, ?, ?)";
	private String addAppointmentDetails = "INSERT INTO APPOINTMENTS (PATIENT_EMAIL, REASON, HOSPITAL_NAME, ADDRESS, DOCTOR_NAME, FEE, MEDICINES, TESTS) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	// private String addPrescriptionDetails = "INSERT INTO prescriptions
	// (appointment_email) VALUES (?)";
	private String addMedicineDetails = "INSERT INTO MEDICINES (NAME, PRICE) VALUES (?, ?)";
	private String addTestDetails = "INSERT INTO TESTS (NAME, PRICE, RESULT) VALUES (?, ?, ?)";
	private StringBuffer sbf = new StringBuffer("SELECT ID FROM MEDICINES WHERE (NAME, PRICE) IN(");
	private String medicineId;
	private StringBuffer test = new StringBuffer("SELECT ID FROM TESTS WHERE (NAME, PRICE, RESULT) IN(");
	private String testId;

	private int rowsAffected = 0;
	PreparedStatement pstmt, pstm;
	ResultSet rs;
	private List<Integer> id = new ArrayList<Integer>();
	private List<Integer> testidlist = new ArrayList<Integer>();
	int i = 1;
	private String medicinrecord;
	private String testrecord;
	private int j = 1;

	public MedicalRecordDTO addPatient(MedicalRecordDTO medicalRecordDTO) {

		addMedicine(medicalRecordDTO);

		addTest(medicalRecordDTO);

		addPatientDetails(medicalRecordDTO);

		getMedicineId();

		getTestId();

		return addAppointment(medicalRecordDTO);
	}

	public void addMedicine(MedicalRecordDTO medicalRecordDTO) {

		pstmt = null;
		pstm = null;
		List<MedicinesDTO> medi = medicalRecordDTO.getAppointmentDTO().getPrescriptionDTO().getMedicines();
		try {
			pstmt = DataBaseConnection.getConnection().prepareStatement(addMedicineDetails);

			for (MedicinesDTO m : medi) {
				String checkmediquery = "select 1 from medicines where name = '" + m.getName() + "' and price = "
						+ m.getPrice();
				pstm = DataBaseConnection.getConnection().prepareStatement(checkmediquery);
				ResultSet rs = pstm.executeQuery();
				if (!rs.next()) {
					pstmt.setString(1, m.getName());
					pstmt.setDouble(2, m.getPrice());
					rowsAffected = pstmt.executeUpdate();
					System.out.println("//Inside if");
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// IN(('Aspirin', 10.00), ('Ibuprofen', 15.00), ('Amoxicillin', 20.00))";
		for (MedicinesDTO m : medi) {
			sbf.append("('" + m.getName() + "', " + m.getPrice() + ")");
			if (medi.size() > i) {
				sbf.append(", ");
				i++;
			}
		}
		sbf.append(")");
		medicineId = sbf.toString();
		System.out.println("Queryy : = " + sbf);
	}

	public void addTest(MedicalRecordDTO medicalRecordDTO) {
		pstmt = null;
		pstm = null;
		List<TestsDTO> testdto = medicalRecordDTO.getAppointmentDTO().getPrescriptionDTO().getTests();
		try {
			pstmt = DataBaseConnection.getConnection().prepareStatement(addTestDetails);
			for (TestsDTO t : testdto) {
				String checktestquery = "select 1 from tests where name = '" + t.getName() + "' and price = "
						+ t.getPrice() + " and RESULT = '" + t.getResult() + "'";
				pstm = DataBaseConnection.getConnection().prepareStatement(checktestquery);
				ResultSet rs = pstm.executeQuery();
				if (!rs.next()) {
					pstmt.setString(1, t.getName());
					pstmt.setDouble(2, t.getPrice());
					pstmt.setString(3, t.getResult());
					rowsAffected = pstmt.executeUpdate();
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		// List<TestsDTO> testlist =
		// medicalRecordDTO.getAppointmentDTO().getPrescriptionDTO().getTests();
		for (TestsDTO t : testdto) {
			test.append("('" + t.getName() + "', " + t.getPrice() + ", '" + t.getResult() + "')");
			if (testdto.size() > j) {
				test.append(", ");
				j++;
			}
		}
		test.append(")");
		testId = test.toString();
		System.out.println("Test Queryy : = " + testId);
	}

	public void addPatientDetails(MedicalRecordDTO medicalRecordDTO) {
		try {
			pstmt = DataBaseConnection.getConnection().prepareStatement(addPatientDetails);
			// medicalRecordDTO.getAppointmentDTO().getPrescriptionDTO().getMedicines()
			pstmt.setString(1, medicalRecordDTO.getPatientDTO().getEmail());
			pstmt.setString(2, medicalRecordDTO.getPatientDTO().getName());
			pstmt.setString(3, medicalRecordDTO.getPatientDTO().getContact());

			rowsAffected = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public MedicalRecordDTO addAppointment(MedicalRecordDTO medicalRecordDTO) {
		try {
			pstmt = DataBaseConnection.getConnection().prepareStatement(addAppointmentDetails);

			pstmt.setString(1, medicalRecordDTO.getPatientDTO().getEmail());
			pstmt.setString(2, medicalRecordDTO.getAppointmentDTO().getReason());
			pstmt.setString(3, medicalRecordDTO.getAppointmentDTO().getHospital_name());
			pstmt.setString(4, medicalRecordDTO.getAppointmentDTO().getAddress());
			pstmt.setString(5, medicalRecordDTO.getAppointmentDTO().getDoctor_name());
			pstmt.setDouble(6, medicalRecordDTO.getAppointmentDTO().getFee());

			pstmt.setString(7, medicinrecord);
			pstmt.setString(8, testrecord);

			rowsAffected = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (rowsAffected > 0) {
			return medicalRecordDTO;
		} else {
			return null;
		}
	}

	public void getMedicineId() {
		try {
			pstmt = DataBaseConnection.getConnection().prepareStatement(medicineId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				id.add(rs.getInt("ID"));
			}

			StringJoiner sj = new StringJoiner(",");
			for (Integer value : id) {
				sj.add(value.toString());
			}
			medicinrecord = sj.toString();

			System.out.println("Medicine id : " + id);
			System.out.println("Medicine id in String : " + medicinrecord);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void getTestId() {
		try {
			pstmt = DataBaseConnection.getConnection().prepareStatement(testId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				testidlist.add(rs.getInt("ID"));
			}

			StringJoiner sj = new StringJoiner(",");
			for (Integer value : testidlist) {
				sj.add(value.toString());
			}
			testrecord = sj.toString();

			System.out.println("Test id : " + testidlist);
			System.out.println("Test id in String : " + testrecord);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}