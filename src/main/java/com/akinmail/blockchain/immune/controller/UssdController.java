package com.akinmail.blockchain.immune.controller;

import com.akinmail.blockchain.immune.model.Child;
import com.akinmail.blockchain.immune.model.Session;
import com.akinmail.blockchain.immune.model.UssdRequest;
import com.akinmail.blockchain.immune.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UssdController {
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private AppController appController;

    @RequestMapping(value="/ussd", method=RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String message(UssdRequest ussdRequest) {
        //TODO resume state
        if(ussdRequest.getText().equals("")){
            Session session = new Session();
            session.setSessionId(ussdRequest.getSessionId());
            session.setPhoneNo(ussdRequest.getPhoneNumber());
            sessionRepository.save(session);
            return "CON What would you like to do? \n"+"1. Register Child \n"+ "2. Immunize Child \n";
        }else{
            if(sessionRepository.existsById(ussdRequest.getSessionId())){
                Optional<Session> session = sessionRepository.findById(ussdRequest.getSessionId());
                Session session1 = session.get();
                String returnString = "";
                switch (session1.getState()){
                    case "0":
                        if(ussdRequest.getText().equals("1")){
                            session1.setState("1A");
                            sessionRepository.save(session1);
                            returnString = "CON What is the name of the child? \n";
                        }else if(ussdRequest.getText().equals("2")){
                            session1.setState("2A");
                            sessionRepository.save(session1);
                            returnString = "CON What is the name of the Child? \n";
                        }
                        break;
                    case "1A":
                        Child child = new Child();
                        child.setChildName(ussdRequest.getText().split("\\*")[ussdRequest.getText().split("\\*").length-1]);
                        session1.setChild(child);
                        returnString = "CON What is the name of the child's mother? \n";
                        session1.setState("1B");
                        sessionRepository.save(session1);
                        break;
                    case "1B":
                        session1.getChild().setMotherName(ussdRequest.getText().split("\\*")[ussdRequest.getText().split("\\*").length-1]);
                        returnString = "CON What is the date of birth of the child? \n";
                        session1.setState("1C");
                        sessionRepository.save(session1);
                        break;
                    case "1C":
                        session1.getChild().setDob(ussdRequest.getText().split("\\*")[ussdRequest.getText().split("\\*").length-1]);
                        returnString = "CON What is the phone no of the mother or father or relative? \n";
                        session1.setState("1D");
                        sessionRepository.save(session1);
                        break;
                    case "1D":
                        session1.getChild().setPhoneNumber(Long.valueOf(ussdRequest.getText().split("\\*")[ussdRequest.getText().split("\\*").length-1]));
                        returnString = "CON What is the name of the hospital? \n";
                        session1.setState("1E");
                        sessionRepository.save(session1);
                        break;
                    case "1E":
                        String hospitalName = ussdRequest.getText().split("\\*")[ussdRequest.getText().split("\\*").length-1];
                        returnString = "END Thanks, the child has been registered \n";
                        session1.setState("1F");
                        sessionRepository.save(session1);
                        try {
                            appController.regissterChildByHospitalName(session1.getChild(), hospitalName);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case "2A":
                        Child child1 = new Child();
                        child1.setChildName(ussdRequest.getText().split("\\*")[ussdRequest.getText().split("\\*").length-1]);
                        session1.setChild(child1);
                        returnString = "CON What is the phone no of the mother or father or relative? \n";
                        session1.setState("2B");
                        sessionRepository.save(session1);
                        break;
                    case "2B":
                        session1.getChild().setPhoneNumber(Long.valueOf(ussdRequest.getText().split("\\*")[ussdRequest.getText().split("\\*").length-1]));
                        returnString = "CON What is the name of the Disease being immunized? \n";
                        session1.setState("2C");
                        sessionRepository.save(session1);
                        break;
                    case "2C":
                        session1.getChild().setChildName(ussdRequest.getText().split("\\*")[ussdRequest.getText().split("\\*").length-1]);
                        returnString = "END Thanks, the record of the child being immunized has been saved \n";
                        session1.setState("2D");
                        sessionRepository.save(session1);
                        break;

                }
                return returnString;
            }
        }

        return "CON Hello";
    }
}

