import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class A {

    static int NumberNode= 0;
    static int MAXN= 10000005;
    //private static Object point;
    static Point point[]= new Point[MAXN];
    static int trace[]= new int[MAXN];

    static void convert(String s)
    {
        int index= 0, node, next;
        String string= "";
        while(s.charAt(index)!= ' ')
             string+= s.charAt(index++);
        node= Integer.parseInt(string);
        NumberNode= Math.max(NumberNode, node);
        string= "";
        ++index;
        //point[node]= new Point(node);

        while(index<= s.length()){
            if(index== s.length()|| s.charAt(index)== ' '){
                next= Integer.parseInt(string);
                NumberNode= Math.max(NumberNode, next);
                point[next].list.add(node);
                ++index;
                string= "";
                continue;
            }
            string+= s.charAt(index++);
        }
    }

    static void Print(int index)
    {
        while(true){
            System.out.print(index+ " ");
            index= trace[index];
            if(index== NumberNode) break;
        }
        System.out.println(NumberNode);
    }

    static void DFS(int index)
    {
        if(index== 1){
            Print(1);
            return;
        }

        for(Integer node: point[index].list)
        {
            if(trace[node]!= 0) continue;
            trace[node]= index;
            DFS(node);
            trace[node]= 0;
        }
    }

    public static void main(String[] args) {

        for(int i= 1; i< MAXN; i++)
            point[i]= new Point();

        try {
            File myObj = new File("input.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                convert(data);
                //System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        DFS(NumberNode);
    }
}
