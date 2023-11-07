import java.lang.reflect.Array;
import java.util.ArrayList;

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
}
