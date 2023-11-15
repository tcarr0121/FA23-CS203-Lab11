import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;
// added comment to push
public class Encrypter {

    private int shift;
    private String encrypted;

    /**
     * Default Constructor
     */
    public Encrypter() {
        this.shift = 4;
        this.encrypted = "";
    }

    /**
     * Non-default Constructor
     *
     * @param s - custom shift amount
     */
    public Encrypter(int s) {
        this.shift = s;
        this.encrypted = "";
    }

    public static ArrayList<String> read(String filePath) throws Exception {
        String content = "";
        ArrayList<String> contentList = new ArrayList<>();
        try (Scanner fileScanner = new Scanner(Paths.get(filePath))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                contentList.add(line);
            }
            fileScanner.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }
        return contentList;
    }
    private char shiftedLetter (char letter) {
        int val = (int) letter;
        if (val <= 90 && val >= 65) {
            int shifted = val + shift;
            return shifted > 90 ? (char) (64 + shifted - 90) : (char) shifted;
        } else if (val <= 122 && val >= 97) {
            int shifted = val + shift;
            return shifted > 122 ? (char) (96 + shifted - 122) : (char) shifted;
        } else
            return letter;
    }
    private char decryptingLetters (char letter) {
        int val = (int) letter;
        if (val <= 90 && val >= 65) {
            int shifted = val - shift;
            return shifted > 90 ? (char) (64 + shifted - 90) : (char) shifted;
        } else if (val <= 122 && val >= 97) {
            int shifted = val - shift;
            return shifted > 122 ? (char) (96 + shifted - 122) : (char) shifted;
        } else
            return letter;
    }
    /**
     * Encrypts the content of a file and writes the result to another file.
     *
     * @param inputFilePath     the path to the file containing the text to be encrypted
     * @param encryptedFilePath the path to the file where the encrypted text will be written
     * @throws Exception if an error occurs while reading or writing the files
     */
    public void encrypt(String inputFilePath, String outputFilePath) throws Exception {
        //TODO: Call the read method, encrypt the file contents, and then write to new file
        ArrayList<String> encryptingContent = read(inputFilePath);
        StringBuilder encryptedText = new StringBuilder();

        for (String line : encryptingContent) {
            for (int n = 0; n < line.length(); n++) {
                char textChar = line.charAt(n);
                char encryptedChar = shiftedLetter(textChar);
                encryptedText.append(encryptedChar);
            }
            encryptedText.append("\n");
        }

        writeFile(encryptedText.toString(), outputFilePath);
    }


    /**
     * Decrypts the content of an encrypted file and writes the result to another file.
     *
     * @param messageFilePath   the path to the file containing the encrypted text
     * @param decryptedFilePath the path to the file where the decrypted text will be written
     * @throws Exception if an error occurs while reading or writing the files
     */
    public void decrypt(String encryptedFilePath, String decryptedFilePath) throws Exception {
        //TODO: Call the read method, decrypt the file contents, and then write to new file
        ArrayList<String> decryptingContent = read(encryptedFilePath);
        StringBuilder decryptedText = new StringBuilder();

        for (String line : decryptingContent) {
            for (int n = 0; n < line.length(); n++) {
                char textChar = line.charAt(n);
                char decryptedChar = decryptingLetters(textChar);
                decryptedText.append(decryptedChar);
            }
            decryptedText.append("\n");
        }

        writeFile(decryptedText.toString(), decryptedFilePath);
    }
        /**
         * Reads the content of a file and returns it as a string.
         *
         * @param filePath the path to the file to be read
         * @return the content of the file as a string
         * @throws Exception if an error occurs while reading the file
         */
        /**
         * Writes data to a file.
         *
         * @param data     the data to be written to the file
         * @param filePath the path to the file where the data will be written
         */
        private static void writeFile (String data, String filePath){
            //TODO: Write to filePath
            try (PrintWriter writer = new PrintWriter(filePath)) {
                writer.print(data);
                writer.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        /**
         * Returns a string representation of the encrypted text.
         *
         * @return the encrypted text
         */
        @Override
        public String toString () {
            return encrypted;
        }
    }