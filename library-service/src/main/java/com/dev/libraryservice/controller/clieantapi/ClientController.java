package com.dev.libraryservice.controller.clieantapi;

import com.dev.libraryservice.config.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
public class ClientController {

    @Autowired
    private ClientService clientService;

    @RequestMapping(
            method = {GET, DELETE},
            value = {"/books/**","/users/**"}
    )
    public Object connectToClientService(HttpServletRequest request) throws Exception {
        return clientService.sendRequest(request);
    }

    @RequestMapping(
            method = {POST, PUT},
            value = {"/books/**","/users/**"}
    )
    public Object connectToClientService(HttpServletRequest request, @RequestBody Map requestBody) throws Exception {
        return clientService.sendRequest(request, requestBody);
    }
}
