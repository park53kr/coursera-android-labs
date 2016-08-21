import java.io.*;

/**
 * Created by Junsuk on 2016-08-20.
 */


public enum Elvis implements Serializable {
    //public static final Elvis inst = new Elvis();
    INSTANCE;
    private String name;
    private int age;

    private Elvis(){
        name = "Jun";
        age = 27;
        System.out.println("Constructor is called");
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public static void main(String[] args) {
        try {
            FileOutputStream os = new FileOutputStream("myserial.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(os);
            Elvis e = Elvis.INSTANCE;

            objectOutputStream.writeObject(e);
            objectOutputStream.close();

            FileInputStream fileInputStream = new FileInputStream("myserial.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Elvis e1 = (Elvis) objectInputStream.readObject();
            objectInputStream.close();

            if (e == e1) {
                System.out.println("Same obj");
                System.out.println("Age: " + e.getAge());
            } else System.out.println("Different obj");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
