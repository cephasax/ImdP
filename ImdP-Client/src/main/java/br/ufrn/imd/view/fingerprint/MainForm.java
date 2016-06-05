package br.ufrn.imd.view.fingerprint;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.EnumMap;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

import com.digitalpersona.onetouch.DPFPFingerIndex;
import com.digitalpersona.onetouch.DPFPGlobal;
import com.digitalpersona.onetouch.DPFPTemplate;
import com.digitalpersona.onetouch.verification.DPFPVerification;

/**
 * Enrollment control test console
 */
public class MainForm extends JFrame {
	private EnumMap<DPFPFingerIndex, DPFPTemplate> templates = new EnumMap<DPFPFingerIndex, DPFPTemplate>(
			DPFPFingerIndex.class);
	private EnumMap<DPFPFingerIndex, JCheckBox> checkBoxes = new EnumMap<DPFPFingerIndex, JCheckBox>(
			DPFPFingerIndex.class);

	private static final DPFPTemplate fakeTemplate;
	private SpinnerNumberModel maxCount = new SpinnerNumberModel(DPFPFingerIndex.values().length, 0,
			DPFPFingerIndex.values().length, 1);
	private final JButton enrollButton = new JButton("Enroll Fingerprints");
	private final JButton verifyButton = new JButton("Verify Fingerprint");
	private SpinnerNumberModel farRequested = new SpinnerNumberModel(DPFPVerification.MEDIUM_SECURITY_FAR, 1,
			DPFPVerification.PROBABILITY_ONE, 100);
	private JSpinner farRequestedSpinner;
	private JTextField farAchieved;
	JCheckBox fingerMatched;

	static {
		fakeTemplate = DPFPGlobal.getTemplateFactory().createTemplate();
		try {
			fakeTemplate.deserialize(new byte[0]);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

	
	
	public void clickVerificar(){
		try {
			VerificationDialog dlg = new VerificationDialog(MainForm.this, templates,
					farRequested.getNumber().intValue());
			dlg.addPropertyChangeListener(new PropertyChangeListener() {
				public void propertyChange(final PropertyChangeEvent e) {
					String name = e.getPropertyName();
					if (VerificationDialog.FAR_PROPERTY.equals(name)) {
						farAchieved.setText("" + (Integer) e.getNewValue());
					} else if (VerificationDialog.MATCHED_PROPERTY.equals(name)) {
						fingerMatched.setSelected((Boolean) e.getNewValue());
					}
				}
			});
			dlg.setVisible(true);
		} catch (Exception ex) {
			farRequestedSpinner.requestFocusInWindow();
		}
	}

	public void clickCadastrarDigital(){
		new EnrollmentDialog(MainForm.this, maxCount.getNumber().intValue(), null, templates).setVisible(true);
		UpdateUI();
		enrollButton.setAlignmentX(CENTER_ALIGNMENT);
	}

	private void UpdateUI() {
		// update enrolled fingers checkboxes
		for (DPFPFingerIndex finger : DPFPFingerIndex.values())
		verifyButton.setEnabled(!templates.isEmpty());
	}
	
	public static void main(String... args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainForm();
            }
        });
    }
}
