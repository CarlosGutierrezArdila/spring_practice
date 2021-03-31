package com.links.app.services;

import com.links.app.dto.InvalidateDTO;
import com.links.app.dto.LinkDTO;
import com.links.app.dto.MetricsDTO;
import com.links.app.exceptions.InvalidLinkException;
import com.links.app.exceptions.InvalidPasswordException;
import com.links.app.exceptions.LinkNotFoundException;

public interface LinkService {
    LinkDTO createLink(String url, String password) throws InvalidLinkException;
    LinkDTO getLink(Long linkId, String password) throws LinkNotFoundException, InvalidPasswordException;
    MetricsDTO getMetrics(Long linkId) throws LinkNotFoundException;
    InvalidateDTO invalidateLink(Long linkId) throws LinkNotFoundException;

}
