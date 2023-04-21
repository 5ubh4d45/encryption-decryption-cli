package dev.ixale.encryptdecrypt.Processor;

import dev.ixale.encryptdecrypt.Encryption.Cipher;
import dev.ixale.encryptdecrypt.Encryption.ShiftCipher;
import dev.ixale.encryptdecrypt.Encryption.UnicodeCipher;
import dev.ixale.encryptdecrypt.config.ArgsConfig;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class ArgsProcessorImpl implements ArgsProcessor{
    @Override
    public boolean processArgs(String[] parsedArgs) {
        final ArgsConfig.Mode DEFAULT_MODE = ArgsConfig.DEFAULT_MODE;
        final String DEFAULT_KEY = ArgsConfig.DEFAULT_KEY;
        final String DEFAULT_DATA = ArgsConfig.DEFAULT_DATA;
        final String DEFAULT_IN = ArgsConfig.DEFAULT_IN;
        final String DEFAULT_OUT = ArgsConfig.DEFAULT_OUT;
        final ArgsConfig.Algorithm DEFAULT_ALG = ArgsConfig.DEFAULT_ALG;

        // parsing
//        setDefaultArgs(parsedArgs, DEFAULT_MODE.getValue(), DEFAULT_KEY, DEFAULT_DATA, DEFAULT_IN, DEFAULT_OUT, DEFAULT_ALG.getValue());

        // changed to a more readable format
        String[] defaultArgs = new String[]{
                DEFAULT_MODE.getValue(),
                DEFAULT_KEY,
                DEFAULT_DATA,
                DEFAULT_IN,
                DEFAULT_OUT,
                DEFAULT_ALG.getValue()
        };
        setDefaultArgs(parsedArgs, defaultArgs);

        // local args to do work
        ArgsConfig.Mode parsedMode = ArgsConfig.Mode.fromString(parsedArgs[0].toLowerCase());
        int parsedKey = handleKey(parsedArgs[1]);
        String parsedData = parsedArgs[2];
        String parsedIn = parsedArgs[3];
        String parsedOut = parsedArgs[4];
        ArgsConfig.Algorithm parsedAlg = ArgsConfig.Algorithm.fromString(parsedArgs[5].toLowerCase());

        String inputData = handleData(parsedData, parsedIn);
        String outputData = handleEncryption(parsedMode, parsedAlg, inputData, parsedKey);

        boolean isDataOut = handleOutputMethod(parsedOut, outputData);

        return isDataOut;
    }

    /**
     * Set the default values for the arguments
     * @param parsedArgs the arguments to set
     * @param defaultValue the default values
     */
    private static void setDefaultArgs(String[] parsedArgs, String ...defaultValue) {
        for (int index = 0; index < parsedArgs.length; index++) {
            parsedArgs[index] = parsedArgs[index].equals("") ? defaultValue[index] : parsedArgs[index];
        }
    }

    /**
     * Parse the encryption key into an int
     * @param parsedKey the key to parse
     * @return the parsed key
     */
    private static int handleKey(String parsedKey) {
        int key = 0;
        try {
            key = Integer.parseInt(parsedKey);
        } catch (Exception e) {
            System.out.println("Enter valid key.");
        }
        return key;
    }

    /**
    * Handle the data
    * @param parsedData the data to encrypt
    * @param parsedInPath the path to the file to encrypt
    * @return the data to encrypt
     */
    private static String handleData(String parsedData, String parsedInPath) {
        StringBuilder data = new StringBuilder();

        // prioritize file path over data string
        // if file path is not empty, read from file
        if (!parsedInPath.equals("")) {

            try (Scanner sc = new Scanner(Path.of(parsedInPath))) {
                while (sc.hasNextLine()) {
                    data.append(sc.nextLine());
                }
            } catch (IOException e) {
                System.out.println("Error in handling input, file does not exist: " + e.getMessage());
            }

        }
        // if file path is empty, read from data string
        else if (!parsedData.equals("")) {
            data.append(parsedData);
        }
        else {
            System.out.println("Error in handling input: NO data or file does not exist");
        }
//        System.out.println(data.toString());
        return data.toString();
    }

    /**
     * Handle the encryption
     * @param mode the mode of the program
     * @param algType the algorithm type
     * @param data the data to encrypt
     * @param key the key to encrypt
     * @return the encrypted data
     */
    private static String handleEncryption(ArgsConfig.Mode mode, ArgsConfig.Algorithm algType, String data, int key) {
        Cipher cipher = null;
        switch (algType) {
            case UNICODE -> {
                cipher = new UnicodeCipher();
            }
            case SHIFT -> {
                cipher = new ShiftCipher();
            }
        }

        if (cipher == null) throw new IllegalStateException("Unexpected value: " + algType);

        return switch (mode) {
            case ENCRYPT -> cipher.encrypt(data, key);
            case DECRYPT -> cipher.decrypt(data, key);
            default -> "";
        };
    }

    /**
     * Handle the output method
     * @param parsedOutPath the output path
     * @param outputData the data to output
     * @return true if the data is outputted, false otherwise
     */
    private static boolean handleOutputMethod(String parsedOutPath, String outputData) {
        if (parsedOutPath.equals("")) {
            System.out.println(outputData);
            return false;
        }
        File file = new File(parsedOutPath);
        try (FileWriter writer = new FileWriter(file, false)) {
            writer.write(outputData);
//            System.out.println("OUTPATH: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error in handling output: " + e.getMessage());
        }
        return true;
    }
}
