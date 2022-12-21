import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class App extends JFrame {
    private JPanel pnlMain;
    private JRadioButton rbCustomer;
    private JRadioButton rbClerk;
    private JRadioButton rbManager;
    private JTextField tfName;
    private JTextArea taPersons;
    private JButton btnSave;
    private JTextField tfAge;
    private JTextField tfMonths;
    private JTextField tfSalary;
    private JButton btnClear;
    private JTextField tfLoad;
    private JButton btnLoad;
    private JButton btnSayHi;
    private JButton btnSavePerson;
    private JButton btnLoadPerson;
    private JButton btnReward;
    private List<JTextField> fields;
    private List<Person> persons;
    private List<JRadioButton> info;
    public App() {
        persons = new ArrayList<>();
        // TODO add implementations for all milestones here
        info = new ArrayList<>();
        info.add(rbClerk);
        info.add(rbCustomer);
        info.add(rbManager);

        fields = new ArrayList<>();
        fields.add(tfAge);
        fields.add(tfName);
        fields.add(tfLoad);
        fields.add(tfMonths);
        fields.add(tfSalary);

        btnReward.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int load = Integer.parseInt(tfLoad.getText());
                    int i = 1, flag = 0;
                    for (Person p : persons) {
                        if (i == load) {
                            flag = 1;
                            if (p instanceof Employee) {
                                Employee emp = (Employee) p;
                                JOptionPane.showMessageDialog(null, String.format("Empolyee %s: %.2f reward", emp.getName(), emp.thirteenthmonth()), "Message", 1);
                            } else {
                                throw new Exception();
                            }
                        }
                        i++;
                    }

                    if (flag == 0)
                        throw new outOfReach("OutOFReach");
                } catch (NumberFormatException x) {
                    JOptionPane.showMessageDialog(null, String.format("Age must be an Integer"), "Message", 1);
                } catch (outOfReach x) {
                    JOptionPane.showMessageDialog(null, String.format("OutOfReach"), "Message", 1);
                } catch (Exception x) {
                    JOptionPane.showMessageDialog(null, String.format("Not an employee cant give reward"), "Message", 1);
                }

            }
        });
        btnLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int load = Integer.parseInt(tfLoad.getText());
                    int i = 1, flag = 0;
                    for (Person p : persons) {
                        if (i == load) {
                            tfAge.setText(String.valueOf(p.getAge()));
                            tfName.setText(p.getName());

                            flag = 1;
                            if (p instanceof Clerk) {
                                rbClerk.setSelected(true);
                                Employee emp = (Employee) p;
                                tfMonths.setText(String.valueOf(emp.getMonths_worked()));
                                tfSalary.setText(Double.toString(emp.getSalary()));
                            } else if (p instanceof Customer) {
                                rbCustomer.setSelected(true);
                            } else if (p instanceof Manager) {
                                rbManager.setSelected(true);
                                Employee emp = (Employee) p;
                                tfMonths.setText(String.valueOf(emp.getMonths_worked()));
                                tfSalary.setText(Double.toString(emp.getSalary()));
                            }
                        }
                        i++;
                    }

                    if (flag == 0)
                        throw new outOfReach("OutOFReach");
                } catch (NumberFormatException x) {
                    JOptionPane.showMessageDialog(null, String.format("Must use an Integer"), "Message", 1);
                } catch (outOfReach x) {
                    JOptionPane.showMessageDialog(null, String.format("OutOfReach"), "Message", 1);
                }
            }
        });
        btnSayHi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Person p : persons) {
                    System.out.println(p);
                }
            }
        });
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (JTextField tf : fields) {
                    tf.setText("");
                }
            }
        });

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int num = 0;
                for (JRadioButton rb : info) {
                    try {
                        if (rb.isSelected()) {
                            String infos = rb.getText();
                            switch (infos) {
                                case "Clerk":
                                    num = 1;
                                    Clerk clerk = new Clerk();
                                    clerk.setName(tfName.getText());
                                    clerk.setAge(Integer.parseInt(tfAge.getText()));
                                    clerk.setMonths_worked(Integer.parseInt(tfMonths.getText()));
                                    clerk.setSalary(Double.parseDouble(tfSalary.getText()));
                                    String toBe = String.format("Clerk - %s (%d)\n", clerk.getName(), clerk.getAge());
                                    append(taPersons, toBe);
                                    persons.add(clerk);
                                    break;

                                case "Customer":
                                    num = 2;
                                    Customer cust = new Customer();
                                    cust.setName(tfName.getText());
                                    cust.setAge(Integer.parseInt(tfAge.getText()));
                                    String custprint = String.format("Customer - %s (%d)\n", cust.getName(), cust.getAge());
                                    append(taPersons, custprint);
                                    persons.add(cust);
                                    break;

                                case "Manager":
                                    num = 3;
                                    Manager man = new Manager();
                                    man.setName(tfName.getText());
                                    man.setAge(Integer.parseInt(tfAge.getText()));
                                    man.setMonths_worked(Integer.parseInt(tfMonths.getText()));
                                    man.setSalary(Double.parseDouble(tfSalary.getText()));
                                    String manprint = String.format("Manager - %s (%d)\n", man.getName(), man.getAge());
                                    append(taPersons, manprint);
                                    persons.add(man);
                                    break;
                            }
                        }
                    } catch (NumberFormatException x) {
                        JOptionPane.showMessageDialog(null, String.format("Age must be an Integer"), "Message", 1);
                    }

                }

                for (JTextField tf : fields) {
                    tf.setText("");
                }
            }
        });
    }

    public static void main(String[] args) {
        // add here how to make GUI visible
        App frame = new App();
        frame.setTitle("Simple Calculator");
        frame.setContentPane(frame.pnlMain);
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);


    }

    void append(JTextArea taPersons, String newText){
        taPersons.setText(taPersons.getText() + newText);
    }

    static void giveReward(int n) {

    }
}

class outOfReach extends Exception {
    public outOfReach(String s) {
        super(s);
    }
}
