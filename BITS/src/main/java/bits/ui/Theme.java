package bits.ui;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import javax.imageio.ImageIO;

/** Shared dark electronics-workbench theme for BITS. */
public final class Theme {
    public static final Color BG = new Color(8, 13, 25);
    public static final Color SURFACE = new Color(14, 22, 39);
    public static final Color SURFACE_2 = new Color(22, 32, 52);
    public static final Color TEXT = new Color(235, 242, 255);
    public static final Color MUTED = new Color(157, 174, 202);
    public static final Color ACCENT = new Color(100, 133, 255);
    public static final Color TEAL = new Color(76, 220, 190);
    private static Image background;

    static {
        try (InputStream in = Theme.class.getResourceAsStream("/bits/ui/workshop-background.png")) {
            if (in != null) background = ImageIO.read(in);
        } catch (Exception ignored) { }
        UIManager.put("OptionPane.background", SURFACE);
        UIManager.put("Panel.background", SURFACE);
        UIManager.put("OptionPane.messageForeground", TEXT);
        UIManager.put("Button.background", SURFACE_2);
        UIManager.put("Button.foreground", TEXT);
        UIManager.put("TextField.background", new Color(10, 17, 30));
        UIManager.put("TextField.foreground", TEXT);
        UIManager.put("PasswordField.background", new Color(10, 17, 30));
        UIManager.put("PasswordField.foreground", TEXT);
    }
    private Theme() { }

    public static JPanel backgroundPanel() {
        return new JPanel(new GridBagLayout()) {
            @Override protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (background != null) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    double scale = Math.max(getWidth() / (double) background.getWidth(null), getHeight() / (double) background.getHeight(null));
                    int w = (int)(background.getWidth(null) * scale), h = (int)(background.getHeight(null) * scale);
                    g2.drawImage(background, (getWidth()-w)/2, (getHeight()-h)/2, w, h, null);
                    g2.setColor(new Color(4, 8, 18, 178));
                    g2.fillRect(0, 0, getWidth(), getHeight());
                    g2.dispose();
                } else { g.setColor(BG); g.fillRect(0, 0, getWidth(), getHeight()); }
            }
        };
    }

    public static JPanel card() {
        JPanel p = new JPanel();
        p.setBackground(new Color(12, 20, 36, 245));
        p.setBorder(new CompoundBorder(new LineBorder(new Color(73, 96, 143), 1, true), BorderFactory.createEmptyBorder(22, 24, 22, 24)));
        return p;
    }

    public static void styleWindow(Window w) {
        if (w instanceof JFrame f) f.getContentPane().setBackground(BG);
        if (w instanceof JDialog d) d.getContentPane().setBackground(SURFACE);
        styleTree(w instanceof JFrame f ? f.getContentPane() : ((JDialog)w).getContentPane());
    }

    private static void styleTree(Component c) {
        if (c instanceof JTable t) {
            t.setBackground(SURFACE); t.setForeground(TEXT); t.setGridColor(new Color(39, 53, 78));
            t.setSelectionBackground(new Color(47, 69, 112)); t.setSelectionForeground(Color.WHITE);
            t.setRowHeight(30); t.setFillsViewportHeight(true);
            JTableHeader h = t.getTableHeader(); h.setBackground(new Color(26, 39, 63)); h.setForeground(TEXT);
            h.setFont(h.getFont().deriveFont(Font.BOLD, 12f));
        } else if (c instanceof JScrollPane sp) {
            sp.getViewport().setBackground(SURFACE); sp.setBorder(new LineBorder(new Color(42, 58, 86)));
        } else if (c instanceof JTextField || c instanceof JPasswordField || c instanceof JTextArea || c instanceof JComboBox<?>) {
            c.setBackground(new Color(9, 16, 29)); c.setForeground(TEXT);
            if (c instanceof JComponent jc) jc.setBorder(new CompoundBorder(new LineBorder(new Color(56, 75, 110)), BorderFactory.createEmptyBorder(6, 8, 6, 8)));
            if (c instanceof JTextArea ta) ta.setCaretColor(TEAL);
        } else if (c instanceof JButton b) {
            b.setBackground(SURFACE_2); b.setForeground(TEXT); b.setFocusPainted(false);
            b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            b.setBorder(new CompoundBorder(new LineBorder(new Color(62, 83, 122), 1, true), BorderFactory.createEmptyBorder(8, 12, 8, 12)));
            b.setFont(b.getFont().deriveFont(Font.BOLD, 12f));
        } else if (c instanceof JLabel l) {
            l.setForeground(TEXT);
        } else if (c instanceof JPanel p) {
            if (p.getBackground().equals(UIManager.getColor("Panel.background")) || p.getBackground().equals(Color.WHITE)) p.setBackground(SURFACE);
        }
        if (c instanceof Container container) for (Component child : container.getComponents()) styleTree(child);
    }
}
