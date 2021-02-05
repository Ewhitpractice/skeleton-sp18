import java.security.PKCS12Attribute;

public class NBody
{

    public static double readRadius(String filename)
    {
        In in = new In(filename);
        int i = in.readInt();
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
        StdDraw.setXscale(-R,R);
        StdDraw.setYscale(-R,R);
        StdDraw.picture(0,0,background);

        /**drawing the planets */
        for(int i=0; i<planets.length;i++)
        {
            planets[i].draw();
        }

        StdDraw.enableDoubleBuffering();
        StdDraw.show();
        double time = 0;
        while(time != T)
        {
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];
            for(int i=0; i<planets.length;i++)
            {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for(int i=0; i<planets.length;i++)
            {
                planets[i].update(dt,xForces[i],yForces[i]);
            }

            StdDraw.picture(0,0,background);

            for(int i=0; i<planets.length;i++)
            {
                planets[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            time = time +dt;
        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i<planets.length;i++)
        {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
            planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
        }
        }
    }
}
