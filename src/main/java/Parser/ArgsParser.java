package Parser;

public interface ArgsParser {
    /**
     * Parse the arguments in order
     * @param unParsedArgs the arguments to parse
     * @return the parsed arguments in order
     */
    String[] parseArgsInOrder(String[] unParsedArgs);

}
