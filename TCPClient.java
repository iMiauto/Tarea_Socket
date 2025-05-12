
import java.awt.*;
import java.io.*;
import java.net.*;
import javax.swing.*;


public class TCPClient {
    public static String txt = "";

    public static void main(String[] argv) throws Exception {
        JFrame frame = new JFrame("Cliente");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 700);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.white);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel titulo = new JLabel("Obtenga su nota de cálculo aquí");
        titulo.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(titulo, gbc);

        JTextField[] campos = new JTextField[9];
        String[] etiquetas = {
            "Ingrese la nota del primer parcial:",
            "Ingrese la nota del segundo parcial:",
            "Ingrese la nota del tercer parcial:",
            "Ingrese la nota del primer quiz:",
            "Ingrese la nota del segundo quiz:",
            "Ingrese la nota del tercer quiz:",
            "Ingrese la nota del cuarto quiz:",
            "Ingrese la nota del quinto quiz:",
            "Ingrese la nota del sexto quiz:"
        };

        for (int i = 0; i < etiquetas.length; i++) {
            gbc.gridy++;
            panel.add(new JLabel(etiquetas[i]), gbc);
            gbc.gridy++;
            campos[i] = new JTextField(10);
            panel.add(campos[i], gbc);
        }

        gbc.gridy++;
        JTextArea mostrar = new JTextArea(5, 25);
        mostrar.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(mostrar);
        panel.add(scrollPane, gbc);

        gbc.gridy++;
        JButton btnCalcular = new JButton("Calcular");
        panel.add(btnCalcular, gbc);

        frame.add(panel);
        frame.setVisible(true);

        btnCalcular.addActionListener(e -> {
            try {
                double[] notas = new double[9];
                String[] valores = new String[9];
                for (int i = 0; i < campos.length; i++) {
                    valores[i] = campos[i].getText();
                }

                boolean validacion = true;
                if (validacioncampos(valores)) validacion = false;
                for (int i = 0; i < valores.length; i++) {
                    notas[i] = Double.parseDouble(valores[i]);
                }
                if (esNumerico(notas)) validacion = false;

                if (validacion) {
                    metodo_servidor(notas[0], notas[1], notas[2], notas[3], notas[4],
                                    notas[5], notas[6], notas[7], notas[8]);
                    mostrar.setText(txt);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        });
    }

    public static void metodo_servidor(double p1, double p2, double p3, double q1, double q2, double q3, double q4, double q5, double q6) throws Exception {
        txt = ""; 
        String[] recibido = new String[12];
        Socket clientSocket = new Socket("Miauto",  7544);

        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        outToServer.writeBytes(p1 + "\n");
        outToServer.writeBytes(p2 + "\n");
        outToServer.writeBytes(p3 + "\n");
        outToServer.writeBytes(q1 + "\n");
        outToServer.writeBytes(q2 + "\n");
        outToServer.writeBytes(q3 + "\n");
        outToServer.writeBytes(q4 + "\n");
        outToServer.writeBytes(q5 + "\n");
        outToServer.writeBytes(q6 + "\n");

        for (int i = 0; i < 12; i++) {
            recibido[i] = inFromServer.readLine();
            txt += recibido[i] + "\n";
        }
        clientSocket.close();
    }

    public static boolean esNumerico(double[] notas) {
        for (double nota : notas) {
            if (nota < 0 || nota > 100) {
                JOptionPane.showMessageDialog(null, "Error: La nota " + nota + " no es válida. Debe estar entre 0 y 100.");
                return true;
            }
        }
        return false;
    }

    public static boolean validacioncampos(String[] StringNotas) {
        for (String nota : StringNotas) {
            if (nota.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Error: Hay campos vacíos.");
                return true;
            }
            try {
                Double.parseDouble(nota);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error: La nota '" + nota + "' no es un número válido.");
                return true;
            }
        }
        return false;
    }
}
