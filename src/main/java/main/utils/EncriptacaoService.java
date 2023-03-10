package main.utils;


import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

@Deprecated
public class EncriptacaoService {

    private static final String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final int KEY_SIZE = 128;
    private static final int ITERATION_COUNT = 65536;
    private static final int SALT_LENGTH = 8;
    private static final int IV_LENGTH = 16;

    public static String encode(String json, String encode) {
        String encoded = null;
        try {
            byte[] salt = new byte[SALT_LENGTH];
            SecureRandom random = new SecureRandom();
            random.nextBytes(salt);
            PBEKeySpec keySpec = new PBEKeySpec(encode.toCharArray(), salt, ITERATION_COUNT, KEY_SIZE);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            SecretKey key = keyFactory.generateSecret(keySpec);
            byte[] iv = new byte[IV_LENGTH];
            random.nextBytes(iv);
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv));
            byte[] encrypted = cipher.doFinal(json.getBytes("UTF-8"));
            byte[] combined = new byte[SALT_LENGTH + IV_LENGTH + encrypted.length];
            System.arraycopy(salt, 0, combined, 0, SALT_LENGTH);
            System.arraycopy(iv, 0, combined, SALT_LENGTH, IV_LENGTH);
            System.arraycopy(encrypted, 0, combined, SALT_LENGTH + IV_LENGTH, encrypted.length);
            encoded = Base64.getEncoder().encodeToString(combined);
        }catch (Exception e){
            e.printStackTrace();
        }
        return encoded;
    }
    public static String desencode(String encryptedJson, String encode) {
        String decripted = null;
        try {
            byte[] combined = Base64.getDecoder().decode(encryptedJson);
            byte[] salt = new byte[SALT_LENGTH];
            byte[] iv = new byte[IV_LENGTH];
            byte[] encrypted = new byte[combined.length - SALT_LENGTH - IV_LENGTH];
            System.arraycopy(combined, 0, salt, 0, SALT_LENGTH);
            System.arraycopy(combined, SALT_LENGTH, iv, 0, IV_LENGTH);
            System.arraycopy(combined, SALT_LENGTH + IV_LENGTH, encrypted, 0, encrypted.length);
            PBEKeySpec keySpec = new PBEKeySpec(encode.toCharArray(), salt, ITERATION_COUNT, KEY_SIZE);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            SecretKey key = keyFactory.generateSecret(keySpec);
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
            byte[] decrypted = cipher.doFinal(encrypted);
            decripted = new String(decrypted, StandardCharsets.UTF_8);
        }catch (Exception e){
            e.printStackTrace();
        }
        return decripted;
    }
}
