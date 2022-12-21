public abstract class  Person {
    // TODO implement Person and its subclasses in other Java files
    private String name;
    private int age;

    public String getName() {
        return this.name;
    }

    public void setName(String s) {
        name = s;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int s) {
        age = s;
    }
    @Override
    public String toString() {
        return String.format("Hello, my name is %s", name);
    }
}
abstract class Employee extends Person {
    private int months_worked;
    private double salary;
    //salary / (12 / months_worked
    public double thirteenthmonth() {
        double months_work = 12/months_worked;
        return 12 / months_work;
    }
}
class Clerk extends Employee {
    @Override
    public String toString() {
        return "How may I help you?";
    }
}


class Customer extends Person {

}

class Manager extends Employee {

}