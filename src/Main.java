import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args) throws IOException {

//        ArrayList<Character> tmp = new ArrayList<>();
//        tmp.add('d');
//        tmp.add('u');
//        tmp.add('d');
//        tmp.add('o');
//        tmp.add('s');
//
//        ArrayList<Character> tmp2 = new ArrayList<>(tmp);
//        tmp2.set(3,'z');
//
//
//
//        if(tmp.equals(tmp2)){
//            System.out.println("equals");
//        }
//        else
//        {
//            System.out.println("NOT equals");
//        }

        //long start = System.currentTimeMillis();
        LLK llk = new LLK();

        int first = 1200;
        int second = 1200;
        List<Integer> list = new ArrayList<>(){{
            this.add(first);
            this.add(second);
        }};
        System.out.println(list.get(0) == list.get(1));

        //long end = System.currentTimeMillis();
        //System.out.println("time = " + (end - start) / 1000.0);

//        if( false ){
//            Scaner  scaner = new Scaner();
//            ArrayList<Character> l = new ArrayList<>();
//            //scaner.scaning(l);
//            int resultScaner = 0;
//            System.out.print("\n\n==========================================================================================\n\n");
//            do {
//                resultScaner = scaner.next(l);
//                System.out.print(resultScaner + " - тип \t\t");
//                for (char c : l) {
//                    System.out.print(c);
//                }
//                System.out.print("\n");
//            } while (resultScaner != scaner._END);
//        }
//        else {
//            TDiagram tDiagram = new TDiagram();
//        }

    }
}