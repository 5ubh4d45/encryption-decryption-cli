package dev.ixale.encryptdecrypt.Encryption;

public class ShiftCipher implements Cipher{
    @Override
    public String encrypt(String text, int key) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++){
            char c = text.charAt(i);
            int t = 0;
            if (c >= 'a' && c <= 'z') {
                t = ((c + key - 'a') % 26) + 'a';
            }
            else if (c >= 'A' && c <= 'Z') {
                t = ((c + key - 'A') % 26) + 'A';
            }
            else {
                t = c;
            }
//            int t = c + key;
            sb.append((char)t);
        }
        return sb.toString();
    }

    @Override
    public String decrypt(String text, int key) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++){
            char c = text.charAt(i);
            int t = 0;
            if (c >= 'a' && c <= 'z') {
                int n = (c - key - 'a');
                if (n < 0) n = 26 + n;
                t = (n % 26) + 'a';
            }
            else if (c >= 'A' && c <= 'Z') {
                int n = (c - key - 'A');
                if (n < 0) n = 26 + n;
                t = (n % 26) + 'A';
            }
            else {
                t = c;
            }
//            int t = c - key;
            sb.append((char)t);
        }
        return sb.toString();
    }
}
