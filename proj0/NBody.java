public class NBody {

  public static double readRadius(String s) {
    In in = new In(s);
    int N = in.readInt();
    double r = in.readDouble();
    return r;
  }

  public static Planet[] readPlanets(String s) {
    In in = new In(s);
    int N = in.readInt();
    double r = in.readDouble();
    Planet p;
    Planet[] ps = new Planet[N];
    for (int i = 0; i < N; i++) {
      double x = in.readDouble();
      double y = in.readDouble();
      double xv = in.readDouble();
      double yv = in.readDouble();
      double m = in.readDouble();
      String img = in.readString();
      p = new Planet(x, y, xv, yv, m, img);
      ps[i] = p;
    }
    return ps;
  }

  public static void main(String[] args) {
    Double T = Double.parseDouble(args[0]);
    Double dt = Double.parseDouble(args[1]);
    String filename = args[2];
    Planet[] planets = readPlanets(filename);
    Double radius = readRadius(filename);
    String img_background = "images/starfield.jpg";
    StdDraw.setScale(-radius, radius);
    StdDraw.clear();
    int N = planets.length;
    // StdDraw.pause(2000);
    for (Planet p : planets) {
      p.draw();
    }
    StdDraw.enableDoubleBuffering();
    double t = 0;
    while (t < T) {
      Double[] xForces = new Double[N];
      Double[] yForces = new Double[N];
      Double Fx;
      Double Fy;
      StdDraw.picture(0, 0, img_background);
      for (int i = 0; i < N; i++) {
        Fx = planets[i].calcNetForceExertedByX(planets);
        Fy = planets[i].calcNetForceExertedByY(planets);
        xForces[i] = Fx;
        yForces[i] = Fy;
      }
      for (int i = 0; i < N; i++) {
        Fx = xForces[i];
        Fy = yForces[i];
        planets[i].update(dt, Fx, Fy);
        planets[i].draw();
      }
      StdDraw.show();
      StdDraw.pause(5);
      t = t + dt;

    }
    StdOut.printf("%d\n", planets.length);
    StdOut.printf("%.2e\n", radius);
    for (int i = 0; i < N; i++) {
      StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n", planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
          planets[i].yyVel, planets[i].mass, planets[i].imgFileName);

    }

  }
}