package com.envision.yuanj.dao;


import com.envision.yuanj.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

    /**
     * 按照用户查找用户
     *
     * @since 2019年10月31日 下午4:22:06
     * @param userName
     * @return
     */
    Optional<User> findTopByUserName(String userName);
}
