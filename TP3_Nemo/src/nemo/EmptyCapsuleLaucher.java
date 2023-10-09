package nemo;

public class EmptyCapsuleLaucher extends CapsuleLauncher {
    public boolean isLoaded() {
        return false;
    }
     public void launch() {
        throw new RuntimeException("Nemo cannot launch the capsule twice.");
    }
}
