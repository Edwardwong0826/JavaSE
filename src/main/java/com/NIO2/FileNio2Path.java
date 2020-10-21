package com.NIO2;

import com.sun.source.tree.WhileLoopTree;
import org.w3c.dom.ls.LSOutput;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileNio2Path {

    public static void Path() throws URISyntaxException {
        // plural form Paths is the factory class, singular form Path is the instance
        // is possible to create path using vararg of string, operating system-dependent path.separator
        // is automatically inserted between elements

        // readAll lines() load the file byte into memory, if is very large file , it could hit
        // out of memory error

        List<String> fileArr;
        Path file = Paths.get("C:/Java/Count.txt");
        Path file2 = Paths.get("C:","Java","Count.txt");
        Path uri = Paths.get(new URI("file:///C:/Java/Count.txt"));

        try{

            fileArr = Files.readAllLines(file);


            System.out.println("First Group-------");
            fileArr.stream().filter(line->line.contains("h")).forEach(line-> System.out.println(line));

            System.out.println("Second Group--------");
            fileArr.stream().filter(line->line.contains("l")).forEach(line-> System.out.println(line));

        } catch (IOException e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    public static void PathInfo(Path path){

        // Get root on relative path will stopped at top of the relative path, it does not traverse outside

        System.out.println(Files.exists(Paths.get("C:/Java/Count.txt")));
        System.out.println("FileName is : " + path.getFileName());
        System.out.println("Root is : " + path.getRoot());
        System.out.println("Path is Absolute? : " + path.isAbsolute());
        System.out.println("Absolute Path : " + path.toAbsolutePath());
        System.out.println("Subpath from 0 to 2 is :" + path.subpath(0,2)); // subpath will not included root
        System.out.println("Subpath from 1 to 2 is :" + path.subpath(1,2));



        while((path = path.getParent()) != null){
            System.out.println("Current parent is : " + path);
        }

        System.out.println();

    }

    public static void PathMethod()
    {
        // relativize combine two path as one new relative, first path will become .. and second path display
        // relativize only can be both absolute or relative path, can't mix will throw run time exception
        // unix both must slash, windows must root directory or drive letter
        Path path1 = Paths.get("E:\\habitat");
        Path path2 = Paths.get("E:\\sanctuary\\raven");
        System.out.println(path1.relativize(path2)); //../sanctuary/raven
        System.out.println(path2.relativize(path1)); // ../../habitat

        // resolve creating new Path by joining existing path to the current path
        // resolve does not clean up parent directory .. symbol
        // it will not check the path is exist , will just join
        final Path path3 = Paths.get("/cats/../panther");
        final Path path4 = Paths.get("food");

        // if resolve() parameter is absolute it will return that absolute path
        System.out.println(path3.resolve(path4));  // /cats/../panther/food

        // if first path is absolute path, first path will be ignored and print second path
        final Path path5 = Paths.get("/turkey/food");
        final Path path6 = Paths.get("/tiger/cage");
        System.out.println(path5.resolve(path6)); // /tiger/cage

        // normalize eliminate the redundancies in the path
        Path path7 = Paths.get("E:\\data");
        Path path8 = Paths.get("E:\\user\\home");
        Path relativePath = path7.relativize(path8);
        System.out.println(path7.resolve(relativePath).normalize());

        // ./ means in the current directory just like /java inside only, we can also /Java/./. is same /Java
        // ../ means the parent of the current directory, can put any and use normalize() to remove it
        Path path9 = Paths.get("/Java/./").resolve(Paths.get("Count.txt"));
        Path path10 = new File("/Java/././home/../Count.txt").toPath();
        System.out.println(path9.normalize());
        System.out.println(path10.normalize());
        System.out.print(" "+path9.equals(path10));
        System.out.print(" "+path9.normalize().equals(path10.normalize()));

    }


    public static void main(String[] args) throws URISyntaxException {

        Path();
        System.out.println();
        PathInfo(Paths.get("C:/Java/Count.txt"));
        PathInfo(Paths.get("Java/Count.txt"));
        PathMethod();




    }

}
