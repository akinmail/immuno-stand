package com.akinmail.blockchain.immune;

import com.akinmail.blockchain.immune.model.Child;
import com.akinmail.blockchain.immune.model.Hospital;
import com.akinmail.blockchain.immune.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class AppController {
    @Autowired
    HospitalRepository hospitalRepository;

    @RequestMapping(value="/hospital", method=RequestMethod.POST)
    public Hospital createHospital(@RequestBody Hospital hospital){
        hospital.generateId();
        return hospitalRepository.save(hospital);
    }

    @RequestMapping(value="/hospital", method=RequestMethod.GET)
    public List<Hospital> getHospital(){
        return hospitalRepository.findAll();
    }

    @RequestMapping(value="/hospital/{id}", method=RequestMethod.GET)
    public Optional<Hospital> getHospital(@PathVariable String id){
        return hospitalRepository.findById(id);
    }

    @RequestMapping(value="/child/{hospitalid}", method=RequestMethod.POST)
    public Child registerChild(@RequestBody Child child, @PathVariable String hospitalid){
        child.generateDetailsHash();
        Optional<Hospital> hospital = hospitalRepository.findById(hospitalid);
        hospital.ifPresent(m->{
            m.getChildren().add(child);
            hospitalRepository.save(m);
        });

        //TODO call smart contract
        return child;
    }

    @RequestMapping(value="/child/{hash}", method=RequestMethod.GET)
    public List<Child> getChild(@PathVariable String hash){

        return hospitalRepository.findAll().stream()
                .flatMap(elt -> elt.getChildren().stream().filter(elt2 -> elt2.getDetailsHash().equals(hash)))
                .collect(Collectors.toList());
        //TODO call smart contract
        /*Child child = new Child();
        child.setChildName("akinyemi akindele");
        child.setDob("23/11/95");
        child.setMotherName("Ayo");
        child.setDetailsHash(hash);
        return child;*/
    }



}
