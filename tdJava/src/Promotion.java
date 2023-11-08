import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.InetAddress;
import java.util.ArrayList;

//Exercise 5 - Find Function
public class Promotion {
    private ArrayList<StudentGroup> alist;

    //Creation of an arrayList for the Promotion, which will contain groups
    public Promotion(){
        alist = new ArrayList<>();
    }

    public StudentGroup findGroup(String name){
        for(StudentGroup group : alist){
            if(group.getName().equals(name)){
                return group;
            }
        } return null;
    }

    public Student findStudent(StudentGroup g, String first, String last) {
        for (Student s : g.alist) {
            if (s.getFirstName().equals(first) && s.getLastName().equals(last)) {
                return s;
            }
        } return null;
    }

    public void presenceList(){
        for(StudentGroup g: alist){
            System.out.println("Liste de présence du TD : "+g.getName());
            for(Student s : g.alist){
                System.out.println("Prénom : "+s.getFirstName()+", Nom : "+s.getLastName());
            }
        }
    }

    public void addGroup(StudentGroup g){
        alist.add(g);
    }

    public static void main(String[] args){
        Promotion p = new Promotion();
        StudentGroup a = null;
        try{a = new StudentGroup("1G1TD3TP6");
        }
        catch (Exception e){
            e.printStackTrace();
        }

        p.addGroup(a);

        a.addStudent(new Student("Ousmane", "Thiongane"));
        a.addStudent(new Student("Alexandre", "Petit"));
        a.addStudent(new Student("Arthur", "Laumy"));

        p.presenceList();

        System.out.println(p.findGroup("1G1TD3TP6"));
        System.out.println(p.findGroup("1G1TD1TP2"));
        System.out.println(p.findStudent(p.findGroup("1G1TD3TP6"), "Ousmane", "Thiongane"));
        System.out.println(p.findStudent(p.findGroup("1G1TD3TP6"), "Ousmane", "Petit"));
    }

    //Exercise 6 - File manipulation

    /*
    FileInputStream(File file)
    Creates a FileInputStream by opening a connection to an actual file, the file named by the File object file in the file system.

    FileInputStream(FileDescriptor fdObj)
    Creates a FileInputStream by using the file descriptor fdObj, which represents an existing connection to an actual file in the file system.

    FileInputStream(String name)
    Creates a FileInputStream by opening a connection to an actual file, the file named by the path name name in the file system.

    BufferedReader(Reader in)
    Creates a buffering character-input stream that uses a default-sized input buffer.

    BufferedReader(Reader in, int sz)
    Creates a buffering character-input stream that uses an input buffer of the specified size.

    How to manipulate strings : https://docs.oracle.com/javase/tutorial/java/data/manipstrings.html
     */

    public void fillPromotion(String fileName){
        try{
            FileInputStream fis = new FileInputStream(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String line = reader.readLine();

            while(line != null){
                int firstComma = line.indexOf(',');
                String lastName = line.substring(0,firstComma);
                int secondComma = line.indexOf(',',firstComma+1);
                String firstName = line.substring(firstComma+1,secondComma);
                int lastComma = line.indexOf(',',secondComma+1);
                String groupName = line.substring(secondComma+1,lastComma);

                StudentGroup g = this.findGroup(groupName);
                if (g == null){
                    g = new StudentGroup(groupName);
                    this.addGroup(g);
                }

                Student s = this.findStudent(g,firstName,lastName);
                if(s == null){
                    s = new Student(firstName, lastName);
                    g.addStudent(s);
                }

                line = reader.readLine();
            }


        } catch(Exception e){
            e.printStackTrace();
        }

        //This exercise is not quite realistic because you can create any group you want
        //and any student. Some regular expressions could fix that


    }
}
