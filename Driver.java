import java.io.*;
import java.util.*;
import java.math.*;
import java.util.concurrent.TimeUnit;
public class Driver
{
    static String l[]={"logic ", "./logic","java logic", "python logic.py"};
    //printing in file starts from here
    public static void makeTC(int tcfiles)
    {
        try
        {
            new File("/input");
            new File("/output");
            Random ob=new Random();
            for(int t=0;t<tcfiles;t++)
		    {
                 System.out.println("Generating Testcase: "+t);
			     int n=Math.abs(ob.nextInt()%1000); //mod it with 10^n+1 for random 10^n testcases
			     String x="input\\input0"+t+".txt";
			     Writer f=new FileWriter(new File(x));
			     f.write(n);
			     f.write(System.getProperty("line.separator"));
			     for(int i=0;i<n;i++)
			     {
                    f.write(Math.abs(ob.nextInt()%1000)+" ");
                    f.write(System.getProperty("line.separator"));
			     }
			     f.close();
		    }
        }
        catch(Exception E)
        {
            E.printStackTrace();
            System.out.println("Report above exception on GitHub in the repository : https://github.com/TanmayAmbadkar/Testcase-generator");
        }            
    }
    
    public static void compile(int lang)throws IOException
    {
        switch(lang)
        {
            case 1:
                Runtime.getRuntime().exec("\"g++ logic.c -o logic\"");
                break;
            case 2:
                Runtime.getRuntime().exec("\"g++ logic.cpp -o logic\"");
                break;
            case 3:
                Runtime.getRuntime().exec("\"javac logic.java\"");
                break;
            case 4:
                break;
            default:
                System.out.println("Wrong choice");
        }
    }
    
    public static String exec(String s,int os, int lang)
    {
        switch(lang)
        {
            case 1:
                switch(os)
                {
                    case 1:
                        s=l[0]+s;
                        break;
                    case 2:
                        s=l[1]+s;
                        break;
                }
                break;
            case 2:
                switch(os)
                {
                    case 1:
                        s=l[0]+s;
                        break;
                    case 2:
                        s=l[1]+s;
                        break;
                }
                break;
            case 3:
                s=l[2]+s;
                break;
            case 4:
                s=l[3]+s;
        }
	s="\""+s+"\"";
        return s;
    }
    public static void main(String args[])throws IOException
    {
        Scanner in=new Scanner(System.in);
        System.out.println("Enter number of TCfiles");
        int tcfiles=in.nextInt(); 
        makeTC(tcfiles);
        int os=0,lang=0;
        try
        {
            TimeUnit.SECONDS.sleep(3);
            System.out.flush();  
            System.out.println("Enter 1 for Windows\n2 for Linux");
            os=in.nextInt();
            System.out.println("Enter 1 for c\n2 for c++\n3 for Java\n4 for python");
            lang=in.nextInt();
        }
        catch(InterruptedException ie)
        {
            Thread.currentThread().interrupt();
        }
        
        try
        {
		    compile(lang);
            for(int i=0;i<tcfiles;i++)
            {
                String s=exec("logic <input\\input0"+i+".txt >output\\output0"+i+".txt",os,lang);
    	        long x=System.nanoTime();
                Runtime.getRuntime().exec(s);
                long y=System.nanoTime();
                System.out.println("Time taken to execute test case "+i+" is:"+((y-x)/1000000000.0));
            }
		    System.out.println("All programs run successfully");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("Report as issue on github, whatever error has been thrown");
        }
    }
}
