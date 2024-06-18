public class Planet {
  public double xxPos;
  public double yyPos;
  public double xxVel;
  public double yyVel;
  public double mass;
  public String imgFileName;
  private static final double G = 6.67e-11;

  public Planet(double xP, double yP, double xV, double yV, double m, String img) {
    xxPos = xP;
    yyPos = yP;
    xxVel = xV;
    yyVel = yV;
    mass = m;
    imgFileName = img;
  }

  public Planet(Planet p) {
    xxPos = p.xxPos;
    yyPos = p.yyPos;
    xxVel = p.xxVel;
    yyVel = p.yyVel;
    mass = p.mass;
    imgFileName = p.imgFileName;
  }

  public double calcDistance(Planet p) {
    double r;
    double dx = this.xxPos - p.xxPos;
    double dy = this.yyPos - p.yyPos;
    r = Math.sqrt(dx * dx + dy * dy);
    return r;
  }

  public double calcForceExertedBy(Planet p) {
    double r = this.calcDistance(p);
    double F;
    F = G * this.mass * p.mass / r / r;
    return F;
  }

  public double calcForceExertedByX(Planet p) {
    double dx = p.xxPos - this.xxPos;
    double F = this.calcForceExertedBy(p);
    double r = this.calcDistance(p);
    double Fx = F * dx / r;
    return Fx;
  }

  public double calcForceExertedByY(Planet p) {
    double dy = p.yyPos - this.yyPos;
    double F = this.calcForceExertedBy(p);
    double r = this.calcDistance(p);
    double Fy = F * dy / r;
    return Fy;
  }

  public double calcNetForceExertedByX(Planet[] pp) {
    double sum = 0;
    for (int i = 0; i < pp.length; i++) {
      if (this.equals(pp[i])) {
        continue;
      }
      sum = sum + this.calcForceExertedByX(pp[i]);
    }
    return sum;
  }

  public double calcNetForceExertedByY(Planet[] pp) {
    double sum = 0;
    for (int i = 0; i < pp.length; i++) {
      if (this.equals(pp[i])) {
        continue;
      }
      sum = sum + this.calcForceExertedByY(pp[i]);
    }
    return sum;
  }

  public void update(double dt, double fX, double fY) {
    double ax = fX / this.mass;
    double ay = fY / this.mass;
    this.xxVel = this.xxVel + dt * ax;
    this.yyVel = this.yyVel + dt * ay;
    this.xxPos = this.xxPos + dt * this.xxVel;
    this.yyPos = this.yyPos + dt * this.yyVel;
  }

  public void draw() {
    StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
  }
}
