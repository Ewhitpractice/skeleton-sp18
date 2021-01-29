import java.lang.Math;

import java.lang.Math;

public class Planet
{
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    private static final double gravity = 6.67e-11;

    public Planet(double xP, double yP, double xV, double yV, double m, String img)
    {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img; 
    }
    
    /**take in a planet and initialize an identical planet */
    public Planet(Planet p)
    {
        this(p.xxPos, p.yyPos, p.xxVel, p.yyVel, p.mass, p.imgFileName);
    }

    /** takes in a single planet and reutrns the distance between the supplied planet and this planet */
    public double calcDistance(Planet other_planet)
    {
        double dx = this.xxPos-other_planet.xxPos;
        double dy = this.yyPos-other_planet.yyPos;

        double distance = (Math.sqrt(dx*dx + dy*dy));

        return distance;
    }

    /** describes the force that the given planet exerts on this planet */
    public double calcForceExertedBy(Planet other_planet)
    {
        double distance = calcDistance(other_planet);
        double F = (gravity* this.mass * other_planet.mass)/(distance*distance);
        return F;
    }

    /** describe the force that the given planet exerts on this planet in the X and Y directions */
    public double calcForceExertedByX(Planet other_planet)
    {
        double dx = other_planet.xxPos-this.xxPos;
        double F1 = calcForceExertedBy(other_planet);
        double distance = calcDistance(other_planet);
        double F1x = (F1*dx)/distance;
        return F1x;
    }

    public double calcForceExertedByY(Planet other_planet)
    {
        double dy = other_planet.yyPos-this.yyPos;
        double F1 = calcForceExertedBy(other_planet);
        double distance = calcDistance(other_planet);
        double F1y = (F1*dy)/distance;
        return F1y;
    }

    /** takes an array of Planets and calculates the net X force exerted by all these planets on the current planet */
    public double calcNetForceExertedByX(Planet[] planets)
    {
        double Fnetx = 0;
        for(int i=0; i<planets.length; i++)
        {
            if(planets[i].equals(this))
            {
                continue;
            }
            else
            {
                Fnetx = Fnetx + calcForceExertedByX(planets[i]);
            }
        }
        return Fnetx;
    }
    
    public double calcNetForceExertedByY(Planet[] planets)
    {
        double Fnety = 0;
        for(int i=0; i<planets.length; i++)
        {
            if(planets[i].equals(this))
            {
                continue;
            }
            else
            {
            Fnety = Fnety + calcForceExertedByY(planets[i]);
            }
        }
        return Fnety;

    }
    
    /** how much the forces on the planet cause it to accelerate and the resulting change in the planet's velocity and position in a small period of time dt */
    public void update(double dt, double fX, double fY)
    {
        double ax = fX/this.mass;
        double ay = fY/this.mass;
        xxVel = (xxVel + dt * ax);
        yyVel = (yyVel + dt * ay);
        xxPos = (xxPos + dt * xxVel);
        yyPos = (yyPos + dt * yyVel);

    }

    public void draw()
    {
        String image = "images/"+this.imgFileName;
        StdDraw.picture(this.xxPos,this.yyPos,image);
    }
}
