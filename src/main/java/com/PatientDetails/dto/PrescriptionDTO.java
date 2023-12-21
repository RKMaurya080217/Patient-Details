package com.PatientDetails.dto;

import java.util.List;

public class PrescriptionDTO {
	List<MedicinesDTO> medicines;
	List<TestsDTO> tests;
	
	public List<MedicinesDTO> getMedicines() {
		return medicines;
	}
	public void setMedicines(List<MedicinesDTO> medicines) {
		this.medicines = medicines;
	}
	public List<TestsDTO> getTests() {
		return tests;
	}
	public void setTests(List<TestsDTO> tests) {
		this.tests = tests;
	}
	
}
