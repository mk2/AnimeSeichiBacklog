package com.me.h.lycaon.animeseichibacklog.controller;

import com.google.common.collect.Sets;
import com.me.h.lycaon.animeseichibacklog.domain.User;
import org.junit.Test;

import java.awt.image.BufferedImage;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Created by lycaon_h on 2014/03/13.
 */
public class UserControllerTest extends AbstractControllerTest {

    private User user;


    @Override
    protected void setupBeans() {
        user = getUser();
        when(userInfoService.getUserId()).thenReturn(user.getUserId());
        when(userInfoService.login(user.getUserName(), user.getUserPassword())).thenReturn(true);
        when(userInfoService.getUserName()).thenReturn(user.getUserName());
        when(userInfoService.getUserRoles()).thenReturn(Sets.newHashSet("ROLE_USER"));

        when(imageService.saveImageWithThumbnail(any(BufferedImage.class))).thenReturn("test.png");

        when(userCrudService.readByEmail(anyString())).thenReturn(getUser());
        doThrow(new RuntimeException()).when(userCrudService)
                                       .create(any(User.class));
    }


    /**
     * ログインテスト
     *
     * @throws Exception
     */
    public void testLogin() throws Exception {
        // ユーザー名は何でもパスする用に設定されている
        mockMvc.perform(post("/u/login").param("userEmail", user.getUserEmail())
                                        .param("userPassword", user.getUserPassword()))
                // ログインに成功した場合、地図画面へ飛ぶので302コードが返ってくることを確認
                .andExpect(status().isFound())
                .andExpect(redirectedUrlPattern("/map*"))
                .andExpect(model().attribute("userRole", "ROLE_USER"));
    }


    /**
     * ユーザー作成テスト
     * ユーザー作成からその後の自動ログインまでをテスト
     *
     * @throws Exception
     */
    @Test
    public void testCreate() throws Exception {
        mockMvc.perform(post("/u/create").param("userAlias", user.getUserAlias())
                                         .param("userPassword", user.getUserPassword())
                                         .param("userEmail", user.getUserEmail()))
               .andExpect(status().isInternalServerError())
               .andExpect(model().attributeExists("msg.body"))
               .andExpect(model().attributeExists("msg.title"));
    }


    @Test
    public void testUpdate() throws Exception {

    }


    @Test
    public void testWithdraw() throws Exception {

    }
}
