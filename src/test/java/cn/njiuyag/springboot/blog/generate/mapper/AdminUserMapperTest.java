package cn.njiuyag.springboot.blog.generate.mapper;

import cn.njiuyag.springboot.blog.generate.model.AdminUserExample;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AdminUserMapperTest {
    @Autowired
    AdminUserMapper adminUserMapper;

    @Test
    void selectByExample() {
        AdminUserExample adminUserExample = new AdminUserExample();
        adminUserExample.createCriteria();
        long l = adminUserMapper.countByExample(adminUserExample);
        System.out.println(l);

    }
}