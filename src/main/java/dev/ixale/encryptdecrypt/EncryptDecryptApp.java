package dev.ixale.encryptdecrypt;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

import Parser.ArgsParser;
import Parser.ArgsParserImpl;
import dev.ixale.encryptdecrypt.Encryption.Cipher;
import dev.ixale.encryptdecrypt.Encryption.ShiftCipher;
import dev.ixale.encryptdecrypt.Encryption.UnicodeCipher;
import dev.ixale.encryptdecrypt.Processor.ArgsProcessor;
import dev.ixale.encryptdecrypt.Processor.ArgsProcessorImpl;

public class EncryptDecryptApp {
    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        String command = sc.nextLine();
//        String data = sc.nextLine();
//        int key = sc.nextInt();


        ArgsParser argsParser = new ArgsParserImpl();
        ArgsProcessor argsProcessor = new ArgsProcessorImpl();

//        String[] parsedArgs = parseArgsInOrder(args);
        String[] parsedArgs = argsParser.parseArgsInOrder(args);

//        boolean result = processArgs(parsedArgs);
        boolean result = argsProcessor.processArgs(parsedArgs);
    }

//    /*
    private static String[] parseArgsInOrder(String[] unParsedArgs) {
        final int ARGS_TOTAL_LENGTH = 6;

        int unParsedArgsLength = unParsedArgs.length;
        String[] parsedArgs = new String[ARGS_TOTAL_LENGTH];

        for (int i = 0; i < ARGS_TOTAL_LENGTH; i++) {
            parsedArgs[i] = "";
        }

        for (int i = 0; i < unParsedArgsLength; i++) {
            String s = unParsedArgs[i];

            switch (s) {
                case "-mode" -> {
                    fillParsedArgs(unParsedArgs, i, parsedArgs, 0);
                }
                case "-key" -> {
                    fillParsedArgs(unParsedArgs, i, parsedArgs, 1);
                }
                case "-data" -> {
                    fillParsedArgs(unParsedArgs, i, parsedArgs, 2);
                }
                case "-in" -> {
                    fillParsedArgs(unParsedArgs, i, parsedArgs, 3);
                }
                case "-out" -> {
                    fillParsedArgs(unParsedArgs, i, parsedArgs, 4);
                }
                case "-alg" -> {
                    fillParsedArgs(unParsedArgs, i, parsedArgs, 5);
                }
                default -> {
                }
            }
        }

        return parsedArgs;
    }

    private static boolean processArgs(String[] parsedArgs) {
        final String DEFAULT_MODE = "enc";
        final String DEFAULT_KEY = "0";
        final String DEFAULT_DATA = "";
        final String DEFAULT_IN = "";
        final String DEFAULT_OUT = "";
        final String DEFAULT_ALG = "shift";

        // parsing
        setDefaultArgs(parsedArgs, DEFAULT_MODE, DEFAULT_KEY, DEFAULT_DATA, DEFAULT_IN, DEFAULT_OUT, DEFAULT_ALG);
//        parsedArgs[0]     -mode
//        parsedArgs[1]     -key
//        parsedArgs[2]     -data
//        parsedArgs[3]     -in
//        parsedArgs[4]     -out
//        parsedArgs[5]     -alg


        // local args to do work
        int key = handleKey(parsedArgs[1]);
        String inputData = handleData(parsedArgs[2], parsedArgs[3]);
        String outputData = handleEncryption(parsedArgs[0], parsedArgs[5], inputData, key);
        boolean isDataOut = handleOutputMethod(parsedArgs[4], outputData);

        return isDataOut;
    }

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

    private static String handleEncryption(String mode, String algType, String data, int key) {
        Cipher cipher;
        switch (algType) {
            case "unicode" -> {
                cipher = new UnicodeCipher();
            }
            default -> {
                cipher = new ShiftCipher();
            }
        }

        return switch (mode) {
            case "enc" -> cipher.encrypt(data, key);
            case "dec" -> cipher.decrypt(data, key);
            default -> "";
        };
    }

    private static String handleData(String parsedData, String parsedInPath) {
        StringBuilder data = new StringBuilder();
        if (parsedInPath.equals("")) {
            return parsedData;
        } else if (parsedData.equals("")) {

            try (Scanner sc = new Scanner(Path.of(parsedInPath))) {
                while (sc.hasNextLine()) {
                    data.append(sc.nextLine());
                }
            } catch (IOException e) {
                System.out.println("Error in handling input, file does not exist: " + e.getMessage());
            }

        }
//        System.out.println(data.toString());
        return data.toString();
    }

    private static int handleKey(String parsedKey) {
        int key = 0;
        try {
            key = Integer.parseInt(parsedKey);
        } catch (Exception e) {
            System.out.println("Enter valid key.");
        }
        return key;
    }

    private static void setDefaultArgs(String[] parsedArgs, String ...defaultValue) {
        for (int index = 0; index < parsedArgs.length; index++) {
            parsedArgs[index] = parsedArgs[index].equals("") ? defaultValue[index] : parsedArgs[index];
        }
    }

    private static void fillParsedArgs(String[] unParsedArgs, int unParsedIndex,
                                       String[] parsedArgs, int parsedIndex) {

        // parsedArgs[parsedIndex] = unParsedArgs[unParsedIndex];

        if (unParsedIndex < unParsedArgs.length - 1) {
            parsedArgs[parsedIndex] = unParsedArgs[++unParsedIndex];
        }
    }
//    */
}


