import javax.swing.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class User2_Sumona extends JFrame {
    private JPanel mainPanel;
    private JButton button1;
    private JButton button2;
    private JTextPane textPane1;
    private JLabel label1;
    private JTextArea textArea1;

    private static Socket socket;

    public User2_Sumona(String title) throws IOException {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();

        socket = new Socket(InetAddress.getByName("localhost"), 25000);
        // socket = new Socket(InetAddress.getByName("127.0.0.1"), 25000);

        button2.addActionListener(actionEvent -> {
            try
            {
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                textPane1.setText(textPane1.getText()+"\nSabrina : "+ dis.readUTF()+"\n");
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    while(textPane1.getText().equals("Bye"))  {
                        socket.close();
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });

        button1.addActionListener(actionEvent -> {
            try
            {
                textPane1.setText(textPane1.getText()+"\nMe : "+ textArea1.getText()+"\n");
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                dos.writeUTF(textArea1.getText());
                dos.flush();
                textArea1.setText("");
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    while(textPane1.getText().equals("Bye"))  {
                        socket.close();
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) throws IOException {
        JFrame frame = new User2_Sumona("User2_Sumona");
        frame.setVisible(true);
        frame.setSize(600,700);
    }
}
