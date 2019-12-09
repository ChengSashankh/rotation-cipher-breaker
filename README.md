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

## Usage Example

The example below shows the decryption of a rotation cipher using the `break` command.  

```$xslt
Welcome to FrequencyAttack tool.
This tool will assist you in executing a frequency analysis attack on an English language input text file.
At any point, stop the program with Ctrl + C to exit
Commands:
	1. analyse PATH_TO_FILE
	2. break PATH_TO_FILE
> break cipherinput.txt
Rotation cipher break results: 
Best rotation: 5
Deciphered plaintext:
parliament passed the stamp act in 1765 to pay for british military troops stationed in the american colonies after the french and indian war. parliament had previously passed legislation to regulate trade, but the stamp act introduced a new principle of a direct internal tax. americans began to question the extent of the british parliament's power in america, and the colonial legislatures argued that they had exclusive right to impose taxes within their jurisdictions.[47] colonists condemned the tax because their rights as englishmen protected them from being taxed by a parliament in which they had no elected representatives.[48] parliament argued that the colonies were "represented virtually", an idea that was criticized throughout the empire.[49] parliament did repeal the act in 1766, but it also affirmed its right to pass laws that were binding on the colonies.[50] from 1767, parliament began passing legislation to raise revenue for the salaries of civil officials, ensuring their loyalty while inadvertently increasing resentment among the colonists, and opposition soon became widespread.
> exit

Process finished with exit code 0

```

## Developer Setup!

To develop further from this repository, you can clone this repository. This root of this repository serves as project root as well. In addition, note that:

 - You must have a recent version of Java Development Kit (JDK) installed.
 - The project can be set up with IntelliJ IDEA, which was used in the development of this project.