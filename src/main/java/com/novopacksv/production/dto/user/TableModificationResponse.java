package com.novopacksv.production.dto.user;

import com.novopacksv.production.dto.BaseEntityResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TableModificationResponse extends BaseEntityResponse {

    private Long userId;

    private String modificationDateTime;

    private String tableType;

}