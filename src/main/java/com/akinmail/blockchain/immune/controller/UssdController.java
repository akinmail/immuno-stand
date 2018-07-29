package com.akinmail.blockchain.immune.controller;

import com.akinmail.blockchain.immune.model.UssdRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UssdController {

    @RequestMapping(value="/ussd", method=RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String message(UssdRequest ussdRequest) {
        if(ussdRequest.getText().equals("")){
            return "CON What would you like to do? \n"+"1. Register Child \n"+ "2. Immunize Child \n";
        }else if(ussdRequest.getText().equals("1")){
            return "END Hello";
        }
        return "CON Hello";
    }
}

