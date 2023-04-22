package dev.ixale.encryptdecrypt;

import dev.ixale.encryptdecrypt.parser.ArgsParser;
import dev.ixale.encryptdecrypt.parser.ArgsParserImpl;
import dev.ixale.encryptdecrypt.processor.ArgsProcessor;
import dev.ixale.encryptdecrypt.processor.ArgsProcessorImpl;

public class EncryptDecryptApp {
    public static void main(String[] args) {

        ArgsParser argsParser = new ArgsParserImpl();
        ArgsProcessor argsProcessor = new ArgsProcessorImpl();

        String[] parsedArgs = argsParser.parseArgsInOrder(args);

        boolean result = argsProcessor.processArgs(parsedArgs);
    }

}


