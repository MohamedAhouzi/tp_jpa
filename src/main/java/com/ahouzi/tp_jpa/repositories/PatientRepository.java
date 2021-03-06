package com.ahouzi.tp_jpa.repositories;

import com.ahouzi.tp_jpa.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient,Long> {
   public List<Patient> findByNomContains(String nom);
   public  List<Patient> findByMalade(boolean m);
   public List<Patient> findByNomContainsAndMalade(String nom,boolean b);
}
