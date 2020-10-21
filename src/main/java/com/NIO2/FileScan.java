package com.NIO2;

import java.io.*;
import java.util.Scanner;

public class FileScan {

    public int countTokens(String file, String search) throws IOException {
        int count = 0;
        try(FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            Scanner sc = new Scanner(br) )
        {
            sc.useDelimiter(",");
            while(sc.hasNext())
            {
                if(search.equalsIgnoreCase(sc.next().trim()))
                    count++;
            }
        }

        return count;
    }

    public static void main(String[] args)
    {
        String file = args[0];
        FileScan scan = new FileScan();

        // program input stream can read data from memory called buffer, only when buffer is empty the native
        // input API will call , as opposite native output API called when buffer full

        // Buffered reader create buffered character streams
        try(BufferedReader in = new BufferedReader(new InputStreamReader(System.in)))
        {
            String search = "";
            while(true)
            {
                System.out.println("Searching the file: " + file);
                search = in.readLine().trim();
                if(search.equalsIgnoreCase("q"))
                {
                    break;
                }
                int count = scan.countTokens(file,search);
                System.out.println(search + " appears " + count + " times in file");
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
        
    }


}
