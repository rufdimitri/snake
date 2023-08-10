package rd.pruefung.coden_08_pruefziffer;

public class Main {

	static boolean pruefeId(String cardId) {
		int querSumGesamt = 0;
		int geradeSum = 0;
		int letzteZiffer = Character.getNumericValue(cardId.charAt(cardId.length() - 1));
		for (int i = 0; i < cardId.length() - 1; i++) {
//			int ziffer = Integer.parseInt(id.substring(i, i + 1)); //Alternative Lösung to convert char to int
			int ziffer = Character.getNumericValue(cardId.charAt(i));
			boolean istGeradeStelle = (i + 1) % 2 == 0;
			if (!istGeradeStelle) {
				int zifferX2 = ziffer * 2;
				int querSum;
				if (zifferX2 / 10 >= 1) {
					querSum = 1 + zifferX2 % 10;
				} else {
					querSum = zifferX2 % 10;
				}
				querSumGesamt += querSum;
				System.out.format("ziffer = %d, zifferX2 = %d, querSum = %d \n", ziffer, zifferX2, querSum);
			} else {
				geradeSum += ziffer;
				System.out.format("ziffer = %d \n", ziffer);
			}
		}
		System.out.format("querSumGesamt = %d, geradeSum = %d \n", querSumGesamt, geradeSum);
		int alleSummen = querSumGesamt + geradeSum;
		int diff = (10 - alleSummen % 10) % 10;
		int gerundet = alleSummen + diff;
		System.out.format("alleSummen %d, gerundet = %d \n", alleSummen, gerundet);
		return (diff == letzteZiffer);
	}

	public static void main(String[] args) {
		boolean cardValid;
		String cardId;

		cardId = "12345";
		cardValid = pruefeId(cardId);
		System.out.format("cardId = %s, cardValid = %b \n\n", cardId, cardValid);

		cardId = "12346";
		cardValid = pruefeId(cardId);
		System.out.format("cardId = %s, cardValid = %b \n\n", cardId, cardValid);

		cardId = "6258431979";
		cardValid = pruefeId(cardId);
		System.out.format("cardId = %s, cardValid = %b \n\n", cardId, cardValid);
	}

}
