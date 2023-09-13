package basis;

public class PassByValue {
    static class Dog {
        String name;

        Dog(String name) {
            this.name = name;
        }

        String getObjectAddress() {
            return super.toString();
        }

        @Override
        public String toString() {
            return "Dog{" + "name='" + name + '\'' + '}';
        }
    }

    private static void modify(Dog dog) {
        dog.name = "Jerry";
    }

    private static void change(Dog dog) {
        System.out.println(dog.getObjectAddress());
        dog = new Dog("Harry");
        System.out.println(dog.getObjectAddress());
        System.out.println(dog);
    }

    public static void main(String[] args) {
        Dog dog = new Dog("Tom");
        modify(dog);
        // 在方法中改变对象的字段值会改变原对象该字段值，因为引用的是同一个对象
        System.out.println(dog);
        // 但是在方法中将指针引用了其它对象，那么此时方法里和方法外的两个指针指向
        // 了不同的对象，在一个指针改变其所指向对象的内容对另一个指针所指向的对象没有影响
        change(dog);
    }
}
