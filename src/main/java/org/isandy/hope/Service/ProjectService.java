package org.isandy.hope.Service;

import org.isandy.hope.Entity.HopeProjectTwitter;

import java.util.List;

public interface ProjectService {

    /**
     * 编辑twitter密码
     */
    default int CreateTwitterSourceData(Long userId) {return -2;}

    /**
     * 获取twitter列表 根据是否编辑过密码
      * @param userId 用户id
     * @param isEditPassword 是否编辑密码
     * @return twitter列表
     */
    default List<HopeProjectTwitter> GetTwitterList(Long userId, boolean isEditPassword) {return null;}

}
