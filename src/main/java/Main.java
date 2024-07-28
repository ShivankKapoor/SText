import javax.swing.*;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    private JButton saveButton;
    private JButton openButton;
    private JTextArea textArea1;
    private JPanel SText;
    private JButton lightDarkButton;
    private boolean darkModeSelected = false;

    public Main() {
        setTitle("SText");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(SText);
        setVisible(true);
        lightDarkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                darkModeSelected=!darkModeSelected;
                if(darkModeSelected){
                    setLookAndFeel(new FlatDarkLaf());
                }else{
                    setLookAndFeel(new FlatLightLaf());
                }
            }
        });
    }

    private void setLookAndFeel(LookAndFeel laf) {
        try {
            UIManager.setLookAndFeel(laf);
            SwingUtilities.updateComponentTreeUI(this);
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }


        public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> new Main());
    }
}
