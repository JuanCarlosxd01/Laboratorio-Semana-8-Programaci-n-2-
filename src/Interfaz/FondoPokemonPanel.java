package interfaz;

import java.awt.*;
import javax.swing.*;

public class FondoPokemonPanel extends JPanel {

    public FondoPokemonPanel() {
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();

        GradientPaint degradado = new GradientPaint(
                0, 0, new Color(76, 175, 124),
                0, getHeight(), new Color(25, 80, 95)
        );

        g2.setPaint(degradado);
        g2.fillRect(0, 0, getWidth(), getHeight());

        g2.setColor(new Color(255, 255, 255, 20));

        for (int x = -100; x < getWidth(); x += 120) {
            for (int y = -100; y < getHeight(); y += 120) {
                g2.fillOval(x, y, 160, 160);
            }
        }

        g2.setColor(new Color(255, 255, 255, 25));

        for (int x = 0; x < getWidth(); x += 80) {
            g2.drawLine(x, 0, x - 200, getHeight());
        }

        g2.setColor(new Color(0, 0, 0, 35));
        g2.fillOval(-150, getHeight() - 170, getWidth() + 300, 300);

        g2.dispose();
    }
}
