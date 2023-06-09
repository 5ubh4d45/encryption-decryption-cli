package dev.ixale.encryptdecrypt.processor;

public interface ArgsProcessor {
    /**
     * Process the arguments
     * @param parsedArgs the arguments to process
     * @return true if the data is outputted, false otherwise
     */
    boolean processArgs(String[] parsedArgs);
}
