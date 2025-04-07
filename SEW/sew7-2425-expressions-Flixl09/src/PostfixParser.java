
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class PostfixParser implements ParserType{

    public static ArrayList<Object> convertToSmth(String s) {
        ArrayList<Object> a = new ArrayList<>();
        for (String c:s.split(" ")) {
            if (c.matches("[0-9]+")){
                a.add(new Value(Double.parseDouble(c)));
            } else {
                a.add(c);
            }
        }
        return a;
    }

    @Override
    public Operation parse(String s) {
        ArrayList<Object> calc = convertToSmth(s);
        ArrayList<Object> operands = convertToSmth(s.replaceAll("[0-9]+", "").replaceAll(" {2}", " ").trim());

        System.out.println(Arrays.toString(calc.toArray()));
        System.out.println(Arrays.toString(operands.toArray()));


        while(calc.size() > 1) {
            String c = (String) operands.getFirst();
            int i = calc.indexOf(c);
            Object l = calc.get(i-2);
            Object r = calc.get(i-1);
            if (r instanceof Operation && l instanceof Operation) {
                Operation o = null;
                switch (c) {
                    case "+":
                        o = new Add((Operation) l, (Operation) r);
                        break;
                    case "-":
                        o = new Subtract((Operation) l, (Operation) r);
                        break;
                    case "*":
                        o = new Multi((Operation) l, (Operation) r);
                        break;
                    case "/":
                        o = new Div((Operation) l, (Operation) r);
                        break;
                    case "^":
                        o = new Potenz((Operation) l, (Operation) r);
                        break;
                    case "min":
                        o = new Min((Operation) l, (Operation) r);
                        break;
                    case "max":
                        o = new Max((Operation) l, (Operation) r);
                        break;
                }
                calc.remove(i);
                operands.remove(operands.getFirst());
                calc.remove(i-1);
                calc.set(i-2, o);
            }
        }

        return (Operation) calc.get(0);
    }
}
