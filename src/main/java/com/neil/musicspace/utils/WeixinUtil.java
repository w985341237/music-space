package com.neil.musicspace.utils;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.AlgorithmParameters;
import java.security.Security;
import java.util.*;

/**
 * @Description 微信 工具
 * @Author neil
 * @Date 2021/6/29 13:26
 * @Version 1.0
 **/
@Slf4j
@Configuration
public class WeixinUtil {

    @Value("${wechat.appId}")
    private String appId;

    @Value("${wechat.appSecret}")
    private String appSecret;

    public Map<String, String> getWechatConfig() {
        Map<String, String> map = new HashMap<>();
        map.put("appId", appId);
        map.put("appSecret", appSecret);
        return map;
    }

    /**
     * 校验签名
     *
     * @param rawData
     * @param sessionKey
     * @param signature
     * @return
     */
    public boolean checkSignature(String rawData, String sessionKey, String signature) {
        Digester digester = new Digester(DigestAlgorithm.SHA1);
        String data = rawData + sessionKey;
        String genSignature = digester.digestHex(data);
        return genSignature.equals(signature);
    }

    /**
     * 解密，获取信息
     *
     * @param encryptedData
     * @param sessionKey
     * @param iv
     * @return
     */
    public JSONObject wxDecrypt(String encryptedData, String sessionKey, String iv) {
        // 被加密的数据
        byte[] dataBytes = Base64.getDecoder().decode(encryptedData);
        // 加密秘钥
        byte[] keyBytes = Base64.getDecoder().decode(sessionKey);
        // 偏移量
        byte[] ivBytes = Base64.getDecoder().decode(iv);
        try {
            // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
            int base = 16;
            if (keyBytes.length % base != 0) {
                int groups = keyBytes.length / base + (keyBytes.length % base != 0 ? 1 : 0);
                byte[] temp = new byte[groups * base];
                Arrays.fill(temp, (byte) 0);
                System.arraycopy(keyBytes, 0, temp, 0, keyBytes.length);
                keyBytes = temp;
            }
            // 初始化
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
            SecretKeySpec spec = new SecretKeySpec(keyBytes, "AES");
            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivBytes));
            // 初始化
            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);
            byte[] resultByte = cipher.doFinal(dataBytes);
            if (null != resultByte && resultByte.length > 0) {
                String result = new String(resultByte, "UTF-8");
                return JSONObject.parseObject(result);
            } else {
                log.error("decrypt Data fail!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
