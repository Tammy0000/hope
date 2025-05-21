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
     * @description 基于本机IP来获取虚拟浏览器APIKEY
     * @return APIKEY
     */
    //todo 基于本机IP来获取虚拟浏览器APIKEY
    default String getVirtualBrowserApiKey() {return null;}

    /**
     * @description 基于本机IP虚拟浏览器实体类
     * @return 虚拟浏览器实体类
     */
    //todo 获取基于本机IP虚拟浏览器实体类
    default HopeProjectVirtualBrowser getVirtualBrowserByHost() {return null;}
}
