package com.rong.community.community.controller;

import com.rong.community.community.dto.AccessTokenDTO;
import com.rong.community.community.dto.GitHubUser;
import com.rong.community.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_url("http://localhost:8887/callback");
        accessTokenDTO.setState(state);
        accessTokenDTO.setClient_id("Iv1.dde8a0bd528275a0");
        accessTokenDTO.setClient_secret("a7f698eb9fcc0f6988a49dfa7f9e63834e992028");
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GitHubUser user = githubProvider.getUser(accessToken);
        System.out.println(user.getName());
        return "index";
    }
}
