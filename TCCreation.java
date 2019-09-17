import java.io.*;
import java.util.*;
import java.math.*;
import java.util.concurrent.TimeUnit;
public class TCCreation
{
	public void makeTC(int tcfiles)
    {
        try
        {
            new File("input").mkdir();
            new File("output").mkdir();
            Random ob=new Random();
            for(int t=0;t<tcfiles;t++)
		    {
				System.out.println("Generating Testcase: "+t);
				int n=500000; //mod it with 10^n+1 for random 10^n testcases
				String x="input\\input0"+t+".txt";;
				Writer f=new FileWriter(new File(x));
				f.write(n+" ");
				f.write(System.getProperty("line.separator"));
				for(int i=0;i<n;i++)
				{
					long len=Math.abs(ob.nextLong()%1000000000);
					f.write(len+" ");
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
}