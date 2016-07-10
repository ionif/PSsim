import java.util.*;
import java.util.ArrayList;

public class Model {
	List<Type> Types = new ArrayList();
	List<Particle> Particles = new ArrayList();
	List<Particle> Interactions = new ArrayList();
	
	double x0, x1, y0, y1, z0, z1;
	
	Model (double x0, double x1, double y0, double y1, double z0, double z1){
		this.x0 = x0;
		this.x1 = x1;
		this.y0 = y0;
		this.y1 = y1;
		this.z0 = z0;
		this.z1 = z1;
	}
	
	void addType(Type t) {
		Types.add(t);
	}
	
	void addParticle(Particle p) {
		Particles.add(p);
	}
	
	void addInteraction(Particle a, Particle b) {
		Interactions.add(a);
		Interactions.add(b);
	}
	
	void step(double dt) {
		//check interaction
		for(Particle p : Particles) {
			p.move(x0, x1);
			System.out.println("Particle: " + p + " X: " + p.x + " Y: " + p.y + " Z: " + p.z);
		}
	}
	
	public void run(double t0, double t1, double dt) {
		for (double timer = t0; timer <= t1; timer += dt) {
			System.out.println("Time: " + timer);
			step(dt);
		}
	}
	
}
