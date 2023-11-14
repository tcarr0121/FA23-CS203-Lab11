//public void decrypt(String encryptedFilePath, String decryptedFilePath) throws Exception {
//        //TODO: Call the read method, decrypt the file contents, and then write to new file,
//        String encrypted = Encrypter.read(encryptedFilePath);
  //      String decryptedContent = "";
    //    for (int n = 0; n < encrypted.length(); n++) {
      //  char textChar = encrypted.charAt(n);
        //char decipheredChar = (char) (textChar - shift);
//
  //      if (textChar >= 'A' && textChar <= 'Z') {
    //    if (decipheredChar < 'A') {
      //  decipheredChar = (char) (decipheredChar + 26);
        //}
        //} else if (textChar >= 'a' && textChar <= 'z') {
      //  if (decipheredChar < 'a') {
      //  decipheredChar = (char) (decipheredChar + 26);
      //  }
      //  }
      //  decryptedContent += decipheredChar;
      //  }
       // Encrypter.writeFile(decryptedContent, decryptedFilePath);
       // }

public class EncryptionTester {
	
	public static void main(String[] args) throws Exception {
        String encryptedFilePath = "src/encrypted.txt";
        String decryptedFilePath = "src/decrypted.txt";
        String inputFilePath = "src/encryptMe.txt";
        String outputFilePath = "src/newEncrypted.txt";

        int shift = 4; // Says how much you would like to shift

        Encrypter enc = new Encrypter(shift);
		enc.encrypt(inputFilePath, outputFilePath);
		enc.decrypt(encryptedFilePath, decryptedFilePath);

	}


}
