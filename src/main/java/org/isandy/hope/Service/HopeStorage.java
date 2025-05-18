package org.isandy.hope.Service;

import org.isandy.hope.Entity.Project.HopeProjectVirtualBrowser;

public interface HopeStorage {
    /**
     * @description 获取虚拟浏览器实体类
     * @param userId 用户ID
     * @return 虚拟浏览器实体类
     */
    default HopeProjectVirtualBrowser  getVirtualBrowser(Long userId) {return null;}

    /**
     * @description 根据Host地址来获取虚拟浏览器APIKEY
     * @return APIKEY
     */
    //todo 根据Host地址来获取虚拟浏览器APIKEY
    default String getVirtualBrowserApiKey() {return null;}
}
