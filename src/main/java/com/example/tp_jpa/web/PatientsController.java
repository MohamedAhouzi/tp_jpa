package com.example.tp_jpa.web;

import com.example.tp_jpa.entities.Patient;
import com.example.tp_jpa.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @GetMapping("/formPatient")
    public  String formPatient(Model model){
        model.addAttribute("patient",new Patient());
        model.addAttribute("mode","new");
        return "formPatient";
    }

    @PostMapping("/savePatient")
    public String savePatient(Model model,@Valid Patient patient, BindingResult errors){
       if(errors.hasErrors())
           return "formPatient";
       patientRepository.save(patient);
        return "confirmation";
    }
    @GetMapping("/editPatient")
    public  String formPatient(Model model,Long id){
        Patient p=patientRepository.findById(id).get();
        model.addAttribute("patient",p);
        model.addAttribute("mode","edit");
        return "formPatient";
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

