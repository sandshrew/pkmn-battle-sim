package view;

class Hexagon {
    double [] points;
    double center;
    public Hexagon(double side){
      center = getH(side);
      points = new double[12];
      //     X                          Y
      points[0] = 0.0;           points[1] = 0.0;
      points[2] = side;          points[3] = 0.0;
      points[4] = side+(side/2); points[5] = center;
      points[6] = side;          points[7] = center * 2;
      points[8] = 0.0;           points[9] = center * 2;
      points[10] = -side/2;      points[11] = center;

    }

    private double getH(double side) {
      return ((Math.sqrt(3)/2)*side);
    }
    public double [] getPoints(){
      return points;
    }
  }
