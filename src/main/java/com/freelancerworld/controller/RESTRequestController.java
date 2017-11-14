package com.freelancerworld.controller;

import com.freelancerworld.model.*;
import com.freelancerworld.service.Implementation.AddressServiceImpl;
import com.freelancerworld.service.Implementation.ProfessionServiceImpl;
import com.freelancerworld.service.Implementation.RequestServiceImpl;
import com.freelancerworld.service.Implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Patron on 10.11.2017.
 */
@RestController
@RequestMapping("/request")
public class RESTRequestController {


        @Autowired
        private RequestServiceImpl requestService;

        @Autowired
        private AddressServiceImpl addressService;

        @Autowired
        private UserServiceImpl userService;

        @Autowired
        private ProfessionServiceImpl professionService;

        @RequestMapping("/getall")
        public List<Request> findAll() {
            return requestService.findAllRequests();
        }

        @RequestMapping(value = "/newrequest", method = RequestMethod.POST)
        public @ResponseBody Message addRequest(@RequestBody UserAddressRequestProfessionContext context) {
                addressService.saveAddress(context.getAddress());

                User tempUser = userService.findUserById(context.getUser().getId());
                Profession tempProfession = professionService.findProfessionByName(context.getProfession().getName());

                context.getRequest().setAddress(context.getAddress());
                context.getRequest().setUser(tempUser);
                context.getRequest().setProfession(tempProfession);

                requestService.saveRequest(context.getRequest());
                return new Message(1, "Success!");
        }
}
