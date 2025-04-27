package com.sismics.docs.core.dao.jpa;

import com.sismics.docs.BaseTransactionalTest;
import com.sismics.docs.core.dao.GroupDao;
import com.sismics.docs.core.model.jpa.Group;
import org.junit.Assert;
import org.junit.Test;

public class TestGroupDao extends BaseTransactionalTest {

    @Test
    public void testGetActiveByName_success() throws Exception {
        GroupDao groupDao = new GroupDao();
        String userId = "user123";

        // 创建组
        Group group = new Group();
        group.setName("Test Group by Name");

        // 确保在事务中进行操作
        // 事务由 BaseTransactionalTest 管理，无需显式开始或提交
        groupDao.create(group, userId);  // 保存组

        // 根据名称获取组
        Group foundGroup = groupDao.getActiveByName("Test Group by Name");

        // 验证结果
        Assert.assertNotNull(foundGroup); // 应该找到该组
        Assert.assertEquals("Test Group by Name", foundGroup.getName());  // 名称应该匹配
    }

    @Test
    public void testGetActiveByName_notFound() throws Exception {
        GroupDao groupDao = new GroupDao();

        // 尝试根据不存在的名称获取组
        Group foundGroup = groupDao.getActiveByName("Nonexistent Group");

        // 验证结果
        Assert.assertNull(foundGroup); // 应该返回null，因为没有找到匹配的组
    }
}
