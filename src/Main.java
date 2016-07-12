import java.util.Random;

public class Main {
	static Random rand = new Random();
	
	public static double rand(double x0, double x1) {
		double randomNum = rand.nextDouble() * (x1 - x0)  + x0;
		return randomNum;
	}
	
	public static void main(String[] args) {
		double x0 = -10;
		double x1 = 10;
		Model m = new Model(x0, x1, x0, x1, x0, x1);
		
		Type A = new Type("A", 15, 0.03);
		Type B = new Type("B", 15, 0.05);
		Type Ar = new Type("Ar", 15, 0.03);
		Type Br = new Type("Br", 15, 0.05);
		Interaction rx1 = new Interaction(A, B, Ar, Br, 5, 5);
		m.addType(A);
		m.addType(B);
		m.addType(Ar);
		m.addType(Br);
		m.addInteraction(rx1);
		for (int i = 0; i <= 10; i++) {
			m.addParticle(new Particle(A, rand(x0, x1), rand(x0, x1), rand(x0, x1)));
		}
		
		for(int i = 0; i < 10; i++) {
			m.addParticle(new Particle(B, rand(x0, x1), rand(x0, x1), rand(x0, x1)));
		}
		m.run(0, 60, 10);
	}

}
