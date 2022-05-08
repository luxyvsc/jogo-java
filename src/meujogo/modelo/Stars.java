package meujogo.modelo;

import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

public class Stars {
	
	private Image imagem;
	private int x,y;
	private int largura, altura;
	private boolean isVisivel;
	
	private static int VELOCIDADE = 6;
	
	public Stars(int x, int y) {
		this.x = x;
		this.y = y;
		isVisivel = true;
	}
	
	public void load() {
		ImageIcon referencia = new ImageIcon("res\\Sta.png");
		imagem = referencia.getImage();
		
		this.largura = imagem.getWidth(null);
		this.altura = imagem.getHeight(null);
	}
	
	public void update() {
		if(this.x < 0) {
			this.x = largura;
			Random a = new Random();
			int m = a.nextInt(500);
			this.x = m +1024;
			Random r = new Random();
			int n = r.nextInt(768);
			this.y = n;
		} else {
			this.x -= VELOCIDADE;
		}	
	}

	public boolean isVisivel() {
		return isVisivel;
	}

	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}

	public static int getVELOCIDADE() {
		return VELOCIDADE;
	}

	public static void setVELOCIDADE(int vELOCIDADE) {
		VELOCIDADE = vELOCIDADE;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Image getImagem() {
		return imagem;
	}
	
	
	
}
