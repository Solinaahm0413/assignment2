import javax.swing.*;
import java.awt.*;

/**
 * This class represents the full view of the MVC pattern of your car simulator.
 * It initializes with being center on the screen and attaching its controller in its state.
 * It communicates with the Controller by calling its methods when an action fires in
 * its components.
 * TODO: Write more actionListeners and wire the rest of the buttons
 **/

public class CarView extends JFrame {
    public static final int X = 800;
    public static final int Y = 800;

    // The controller member
    CarController carC;

    DrawPanel drawPanel = new DrawPanel(X, Y-240);

    JPanel controlPanel = new JPanel();

    JPanel gasPanel = new JPanel();
    JSpinner gasSpinner;
    int gasAmount = 50;
    JLabel gasLabel = new JLabel("Amount of gas");

    JButton gas = new JButton("Gas");
    JButton brake = new JButton("Brake");
    JButton turnLeft = new JButton("← Turn left ←");
    JButton turnRight = new JButton("→ Turn right →");
    JButton turboOn = new JButton("Saab Turbo on");
    JButton turboOff = new JButton("Saab Turbo off");

    JButton startEngine = new JButton("Start all cars");
    JButton stopEngine = new JButton("Stop all cars");

    // Constructor
    public CarView(String framename, CarController cc) {
        this.carC = cc;
        initComponents(framename);
    }

    // Sets everything in place and fits everything
    // TODO: Take a good look and make sure you understand (roughly) how these methods and components work
    private void initComponents(String title) {
        this.setTitle(title);
        this.setPreferredSize(new Dimension(X,Y));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        this.add(drawPanel);

        SpinnerModel spinnerModel =
                new SpinnerNumberModel(gasAmount, //initial value
                        0, //min
                        50, //max
                        1);//step
        gasSpinner = new JSpinner(spinnerModel);
        gasSpinner.addChangeListener(e -> {
                gasAmount = (int) ((JSpinner)e.getSource()).getValue();
        });

        gasPanel.setLayout(new BorderLayout());
        gasPanel.add(gasLabel, BorderLayout.PAGE_START);
        gasPanel.add(gasSpinner, BorderLayout.PAGE_END);

        this.add(gasPanel);

        controlPanel.setLayout(new GridLayout(2,4));

        controlPanel.add(gas, 0);
        controlPanel.add(turboOn, 1);
        controlPanel.add(brake, 2);
        controlPanel.add(turboOff, 3);
        controlPanel.add(turnLeft, 4);
        controlPanel.add(turnRight, 5);
        controlPanel.setPreferredSize(new Dimension((X/2)+4, 200));
        this.add(controlPanel);
        controlPanel.setBackground(Color.CYAN);


        startEngine.setBackground(Color.blue);
        startEngine.setForeground(Color.green);
        startEngine.setPreferredSize(new Dimension(X/5-15,200));
        this.add(startEngine);

        stopEngine.setBackground(Color.red);
        stopEngine.setForeground(Color.black);
        stopEngine.setPreferredSize(new Dimension(X/5-15,200));
        this.add(stopEngine);

        // This actionListener is for the gas button only
        // TODO: Create more for each component as necessary
        gas.addActionListener(e -> {
            carC.gas(gasAmount);
        });

        brake.addActionListener(e -> {
            carC.brake(gasAmount);
        });

        turnLeft.addActionListener(e -> {
            carC.turnLeft();
        });

        turnRight.addActionListener(e -> {
                    carC.turnRight();
        });

        startEngine.addActionListener(e -> {
            carC.startEngine();
        });

        stopEngine.addActionListener(e -> {
            carC.stopEngine();
        });


        // Make the panel layout more compact
        this.pack();

        // Get the screen resolution
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // Center the frame
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        // Make the frame visible
        this.setVisible(true);
        // Make sure the frame exits when "x" is pressed
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}