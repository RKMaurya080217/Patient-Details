package com.PatientDetails.dto;

public class MedicalRecordDTO {
	private PatientDTO patientDTO;
	private AppointmentDTO appointmentDTO;

	public PatientDTO getPatientDTO() {
		return patientDTO;
	}

	public void setPatientDTO(PatientDTO patientDTO) {
		this.patientDTO = patientDTO;
	}

	public AppointmentDTO getAppointmentDTO() {
		return appointmentDTO;
	}

	public void setAppointmentDTO(AppointmentDTO appointmentDTO) {
		this.appointmentDTO = appointmentDTO;
	}

}
