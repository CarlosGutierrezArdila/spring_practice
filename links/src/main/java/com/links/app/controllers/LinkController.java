package com.links.app.controllers;

import com.links.app.dto.InvalidateDTO;
import com.links.app.dto.LinkDTO;
import com.links.app.dto.MetricsDTO;
import com.links.app.exceptions.ErrorDTO;
import com.links.app.exceptions.InvalidLinkException;
import com.links.app.exceptions.InvalidPasswordException;
import com.links.app.exceptions.LinkNotFoundException;
import com.links.app.services.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LinkController {
    @Autowired
    private LinkService linkService;

    @PostMapping("/createLink")
    public ResponseEntity createLink(@RequestParam(value = "url") String url, @RequestParam(value = "password", defaultValue = "", required = false) String password) throws InvalidLinkException {
        LinkDTO linkDTO = linkService.createLink(url, password);

        return new ResponseEntity(linkDTO, HttpStatus.OK);
    }

    @PostMapping("/link/{linkId}")
    public void createLink(@PathVariable Long linkId, @RequestParam(value = "password", defaultValue = "", required = false) String password, HttpServletResponse response) throws LinkNotFoundException, IOException, InvalidPasswordException {
        LinkDTO linkDTO = linkService.getLink(linkId, password);

        response.sendRedirect(linkDTO.getUrl());
    }

    @PostMapping("/metrics/{linkId}")
    public ResponseEntity createLink(@PathVariable Long linkId) throws LinkNotFoundException {
        MetricsDTO metricsDTO = linkService.getMetrics(linkId);

        return new ResponseEntity<MetricsDTO>(metricsDTO, HttpStatus.OK);
    }

    @PostMapping("/invalidate/{linkId}")
    public ResponseEntity invalidateLink(@PathVariable Long linkId) throws LinkNotFoundException {
        InvalidateDTO invalidateDTO = linkService.invalidateLink(linkId);

        return new ResponseEntity<InvalidateDTO>(invalidateDTO, HttpStatus.OK);
    }

    @ExceptionHandler(InvalidLinkException.class)
    public ResponseEntity exceptionHandler(InvalidLinkException e) {
        return new ResponseEntity(new ErrorDTO("Link inválido", e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(LinkNotFoundException.class)
    public ResponseEntity exceptionHandler(LinkNotFoundException e) {
        return new ResponseEntity(new ErrorDTO("Link no encontrado", e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity exceptionHandler(InvalidPasswordException e) {
        return new ResponseEntity(new ErrorDTO("Password incorrecta", e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
