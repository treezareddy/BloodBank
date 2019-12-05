package com.cognizant.bloodbankservice.service;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.bloodbankservice.model.Hospital;
import com.cognizant.bloodbankservice.model.SlotBooking;
import com.cognizant.bloodbankservice.repository.DonorSlotRepository;
import com.cognizant.bloodbankservice.repository.HospitalRepository;

@Service
public class DonorSlotService {

	@Autowired
	DonorSlotRepository donorSlotRepository;
	
	@Autowired
	HospitalRepository hospitalRepository;
	
	
	@Transactional
	public List<String> getHospitalList(int pincode) {
		return hospitalRepository.getHospitalList(pincode);
	}

	/*@Transactional
	public int getDonorSlotList(SlotBooking slot) {
		int hospitalId = slot.getHospital().getId();
		Time time = slot.getTime();
		Date date = slot.getDate();
		return donorSlotRepository.getDonorSlotList(hospitalId,time,date);
	}*/
	
	@Transactional
	public int getNoOfDonors(String hospitalName,Date date,Time time) {
		int hospitalId = hospitalRepository.findId(hospitalName);
		System.out.println("+++++++++++++"+hospitalId);
		List<SlotBooking> s = donorSlotRepository.findAll();
		int count = 0;
		for(SlotBooking booking: s) {
		System.err.println(booking.getDate()+" "+booking.getTime());
			if(booking.getHospital().getName().equals(hospitalName) && booking.getTime().equals(time) && booking.getDate().equals(date)) {
				count++;
			}
		}
		System.err.println(count);
		return count;
//		return donorSlotRepository.getNoOfDonors(hospitalId,date);
	}
	
	
	@Transactional
	public void saveSlot(SlotBooking slot,String hospitalName) {
//		Hospital hospital = hospitalRepository.findByName(hospitalName);
//		int hospitalId = hospitalRepository.findId(hospitalName);
//		String hospitalName = slot.getHospital();
		System.out.println("safs"+hospitalName);
		Hospital h = hospitalRepository.findByName(hospitalName);
		slot.setHospital(h);
		System.out.println("safs"+slot);
		donorSlotRepository.save(slot);
	}
	
}
