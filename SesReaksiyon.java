import javax.swing.*;
import javax.sound.sampled.*;
import java.awt.*;

public class SesReaksiyon extends JFrame {
    private JPanel panel;

    public SesReaksiyon() {
        // Pencere Ayarları
        setTitle("Ses Reaksiyon Sistemi");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setAlwaysOnTop(true); // Her zaman en üstte durması için

        panel = new JPanel();
        panel.setBackground(Color.GREEN); // Başlangıçta yeşil
        add(panel);
        setVisible(true);

        startMonitoring();
    }

    private void startMonitoring() {
        try {
            // Mikrofon Formatı
            AudioFormat format = new AudioFormat(44100, 16, 1, true, false);
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
            TargetDataLine line = (TargetDataLine) AudioSystem.getLine(info);
            line.open(format);
            line.start();

            byte[] buffer = new byte[1024];
            
            // Arka planda sürekli dinleme yapacak Thread
            new Thread(() -> {
                while (true) {
                    int bytesRead = line.read(buffer, 0, buffer.length);
                    if (bytesRead > 0) {
                        double level = calculateLevel(buffer);
                        // Hassasiyet eşiği: 50 (Bunu test ederek değiştirebilirsin)
                        if (level > 50) {
                            panel.setBackground(Color.RED);
                        } else {
                            panel.setBackground(Color.GREEN);
                        }
                    }
                }
            }).start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Basit bir ses seviyesi hesaplama (RMS mantığı)
    private double calculateLevel(byte[] buffer) {
        long sum = 0;
        for (byte b : buffer) {
            sum += b * b;
        }
        return Math.sqrt(sum / buffer.length);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SesReaksiyon::new);
    }
}