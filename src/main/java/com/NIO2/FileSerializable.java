package com.NIO2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileSerializable implements Serializable {


    //ObjectInputStream And ObjectOutputStream are work with Serializable
    //ObjectInputStream readObject() throw IOException and ClassNotFoundException
    //ObjectOutputStream writeObject() throw IOException

    //Serializable is the process of converting in-memory object to byte stream
    //deserialization is the process converting from byte stream into an object


    public static List<Animal> getAnimals(File inFile) throws IOException
    {
        List<Animal> animals = new ArrayList<>();
        try(ObjectInputStream input = new ObjectInputStream(new BufferedInputStream(new FileInputStream(inFile))))
        {
            while(true)
            {
                Object obj = input.readObject();
                if(obj instanceof Animal)
                {
                    animals.add((Animal) obj);
                }
            }

        } catch (EOFException | ClassNotFoundException e) {

        }

        return animals;
    }

    public static void addAnimals(File destinationFile, List<Animal> animals)
    {

        try(ObjectOutputStream output = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(destinationFile))))
        {
            for( Animal animal :  animals)
            {
                output.writeObject(animal);
            }

        }
        catch (IOException e)
        {
            System.out.println("Error : " + e.getMessage());
        }
    }

    public static void main(String[] args) throws IOException {

        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Lion",10, 1));
        animals.add(new Animal("Tiger", 15,2));

        File dataFile = new File("C:/Java/Animals.data");

        addAnimals(dataFile,animals);
        System.out.println(getAnimals(dataFile));


        Console console = System.console();

    }

    // 1.F 2.CEG 3.E 4.A 5.BEF 6.E 7.G 8.A 9.AB 10.E 11.F 12.A 13.BDE 14.ACE 15.E 16.AD
    // 17.F 18.BC 19.DS 20.C 21.AC 22.A
}
