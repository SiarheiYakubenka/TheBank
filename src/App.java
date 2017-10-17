
public class App {

    public static void main(String[] args) {
        System.out.println("Hello, world!");


        byte b = 10; //-128 ..127

        short s = -1000; //-32768 .. 32 767(2 байта)

        int i = 100000; //-2^31 .. 2^31 -1 (4 байт)

        long l = 1000000000;//-2^63 .. 2^63 -1(8 байт)


        float f = 1.0f;//(4 байта, одинарная точность)

        double d = -7.5e-5;//(8 байт, двойная точность)


        char ch = 'a'; //символьный тип(2 байта)

        boolean bool = true;//логический тип


        //условное выражение (сокращенное)
        if (l>10000) {
            System.out.println("1 > 10000");
        }

        //условное выражение (полное)
        if (i<1000) {
            System.out.println("i<1000");
        } else {
            System.out.println("i>=1000");
        }

        //условие с множественным выбором
        switch (b){
            case 1:
                System.out.println("b == 1");
                break;
            case 2:
                System.out.println("b == 2");
                break;
            case 3:
            case 4:
                System.out.println("b == 3 или b == 4");
                default:
                    System.out.println("b неправильное");
                    break;
        }

        //условный тернарный оператор
        int j= s >100 ? 200 : 300;
        /*
        int j = 0
        if (s>100) {
        j=200;
        } else {
        j=300
         }
         */


        while (j<400){
            j=j+1;
            //continue;
            //break;
        }

        do {
            j=j-1;
        } while (j>100);

        for (int i1 = 0; i1<100; i1++){
            System.out.println(i1);
            //continue;
            //break;
        }

        i = 100+200;
        i = 100-200;
        i = 100*200;

        i = 100/200;
        i = 100%200;
        d = 100.0 / 200;

        i = i|s; //арифметитическое или
        i = i&s; //арифметитическое и
        i = i^s; //арифметитическое исключаещее или
        i = i << 1; //*2
        i = i << 1; // /2
        i = i >>> 1;


        boolean a = true && false; //логическое и(ленивое)
        a = true||false;//логическое или (ленивое)
        a= !true; //логическое не
        a = true&false;//логическое и
        a = true|false;//логическое или

    }
}
