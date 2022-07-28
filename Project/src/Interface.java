import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Interface extends JFrame implements  ActionListener {
    String username, password;
    JButton bsend,breceive;
    JLabel welcomemsg;
    public Interface(String user, String pass){
        username = user;
        password = pass;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Mail");
        this.setLayout(new FlowLayout());

        welcomemsg = new JLabel();
        welcomemsg.setText("WECLOME TO PRO-SENDER ");
        this.add(welcomemsg);


        bsend = new JButton("Send Mail");
        bsend.addActionListener(this);
        this.add(bsend);

        breceive = new JButton("Receive Mail");
        breceive.addActionListener(this);
        this.add(breceive);

        this.setResizable(false);
        this.setSize(260, 200);
        this.setVisible(true);
        this.setLocation(150, 100);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==bsend){
            compose.mail(username, password);
        }else if (e.getSource()==breceive){
            receive.mail(username, password);
        }
    }
    
}
