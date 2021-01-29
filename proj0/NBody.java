import java.security.PKCS12Attribute;

public class NBody
{

    public static double readRadius(String filename)
    {
        In in = new In(filename);
        double R = in.readDouble();
        return R;
    }

    public static Planet[] readPlanets(String filename)
    {
        In in = new In(filename);
        int N = in.readInt();
        double R = in.readDouble();
        Planet[] planets = new Planet[N];
        for(int i=0;i<N;i++)
        {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String img = in.readString();
            Planet planet = new Planet(xxPos,yyPos,xxVel,yyVel,mass,img);
            planets[i] = planet;
        }

        return planets;

    }

    public static void main(String[] args)
    {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double R = readRadius(filename);
        Planet[] planets = readPlanets(filename);

        /** drawing the background */
        String background = "images/starfield.jpg";
        StdDraw.setScale(-R,R);
        StdDraw.picture(0,0,background);

    }
}
