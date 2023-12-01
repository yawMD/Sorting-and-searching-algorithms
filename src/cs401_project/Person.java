package cs401_project;

class Person implements Comparable<Person> {
    String name;
    int number;

    Person(String name, int number) {
        this.name = name;
        this.number = number;
    }

    @Override
    public int compareTo(Person other) {
        return Integer.compare(this.number, other.number);
    }

    @Override
    public String toString() {
        return name + " " + number;
    }
}

