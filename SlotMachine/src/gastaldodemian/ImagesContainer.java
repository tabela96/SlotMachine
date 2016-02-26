package gastaldodemian;

import javax.swing.ImageIcon;

public class ImagesContainer {
	private String[] images = new String[8];
	
	
	public ImagesContainer(){
		images[0] = "/Immagini/anguria.PNG";
		images[1] = "/Immagini/arancia.PNG";
		images[2] = "/Immagini/banana.PNG";
		images[3] = "/Immagini/bar.PNG";
		images[4] = "/Immagini/ciliegia.PNG";
		images[5] = "/Immagini/limone.PNG";
		images[6] = "/Immagini/mela.PNG";
		images[7] = "/Immagini/uva.PNG";
	}

	public String getImage(int i){
		return images[i];
	}
	
}
