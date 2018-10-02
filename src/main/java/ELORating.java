import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ELORating
{
    public static void main(String... args) throws IOException, GeneralSecurityException
    {
        double p1 = 2400;
        double p2 = 2000;
        double p3 = 1600;
        double r1;
        double r2;
        double e1;
        double e2;
        double s1;
        double s2;
        double k = 32;

        r1 = Math.pow(10, p1 / 400);
        r2 = Math.pow(10, p3 / 400);
        System.out.println("r: " + r1 + " " + r2);
        e1 = r1 / (r1 + r2);
        e2 = r2 / (r1 + r2);
        System.out.println("e: " + e1 + " " + e2);
        s1 = 1;
        s2 = 0;
        System.out.println("s: " + s1 + " " + s2);
        p1 = p1 + k * (s1 - e1);
        p3 = p2 + k * (s2 - e2);
        System.out.println("p: " + p1 + " " + p2 + " " + p3);

        System.out.println();
        r1 = Math.pow(10, p1 / 400);
        r2 = Math.pow(10, p2 / 400);
        System.out.println("r: " + r1 + " " + r2);
        e1 = r1 / (r1 + r2);
        e2 = r2 / (r1 + r2);
        System.out.println("e: " + e1 + " " + e2);
        s1 = 1;
        s2 = 0;
        System.out.println("s: " + s1 + " " + s2);
        p1 = p1 + k * (s1 - e1);
        p2 = p2 + k * (s2 - e2);
        System.out.println("p: " + p1 + " " + p2 + " " + p3);
    }
}