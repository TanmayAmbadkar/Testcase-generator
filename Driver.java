import java.io.*;
import java.util.*;
import java.math.*;
import java.util.concurrent.TimeUnit;
public class Driver
{
    static String l[]={"logic", "./logic","java", "python"};
    //printing in file starts from here
    public static void makeTC(int tcfiles)
    {
        try
        {
            new File("input").mkdir();
            new File("output").mkdir();
            Random ob=new Random();
            for(int t=0;t<tcfiles;t++)
		    {
                 System.out.println("Generating Testcase: "+t);
			     int n=Math.abs(100000); //mod it with 10^n+1 for random 10^n testcases
			     String x="input\\input0"+t+".txt";;
			     Writer f=new FileWriter(new File(x));
			     f.write(n+" ");
			     f.write(System.getProperty("line.separator"));
			     for(int i=0;i<n;i++)
			     {
					int a=Math.abs(ob.nextInt()%100000);
					int b=Math.abs(ob.nextInt()%100000);
					int c=Math.abs(ob.nextInt()%100000);
					int d=Math.abs(ob.nextInt()%100000);
					int e=Math.abs(ob.nextInt()%100000);
					int g=Math.abs(ob.nextInt()%100000);
					f.write(a+" ");
					f.write(b+" ");
					f.write(c+" ");
					f.write(d+" ");
					f.write(e+" ");
					f.write(g+" ");
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
		try
		{
			switch(lang)
			{
				case 1:
					Process p=Runtime.getRuntime().exec("cmd /c start cmd.exe /K \""+"gcc logic.c -o logic && exit\"");
					p.waitFor();
					break;
				case 2:
					p=Runtime.getRuntime().exec("cmd /c start cmd.exe /K \""+"g++ logic.cpp -o logic && exit\"");
					p.waitFor();
					break;
				case 3:
					p=Runtime.getRuntime().exec("cmd /c start cmd.exe /K \""+"javac logic.java && exit\"");
					p.waitFor();
					break;
				case 4:
					break;
				default:
					System.out.println("Wrong choice");
			}
		}
		catch(Exception E)
		{
			System.out.println("Something went wrong, please check whether the compiler is integrted with your terminal");
		}
    }
    
    public static String exec(String s[],int os, int lang)
    {
	
        switch(lang)
        {
            case 1:
                switch(os)
                {
                    case 1:
                        return l[0]+" "+s[0]+" "+s[1];

                    case 2:
                        return l[1]+" "+s[0]+" "+s[1];

                }

            case 2:
                switch(os)
                {
                    case 1:
                        return l[0]+" "+s[0]+" "+s[1];

                    case 2:
                        return l[1]+" "+s[0]+" "+s[1];
                }

            case 3:
                return l[2]+" logic "+s[0]+" "+s[1];

            case 4:
                return l[3]+" logic.py "+s[0]+" "+s[1];
        }
		return "";
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
			System.out.println("TC Files generated");
            TimeUnit.SECONDS.sleep(1);
            System.out.println("Enter 1 for Windows\n2 for Linux");
            os=in.nextInt();
            System.out.println("Enter 1 for c\n2 for c++\n3 for Java\n4 for python");
            lang=in.nextInt();
			compile(lang);
            System.out.println("If the compile window has closed, press any character key, then enter");
			String temp=in.next();
			for(int i=0;i<tcfiles;i++)
            {
                String s=exec(new String[]{"<input/input0"+i+".txt",">output/output0"+i+".txt" },os,lang);
				s="cmd /c start cmd.exe /K \""+s+" && exit\"";
				Runtime.getRuntime().exec(s);
                System.out.println("Test Case: "+i+" executed successfully");
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
