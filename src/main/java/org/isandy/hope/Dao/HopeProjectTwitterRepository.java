package org.isandy.hope.Dao;

import org.isandy.hope.Entity.Project.HopeProjectTwitter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HopeProjectTwitterRepository extends JpaRepository<HopeProjectTwitter, Long> {
    boolean existsByTwitterAccount(String twitterAccount);

    List<HopeProjectTwitter> findByUserIdAndIsEditPassword(Long userId, Boolean isEditPassword);

    /**
     * @description 根据用户id和是否登录查询
     * @param userId 用户id
     * @param isLogin 是否登录
     * @return HopeProjectTwitter
     */
    // todo 根据用户id和是否登录查询
    @Query("select h from HopeProjectTwitter h where h.userId = ?1 and h.isLogin = ?2")
    List<HopeProjectTwitter> findByUserIdAndIsLogin(Long userId, Boolean isLogin);

    /**
     * @description 根据twitter账号查询
     * @param twitterAccount twitter账号
     * @return HopeProjectTwitter
     */
    // todo 根据twitter账号查询
    @Query("select h from HopeProjectTwitter h where h.twitterAccount = ?1")
    HopeProjectTwitter findByTwitterAccount(String twitterAccount);

    /**
     * @description 根据用户id查询
     * @param userId 用户id
     * @return HopeProjectTwitter
     */
    // todo 根据用户id查询
    @Query("select h from HopeProjectTwitter h where h.userId = ?1")
    List<HopeProjectTwitter> findByUserId(Long userId);


}