public class Student {
    private String firstName;
    private String lastName;
    static private int totalNumberOfStudents;

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.totalNumberOfStudents++;
    }

    @Override
    public String toString(){
        return"Nom : "+this.lastName+", Prénom : "+this.firstName;
    }

    public int getTotalNumberOfStudents(){
        return totalNumberOfStudents;
    }

    public static void main1(String[] args) {
        Student a = new Student("Ousmane","Thiongane");
        Student b = new Student("Alexandre", "Petit");
        System.out.println(a);
        System.out.println(b);
        System.out.println("Number of students : "+a.getTotalNumberOfStudents());
        System.out.println();
    }

    //Exercise 2
    @Override
    protected  void finalize(){
        totalNumberOfStudents--;
    }

    //args[0] defines the firstName of a new Student and args[1] his lastName, where both are arguments are given to terminal
    public static void main(String[] args) {
        Student tauvel = new Student("Antoine", "Tauvel");
        Student bares = new Student("Christophe","Bares");
        Student argument = new Student(args[0], args[1]);

        System.out.println(tauvel);
        System.out.println(bares);
        System.out.println(argument);

        System.out.println("Total number of students : "+tauvel.getTotalNumberOfStudents());

        //new A = newStudent(); : "We created an instance with for reference A"
        //Before this line, there are 3 instances and 3 references of Students objects
        bares = argument;
        //But after this line, there are 3 instances and 2 references of Students objects
        System.gc();

        System.out.println(tauvel);
        System.out.println(bares);
        System.out.println(argument);

        System.out.println("Total number of students : "+tauvel.getTotalNumberOfStudents());

    }

}



