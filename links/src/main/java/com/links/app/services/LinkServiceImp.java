package com.links.app.services;

import com.links.app.dto.InvalidateDTO;
import com.links.app.dto.LinkDTO;
import com.links.app.dto.MetricsDTO;
import com.links.app.exceptions.InvalidLinkException;
import com.links.app.exceptions.InvalidPasswordException;
import com.links.app.exceptions.LinkNotFoundException;
import com.links.app.repositories.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;

public class LinkServiceImp implements LinkService{
    @Autowired
    private LinkRepository linkRepository;

    @Override
    public LinkDTO createLink(String url, String password) throws InvalidLinkException {
        try {
            new URL(url).toURI();

            return linkRepository.createLink(url, password);
        }
        catch (Exception e) {
            throw new InvalidLinkException("El link ingresado es inválido");
        }
    }

    @Override
    public LinkDTO getLink(Long linkId, String password) throws LinkNotFoundException, InvalidPasswordException {
        LinkDTO linkDTO = getLinkByLinkId(linkId);

        if (linkDTO.getPassword().equals(password))
            linkRepository.incrementRedirect(linkDTO);
        else
            throw new InvalidPasswordException("La password ingresada es incorrecta");

        return linkDTO;
    }

    @Override
    public MetricsDTO getMetrics(Long linkId) throws LinkNotFoundException {
        LinkDTO linkDTO = getLinkByLinkId(linkId);

        return linkRepository.getMetrics(linkDTO);
    }

    @Override
    public InvalidateDTO invalidateLink(Long linkId) throws LinkNotFoundException {
        LinkDTO linkDTO = getLinkByLinkId(linkId);

        return linkRepository.invalidateLink(linkDTO);
    }

    private LinkDTO getLinkByLinkId(Long linkId) throws LinkNotFoundException {
        LinkDTO linkDTO = linkRepository.getLinkByLinkId(linkId);

        if (linkDTO == null)
            throw new LinkNotFoundException("El link ingresado no se encuentra en la base de datos");

        return linkDTO;
    }
}
