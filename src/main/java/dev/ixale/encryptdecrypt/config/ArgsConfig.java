package dev.ixale.encryptdecrypt.config;

public class ArgsConfig {

    public enum ArgsFlag {
        // Flags for the program
        MODE("-mode"),
        KEY("-key"),
        DATA("-data"),
        IN("-in"),
        OUT("-out"),
        ALG("-alg");
        private final String value;
        ArgsFlag(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }

        /**
         * Get the flag from the string value
         * @param value the string value
         * @return the flag
         */
        public static ArgsFlag fromString(String value) {
            for (ArgsFlag flag : ArgsFlag.values()) {
                if (flag.value.equals(value)) {
                    return flag;
                }
            }
            throw new IllegalArgumentException("Invalid flag: " + value);
        }
    }

    /**
     * The mode of the program. Can be either be ENCRYPT or DECRYPT
     */
    public enum Mode {
        // make two mode for encrypt and decrypt, encrypt values is "-enc" and decrypt values is "-dec"
        ENCRYPT("enc"),
        DECRYPT("dec");
        private final String value;
        Mode(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }

        /**
         * Get the mode from the string value
         * @param value the string value
         * @return the mode
         */
        public static Mode fromString(String value) {
            for (Mode mode : Mode.values()) {
                if (mode.value.equals(value)) {
                    return mode;
                }
            }
            throw new IllegalArgumentException("Invalid mode: " + value);
        }
    }

    /**
     * The algorithm of the program.
     * List of algorithms:
     * - SHIFT
     * - UNICODE
     */
    public enum Algorithm {
        // make two algorithm for encrypt and decrypt, shift values is "-shift" and unicode values is "-unicode"
        SHIFT("shift"),
        UNICODE("unicode");
        private final String value;
        Algorithm(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }

        /**
         * Get the algorithm from the string value
         * @param value the string value
         * @return the algorithm
         */
        public static Algorithm fromString(String value) {
            for (Algorithm alg : Algorithm.values()) {
                if (alg.value.equals(value)) {
                    return alg;
                }
            }
            throw new IllegalArgumentException("Invalid algorithm: " + value);
        }
    }

    public static final int ARGS_TOTAL_LENGTH = 6;

    public static final Mode DEFAULT_MODE = Mode.ENCRYPT;
    public static final String DEFAULT_KEY = "0";
    public static final String DEFAULT_DATA = "";
    public static final String DEFAULT_IN = "";
    public static final String DEFAULT_OUT = "";
    public static final Algorithm DEFAULT_ALG = Algorithm.SHIFT;
}
