package com.NIO2;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileNio2Directory {

    public static void NioRead()
    {
        // if a path starts / forward slash, is absolute path such as /Java/Count.txt
        // if a path starts with a drive letter, is a absolute path such as C:/Java/Count.txt
        // otherwise is relative path, like ../CopyText.txt

        // Files.lines() likes Files.readAllLines() but does not suffer from the out of memory issue
        //  return Stream<String> that is lazy , only a small portion of the file is memory at given time
        try(Stream<String> lines = Files.lines(Paths.get("C:/Java/Count.txt")))
        {

            System.out.println("==== NIO file===");
            lines.forEach(line-> System.out.println(line));
        }
        catch (IOException e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void createAndCopyDirectory()
    {
        try
        {
            Files.createDirectory(Paths.get("C:/Java/TestDir"));
            System.out.println("Directory TestDir in C:/Java created");

            Files.copy(Paths.get("C:/Java/Count.txt"),
                    Paths.get("C:/Java/TestDir/Count.txt"));
            System.out.println("Files copy to TestDir directory");

            Files.delete(Paths.get("C:/Java/Count.txt"));
            Files.deleteIfExists(Paths.get("C:/Java/TestDir"));


        }
        catch (IOException e)
        {
            e.getMessage();
        }
    }

    public static void FilesWithStreamAPI()
    {
        Path path = Paths.get("C:/Java");
        long dateFilter = 1420070400000l;
        System.out.println();
        try
        {
            // walk stream use depth first, and it take up to Integer.MAX_VALUE to look directories
            // walk() will not traverse symbolic links as symbolic links might lead to a cycle
            Files.walk(path).filter(s -> s.toString().endsWith(".MF")).forEach(s-> System.out.println(s));
            System.out.println();

            // find() traverses directory by path and find match search cri          teria
            Stream<Path> stream = Files.find(path, 10,  (p,a) -> p.toString().endsWith(".class")
                    && a.lastModifiedTime().toMillis()> dateFilter);

            stream.forEach((s)-> System.out.println(s));

            // list() search single directory , return a objects representing the contents of the direct child
            // and return stream
            System.out.println();
            Files.list(path).filter((d)-> !Files.isDirectory(d)).map((d)->d.toAbsolutePath())
                    .forEach((d)-> System.out.println(d));


        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }




    public static void main(String[] args) throws IOException {

        NioRead();
        createAndCopyDirectory();
        FilesWithStreamAPI();




        Path path1 = Paths.get("/lizard/./").resolve(Paths.get("walking.txt"));
        Path path2 = new File("/lizard/././actions/../walking.txt").toPath();
        System.out.println(path1.normalize());
        System.out.println(path2.normalize());
        System.out.print(" "+path1.equals(path2));
        System.out.print(" "+path1.normalize().equals(path2.normalize()));
    }


    // 1.F 2.CB 3.D 4.C 5.BD 6.C 7.A 8.B 9.BC 10.AB 11.A 12.AF 13.A 14.E 15.DEF 16.EF 17.E 18.B 19.AEF
    // 20.A
}
