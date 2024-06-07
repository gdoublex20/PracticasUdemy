package com.jesus.client.client_practica_app.services;

import com.jesus.client.client_practica_app.util.AESUtils;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.util.Base64;

@Service
public class EncryptionServiceImp {

    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";
    private final SecretKey secretKey;
    private final IvParameterSpec ivParameterSpec;

    public EncryptionServiceImp() throws Exception {
        // Generar clave y IV
        this.secretKey = AESUtils.generateKey(256); // O usar una clave predefinida
        this.ivParameterSpec = new IvParameterSpec(AESUtils.generateIV()); // O usar un IV predefinido
    }

    public String encrypt(String data) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
        byte[] encrypted = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }
}
