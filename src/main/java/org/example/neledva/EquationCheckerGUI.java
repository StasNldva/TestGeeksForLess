package org.example.neledva;

import javax.swing.JFileChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Button;
import java.awt.Menu;
import java.awt.FlowLayout;
import java.awt.MenuItem;
import java.awt.MenuBar;
import java.io.File;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class EquationCheckerGUI extends Frame implements ActionListener {

    private TextField inputField, resultField;
    private Button checkBtn, clearBtn;
    private Menu menu;
    private Label inputEquation, checkerForCorrect;
    private MenuItem writeTo, openFile;
    private MenuBar mb;

    public EquationCheckerGUI() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setMenuContent();
        setAllContent();

        // set frame properties
        setTitle("Check Equations");
        setSize(300, 210);
        setComponentZOrder(inputEquation, 0);
        setComponentZOrder(inputField, 1);
        setComponentZOrder(checkerForCorrect, 2);
        setComponentZOrder(resultField, 3);
        setResizable(false);
        setVisible(true);
        //close app if press Exit button
        addWindowListener(new WindowAdapter() {
            /**
             * Invoked when a window is in the process of being closed.
             * The close operation can be overridden at this point.
             *
             * @param e
             */
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

    private void setMenuContent() {
        menu = new Menu("Menu");
        writeTo = new MenuItem("Save");
        openFile = new MenuItem("Open");
        menu.add(writeTo);
        menu.add(openFile);
        mb = new MenuBar();
        mb.add(menu);
        setMenuBar(mb);
        writeTo.setEnabled(false);
        openFile.setEnabled(false);

        writeTo.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new WriteFile().write(inputField.getText(), countNum(inputField.getText()));
                    }
                }
        );

        openFile.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JFileChooser file = new JFileChooser();
                        file.setCurrentDirectory(new File("C:\\Users\\neled\\IdeaProjects\\TestGeeksForLess\\"));
                        file.showOpenDialog(null);
                    }
                }
        );
    }

    private void setAllContent() {
        inputEquation = new Label("Input equation:");
        checkerForCorrect = new Label("Result of checked:");

        inputField = new TextField(35);
        resultField = new TextField(35);
        resultField.setEditable(false);

        checkBtn = new Button("CHECK");
        clearBtn = new Button("CLEAR");
        checkBtn.addActionListener(this);
        clearBtn.addActionListener(this);

        add(inputEquation);
        add(inputField);
        add(checkerForCorrect);
        add(resultField);
        add(checkBtn);
        add(clearBtn);
    }

    public void actionPerformed(ActionEvent e) {
        boolean state;
        if (e.getSource() == checkBtn) {
            String parentheses = inputField.getText().trim().replaceAll(" ", "");
            state = checkParentheses(parentheses);
            if (state) {

                String text = inputField.getText().trim().replaceAll(" ", "").
                        replaceAll("\\(", "").replaceAll("\\)", "");
                state = checkEquation(text);
                if (state) {
                    resultField.setText("Correct equation! You can save into a file");
                } else {
                    resultField.setText("Invalid.");
                }

                writeTo.setEnabled(true);
                openFile.setEnabled(true);

            } else {
                resultField.setText("Invalid parentheses");
            }
        }
        if (e.getSource() == clearBtn) {
            inputField.setText("");
            resultField.setText("");
        }
    }

    protected boolean checkEquation(String text) {
        Pattern pattern = Pattern.compile("^([\\-]?[\\d(.\\d+)?]+([*]?x?))\\b(([+/*]?[-]||[+/*\\-]+)x?||([\\d(.\\d+)?]+([*]?x?)?)+)+\\b=[\\d(.\\d+)?]+([*]?x?)(([+/*]?[-]||[+/*\\-]+)x?||([\\d(.\\d+)?]+([*]?x?)?)+)+\\b$");
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }

    protected boolean checkParentheses(String text) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '(' || text.charAt(i) == ')') {
                stringBuilder.append(text.charAt(i));
            }
        }

        Stack<Character> stack = new Stack<Character>();
        for (char c : stringBuilder.toString().toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else if (c == ')' && stack.peek() == '(') {
                stack.pop();
            } else {
                return false;
            }
        }
        return stack.isEmpty();
    }

    protected int countNum(String text){
        int count = 0;
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            count++;
        }
        return count;
    }
}