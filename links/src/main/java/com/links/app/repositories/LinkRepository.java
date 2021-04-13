package com.links.app.repositories;

import com.links.app.dto.InvalidateDTO;
import com.links.app.dto.LinkDTO;
import com.links.app.dto.MetricsDTO;

public interface LinkRepository {
    LinkDTO createLink(String url, String password);

    LinkDTO getLinkByLinkId(Long linkId);

    void incrementRedirect(LinkDTO linkDTO);

    MetricsDTO getMetrics(LinkDTO linkDTO);

    InvalidateDTO invalidateLink(LinkDTO linkDTO);
}
