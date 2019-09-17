import java.io.*;
import java.util.*;
import java.math.*;
import java.util.concurrent.TimeUnit;
public class ExecuteTC
{
	String l[]={"logic", "./logic","java", "python"};
    public String exec(String s[],int os, int lang)
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
}