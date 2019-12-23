import java.awt.*;
import java.awt.event.*;


class studyframe {
    


    public static void main(String[] args) 
    {
        Frame f = new Frame();
        
        f.setTitle("Frame");
        f.setSize(400, 100);
        f.setLocation(300, 200);
        
        f.setLayout(new FlowLayout());
       
        
        Button b=new Button("Button");
        f.add(b);
        f.addWindowListener(new WindowAdapter()
        {
               public void windowClosing(WindowEvent e)
               {
                   System.out.println("close the window");
                   System.exit(0);
                 }
        });    

        b.addActionListener(new ActionListener(){
             public void actionPerformed(ActionEvent e){
                  System.out.println("close by button");
                System.exit(0); 
               
             }
        });        
        f.setVisible(true);
    }        

}
