// Command Pattern - Home Automation
// Command encapsulates a request as an object,
// allowing you to queue, log, and undo operations.

// ===== Command Interface =====
interface Command {
    void execute();
}

// ===== Receiver Class (the actual device) =====
class Light {
    private String location;

    public Light(String location) {
        this.location = location;
    }

    public void turnOn() {
        System.out.println(location + " light is ON 💡");
    }

    public void turnOff() {
        System.out.println(location + " light is OFF 🌑");
    }
}

// ===== Concrete Commands =====
class LightOnCommand implements Command {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOn();
    }
}

class LightOffCommand implements Command {
    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOff();
    }
}

// ===== Invoker Class (the remote control) =====
class RemoteControl {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        System.out.print("Button pressed -> ");
        command.execute();
    }
}

// ===== Test Class =====
public class CommandTest {
    public static void main(String[] args) {

        System.out.println("=== Command Pattern Demo ===\n");

        // Create receiver
        Light livingRoomLight = new Light("Living Room");
        Light bedroomLight = new Light("Bedroom");

        // Create commands
        Command lightOn  = new LightOnCommand(livingRoomLight);
        Command lightOff = new LightOffCommand(livingRoomLight);
        Command bedroomOn = new LightOnCommand(bedroomLight);

        // Create invoker (remote control)
        RemoteControl remote = new RemoteControl();

        // Press buttons
        remote.setCommand(lightOn);
        remote.pressButton();

        remote.setCommand(bedroomOn);
        remote.pressButton();

        remote.setCommand(lightOff);
        remote.pressButton();

        System.out.println("\n✅ Command Pattern working correctly!");
    }
}
