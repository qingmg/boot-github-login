package cn.qingmg.demoboot.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * user 和 client 都是默认保存在 session 中的
 */
@Controller
@RequestMapping("/api/oath2")
public class GithubOauthController {

    /**
     * 获取当前认证的OAuth2用户信息
     *
     * @param user OAuth2用户信息
     * @return OAuth2用户信息
     */
    @GetMapping("/user")
    @ResponseBody
    public OAuth2User user(@AuthenticationPrincipal OAuth2User user) {
        return user;
    }

    /**
     * 获取当前认证的OAuth2客户端信息
     *
     * @param oAuth2AuthorizedClient OAuth2客户端信息
     * @return OAuth2客户端信息
     */
    @GetMapping("/client")
    @ResponseBody
    public OAuth2AuthorizedClient user(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient oAuth2AuthorizedClient) {
        return oAuth2AuthorizedClient;
    }
}
