package dev.ixale.encryptdecrypt.encryption;


public interface Cipher {
    /**
     * Encrypt the data with the key
     * @param data the data to encrypt
     * @param key the key to encrypt the data
     * @return the encrypted data
     */
    String encrypt(String data, int key);
    /**
     * Decrypt the data with the key
     * @param data the data to decrypt
     * @param key the key to decrypt the data
     * @return the decrypted data
     */
    String decrypt(String data, int key);
}