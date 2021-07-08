import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class User1_Sabrina extends JFrame {
    private JPanel mainPanel;
    private JButton button1;
    private JButton button2;
    private JTextPane textPane1;
    private JLabel label1;
    private JTextArea textArea1;

    private static Socket socket;

    public User1_Sabrina(String title) throws IOException {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();

        ServerSocket serverSocket = new ServerSocket(25000);
        socket = serverSocket.accept();

        mainPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
            }
        });

        button2.addActionListener(actionEvent -> {
            try
            {
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                textPane1.setText(textPane1.getText()+"\nSumona : "+ dis.readUTF()+"\n");
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
        JFrame frame = new User1_Sabrina("User1_Sabrina");
        frame.setVisible(true);
        frame.setSize(600,700);
    }
}
