package reflection;

public class Player {
    private String name;
    private int age;
    public int number;

    public Player(String name, int age, int number) {
        this.name = name;
        this.age = age;
        this.number = number;
    }

    public Player() { }

    public void play() {
        System.out.println(name + " is playing.");
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", number=" + number +
                '}';
    }
}
