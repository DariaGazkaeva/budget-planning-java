package ru.kpfu.itis.dariagazkaeva.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class HashingPassword {

    private final String SALT = "salt8salt";

    public String hash(String password) {
        return DigestUtils.sha1Hex(password + SALT);
    }
}
