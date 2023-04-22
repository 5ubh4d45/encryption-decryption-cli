package dev.ixale.encryptdecrypt.encryption;

public class UnicodeCipher implements Cipher{
    @Override
    public String encrypt(String text, int key) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++){
            char c = text.charAt(i);
            // int t = c >= 'a' && c <= 'z' ? ((c + key - 'a') % ('z' - 'a' + 1)) + 'a' : c;
            int t = c + key;
            sb.append((char)t);
        }
        return sb.toString();
    }

    @Override
    public String decrypt(String text, int key) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++){
            char c = text.charAt(i);
            // int t = c >= 'a' && c <= 'z' ? ((c - key - 'a') % ('z' - 'a' + 1)) + 'a' : c;
            int t = c - key;
            sb.append((char)t);
        }
        return sb.toString();
    }
}
