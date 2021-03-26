## 使用

1. 更改 application.yml 中的三个值
2. 更改 Github-OauthApp 中的回调地址为 application.yml 中的 redirectUri
3. 启动
4. 请求 /login

登陆成功会请求 /api/oath2/client，返回当前登陆的githubapp信息

## 日志

在 yml 中加上

logging:
  level:
    org.springframework.security: debug
    
## 说明

确保 yml 中的 redirectUri = Github-OauthApp 的 Authorization callback URL = WebSecurityConfigurerAdapter 中的 .loginProcessingUrl
