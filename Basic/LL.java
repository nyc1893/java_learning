
import java.util.*;

public class LinkList
{
    private Node first;
    public LinkList()
    {
        first = null;
    }
    
     public class Node
    {
        public int data;
        public Node next;
        public Node(int data)
        {
            this.data = data;
        }
    }
    
    public void addNode(int value)
    {
        Node newNode =new Node(value);
        if(first == null)
        {
            first = newNode;
        }
        else
        {
            newNode.next = first;
            first =newNode;
        }
    }
    
    public void print()
    {
        if(first == null)
        {
            System.out.println("LL is not exist!");
            return;
        }
        
        Node current = first;
        while(current !=null)
        {
            System.out.println(current.data);
            current = current.next;
        }
         System.out.println("---------------");
    }
    
    
    
    public int len()
    {
        int result = 0;
        if(first == null)
        {
            System.out.println("LL is not exist!");
            return result;
        }
        
        Node current = first;
        while(current !=null)
        {
            // System.out.println(current.data);
            current = current.next;
            ++result;
        }
        return result;
         // System.out.println("---------------");
    }
    
    public  int get_val(int index)
    {

        if(first == null)
        {
            System.out.println("LL is not exist!");
            return -1;
        }
        
        Node current = first;
        for(int i = 0; i< index;i++)
        {

            current = current.next;
            
        }
        return current.data;
         // System.out.println("---------------");
    }    
    public static void main(String[] args) 
    {
        //initialize it
        LinkList nn = new LinkList(); 
        for(int i = 1;i<=6;i++)
        {
            nn.addNode(i+5);
        }
        //display it
        nn.print();
        
        System.out.println(nn.get_val(nn.len()/2));
        
        
    }
}
