package org.isandy.hope.Service.Project;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.isandy.hope.Dao.HopeProjectTwitterRepository;
import org.isandy.hope.Dao.HopeProjectTwitterSourceStrRepository;
import org.isandy.hope.Entity.Project.HopeProjectTwitter;
import org.isandy.hope.Entity.Project.HopeProjectTwitterSourceStr;
import org.isandy.hope.Service.ProjectService;
import org.isandy.hope.Utils.TwitterEntity;
import org.isandy.hope.Utils.TwitterUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TwitterServer implements ProjectService {

    private final HopeProjectTwitterSourceStrRepository  hopeProjectTwitterSourceStrRepository;

    private final HopeProjectTwitterRepository hopeProjectTwitterRepository;

    /**
     * 将推特的原始字符串保存到数据库中
     * @param userId 用户id
     * @return -1 没有可以转换的数据转换，0 表示成功
     */
    //todo 将推特的原始字符串保存到数据库中
    @Override
    public int CreateTwitterSourceData(Long userId) {
        List<HopeProjectTwitterSourceStr> isUseAndUserId = hopeProjectTwitterSourceStrRepository.findByIsUseAndUserId(false, userId);

        if (isUseAndUserId.isEmpty()) return -1;

        // 遍历集合
        for (HopeProjectTwitterSourceStr sourceStr : isUseAndUserId) {
            TwitterEntity entity = TwitterUtils.ConvertToTwitterEntity(sourceStr.getSourceStr());
            HopeProjectTwitter hopeProjectTwitter = new HopeProjectTwitter()
                    .setTwitterAccount(entity.getTwitterAccount())
                    .setTwitterSourcePassword(entity.getTwitterSourcePassword())
                    .setEmail(entity.getEmail())
                    .setEmailSourcePassword(entity.getEmailSourcePassword())
                    .setTwitterSource2faPassword(entity.getTwitterSource2faPassword())
                    .setCreateTime(LocalDateTime.now())
                    .setUserId(userId)
                    .setTwitterLoginWebsite(sourceStr.getTwitterLoginWebsite())
                    .setEmailLoginWebsite(sourceStr.getEmailLoginWebsite())
                    .setTwoFaConversionWebsite(sourceStr.getTwoFaConversionWebsite());

            //判断某个推特账户是否存在
            boolean exists = hopeProjectTwitterRepository.existsByTwitterAccount(entity.getTwitterAccount());

            // todo 如果推特账户已经存在，则需要做一个更新日志存储，这里暂不做处理
            //如果存在，则跳过
            if (exists) continue;

            // 保存到Twitter用户表中
            hopeProjectTwitterRepository.save(hopeProjectTwitter);
            // 将已使用状态保存到TwitterSourceStr表中
            hopeProjectTwitterSourceStrRepository.save(sourceStr.setIsUse(true));
        }
        return 0;
    }

    /**
     * 获取Twitter列表
     * @param userId 用户id
     * @param isEditPassword 是否编辑密码
     * @return List<HopeProjectTwitter>
     */
    //todo 获取Twitter列表
    @Override
    public List<HopeProjectTwitter> GetTwitterList(Long userId, boolean isEditPassword) {
        return hopeProjectTwitterRepository.findByUserIdAndIsEditPassword(userId, isEditPassword);
    }
}
