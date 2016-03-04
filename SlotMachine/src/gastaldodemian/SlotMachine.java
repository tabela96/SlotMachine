package gastaldodemian;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

import com.sun.prism.Image;

import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ImageHyperlink;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;



public class SlotMachine {
	
	public class GiraSlot extends Thread {
		
		public void run() {
			int t = 0;
			while(t < 3500){
				final int a = t;
				Display.getDefault().asyncExec(new Runnable(){
					public void run(){
						
						if(a <= 1750){
							slots[0].setBounds(slots[0].getBounds().x, slots[0].getBounds().y + 1, 188, 175);
							slots[3].setBounds(slots[3].getBounds().x, slots[3].getBounds().y + 1, 188, 175);
						}
						if(a <= 2625){
							slots[1].setBounds(slots[1].getBounds().x, slots[1].getBounds().y + 1, 188, 175);
							slots[4].setBounds(slots[4].getBounds().x, slots[4].getBounds().y + 1, 188, 175);
						}
						slots[2].setBounds(slots[2].getBounds().x, slots[2].getBounds().y + 1, 188, 175);
						slots[5].setBounds(slots[5].getBounds().x, slots[5].getBounds().y + 1, 188, 175);
						
						
							try {
								if(a >= 1750)
									Thread.sleep(1);
								if(a >= 2625)
									Thread.sleep(1);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						System.out.println(slots[0].getBounds().y);
						for(int i = 0; i < 6; i++){
							if(slots[i].getBounds().y > 174){
								generaSlot(i);
								slots[i].setBounds(slots[i].getBounds().x, -175, 188, 175);
							}
						}
					}
				});
				try {
					this.sleep(1);
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
	private String num;
	private int n;
	private Button btnGenera;
	
	public static void main(String[] args) {
		try {
			SlotMachine window = new SlotMachine();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
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
		shlSlotMachine.setImage(SWTResourceManager.getImage("C:\\Users\\demianoleksandr\\Downloads\\Slot-machine-online-gratis-la-soluzione-egrave-a-portata-di-mano.png"));
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
				btnGenera.setEnabled(false);
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
		
		Button btnNewGame = new Button(shlSlotMachine, SWT.NONE);
		btnNewGame.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				text.setText("10");
				if(text.getText()==null){
					JOptionPane.showMessageDialog(null, "Per favore, inserisci credito");
				}
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
		text.setBounds(277, 306, 76, 21);
		formToolkit.adapt(text, true, true);
	}
	
	private void Gira(){
		titolo.setText("Best slot machine ever made by Gastaldo && Demian Oleksandr");
		GiraSlot g = new GiraSlot();
		g.start();
		num=text.getText();
		n=Integer.parseInt(num);
		n=n-1;
		num=String.valueOf(n);
		text.setText(num);
		if(n==0){
			JOptionPane.showMessageDialog(null, "Hai finito i soldi. GAME OVER!");
			btnGenera.setEnabled(false);
			text.setText("");
		}
	}
	
	private void generaSlot(int i){
		numeri[i] = (int) (Math.random()*6);
		slots[i].setImage(SWTResourceManager.getImage(SlotMachine.class, immagini.getImage(numeri[i])));
	}
	
	private void Controlla(){
		Display.getDefault().asyncExec(new Runnable(){
			public void run(){
				btnGenera.setEnabled(true);
				if(numeri[0] != numeri[1] && numeri[1] != numeri[2]){
					titolo.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
					titolo.setText("Niente combo, RIPROVA ;)");
				}
				if(numeri[0] == numeri[1] && numeri[1] == numeri[2]){
					titolo.setFont(SWTResourceManager.getFont("Segoe UI", 20, SWT.NORMAL));
					titolo.setText("VINCITA!!");
					if(numeri[0] == 0 && numeri[1] == 0 && numeri[2] == 0){
						vincita(3);
					}
					if(numeri[0] == 1 && numeri[1] == 1 && numeri[2] == 1){
						vincita(5);
					}
					if(numeri[0] == 2 && numeri[1] == 2 && numeri[2] == 2){
						vincita(6);
					}
					if(numeri[0] == 3 && numeri[1] == 3 && numeri[2] == 3){
						vincita(50);
					}
					if(numeri[0] == 4 && numeri[1] == 4 && numeri[2] == 4){
						vincita(10);
					}
					if(numeri[0] == 5 && numeri[1] == 5 && numeri[2] == 5){
						vincita(15);
					}
					if(numeri[0] == 6 && numeri[1] == 6 && numeri[2] == 6){
						vincita(20);
					}
					if(numeri[0] == 7 && numeri[1] == 7 && numeri[2] == 7){
						vincita(8);
					}
				}
			}
		});
	}
	
	private void vincita(int m){
		num=text.getText();
		n=Integer.parseInt(num);
		n=n+m;
		num=String.valueOf(n);
		text.setText(num);
	}
}
