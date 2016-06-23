package br.ufrn.imd.view.fingerprint;

import javax.swing.SwingUtilities;

import com.digitalpersona.onetouch.DPFPGlobal;
import com.digitalpersona.onetouch.DPFPTemplate;
import com.digitalpersona.onetouch.capture.DPFPCapture;
import com.digitalpersona.onetouch.capture.DPFPCapturePriority;
import com.digitalpersona.onetouch.capture.event.DPFPDataEvent;
import com.digitalpersona.onetouch.capture.event.DPFPDataListener;
import com.digitalpersona.onetouch.readers.DPFPReadersCollection;

public class Teste2 {

	public static void main(String[] args) {
		try {
			// lista de leitores instalados na maquina
			DPFPReadersCollection readers = DPFPGlobal.getReadersFactory().getReaders();

			// inicializa o objeto capture
			DPFPCapture capturer = DPFPGlobal.getCaptureFactory().createCapture();
			capturer.setReaderSerialNumber(readers.get(0).getSerialNumber());
			capturer.setPriority(DPFPCapturePriority.CAPTURE_PRIORITY_LOW);

			// adiciona um listener para observer ao capturar uma digital
			capturer.addDataListener(new DPFPDataListener() {
				public void dataAcquired(final DPFPDataEvent e) {
					if (e != null && e.getSample() != null) {
						SwingUtilities.invokeLater(new Runnable() {
							public void run() {
//								dispareAoCapturar(e.getSample());
								Util.bytesToHex(e.getSample().serialize());
							}
						});
					}
				}
			});
			capturer.startCapture();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
