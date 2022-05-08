package meujogo.modelo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Fase extends JPanel implements ActionListener {

	private Image fundo;
	private Player player;
	private Timer timer;
	private List<Enemy1> enemy1;
	private List<Stars> stars;
	private boolean emJogo;
	public int pontos = 0;

	public Fase() {
		setFocusable(true);
		setDoubleBuffered(true);

		ImageIcon referencia = new ImageIcon("res\\Blackground.gif");
		fundo = referencia.getImage();

		player = new Player();
		player.load();

		addKeyListener(new TecladoAdapter());

		timer = new Timer(5, this);
		timer.start();
		
		inicializaInimigos();
		inicializaStars();
		emJogo = true;
	}
	
	public void inicializaInimigos() {
		int cordenadas [] = new int [50];
		enemy1 = new ArrayList<Enemy1>();
		
		for (int i = 0; i < cordenadas.length; i++) {
			int x = (int)(Math.random() * 15000+1024);
			int y = (int)(Math.random() * 300+250);
			enemy1.add(new Enemy1(x, y));
		}
	}
	
	public void inicializaStars() {
		int cordenadas [] = new int [500];
		stars = new ArrayList<Stars>();
		
		for (int i = 0; i < cordenadas.length; i++) {
			int x = (int)(Math.random() * 1024);
			int y = (int)(Math.random() * 768);
			stars.add(new Stars(x, y));
		}
	}

	public void paint(Graphics g) {
		Graphics2D graficos = (Graphics2D) g;
		if(emJogo == true) {
			graficos.drawImage(fundo, 0, 0, null);
			
			for (int s = 0; s < stars.size(); s++) {
				Stars S = stars.get(s);
				S.load();
				graficos.drawImage(S.getImagem(), S.getX(), S.getY(), this);
			}
			
			graficos.drawImage(player.getImagem(), player.getX(), player.getY(), this);
			System.out.println(pontos);
                        
			/*List<Tiro> tiros = player.getTiros();
			for (int i = 0; i < tiros.size(); i++) {
				Tiro m = tiros.get(i);
				m.load();
				graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
			}*/
			
			for (int o = 0;o < enemy1.size(); o ++) {
				Enemy1 in = enemy1.get(o); 
				in.load();
				graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);
			}
		}else {
			graficos.drawImage(fundo, 0, 0, null);
			ImageIcon fimJogo = new ImageIcon("res\\lixowin.png");
			graficos.drawImage(fimJogo.getImage(), 88, 40, null);
		}

		g.dispose();
	}
	
	public void checarColisoes() {
		Rectangle formaNave = player.getBounds();
		Rectangle formaEnemy1;
		Rectangle formaTiro;
		
		for(int i = 0; i < enemy1.size(); i++) {
			Enemy1 tempEnemy1 = enemy1.get(i);
			formaEnemy1 = tempEnemy1.getBounds();
			if(formaNave.intersects(formaEnemy1)) {
                            pontos +=1;
                            tempEnemy1.setVisivel(false);
			}
		}
		
		/*List<Tiro> tiros = player.getTiros();
		for(int tiro = 0; tiro < tiros.size(); tiro++) {
			Tiro tempTiro = tiros.get(tiro);
			formaTiro = tempTiro.getBounds();
			for(int o = 0; o < enemy1.size(); o++) {
				Enemy1 tempEnemy1 = enemy1.get(o);
				formaEnemy1 = tempEnemy1.getBounds();
				if(formaTiro.intersects(formaEnemy1)) {
					tempEnemy1.setVisivel(false);
					tempTiro.setVisivel(false);
				}
			}
		}*/
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		player.update(); 
		if(player.isTurbo()) {
			timer.setDelay(1);
		}
		
		if(player.isTurbo() == false) {
			timer.setDelay(5);
		}
		
		
		for(int s = 0; s < stars.size(); s++) {
			Stars on = stars.get(s);
				if(on.isVisivel()) {
					on.update();
				}else {
					stars.remove(s);
				}
			}
		
		
		/*List<Tiro> tiros = player.getTiros();
		for (int i = 0; i < tiros.size(); i++) {
			Tiro m = tiros.get(i);
			if (m.isVisivel()) {
				m.update();
				if(player.isTurbo()) {
					tiros.get(i).setVELOCIDADE(-1);;
				}
				if(player.isTurbo() == false) {
					tiros.get(i).setVELOCIDADE(+3);;
				}
			} else {
				tiros.remove(i);
			}
		}*/

		for (int o = 0;o < enemy1.size(); o ++) {
			Enemy1 in = enemy1.get(o);
			if(in.isVisivel()) {
				in.update();
			}else {
				enemy1.remove(o);
			}
		}
		checarColisoes();
		repaint();
	}

	private class TecladoAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			player.keyPressed(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			player.keyRelease(e);
		}
	}

}
