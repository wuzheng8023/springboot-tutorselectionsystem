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


@Component
public class EncryptorComponent {

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${my.secretkey}")
    private String secretKey;
    @Value("${my.salt}")
    private String salt;
    //文本加/解密工具
    @Autowired
    private TextEncryptor textEncryptor;

    //这里保证了单例，避免反复创建增加开销
    @Bean
    public TextEncryptor gettextEncryptor() {
        return Encryptors.text(secretKey, salt);
    }

    /**
     * token加密
     */
    public String encryptToken(MyToken token) {

        try {
            String json = objectMapper.writeValueAsString(token);
            String t = textEncryptor.encrypt(json);
            return t;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "服务器端发生错误");
        }

    }

    /**
     * token解密
     */
    public MyToken decryptToken(String auth) {

        try {
            String string = textEncryptor.decrypt(auth);
            MyToken myToken = objectMapper.readValue(string, MyToken.class);
            return myToken;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "无权限");
        }
    }


}
