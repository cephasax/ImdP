package br.ufrn.imd.view.fingerprint;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EnumMap;
import java.util.EnumSet;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import com.digitalpersona.onetouch.DPFPFingerIndex;
import com.digitalpersona.onetouch.DPFPTemplate;
import com.digitalpersona.onetouch.ui.swing.DPFPEnrollmentControl;
import com.digitalpersona.onetouch.ui.swing.DPFPEnrollmentEvent;
import com.digitalpersona.onetouch.ui.swing.DPFPEnrollmentListener;
import com.digitalpersona.onetouch.ui.swing.DPFPEnrollmentVetoException;

/**
 * Enrollment control test
 */
public class EnrollmentDialog extends JDialog {
	private static final long serialVersionUID = 2488751390726405707L;
	private EnumMap<DPFPFingerIndex, DPFPTemplate> templates;

	public EnrollmentDialog(Frame owner, int maxCount, final String reasonToFail,
			EnumMap<DPFPFingerIndex, DPFPTemplate> templates) {
		super(owner, true);
		this.templates = templates;

		setTitle("Fingerprint Enrollment");

		DPFPEnrollmentControl enrollmentControl = new DPFPEnrollmentControl();

		EnumSet<DPFPFingerIndex> fingers = EnumSet.noneOf(DPFPFingerIndex.class);
		fingers.addAll(templates.keySet());
		enrollmentControl.setEnrolledFingers(fingers);
		enrollmentControl.setMaxEnrollFingerCount(maxCount);

		enrollmentControl.addEnrollmentListener(new DPFPEnrollmentListener() {
			public void fingerDeleted(DPFPEnrollmentEvent e) throws DPFPEnrollmentVetoException {
				if (reasonToFail != null) {
					throw new DPFPEnrollmentVetoException(reasonToFail);
				} else {
					EnrollmentDialog.this.templates.remove(e.getFingerIndex());
				}
			}

			public void fingerEnrolled(DPFPEnrollmentEvent e) throws DPFPEnrollmentVetoException {
				if (reasonToFail != null) {
					// e.setStopCapture(false);
					throw new DPFPEnrollmentVetoException(reasonToFail);
				} else
					EnrollmentDialog.this.templates.put(e.getFingerIndex(), e.getTemplate());
			}
		});

		getContentPane().setLayout(new BorderLayout());

		JButton closeButton = new JButton("Close");
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false); // End Dialog
			}
		});

		JPanel bottom = new JPanel();
		bottom.add(closeButton);
		add(enrollmentControl, BorderLayout.CENTER);
		add(bottom, BorderLayout.PAGE_END);

		pack();
		setLocationRelativeTo(null);
	}
}