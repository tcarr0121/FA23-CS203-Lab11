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
        for (String line : encryptingContent){
            for (int n = 0; n < line.length(); n++) {
                char textChar = line.charAt(n);
                System.out.println(textChar);
                char encryptedChar = 'a';

                if (textChar >= 65 && textChar <= 90) {
                    encryptedChar = (char) (textChar + shift);
                    if(encryptedChar < 65){
                        encryptedChar = (char) (encryptedChar - 65 + 90);
                    }
                    else if (encryptedChar > 90){
                        encryptedChar = (char) (64 +encryptedChar - 90);
                    }
                }
                if (textChar >= 97 && textChar <= 122) {
                    encryptedChar = (char) (textChar + shift);
                    if (encryptedChar < 97){
                        encryptedChar = (char) (encryptedChar - 97 + 122);
                    }
                    else if (encryptedChar > 122){
                        encryptedChar = (char) (96 + encryptedChar - 122);
                    }
                }
            encrypted += encryptedChar;
            }
        }
        writeFile(encrypted, outputFilePath);
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
        String decrypted = "";
        for (String line : decryptingContent) {
            for (int n = 0; n < line.length(); n++) {
                char textChar = line.charAt(n);
                System.out.println(textChar);
                char decipheredChar='a';

                if (textChar >= 65 && textChar <= 90) {
                    decipheredChar = (char) (textChar - shift);
                    if(decipheredChar < 65){
                        decipheredChar = (char) (decipheredChar - 65 + 90);
                    }
                    else if (decipheredChar > 90){
                        decipheredChar= (char) (64 +decipheredChar - 90);
                    }
                }
                if (textChar >= 97 && textChar <= 122) {
                    decipheredChar = (char) (textChar - shift);
                    if (decipheredChar < 97){
                        decipheredChar = (char) (decipheredChar - 97 + 122);
                    }
                    else if (decipheredChar > 122){
                        decipheredChar = (char) (96 + decipheredChar - 122);
                    }
                }
                decrypted += decipheredChar;
            }
            decrypted += "\n";
        }
        writeFile(decrypted, decryptedFilePath);
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