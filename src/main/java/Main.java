import java.io.IOException;
import java.util.Scanner;

class WrongStudentName extends Exception { }
class WrongAge extends Exception { }
class WrongDateOfBirth extends Exception { }

class Main {
    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        while(true) {
            try {
                int ex = menu();
                switch(ex) {
                    case 1: exercise1(); break;
                    case 2: exercise2(); break;
                    case 3: exercise3(); break;
                    default: return;
                }
            } catch(IOException e) {

            } catch(WrongStudentName e) {
                System.out.println("Błędne imię studenta!");
            } catch(WrongAge e) {
                System.out.println("Błędny wiek studenta! Wiek musi być z przedziału 1–99 lat.");
            } catch(WrongDateOfBirth e) {
                System.out.println("Błędna data urodzenia! Poprawny format to DD-MM-YYYY.");
            }
        }
    }

    public static int menu() {
        System.out.println("Wciśnij:");
        System.out.println("1 - aby dodać studenta");
        System.out.println("2 - aby wypisać wszystkich studentów");
        System.out.println("3 - aby wyszukać studenta po imieniu");
        System.out.println("0 - aby wyjść z programu");
        return scan.nextInt();
    }

    public static String ReadName() throws WrongStudentName {
        scan.nextLine(); 
        System.out.println("Podaj imię: ");
        String name = scan.nextLine();
        if(name.contains(" ")) 
            throw new WrongStudentName();
        return name;
    }

    public static void exercise1() throws IOException, WrongStudentName, WrongAge, WrongDateOfBirth {
        var name = ReadName();

        System.out.println("Podaj wiek: ");
        var age = scan.nextInt();
        if(age <= 0 || age >= 100)
            throw new WrongAge(); 

        scan.nextLine();  
        System.out.println("Podaj datę urodzenia w formacie DD-MM-RRRR (np. 01-02-2000):");
        var date = scan.nextLine();

        if (date.length() != 10) {
            throw new WrongDateOfBirth();  
        }
        if (date.charAt(2) != '-' || date.charAt(5) != '-') {
            throw new WrongDateOfBirth();  
        }

        
        (new Service()).addStudent(new Student(name, age, date));
    }

    public static void exercise2() throws IOException {
        var students = (new Service()).getStudents();
        for(Student current : students) {
            System.out.println(current.ToString());
        }
    }

    public static void exercise3() throws IOException {
        scan.nextLine();
        System.out.println("Podaj imię: ");
        var name = scan.nextLine();
        var wanted = (new Service()).findStudentByName(name);
        if(wanted == null)
            System.out.println("Nie znaleziono...");
        else {
            System.out.println("Znaleziono: ");
            System.out.println(wanted.ToString());
        }
    }
}
