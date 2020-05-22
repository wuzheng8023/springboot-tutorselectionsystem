package com.example.tutorselectionsystem.component;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;


@Slf4j
@Component
public class EncryptorComponent {

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${my.secretkey}")
    private String secretKey;
    @Value("${my.salt}")
    private String salt;

    @Autowired
    private TextEncryptor encryptor;

    @Bean
    public TextEncryptor gettextEncryptor() {
        return Encryptors.text(secretKey, salt);
    }


    /**
     *token加密
     */
    public String encryptToken(MyToken token) {

        try {
            String json = objectMapper.writeValueAsString(token);
            String t = encryptor.encrypt(json);
            return t;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "未登陆");
        }

    }

    /**
     * token解密
     */
    public MyToken decryptToken(String auth) {

        try {
            String string = encryptor.decrypt(auth);
          MyToken myToken= objectMapper.readValue(string, MyToken.class);
            return myToken;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "未登陆");
        }
    }

    /**
     * 密码加密
     * @param pwd
     * @return
     */
    public String encryptPwd(String pwd){

        return encryptor.encrypt(pwd);
    }

    /**
     * 密码解密
     * @param pwd
     * @return
     */
    public String decryptPwd(String pwd){

        return encryptor.decrypt(pwd);
    }


}
