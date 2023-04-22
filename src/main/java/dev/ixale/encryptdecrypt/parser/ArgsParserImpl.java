package dev.ixale.encryptdecrypt.parser;

import dev.ixale.encryptdecrypt.config.ArgsConfig;

import java.util.Arrays;

public class ArgsParserImpl implements ArgsParser{
    @Override
    public String[] parseArgsInOrder(String[] unParsedArgs) {
        final int ARGS_TOTAL_LENGTH = ArgsConfig.ARGS_TOTAL_LENGTH;

        int unParsedArgsLength = unParsedArgs.length;
        String[] parsedArgs = new String[ARGS_TOTAL_LENGTH];

        Arrays.fill(parsedArgs, "");

        for (int i = 0; i < unParsedArgsLength; i++) {
            String s = unParsedArgs[i];

            // refactor to use enum so that we can use switch expression instead of switch statement
            // try converting to flags enum
            try {
                ArgsConfig.ArgsFlag flag = ArgsConfig.ArgsFlag.fromString(s);
                switch (flag) {
                    case MODE -> fillParsedArgs(unParsedArgs, i, parsedArgs, 0);
                    case KEY -> fillParsedArgs(unParsedArgs, i, parsedArgs, 1);
                    case DATA -> fillParsedArgs(unParsedArgs, i, parsedArgs, 2);
                    case IN -> fillParsedArgs(unParsedArgs, i, parsedArgs, 3);
                    case OUT -> fillParsedArgs(unParsedArgs, i, parsedArgs, 4);
                    case ALG -> fillParsedArgs(unParsedArgs, i, parsedArgs, 5);
                }
            } catch (IllegalArgumentException e) {
                // do nothing
            }
        }

        return parsedArgs;
    }

    /**
     * Fill the parsedArgs array with appropriate values retrieved from unParsedArgs
     * @param unParsedArgs the unParsedArgs array
     * @param unParsedIndex the index of the unParsedArgs array
     * @param parsedArgs the parsedArgs array
     * @param parsedIndex the index of the parsedArgs array
     */
    private static void fillParsedArgs(String[] unParsedArgs, int unParsedIndex,
                                       String[] parsedArgs, int parsedIndex) {

        // parsedArgs[parsedIndex] = unParsedArgs[unParsedIndex];

        if (unParsedIndex < unParsedArgs.length - 1) {
            parsedArgs[parsedIndex] = unParsedArgs[++unParsedIndex];
        }
    }
}
