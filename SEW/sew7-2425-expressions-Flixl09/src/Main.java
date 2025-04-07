import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        String input = JOptionPane.showInputDialog(null, "Gib eine Formel ein!", "Input Dialog", JOptionPane.QUESTION_MESSAGE);
        if (input == null) {
            System.err.println("Why no Input?");
            System.exit(666);
        }

        Parser p = new Parser(input, new PostfixParser());
        System.out.println(p.parse());
    }
}
