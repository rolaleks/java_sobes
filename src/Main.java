import builder.Person;
import builder.PersonBuilder;

public class Main {

    public static void main(String[] args) {

        PersonBuilder personBuilder = new PersonBuilder();

        personBuilder.setGender("M").setAddress("Russia").setFirstName("Alex").setAge(30);

        Person person = personBuilder.getPerson();

        System.out.println(person.getFirstName());
    }
}
