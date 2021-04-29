package com.ahouzi.tp_jpa;

import com.ahouzi.tp_jpa.entities.Patient;
import com.ahouzi.tp_jpa.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;

@SpringBootApplication

public class TpJpaApplication implements CommandLineRunner {
	@Autowired
   private PatientRepository patientRepository;
	public static void main(String[] args) {
		SpringApplication.run(TpJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
   /*  patientRepository.save(new Patient(null,"ahouzi",new Date(),12000,false));
     patientRepository.save(new Patient(null,"ahmed",new Date(),12000,false));
     patientRepository.save(new Patient(null,"hayat",new Date(),12000,false));
     patientRepository.save(new Patient(null,"ali",new Date(),12000,true));
     patientRepository.save(new Patient(null,"nawal",new Date(),12000,true));
     patientRepository.save(new Patient(null,"hamza",new Date(),12000,false));
     patientRepository.save(new Patient(null,"hamza",new Date(),12000,false));
		patientRepository.save(new Patient(null,"ali",new Date(),12000,true));
		patientRepository.save(new Patient(null,"nawal",new Date(),12000,true));
		patientRepository.save(new Patient(null,"hamza",new Date(),12000,false));
		patientRepository.save(new Patient(null,"hamza",new Date(),12000,false));



		patientRepository.findAll().forEach(patient -> {
		 System.out.println(patient.toString());
	 });
		System.out.println("\n*****************************************");

		Patient patient=patientRepository.findById(1L).get();
		System.out.println(patient.getNom());

		System.out.println("\n*****************************************");
		List<Patient> patients =patientRepository.findByNomContains("ah");
		patients.forEach(patient1 -> {
			System.out.println(patient1.toString());
		});

		System.out.println("\n*****************************************");
		List<Patient> patients1 =patientRepository.findByMalade(true);
		patients1.forEach(patient1 -> {
			System.out.println(patient1.toString());
		});

		System.out.println("\n*****************************************");
		List<Patient> patients2 =patientRepository.findByNomContainsAndMalade("h",false);
		patients2.forEach(patient1 -> {
			System.out.println(patient1.toString());
		});*/

		System.out.println("\n*****************************************");
		Page<Patient> patients3 =patientRepository.findAll(PageRequest.of(0,5));
		patients3.getContent().forEach(patient1 -> {
			System.out.println(patient1.toString());
		});


		//patientRepository.deleteById(7L);
	}
}
