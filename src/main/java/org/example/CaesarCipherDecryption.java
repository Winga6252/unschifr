import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class CaesarCipherDecryption {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        // Читаємо зашифрований текст з файлу
        String encryptedText = new String(Files.readAllBytes(Paths.get("encrypted_output.txt")));

        // Вводимо ключ для розшифрування з клавіатури
        System.out.print("Введіть ключ для розшифрування: ");
        int key = scanner.nextInt();

        // Розшифровуємо текст з використанням введеного ключа
        String decryptedText = decrypt(encryptedText, key);

        // Записуємо розшифрований текст у файл
        BufferedWriter writer = new BufferedWriter(new FileWriter("decrypted_output.txt"));
        writer.write(decryptedText);
        writer.close();

        System.out.println("Розшифрування завершено. Результат записано у файл decrypted_output.txt.");

        scanner.close();
    }

    // Метод для розшифрування тексту за допомогою шифру Цезаря
    public static String decrypt(String text, int key) {
        StringBuilder decryptedText = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);
            // Перевіряємо чи символ є літерою алфавіту
            if (Character.isLetter(currentChar)) {
                // Визначаємо базове значення (A для великих літер, a для малих)
                char base = Character.isUpperCase(currentChar) ? 'A' : 'a';
                // Розшифровуємо символ за допомогою формули Цезаря
                char decryptedChar = (char) (((currentChar - base - key + 26) % 26) + base);
                decryptedText.append(decryptedChar);
            } else {
                // Якщо символ не є літерою, додаємо його без змін
                decryptedText.append(currentChar);
            }
        }
        return decryptedText.toString();
    }
}
