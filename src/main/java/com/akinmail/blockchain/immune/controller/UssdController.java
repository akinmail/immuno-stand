package com.akinmail.blockchain.immune.controller;

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

    @RequestMapping(value="/ussd", method=RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String message(UssdRequest ussdRequest) {
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
                switch (session1.getState()){}
            }
        }

        return "CON Hello";
    }
}

