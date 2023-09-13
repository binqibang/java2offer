package basis;

public class CloneObjectTest {

    static class Player implements Cloneable{
        String name;
        int number;
        String position;

        public Player(String name, int number, String position) {
            this.name = name;
            this.number = number;
            this.position = position;
        }

        @Override
        public String toString() {
            return "Player{" +
                    "name='" + name + '\'' +
                    ", number=" + number +
                    ", position='" + position + '\'' +
                    '}';
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Player p1 = new Player("Xavi", 6, "Midfielder");
        Player p2 = (Player) p1.clone();
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p1 == p2);
    }
}
