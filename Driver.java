import java.io.*;
import java.util.*;
import java.math.*;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
public class Driver
{
	public static void writeToZipFile(String path, ZipOutputStream zipStream) throws FileNotFoundException, IOException 
	{
        File aFile = new File(path);
        FileInputStream fis = new FileInputStream(aFile);
        ZipEntry zipEntry = new ZipEntry(path);
        zipStream.putNextEntry(zipEntry);

        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zipStream.write(bytes, 0, length);
        }

        zipStream.closeEntry();
        fis.close();
    }
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
			String inp[]=new String[tcfiles];
			String out[]=new String[tcfiles];
			FileOutputStream input=new FileOutputStream("input.zip");
			FileOutputStream output=new FileOutputStream("output.zip");
			ZipOutputStream is=new ZipOutputStream(input);
			ZipOutputStream ous=new ZipOutputStream(output);
			for(int i=0;i<tcfiles;i++)
            {
                String s=executer.exec(new String[]{"<input/input0"+i+".txt",">output/output0"+i+".txt" },os,lang);
				inp[i]="input/input0"+i+".txt";
				out[i]="output/output0"+i+".txt";
				s="cmd /c start /wait cmd.exe /K \""+s+" && exit\"";
				long st=System.nanoTime();
				Process p=Runtime.getRuntime().exec(s);
				p.waitFor();
				long et=System.nanoTime();
                System.out.println("Test Case: "+i+" executed successfully, time taken is : "+(et-st)/Math.pow(10,9));
				writeToZipFile(inp[i],is);
				writeToZipFile(out[i],ous);
            }
			is.close();
			ous.close();
			input.close();
			output.close();
		    System.out.println("All programs run successfully");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("Report as issue on github, whatever error has been thrown");
        }
    }
}
