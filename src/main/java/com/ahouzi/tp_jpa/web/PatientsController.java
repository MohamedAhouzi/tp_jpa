package com.ahouzi.tp_jpa.web;

import com.ahouzi.tp_jpa.entities.Patient;
import com.ahouzi.tp_jpa.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PatientsController {
    @Autowired
    private PatientRepository patientRepository;

    @GetMapping(path= "/index")
    public String index(){
        return "index";
    }

    @GetMapping("/patients")
    public String list(Model model,
                       @RequestParam(name = "page",defaultValue = "0")int page,
                       @RequestParam(name = "size",defaultValue = "5") int size,
                       @RequestParam(name = "keyword",defaultValue = "") String mc){
         Page<Patient> pagePatients=patientRepository.findByNomContains(mc,PageRequest.of(page,size));
         model.addAttribute("patients",pagePatients.getContent());
         model.addAttribute("pages",new int[pagePatients.getTotalPages()]);
         model.addAttribute("currentPage",page);
         model.addAttribute("size",size);
         model.addAttribute("keyword",mc);
         return "listePatients";
    }
    //redirection
    @GetMapping("/deletePatient")
    public String delete(Long id,String keyword,int page,int size){
        patientRepository.deleteById(id);
        return "redirect:/patients?page="+page+"&size="+size+"&keyword="+keyword;
    }
    //forward
    @GetMapping("/deletePatient2")
    public String delete2(Model model,Long id,String keyword,int page,int size){
        patientRepository.deleteById(id);
        return list(model,page,size,keyword);
    }



   /* @GetMapping("/patients/{id}")
    public Patient patient(@PathVariable Long id){
        return patientRepository.findById(id).get();
    }

    @PostMapping("/addPatient")
    private void addPatient(Patient patient){
        patientRepository.save(patient);
    }
*/





}

