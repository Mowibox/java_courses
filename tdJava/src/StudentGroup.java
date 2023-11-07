import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.regex.Pattern;

//Exercise 3 - Custom exception and regular expression
//Exercise 4 - Linked List - Array Manipulation
public class StudentGroup {
    private String name;
    static private int numberGroup;
    //Adding an ArrayList to class
    ArrayList<Student> alist;

    //Constructor
    public StudentGroup(String name) throws Exception{
            //Empty list initialization
            alist = new ArrayList<>();
            if (!Pattern.matches("[1-2]G[1-3]TD[1-3]TP[1-6]", name)){
                throw new Exception((name+" is not a real ENSEA name"));
            }
            else {
                this.name = name;
            }
    }
    public String getName(String name){
        return this.name = name;
    }

    @Override
    public String toString(){
        return this.name+"; Student in group : "+alist.size();
    }

    public void addStudent(Student s){
        alist.add(s);
    }

    public void displayPresenceList(){
        for(Student s: alist){
            System.out.println(s);
        }
    }

    //If we make only one try/catch with a,b and c, c will return an exception because b do
    //To correct this issue, we create one try/catch for each instance
    public static void main (String[] args) {
        StudentGroup a = null, b = null, c = null;
        try {
            a = new StudentGroup("1G1TD1TP1");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        try {
            b = new StudentGroup("3G1TD1TP1");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        try {
            c = new StudentGroup("1G1TD3TP6");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(a);
        System.out.println(b);
        System.out.println(c);

        //Ex4 - We are adding 2 student to the c group
        Student student1 = new Student("Ousmane","Thiongane");
        Student student2 = new Student("Alexandre","Petit");

        c.addStudent(student1);
        c.addStudent(student2);

        System.out.println("Group:");
        c.displayPresenceList();

        System.out.println(c);


    }

    //Exercise 5 - Find function
    public String getName(){
        return this.name;
    }








}
