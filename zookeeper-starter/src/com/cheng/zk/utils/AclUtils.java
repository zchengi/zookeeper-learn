package com.cheng.zk.utils;

import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

/**
 * @author cheng
 *         2018/9/29 14:18
 */
public class AclUtils {

    public static String getDigestUserPwd(String id) throws Exception {
        return DigestAuthenticationProvider.generateDigest(id);
    }

    public static void main(String[] args) throws Exception {

        String id = "cheng:cheng";
        String idDigested = getDigestUserPwd(id);
        System.out.println(id);
    }
}
