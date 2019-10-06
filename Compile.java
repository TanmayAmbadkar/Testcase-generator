import java.io.*;
import java.util.*;
import java.math.*;
import java.util.concurrent.TimeUnit;
public class Compile
{
	public void compile(int lang)throws IOException
    {
		try
		{
			switch(lang)
			{
				case 1:
					Process p=Runtime.getRuntime().exec("cmd /c start /wait cmd.exe /K \""+"gcc logic.c -o logic && exit\"");
					p.waitFor();
					break;
				case 2:
					Process p=Runtime.getRuntime().exec("cmd /c start /wait cmd.exe /K \""+"g++ logic.cpp -o logic && exit\"");
					p.waitFor();
					break;
				case 3:
					Process p=Runtime.getRuntime().exec("cmd /c start /wait cmd.exe /K \""+"javac logic.java && exit\"");
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
}
