import java.io.*;
import java.util.*;
import java.math.*;
import java.util.concurrent.TimeUnit;
public class Driver
{
    public static void main(String args[])throws IOException
    {
        Scanner in=new Scanner(System.in);
		TCCreation tcgen = new TCCreation();
		Compile compiler = new Compile();
		ExecuteTC executer=new ExecuteTC();
        System.out.println("Enter number of TCfiles");
        int tcfiles=in.nextInt(); 
        tcgen.makeTC(tcfiles);
        int os=0,lang=0;
        try
        {
			System.out.println("TC Files generated");
            TimeUnit.SECONDS.sleep(1);
            System.out.println("Enter 1 for Windows\n2 for Linux");
            os=in.nextInt();
            System.out.println("Enter 1 for c\n2 for c++\n3 for Java\n4 for python");
            lang=in.nextInt();
			compiler.compile(lang,os);
            System.out.println("Code Compiled successfully");
			for(int i=0;i<tcfiles;i++)
            {
                String s=executer.exec(new String[]{"<input/input0"+i+".txt",">output/output0"+i+".txt" },os,lang);
				s="cmd /c start /wait cmd.exe /K \""+s+" && exit\"";
				long st=System.nanoTime();
				Process p=Runtime.getRuntime().exec(s);
				p.waitFor();
				long et=System.nanoTime();
                System.out.println("Test Case: "+i+" executed successfully, time taken is : "+(et-st)/Math.pow(10,9));
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
