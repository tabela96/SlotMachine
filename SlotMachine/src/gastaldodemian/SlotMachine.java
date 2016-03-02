package gastaldodemian;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.util.Random;

import javax.swing.ImageIcon;

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
			while(t < 303){
				for(int i = 0; i < 3; i++){
					slots[i].setBounds(slots[i].getBounds().x, slots[i].getBounds().y + 1, 150, 150);
					System.out.println(i + " " + slots[i].getBounds());
					if(slots[i].getBounds().y > 151){
						generaSlot();
						slots[i].setBounds(slots[i].getBounds().x, -151, 150, 150);
					}
				}
				try {
					this.sleep(1);
					t++;
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
	private int[] numeri = new int[3];
	private Label titolo;
	private Text text;
	
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
		shlSlotMachine.setSize(600, 400);
		shlSlotMachine.setText("Slot Machine");
		
		Composite composite = new Composite(shlSlotMachine, SWT.NONE);
		composite.setBounds(11, 71, 560, 152);
		formToolkit.adapt(composite);
		formToolkit.paintBordersFor(composite);
		
		slots[0] = formToolkit.createLabel(composite, "New Label", SWT.NONE);
		slots[0].setBounds(10, 1, 150, 150);
		slots[0].setImage(SWTResourceManager.getImage(SlotMachine.class, "/Immagini/anguria.PNG"));
		
		slots[1] = formToolkit.createLabel(composite, "New Label", SWT.NONE);
		slots[1].setBounds(205, 1, 150, 150);
		slots[1].setImage(SWTResourceManager.getImage(SlotMachine.class, "/Immagini/uva.PNG"));
		
		slots[2] = formToolkit.createLabel(composite, "New Label", SWT.NONE);
		
		Label label = new Label(composite, SWT.SEPARATOR | SWT.VERTICAL);
		label.setBounds(191, 0, 0, 152);
		formToolkit.adapt(label, true, true);
		slots[2].setBounds(400, 1, 150, 150);
		slots[2].setImage(SWTResourceManager.getImage(SlotMachine.class, "/Immagini/bar.PNG"));

		
		Button btnGenera = new Button(shlSlotMachine, SWT.NONE);
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
		
		Button btnNewGame = new Button(shlSlotMachine, SWT.NONE);
		btnNewGame.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
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
		text.setBounds(277, 306, 76, 21);
		formToolkit.adapt(text, true, true);
	}
	
	private void Gira(){
		GiraSlot g = new GiraSlot();
		g.run();
	}
	
	private void generaSlot(){
		for(int i=0;i<3;i++){
			numeri[i] = (int) (Math.random()*8);
			slots[i].setImage(SWTResourceManager.getImage(SlotMachine.class, immagini.getImage(numeri[i])));
		}
		Controlla();
	}
	
	private void Controlla(){
		if(numeri[0] != numeri[1] && numeri[1] != numeri[2]){
			titolo.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
			titolo.setText("Hai stra perso, perdente");
		}
		if(numeri[0] == numeri[1] && numeri[1] == numeri[2]){
			titolo.setFont(SWTResourceManager.getFont("Segoe UI", 20, SWT.NORMAL));
			titolo.setText("HAI MEGA VINTO, PERDENTE");
		}
	}
}
