// a demo for swith swing display
package hi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
public class test extends JFrame {
    private JButton button;
    public static void main(String[] args) {
        new test();
    }
    public test() {
        this.setSize(800, 600);
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        button = new JButton("耗时很长的事e79fa5e98193e58685e5aeb931333332613662件");
        button.setBounds(300, 200, 200, 80);
        button.addActionListener(new ButtonAction());
        add(button);
        this.setVisible(true);
    }
    public class ButtonAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            new Thread() {
                {
                    this.setDaemon(true);
                }
                public void run() {
                    button.setEnabled(false);
                    for (int i = 9; i > 0; i--) {
                        button.setText("运行中(" + i + " / 10)");
                        sleep();
                    }
                    button.setText("耗时很长的事件");
                    button.setEnabled(true);
                }
                private void sleep() {
                    try {
                        sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }
}
