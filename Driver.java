import java.io.*;
import java.util.*;
import java.math.*;
public class Driver
{
	public static void main(String args[])throws IOException
	{
        	try
        	{
            		new File("\\input").mkdir();
            		new File("\\output").mkdir();
            		Random ob=new Random();
			int tcfiles=10;  //set your number of testcase files;
	    		for(int t=0;t<tcfiles;t++)
	    		{	
				System.out.println("Generating Testcase: "+t);
				int n=Math.abs(ob.nextInt()%1000); //mod it with 10^n+1 for random 10^n testcases
				String x="input\\input0"+t+".txt";
				Writer f=new FileWriter(new File(x));
				f.write(n+" ");
				f.write(System.getProperty("line.separator"));
				for(int i=0;i<n;i++)
				{
					f.write(Math.abs(ob.nextInt()%1000)+" ");
					f.write(System.getProperty("line.separator"));
				}
				f.close();
	    		}
	    		Runtime.getRuntime().exec("gcc logic.c -o logic");
            		for(int i=0;i<tcfiles;i++)
            		{
                		String s="logic <input\\input0"+i+".txt >output\\output0"+i+".txt";
    	        		long x=System.nanoTime();
	       			Process q=Runtime.getRuntime().exec(s);
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
