package org.isandy.hope.Dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class VbUser {

    private Long userId;

    private Long virtualBrowserIndexId;

    private Long virtualBrowserId;

    private int port;
}
