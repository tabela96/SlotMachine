package gastaldodemian;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;



import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import java.io.*;



public class SlotMachine {
	
	public class GiraSlot extends Thread {
		
		public void run() {
			
			if(clip2.isActive()){
				clip2.setFramePosition(0);
				clip2.stop();
			}
			
			clip1.setFramePosition(0);
			clip1.start();
			
			int t = 0;
			while(t < 3500){
				
				final int a = t;
				Display.getDefault().asyncExec(new Runnable(){
					public void run(){
						
						if(!clip1.isActive()){
							clip1.setFramePosition(0);
							clip1.start();
						}
						
						if(a < 1750){
							slots[0].setBounds(slots[0].getBounds().x, slots[0].getBounds().y + 2, 188, 175);
							slots[3].setBounds(slots[3].getBounds().x, slots[3].getBounds().y + 2, 188, 175);
						}
						if(a < 2625){
							slots[1].setBounds(slots[1].getBounds().x, slots[1].getBounds().y + 2, 188, 175);
							slots[4].setBounds(slots[4].getBounds().x, slots[4].getBounds().y + 2, 188, 175);
						}
						slots[2].setBounds(slots[2].getBounds().x, slots[2].getBounds().y + 2, 188, 175);
						slots[5].setBounds(slots[5].getBounds().x, slots[5].getBounds().y + 2, 188, 175);
						
						
						try {
							if(a >= 1750)
								Thread.sleep(1);
							if(a >= 2625)
								Thread.sleep(1);
							if(a >= 3499)
								return;
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						for(int i = 0; i < 6; i++){
							if(slots[i].getBounds().y > 174){
								generaSlot(i);
								slots[i].setBounds(slots[i].getBounds().x, -175, 188, 175);
							}
						}
					}
				});
				try {
					Thread.sleep(1);
					t++;
					if(t == 3500){
						Controlla();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
	    }
	}

	protected Shell shlSlotMachine;
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private Label[] slots = new Label[6];
	private ImagesContainer immagini = new ImagesContainer();
	private int[] numeri = new int[6];
	private Label titolo;
	private Text text;
	private int n;
	private Button btnGenera;
	private Button btnNewGame;
	private File ruota;
	private File vinci;
	private AudioInputStream gira;
	private AudioInputStream vincita;
	private Clip clip1;
	private Clip clip2;
	private boolean cheat = false;
	private Text text_1;
	
	public static void main(String[] args) {
		try {
			SlotMachine window = new SlotMachine();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public SlotMachine(){
		ruota = new File("src/Musica/Gira.wav");
		vinci = new File("src/Musica/Vincita.wav");
		try {
			clip2=AudioSystem.getClip();
		} catch (LineUnavailableException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		try {
			clip1=AudioSystem.getClip();
		} catch (LineUnavailableException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			vincita=AudioSystem.getAudioInputStream(vinci);
		} catch (UnsupportedAudioFileException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			gira=AudioSystem.getAudioInputStream(ruota);
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		try {
			clip1.open(gira);
			clip2.open(vincita);
		} catch (LineUnavailableException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		shlSlotMachine.open();
		shlSlotMachine.layout();
		while (!shlSlotMachine.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlSlotMachine = new Shell();
		shlSlotMachine.setImage(SWTResourceManager.getImage(SlotMachine.class, "/Immagini/bar.png"));
		shlSlotMachine.setSize(599, 400);
		shlSlotMachine.setText("Slot Machine");
		
		Composite composite = new Composite(shlSlotMachine, SWT.BORDER);
		composite.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		composite.setBounds(10, 70, 564, 175);
		formToolkit.adapt(composite);
		formToolkit.paintBordersFor(composite);
		
		slots[0] = formToolkit.createLabel(composite, "New Label", SWT.NONE);
		slots[0].setBounds(0, 0, 188, 175);
		slots[0].setImage(SWTResourceManager.getImage(SlotMachine.class, "/Immagini/bar.PNG"));
		
		slots[1] = formToolkit.createLabel(composite, "New Label", SWT.NONE);
		slots[1].setBounds(188, 0, 188, 175);
		slots[1].setImage(SWTResourceManager.getImage(SlotMachine.class, "/Immagini/bar.PNG"));
		
		slots[2] = formToolkit.createLabel(composite, "New Label", SWT.NONE);
		slots[2].setBounds(376, 0, 188, 175);
		slots[2].setImage(SWTResourceManager.getImage(SlotMachine.class, "/Immagini/bar.PNG"));
		
		slots[3] = formToolkit.createLabel(composite, "New Label", SWT.NONE);
		slots[3].setBounds(0, -175, 188, 175);
		slots[3].setImage(SWTResourceManager.getImage(SlotMachine.class, "/Immagini/arancia.PNG"));
		
		slots[4] = formToolkit.createLabel(composite, "New Label", SWT.NONE);
		slots[4].setBounds(188, -175, 188, 175);
		slots[4].setImage(SWTResourceManager.getImage(SlotMachine.class, "/Immagini/uva.PNG"));
		
		slots[5] = formToolkit.createLabel(composite, "New Label", SWT.NONE);
		slots[5].setBounds(376, -175, 188, 175);
		slots[5].setImage(SWTResourceManager.getImage(SlotMachine.class, "/Immagini/bar.PNG"));

		
		btnGenera = new Button(shlSlotMachine, SWT.NONE);
		btnGenera.setEnabled(false);
		btnGenera.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Gira();
			}
		});
		btnGenera.setBounds(446, 282, 125, 68);
		formToolkit.adapt(btnGenera, true, true);
		btnGenera.setText("GIOCA!");		
		
		titolo = new Label(shlSlotMachine, SWT.NONE);
		titolo.setAlignment(SWT.CENTER);
		titolo.setBounds(4, 4, 567, 61);
		titolo.setText("Best slot machine ever made by Gastaldo && Demian Oleksandr");
		
		//JOptionPane.showMessageDialog(null, "Benvenuto! Inserisci credito e premi nuova partita");
		
		btnNewGame = new Button(shlSlotMachine, SWT.NONE);
		btnNewGame.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				text.setText("10");
				btnGenera.setEnabled(true);
			}
		});
		btnNewGame.setBounds(11, 281, 125, 69);
		formToolkit.adapt(btnNewGame, true, true);
		btnNewGame.setText("NUOVA PARTITA");
		
		Label lblSaldo = new Label(shlSlotMachine, SWT.NONE);
		lblSaldo.setBounds(218, 309, 36, 15);
		formToolkit.adapt(lblSaldo, true, true);
		lblSaldo.setText("Saldo");
		
		text = new Text(shlSlotMachine, SWT.BORDER);
		text.setEditable(false);
		text.setBounds(260, 306, 93, 21);
		formToolkit.adapt(text, true, true);
		
		text_1 = new Text(shlSlotMachine, SWT.BORDER);
		text_1.setBounds(446, 251, 125, 21);
		formToolkit.adapt(text_1, true, true);
		
		Label lblInserisciSoldiQui = new Label(shlSlotMachine, SWT.NONE);
		lblInserisciSoldiQui.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lblInserisciSoldiQui.setBounds(347, 254, 93, 21);
		formToolkit.adapt(lblInserisciSoldiQui, true, true);
		lblInserisciSoldiQui.setText("Inserisci soldi qui:");
	}
	
	private void Gira(){
		if(Soldi() - SoldiInseriti() >= 0 && SoldiInseriti() > 0){
			btnGenera.setEnabled(false);
			btnNewGame.setEnabled(false);
			titoloSetText("Best slot machine ever made by Gastaldo && Demian Oleksandr", 14);
			text_1.setEditable(false);
			
			GiraSlot g = new GiraSlot();
			g.start();
			SetSoldi(Soldi() - SoldiInseriti());
		}
		else{
			Info("Inserisci credito");
		}
	}
	
	private void generaSlot(int i){
		if(i == 1){
			int c = (int) (Math.random()*Soldi());
			if(c == 3){
				cheat = true;
			}
			else{
				cheat = false;
			}
		}
		if(!cheat){
			numeri[i] = (int) (Math.random()*6);
			slots[i].setImage(SWTResourceManager.getImage(SlotMachine.class, immagini.getImage(numeri[i])));
		}
		if(cheat){
			if(i == 1 || i == 2){
				numeri[i] = numeri[0];
				slots[i].setImage(SWTResourceManager.getImage(SlotMachine.class, immagini.getImage(numeri[i])));
			}
			else{
				numeri[i] = (int) (Math.random()*6);
				slots[i].setImage(SWTResourceManager.getImage(SlotMachine.class, immagini.getImage(numeri[i])));
			}
		}
	}
	
	private void Controlla(){
		Display.getDefault().asyncExec(new Runnable(){
			public void run(){
				text_1.setEditable(true);
				if(clip1.isActive()){
					clip1.setFramePosition(0);
					clip1.stop();
				}
				if(Soldi() > 0)
					btnGenera.setEnabled(true);
				btnNewGame.setEnabled(true);
				
				if(numeri[0] != numeri[1] || numeri[1] != numeri[2] ||  numeri[0] != numeri[2]){
					titoloSetText("Niente combo, RIPROVA ;)", 14);
				}
				if(numeri[0] == numeri[1] && numeri[1] == numeri[2]){
					if(numeri[0] == 0 && numeri[1] == 0 && numeri[2] == 0){
						vincita(2);
					}
					if(numeri[0] == 1 && numeri[1] == 1 && numeri[2] == 1){
						vincita(3);
					}
					if(numeri[0] == 2 && numeri[1] == 2 && numeri[2] == 2){
						vincita(3);
					}
					if(numeri[0] == 3 && numeri[1] == 3 && numeri[2] == 3){
						vincita(5);
					}
					if(numeri[0] == 4 && numeri[1] == 4 && numeri[2] == 4){
						vincita(3);
					}
					if(numeri[0] == 5 && numeri[1] == 5 && numeri[2] == 5){
						vincita(4);
					}
				}
			}
		});
	}
	
	private void vincita(int m){
		clip2.start();
		titoloSetText("Vincita: x" + m, 20);
		float soldi = SoldiInseriti() * m;
		btnGenera.setEnabled(true);
		AddSoldi(soldi);
	}
	
	private void titoloSetText(String text, int font){
		titolo.setFont(SWTResourceManager.getFont("Segoe UI", font, SWT.NORMAL));
		titolo.setText(text);
	}
	
	private void Print(String s){
		System.out.println(s);
	}
	
	public float Soldi(){
		float soldi = 0;
		try{
			soldi=Float.parseFloat(Round(text.getText()));
		}catch(NumberFormatException e){
			soldi = 0;
		}
		return soldi;
	}
	
	private void SetSoldi(float soldi){
		text.setText(String.valueOf(soldi));
	}
	
	private void AddSoldi(float soldi){
		text.setText(String.valueOf(soldi + Soldi()));
	}
	
	private float SoldiInseriti(){
		float soldi = 0;
		try{
			soldi=Float.parseFloat(Round(text_1.getText()));
		}catch(NumberFormatException e){
			soldi = 0;
		}
		return soldi;
	}
	
	private void Info(String s){
		JOptionPane.showMessageDialog(null, s);
	}
	
	private String Round(String s){
		String temp = "";
		String[] arr = s.split("\\.");
		if(arr.length > 1){
			temp += arr[0];
			temp += ".";
			for(int i = 0; (i < 2 && i < arr[1].length()); i++){
				temp += arr[1].charAt(i);
			}
		}
		else{
			temp = arr[0];
		}
		return temp;
	}
}
