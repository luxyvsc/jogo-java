package meujogo.modelo;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class World {
	
	public static List<Blocks> blocos = new ArrayList<Blocks>();
	
	public World() {
		for(int xx= 0; xx < 30; xx++) {
			blocos.add(new Blocks(xx*40,150));
		}
		
	}
	
	public static boolean isFree(int x, int y) {
		for(int i = 0; i < blocos.size(); i++) {
			Blocks blocoAtual = blocos.get(i);
			if(blocoAtual.intersects(new Rectangle(x, y, 62,32))) {
				return false;
			}
		}
		return true;
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < blocos.size(); i++) {
			blocos.get(i).render(g);
		}
	}
	
}
