import java.util.*;
import java.util.ArrayList;

public class Model {
	List<Type> Types = new ArrayList();
	List<Particle> Particles = new ArrayList();
	List<Interaction> Interactions = new ArrayList();
	
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
	
	void removeParticle(Particle p) {
		Particles.remove(p);
	}
	
	void addInteraction(Interaction rx) {
		Interactions.add(rx);
	}
	
	void step(double dt) {
		//check interaction
		for(Particle p : Particles) {
			p.move(x0, x1);
			//System.out.println("Particle: " + p + " X: " + p.x + " Y: " + p.y + " Z: " + p.z);
		}
	}
	
	Interaction findInteraction(Particle Part1, Particle Part2) {
		Type t1 = Part1.type;
		Type t2 = Part2.type;
		for (Interaction rx : Interactions) {
			if ((rx.A == t1 && rx.B == t2) || (rx.A == t2 && rx.B == t1)){
				return rx;
			}
		}
		return null;
	}
	
	double calcDist(Particle p1, Particle p2) {
			double dist = Math.sqrt((p1.x-p2.x)*(p1.x-p2.x) + (p1.y-p2.y)*(p1.y-p2.y) - (p1.z-p2.z)*(p1.z-p2.z));
			return dist;
	}
	
	void checkInteractions() {
		for (int i = 0; i < Particles.size(); i++) {
			Particle p1 = Particles.get(i);
			for (int j = i + 1; j < Particles.size(); j++) {
				Particle p2 = Particles.get(j);
				Interaction rxn = findInteraction(p1, p2);
				if (rxn != null) {
					double dist = calcDist(p1, p2);
					if (dist < rxn.dist) {
						//System.out.println("Interaction p1 (" + p1.x + ", " + p1.y + ", " + p1.z + 
						//") p2 (" + p2.x + ", " + p2.y + ", " + p2.z + ")");
						removeParticle(p1);
						removeParticle(p2);
						if (rxn.Ar != null) {
							addParticle(new Particle(rxn.Ar, p1.x, p1.y, p1.z)); //TODO: fix coords
							System.out.println("Products: " + Particles);
						}
						else if (rxn.Br != null) {
							addParticle(new Particle(rxn.Br, p2.x, p2.y, p2.z)); //TODO: fix coords
						}
					}
				}
			}
		}
	}
	
	public void run(double t0, double t1, double dt) {
		for (double timer = t0; timer <= t1; timer += dt) {
			System.out.println("Time: " + timer);
//			System.out.println("Interactions: " + Interactions);
			//System.out.println("Particles: " + Particles);
			checkInteractions();
			step(dt);
		}
	//	checkInteractions();
	}
	
}
