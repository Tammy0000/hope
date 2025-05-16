package org.isandy.hope.Service;

import org.isandy.hope.Entity.Project.HopeProjectVirtualBrowser;

public interface HopeStorage {
    default HopeProjectVirtualBrowser  getVirtualBrowser(Long userId) {return null;}
}
