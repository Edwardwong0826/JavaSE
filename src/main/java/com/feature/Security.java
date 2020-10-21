package com.feature;

import java.io.*;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class Security implements Cloneable, Serializable {

    // Designing a secure object
    // limit accessibility and restricting extensibility
    // key security principle is to limit access as much as possible,
    // call principle of least privilege

    // private > default > public
    // others will tried to extends the class and overloading the method by its method to do
    // malicious thing, use final to not allowed class to be inherit prevent this issue

    // Creating immutable object
    // 1. mark the class as final
    // 2. mark all instance variables private
    // 3. don't define any setter methods and mark fields final
    // 4. don't allow referenced mutable objects to be modified
    // 5. use a constructor to set all properties of the object, making a copy if needed

    // Injection and Input Validation
    // use PreparedStatement and bind variables to prevent SQL injection
    // use whitelist to specific which values are allowed when input, for directories

    // Working with Confidential Information
    // careful calls with method, ensure sensitive contexts confidential information don't escape
    // protecting data in memory, keep the confidential data in memory as short as possible,
    // like after use string set it to null, because might stored in string pool
    // look for the policy , see how its limit file access

    // Serializing and De-serializing Objects
    // below are optional methods when for serialize and deserialize, and must declared in the
    // serializable object to be used.
    // applying readResolve() after readObject(),it replace the reference object return by de-serializing
    // writeReplace() is before writeObject(), allows us to replace object that gets serialized


    // Constructing Sensitive Objects
    // constructing sensitive objects, we need to ensure subclass can't change the behaviour
    // put final on the method to not allowed subclass override the method
    // makes the constructor private to disallowed controlling the subclass

    //Preventing Denial of Service Attacks
    // use try-with-resources to close the resource might cause by infinite loop called never close
    // check size of file before handle it with out having out of memory and crash
    // overflowing numbers, check the primitive type maximum size that reach max  might cause overflow


    private static Map<String,Security> pool = new ConcurrentHashMap<>();
    private ArrayList<String> combos = new ArrayList<>();

    private String name;
    private String ssn;
    private int age;

    // Another way of serialized using array, must be private static final, else will be ignored
    private static final ObjectStreamField[] serialPersistentFields =
    {new ObjectStreamField("name",String.class), new ObjectStreamField("SSN",String.class)};

    // when deserializing object, java does not call constructor
    private Security(ArrayList<String> combos)
    {
        this.combos = new ArrayList<String>(combos); // rule 5
    }

    private Security()
    {

    }

    // set the constructor to private, use static factory methods to obtain the object
    public static Security getSecurity(ArrayList<String> combos)
    {
        return new Security(combos);
    }

    public final int getCombosSize()
    {
        return combos.size();
    }

    public final String getComboElement(int index)
    {
        return combos.get(index); // rule 4
    }

    private static String encrypt(String input)
    {
        return "encrypt";
    }

    public synchronized static Security getSecurity(String name)
    {
        if(pool.get(name) == null)
        {
            var e = new Security();
            e.name = name;
            pool.put(name,e);
        }

        return pool.get(name);

    }

    // after readObject()
    public Object readResolve() throws ObjectStreamException
    {
        var existingSecurity = pool.get(name);
        if(pool.get(name) == null)
        {
            pool.put(name,this);
            return this;
        }
        else
        {
            existingSecurity.name = this.name;
            existingSecurity.ssn = this.ssn;
            return existingSecurity;
        }
    }

    // before writeObject()
    public Object writeReplace() throws ObjectStreamException
    {
        var e = pool.get(name);
        return e != null ? e : this;
    }

    private void readObject(ObjectInputStream s) throws ClassNotFoundException, IOException {
        ObjectInputStream.GetField fields = s.readFields();
        this.name = (String)fields.get("name",null);
        this.ssn = (String)fields.get("ssn",null);

    }

    private void writeObject(ObjectOutputStream s) throws IOException
    {
        ObjectOutputStream.PutField fields = s.putFields();
        fields.put("name",encrypt(ssn));
        fields.put("ssn",ssn);
        s.writeFields();
    }


    public static void main(String[] args) throws CloneNotSupportedException
    {

        List<String> fileArr;
        Path file = Paths.get("C:/Java/Count.txt");
;
        try(var stream = Files.lines(file))
        {
            // read the file in try-with resource to prevent leaking resource
            // if not using, other hacker might create infinite loop to open a file never close it
        }
        catch(IOException io)
        {

        }


        ArrayList<String> inCombo = new ArrayList<>();

        //ArrayList<String> copyCommbo = new ArrayList<>(inCombo); - copy of inCombo

        // Clone() by default is shallow copy, if the object contains a reference ArrayList
        // shallow copy contains the same reference to that ArrayList, changes will affect that one
        // only if we overridden clone(), then it may provide deep copy of our write implementation
        // calling Clone() on any object will compile, because if defined in Object class, just will
        // throw exception for the class object that not implements Cloneable interface
        Security s = new Security(inCombo);
        Security s2 = (Security) s.clone();

    }

    // 1.D 2.D 3.A 4.D 5.D 6.BD 7.F 8.A 9.AC 10.CD 11.AEF 12.D 13.BE 14.BC 15.ABF 16.D 17.AB
    // 18.A 19.E 20.C
}
