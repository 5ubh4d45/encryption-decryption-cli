# Simple Text Encryption CLI Tool

---

## Description
_This tool is a simple CLI tool that encrypts and decrypts text using a simple substitution cipher.
The cipher is a simple substitution cipher that uses a 26 character alphabet.
The alphabet is shuffled and then used to encrypt the text._

I wrote this just to learn java and make basic CLI tools to handle cli arguments and file input/output.

### _**THE CIPHER IS NOT SECURE AND SHOULD NOT BE USED FOR SENSITIVE INFORMATION.**_

---

## Usage
To execute, use the following way:
```bash
java -jar EncyptDecryptApp.jar <Arguments>
```

### Flags and their arguments:

| Flag Name           | Flag      | Argument Values                                                                                  |
|---------------------|-----------|--------------------------------------------------------------------------------------------------|
| Mode                | **-mode** | enc (Encrypt), dec (Decrypt)<br/>**Default: enc**                                                |       |                                                  |
| Key                 | **-key**  | 0,1,2,3....Any positive integer<br/>**Default: 0**                                               |
| Data                | **-data** | "Any string data directly as argument"                                                           |
| Input Path          | **-in**   | "path/to/the/input/file"<br/> **if both -data and -in present, -in takes priority**              |
| Output Path         | **-out**  | "path/to/the/output/file"<br/>**Optional flag, if not present app print result on the std out.** |
| Preferred Algorithm | **-alg**  | shift, unicode<br/>**Default: shift**                                                            |

### Examples:
```bash
java -jar EncyptDecryptApp.jar -mode enc -key 5 -data "Hello World" -alg unicode
```
```bash
java -jar EncyptDecryptApp.jar -mode dec -key 10 -in "path/to/input" -out "path/to/output" -alg shift
```
```bash
java -jar EncyptDecryptApp.jar -in "path/to/input" -alg unicode -key 10 -mode dec
```

and much more...

---



