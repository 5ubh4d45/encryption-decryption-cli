package Parser;

import dev.ixale.encryptdecrypt.config.ArgsConfig;

public class ArgsParserImpl implements ArgsParser{
    @Override
    public String[] parseArgsInOrder(String[] unParsedArgs) {
        final int ARGS_TOTAL_LENGTH = ArgsConfig.ARGS_TOTAL_LENGTH;

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
