package main.enums;

import lombok.Getter;

import java.util.Random;

@Deprecated
public enum SaveSlotEncripto {
	UM("ãsgihjaoiphpdsaiohgas4", 1),
	DOIS("aslçdgiu34892-tygyhaw9p9g", 2),
	TRES("asg241-8h2348hgwqagerf", 3),
	QUATRO("agser8-h0-3g4-w9egh8f", 4),
	CINCO("aag43h9h4g8efiuwah", 5),
	SEIS("afg-4hw908a4gh-4039", 6),
	SETE("asgrh-98hars98ahsr9-a8rsheg", 7),
	OITO("ag0s9erhg90aw8sgasrg", 8),
	NOVE("asg9ah8-ahg-89h3429-h423", 9);

	@Getter
	private final String encrypto;
	private final int numero;

	SaveSlotEncripto(String encrypto, int numero) {
		this.encrypto = encrypto;
		this.numero = numero;
	}
	public static SaveSlotEncripto fromInt(int i) {
		for (SaveSlotEncripto t :SaveSlotEncripto.values()) {
			if (t.numero == i) {
				return t;
			}
		}
		Random random = new Random();
		return SaveSlotEncripto.fromInt(random.nextInt(9));
	}
}
