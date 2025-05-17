package com.sismics.docs.rest.resource;

import com.sismics.docs.core.constant.Constants;
import com.sismics.docs.core.dao.UserDao;
import com.sismics.docs.core.model.jpa.User;
import com.sismics.rest.exception.ClientException;
import com.sismics.rest.exception.ServerException;
import com.sismics.rest.util.ValidationUtil;

import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Date;

/**
 * Public register REST resource.
 * 允许未登录用户创建普通账号。
 */
@Path("/public/register")
public class PublicRegisterResource {

    /**
     * 公开注册接口。
     *
     * @param username 用户名
     * @param password 密码
     * @param email    邮箱
     * @return 注册结果
     */
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(@FormParam("username") String username,
                             @FormParam("password") String password,
                             @FormParam("email") String email) {

        // 基本校验
        username = ValidationUtil.validateLength(username, "username", 3, 50);
        ValidationUtil.validateUsername(username, "username");
        password = ValidationUtil.validateLength(password, "password", 8, 50);
        email = ValidationUtil.validateLength(email, "email", 1, 100);
        ValidationUtil.validateEmail(email, "email");

        // 创建用户
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setStorageQuota(100_000_000L);
        user.setRoleId(Constants.DEFAULT_USER_ROLE); // 默认用户角色
        user.setOnboarding(true);
        user.setDisableDate(new Date()); // 注册默认禁用（等待管理员审批）

        try {
            new UserDao().create(user, null); // null 表示系统创建者（未登录）
        } catch (Exception e) {
            if ("AlreadyExistingUsername".equals(e.getMessage())) {
                throw new ClientException("AlreadyExistingUsername", "用户名已存在", e);
            } else {
                throw new ServerException("UnknownError", "注册失败：" + e.getMessage(), e);
            }
        }

        // 成功返回
        JsonObjectBuilder response = Json.createObjectBuilder().add("status", "ok");
        return Response.ok(response.build()).build();
    }
}
