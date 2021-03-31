package com.links.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class LinkDTO {
    private Long linkId;
    private String url;
    private String password;

}
