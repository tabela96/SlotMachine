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

public class SlotMachine {

	protected Shell shlSlotMachine;
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private Label[] slots = new Label[3];
	private ImagesContainer immagini = new ImagesContainer();
	private int[] numeri = new int[3];
	private Label titolo;

	/**
	 * Launch the application.
	 * @param args
	 */
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
		
		slots[0] = formToolkit.createLabel(shlSlotMachine, "New Label", SWT.NONE);
		slots[0].setBounds(74, 100, 150, 150);
		slots[0].setImage(SWTResourceManager.getImage(SlotMachine.class, "/Immagini/anguria.PNG"));
		
		slots[1] = formToolkit.createLabel(shlSlotMachine, "New Label", SWT.NONE);
		slots[1].setBounds(225, 100, 150, 150);
		slots[1].setImage(SWTResourceManager.getImage(SlotMachine.class, "/Immagini/uva.PNG"));
		
		slots[2] = formToolkit.createLabel(shlSlotMachine, "New Label", SWT.NONE);
		slots[2].setBounds(376, 100, 150, 150);
		slots[2].setImage(SWTResourceManager.getImage(SlotMachine.class, "/Immagini/bar.PNG"));
		
		Button btnGenera = new Button(shlSlotMachine, SWT.NONE);
		btnGenera.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				generaSlot();
			}
		});
		btnGenera.setBounds(10, 282, 561, 68);
		formToolkit.adapt(btnGenera, true, true);
		btnGenera.setText("GIOCA!");		
		
		titolo = new Label(shlSlotMachine, SWT.NONE);
		titolo.setAlignment(SWT.CENTER);
		titolo.setBounds(4, 4, 567, 68);
		titolo.setText("Best slot machine ever made by Gastaldo && Demian Oleksandr");
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
			titolo.setText("Hai stra perso, perdente");
			titolo.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		}
		if(numeri[0] == numeri[1] && numeri[1] == numeri[2]){
			titolo.setText("HAI MEGA VINTO, PERDENTE");
			titolo.setFont(SWTResourceManager.getFont("Segoe UI", 18, SWT.NORMAL));
		}
	}
}
