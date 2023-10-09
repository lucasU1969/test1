package nemo;


public abstract class Directions {
    //public abstract String moveForward();

    public static Directions west() {
        return new West();
    }

    public static Directions east() {
        return new East();
    }

    public static Directions north() {
        return new North();
    }

    public static Directions south() {
        return new South();
    }

    public abstract Directions turnLeft();
    public abstract Directions turnRight();
}
