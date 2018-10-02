package com.cheng.utils;

import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

import java.security.NoSuchAlgorithmException;

/**
 * @author cheng
 *         2018/10/2 14:55
 */
public class AclUtils {

    private AclUtils() {
    }

    public static String getDigestUserPwd(String id) {
        String digest = "";
        try {
            digest = DigestAuthenticationProvider.generateDigest(id);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return digest;
    }

    public static void main(String[] args) {

        String id = "cheng:cheng";
        String idDigested = getDigestUserPwd(id);
        System.out.println(id);
    }
}
