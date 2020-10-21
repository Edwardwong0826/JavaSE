package com.NIO2;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class IOMain {

    // windows file system use \, unix-based file system use /
    // java support both format
    Double d = 20.0;


    public static void file()
    {
        // three file constructors
        // if compiler see // or \\ it will interprets as a single / or \
        File file = new File("C:/Java/Count.txt");
        //File file = new File("C:/Java","Count.txt");
        //File file = new File(new File("c:/Java/"), "/Count.txt");

        Path file2 = Paths.get("C:/Java/Count.txt");

        if(file.exists()) {
            System.out.println("Absolute Path: "+file.getAbsolutePath());
            System.out.println("Is Directory: "+file.isDirectory());
            System.out.println("Parent Path: "+file.getParent());
            if(file.isFile())
            {
                System.out.println("File size: "+file.length());
                System.out.println("File LastModified: "+file.lastModified());
            }
            else
            {
                for(File subfile: file.listFiles())
                {

                    System.out.println("\t"+subfile.getName());
                }
            }
        }

        System.out.println(file.exists());
    }

    public static void ByteAndCharacterStreams()
    {
        // FileInputStream used for binary or byte data
        // FileReader used for character and string data, this is still a type of streams just to distinguish
        // from binary and byte data stream

        // we can wrap the low level stream with high level stream
        // low level stream directly connects with the data source
        // buffered Stream wrap with low level stream it reads or writes data in group of bytes or characters
        // often improves performance in sequential file system
        // high level stream can also wrap another high level stream as well
        try(ObjectInputStream objectStream = new ObjectInputStream( new BufferedInputStream(
                new FileInputStream("C:/Java/Count.txt"))))
        {
            System.out.println(objectStream.readObject());


        }
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }



    }

    public static void BufferInputAndOutputStream() {
        // pathname in windows can use / or // or \\ , java will covert the \\ to the right one
        File source = new File("C:/Java/Count.txt");
        File destination = new File("C:\\Java\\TextCopy.txt");

        // flush() used in output stream to force the writing of the data to the underlying resource
        // close() shared by all stream and can implicitly with try-with-resource syntax
        // mark() used in input stream to mark a position - not all stream classes support this
        // reset() used in input stream return the mark() position data
        // skip() used in input stream to skip certain number of bytes
        // markSupported() used to check if stream support mark feature

        //InputStream, Writer are abstract class for all input character streams
        //InputStream able to throw exception if we try to operate on a closed stream
        try(InputStream in = new BufferedInputStream( new FileInputStream(source));
            OutputStream out = new BufferedOutputStream(new FileOutputStream(destination)))
        {
            byte[] buffer = new byte[1024]; //we can set buffer by have 2 of power size
            int read = 3; // this read is the length of read, it is 3 then means read 3 bytes of data
            // offset, means starting position of array
            while((read = in.read(buffer,0,read)) > 0 )
            {

                out.write(buffer,0,read);
                out.flush();
            }
        }
        catch (IOException  e)
        {
            System.out.println("Error : " + e.getMessage());
        }

        try(InputStream in = new BufferedInputStream( new FileInputStream(source));
            OutputStream out = new BufferedOutputStream(new FileOutputStream(destination)))
        {
           
            if(in.markSupported())
            {
                in.mark(100);
                System.out.println((char)in.read());
                System.out.println((char)in.read());
                in.reset();
            }
            System.out.println((char)in.read());
            System.out.println((char)in.read());
            System.out.println((char)in.read());
        }
        catch (IOException e)
        {

        }


    }

    public static void inputStreamAndOutputStream() {
        File source = new File("C:/Java/Count.txt");
        File destination = new File("C:/Java/TextCopy.txt");


        try(InputStream in = new FileInputStream(source);
            OutputStream out = new FileOutputStream(destination))
        {
            int read;
            while((read = in.read()) != -1)
            {
                out.write(read);
            }
        }
        catch (IOException  e)
        {
            System.out.println("Error : " + e.getMessage());
        }
    }

    public static List<String> readFile(File source) throws IOException {

        List<String> data = new ArrayList<String>();
        try (BufferedReader reader = new BufferedReader(new FileReader(source)))
        {
            String s;
            char[] buffer = new char[1024];

            // int s;
            // ((s = reader.readLine()) != -1
            while((s = reader.readLine()) != null)
            {
                data.add(s);

            }
        }
        return data;
    }

    public static void writeFile(List<String> data, File destination) throws IOException
    {
        char[] buffer = new char[1024];
        int read = 3; // this read is the length of read, it is 3 then means read 3 bytes of data

        // writer.write(buffer,0,read)
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(destination))) {
            for (String s : data) {
                writer.write(s);
                writer.newLine();
            }
        }
    }

    // use printStream and printWriter we can directly add data or string to the file like in Java console
    public static void printStreamAndPrintWriter() {

        // PrintStream and PrintWrite do not have OutputStream / PrintReader
        // System.out do not throw any checked exception and only use checkError() to report errors
        // System.err does report error but if being Close() , it will not able throw when operate in closed stream

        File source = new File("C:/Java/Prints.txt");

        try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(source))))
        {
            out.print("Today's weather is: ");
            out.println("Sunny");
            out.print("Today's temperature at the zoo is: ");
            out.print(1/3.0);
            out.println('C');
            out.format("It has rained 10.12 inches this year");
            out.println();
            out.printf("It may rain 21.2 more inches this year");
        }
        catch(IOException e)
        {
            System.out.println("Error : " + e.getMessage());
        }

    }

    public static void console(){

        // beware Console class is singleton and only create instance by factory method
        // it might null if not available, will not throw exception
        Console console = System.console();

        if(console != null)
        {
            String input = console.readLine();
            console.writer().println("You entered: " + input);
            console.format("Your entered %s",input);
            console.writer().println();
            console.format("Your entered %s",input);
        }
        else
        {
            System.err.println("Console not available");
        }

    }

    public static void main(String[] args) throws IOException {
        File source = new File("C:/Java/Count.txt");
        File destination = new File("C:/Java/TextCopy.txt");

        System.out.println(System.getProperty("file.separator"));
        file();
        //ByteAndCharacterStreams();
        //inputStreamAndOutputStream();
        BufferInputAndOutputStream();
        List<String> data =  readFile(source);
        writeFile(data,destination);
        printStreamAndPrintWriter();
        //console();


    }

    // 1. AD 2. BEF 3. CD 4. CD 5. BD 6. AE 7. D 8. ABD 9. ADF 10. B 11. CEG 12. CD 13. CE 14. 15. A
    // 16. D 17. ABDG 18. BD 19. BCD 20. ABCE 21. AB 22. E 23. E

}
