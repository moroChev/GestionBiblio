package objet_Métier;

public class Utilities {

	public static final int MAX_MOUNTH = 12;
	public static final int MAX_DAY = 30;

	public static String[] DateTransformer(String a) {

		return a.split("-");
	}

	public static String DateIncrementer(String[] date, int n) {
		String result = null;

		int jour = Integer.parseInt(date[2]);
//		int jourPlus=0;
		int mois = Integer.parseInt(date[1]);
//		int moisPlus = 0;
		int annee = Integer.parseInt(date[0]);
//		int anneePlus=0;

		while (n > 0) {
			while (mois <= MAX_MOUNTH && n > 0) {
				while (jour < MAX_DAY && n > 0) {
					if (n > 0) {
						
						jour++;
						System.out.println("jour incrémenté");
						n--;
					} else
						break;
				}
				if (n > 0) {
					mois++;
					System.out.println("mois incrémenté");

					n--;
					jour = 1;
				} else
					break;
			}
			if (n > 0) {
				annee++;
				System.out.println("anne incrémenté");

				n--;
				jour = 1;
				mois = 1;
			} else
				break;
		}

		String StrAnnee, StrMois, StrJour;

		StrAnnee = String.valueOf(annee);
		StrMois = String.valueOf(mois);
		StrJour = String.valueOf(jour);

		if (annee < 1000) {
			StrAnnee = "0" + String.valueOf(annee);
		}
		if (mois < 10) {
			StrMois = "0" + String.valueOf(mois);
		}
		if (jour < 10) {
			StrJour = "0" + String.valueOf(jour);
		}

		result = StrAnnee + "-" + StrMois + "-" + StrJour;
		return result;
	}
}
