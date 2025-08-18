package itin_pc;

import com.formdev.flatlaf.FlatLightLaf;
import itin_pc.view.Login;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author JILMAR
 */
public class ITIN_PC {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            System.err.println("Error al inicializar FlatLaf: " + ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            new Login().setVisible(true);
        });
    }

}
