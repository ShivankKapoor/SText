import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class SText extends JFrame {
    private JButton saveButton;
    private JButton openButton;
    private JTextArea textArea1;
    private JPanel SText;
    private JButton lightDarkButton;
    private JButton exitButton;
    private boolean darkModeSelected = false;

    public SText() {
        setTitle("SText");
        setSize(1300, 900);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(SText);
        ImageIcon img = new ImageIcon("src/main/resources/images/SText.png");
        setIconImage(img.getImage());
        setVisible(true);

        lightDarkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                darkModeSelected = !darkModeSelected;
                if (darkModeSelected) {
                    setLookAndFeel(new FlatDarkLaf());
                } else {
                    setLookAndFeel(new FlatLightLaf());
                }
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter txtFilter = new FileNameExtensionFilter("Text Files (*.txt)", "txt");
                FileNameExtensionFilter csvFilter = new FileNameExtensionFilter("CSV Files (*.csv)", "csv");
                fileChooser.addChoosableFileFilter(txtFilter);
                fileChooser.addChoosableFileFilter(csvFilter);
                fileChooser.setFileFilter(txtFilter);

                int option = fileChooser.showSaveDialog(SText.this);
                if (option == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    String filePath = file.getAbsolutePath();
                    String selectedExtension = "";

                    if (fileChooser.getFileFilter() == txtFilter) {
                        selectedExtension = ".txt";
                    } else if (fileChooser.getFileFilter() == csvFilter) {
                        selectedExtension = ".csv";
                    }

                    if (!filePath.endsWith(selectedExtension)) {
                        file = new File(filePath + selectedExtension);
                    }

                    try (FileWriter writer = new FileWriter(file)) {
                        writer.write(textArea1.getText());
                    } catch (IOException ioException) {
                        //ioException.printStackTrace();
                    }
                }
            }
        });

        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter txtFilter = new FileNameExtensionFilter("Text Files (*.txt)", "txt");
                FileNameExtensionFilter csvFilter = new FileNameExtensionFilter("CSV Files (*.csv)", "csv");
                fileChooser.addChoosableFileFilter(txtFilter);
                fileChooser.addChoosableFileFilter(csvFilter);
                fileChooser.setFileFilter(txtFilter);

                int option = fileChooser.showOpenDialog(SText.this);
                if (option == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                        textArea1.setText("");
                        String line;
                        while ((line = reader.readLine()) != null) {
                            textArea1.append(line + "\n");
                        }
                    } catch (IOException ioException) {
                        //ioException.printStackTrace();
                    }
                }
            }
        });
    }

    private void setLookAndFeel(LookAndFeel laf) {
        try {
            UIManager.setLookAndFeel(laf);
            SwingUtilities.updateComponentTreeUI(this);
        } catch (UnsupportedLookAndFeelException e) {
            //e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException e) {
            //e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> new SText());
    }
}
