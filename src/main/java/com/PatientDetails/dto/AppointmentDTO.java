package com.PatientDetails.dto;

public class AppointmentDTO {
	private String reason;
	private String hospital_name;
	private String address;
	private String doctor_name;
	private double fee;
	private PrescriptionDTO prescriptionDTO;

	public String getHospital_name() {
		return hospital_name;
	}

	public void setHospital_name(String hospital_name) {
		this.hospital_name = hospital_name;
	}

	public String getDoctor_name() {
		return doctor_name;
	}

	public void setDoctor_name(String doctor_name) {
		this.doctor_name = doctor_name;
	}

	public PrescriptionDTO getPrescriptionDTO() {
		return prescriptionDTO;
	}

	public void setPrescriptionDTO(PrescriptionDTO prescriptionDTO) {
		this.prescriptionDTO = prescriptionDTO;
	}

	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
