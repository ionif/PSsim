import java.util.Random;

public class Particle {
	double x, y, z;
	Type type;
	Random rand = new Random();
	
	Particle (Type type,double x,double y,double z) {
		this.type = type;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public double rand(double x0, double x1) {
		double randomNum = rand.nextDouble() * (x1 - x0)  + x0;
		return randomNum;
	}
	
	public void move(double x0, double x1){
		double x = this.x + type.df * rand(x0, x1);
		double y = this.y + type.df * rand(x0, x1);
		double z = this.z + type.df * rand(x0, x1);
		if (x >= x0 && x < x1)
			this.x = x;
		if (y >= x0 && y < x1)
			this.y = y;
		if (z >= x0 && z < x1)
			this.z = z;
	}
}
