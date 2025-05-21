package org.isandy.hope.Service;

public interface VirtualBrowser {
    /**
     * 获取浏览器列表
     * @return 浏览器列表实体类
     */
    default Object getBrowserList() {return null;}

    /**
     * 添加浏览器
     * @return 添加成功返回 该浏览器id, 否则返回 0
     */
    default int addBrowser() {return 0;}

    /**
     * 启动浏览器
     * @param id 浏览器 id
     * @return 启动成功返回该浏览器debug 端口, 启动失败返回 -1
     */
    default int launchBrowserId(int id) {return 0;}

    /**
     * 启动浏览器
     * @param UserId 浏览器 id
     * @param accountType 账号类型
     * @return 启动成功返回该浏览器debug 端口, 启动失败返回 -1
     */
    default int launchBrowser(Long UserId, String accountType) {return 0;}

    /**
     * 停止浏览器
     * @param id 浏览器 id
     * @return 停止成功返回 true, 否则返回 false
     */
    default boolean stopBrowser(int id) {return false;}
}
