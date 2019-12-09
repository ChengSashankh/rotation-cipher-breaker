# Rotation Cipher Breaker

This is a command line tool written in Java to break a rotation cipher automatically based on frequency analysis of the 
characters. This is intended to be used to decrypt English language texts.

## Method

English language text typically follows a natural frequency distribution of characters, which can be used to 
attack a rotation cipher. For example, the character `e` occurs most often in English text - almost 3 times as often as `d`. Rotation ciphers do not obscure the relative frequencies of the characters, and can hence be 
broken by matching similar frequencies between ciphertext and the expected frequencies. 

The natural character distribution 
of English language characters is shown [here](http://pi.math.cornell.edu/~mec/2003-2004/cryptography/subs/frequencies.html). 

## Usage

To use this utility you must have Java on your machine. Download `frequency-attack.jar` from the most recent release and run it with:

    `java -jar frequency-attack.jar` 
    
Ensure that you are in the directory containing the jar file.

To perform the attack on a file containing the ciphertext, use the commands as shown below. Ensure that the 
text file is placed in the same directory as the jar file.

| Command                | Description                                                            |
|---------------------------|------------------------------------------------------------------------|
| `analyse cipherinput.txt` | Displays the relative character frequencies in the ciphertext.         |
| `break cipherinput.txt`   | Displays the decrypted plaintext, along with the most likely rotation. |
| `exit`   | Exits the command line interface | 

## Developer Setup

To develop further from this repository, you can clone this repository. This root of this repository serves as project root as well. In addition, note that:

 - You must have a recent version of Java Development Kit (JDK) installed.
 - The project can be set up with IntelliJ IDEA, which was used in the development of this project.