import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Main {

    private static List<String> quotes = new ArrayList<>();
    private static Random random = new Random();

    public static void main(String[] args) {

        // This method reads quotes from a resources/ file
        loadQuotes("resources/quotes.txt");

        // Set up and display the GUI
        createAndShowGUI();
    }

    private static void loadQuotes(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                quotes.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading quotes file.");
            e.printStackTrace();
        }
    }

    private static String getRandomQuote() {
        if (quotes.isEmpty()) {
            return "No quotes available.";
        } else {
            int index = random.nextInt(quotes.size());
            return quotes.get(index);
        }
    }


    private static void createAndShowGUI() {
        // Creates window
        JFrame frame = new JFrame("Random Quote Generator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 200);

        // Create components
        JTextArea quoteArea = new JTextArea();
        quoteArea.setEditable(false);
        quoteArea.setLineWrap(true);
        quoteArea.setWrapStyleWord(true);

        // Set background and text colors
        quoteArea.setBackground(Color.BLACK);
        quoteArea.setForeground(Color.WHITE);

        JButton generateButton = new JButton("Generate New Quote");
        // Add button click listener
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String randomQuote = getRandomQuote();
                quoteArea.setText(randomQuote);
            }
        });

        // Set up the layout
        frame.setLayout(new BorderLayout());
        frame.add(new JScrollPane(quoteArea), BorderLayout.CENTER);
        frame.add(generateButton, BorderLayout.SOUTH);

        // Show the window
        frame.setVisible(true);
    }
}
