import java.util.Random;

//Exercise 7 - Inheritance and polymorphism

/*
    Random alea = new Random();
    for (int i = 0; i < 6; i++){
        grades[i] = Math.floor((12 + alea.nextGaussian() * 3) * 10/10;
        grades[i]=grades[i]>20?20:grades[i]

    The next Gaussian generates a value between 0 and 1, so the notes will be
    between 12 and 15

    The ternary operator allow to set all the notes above 20 to 20
 */
abstract class Student_ex7 {
    //When we change the class to an abstract class, our old code will not work
    //because the abstract classes cannot be instanced.
    //Class attributes
    private String firstName;
    private String lastName;
    static private int totalNumberOfStudents;

    protected Boolean yearValidation;
    protected double grades[];

    //Constructor : Updating the number of students
    public Student_ex7(String firstName, String lastName) {
        //Getter methods
        this.firstName = firstName;
        this.lastName = lastName;
        this.totalNumberOfStudents++;
        this.yearValidation = Boolean.FALSE;

        Random alea = new Random();
        for (int i = 0; i < 6; i++){
            this.grades[i] = Math.floor((12 + alea.nextGaussian() * 3) * 10)/10;
            this.grades[i]=this.grades[i]>20?20:this.grades[i];
    }

  
    //Test 1
    public static void main(String[] args){
            Promotion p = new Promotion();
            p.fillPromotion("./src/liste2_2020_2021.csv");
            p.presenceList();

            System.out.println("Failed Students : ");
            for(StudentGroup g : p.listgroup){
                for(Student s : g.getList()){
                    s.checkResult();
                    if (!s.getValidatedYear()){
                        System.out.println(g.getName()+" "+s);
                    }
                }
            }
        }


}



