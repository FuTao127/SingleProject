package com.szeastroc.middle.code.syn.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class ShiroSaltUtils {
    public static String generatePassword(String password, String salt) {
        ByteSource credentialsSalt = ByteSource.Util.bytes(salt);
        Object passwordObject = new SimpleHash("MD5", password, credentialsSalt, 1024);
        return passwordObject.toString();
    }

}
