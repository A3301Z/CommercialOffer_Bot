package MainPackage;

public class MessageHandler {
	CostOfEquipment costOfEquipment = new CostOfEquipment();
	boolean offerIsReady = false;
	boolean isAdministrative = false;
	boolean isIndustrial = false;
	boolean hIsHere = false;
	boolean totalAreaIsHere = false;
	boolean lIsHere = false;
	double h = 0;
	double totalArea = 0;
	double l = 0;

	public String purposeOfThePremises(String text) {
		String response;

		if (text.contains("/start")) {
			response = """
					🟢Все дальнейшие величины указывать строго в метрах🟢.
					Выберите тип помещения:\s
					""";
		} else if (text.equalsIgnoreCase("/Administration")
				&& !offerIsReady && !isNumber(text)
				|| text.equalsIgnoreCase("Административная часть")
				&& !offerIsReady && !isNumber(text)) {
			isAdministrative = true;
			response = "Укажите высоту до потолка.";
		} else if (text.equalsIgnoreCase("/Industrial")
				&& !offerIsReady && !isNumber(text)
				|| text.equalsIgnoreCase("Промышленная часть")
				&& !offerIsReady && !isNumber(text)) {
			isIndustrial = true;
			response = "Укажите высоту до потолка.";
		} else if (isNumber(text) && !totalAreaIsHere && !lIsHere
				&& !hIsHere && !offerIsReady && isAdministrative
				|| isNumber(text) && !totalAreaIsHere && !lIsHere
				&& !hIsHere && !offerIsReady && isIndustrial) {
			if (text.contains(",")) {
				h = Double.parseDouble((text.replace(",", ".")));
			} else {
				h = Double.parseDouble(text);
			}
			hIsHere = true;
			response = "Укажите общую площадь помещения.";
		} else if (h > 0 && !totalAreaIsHere && hIsHere && !offerIsReady && isNumber(text) && !lIsHere) {

			if (text.contains(",")) {
				totalArea = Double.parseDouble((text.replace(",", ".")));
			} else {
				totalArea = Double.parseDouble(text);
			}
			totalAreaIsHere = true;
			response = "Укажите самый длинный укчасток помещения.";
		} else if (h > 0 && totalAreaIsHere && hIsHere && isNumber(text)
				&& !offerIsReady && !lIsHere && totalArea > 0) {
			if (text.contains(",")) {
				l = Double.parseDouble((text.replace(",", ".")));
			} else {
				l = Double.parseDouble(text);
			}
			lIsHere = true;
			offerIsReady = true;
			response = "Коммерческое предложение готово. ➡️/download⬅️";
		} else if (text.equalsIgnoreCase("/download") && offerIsReady
				&& isIndustrial && hIsHere && totalAreaIsHere && lIsHere
				&& l > 0 && totalArea > 0 && h > 0) {
			response = "Ваше КП для административной части сформировано. " + "Объем воздуха в помещении = " + airVolume() + ". " + "Стоимость услуг составит " + costOfEquipment.returnOfPrice(airVolume());
		} else if (text.equalsIgnoreCase("/download") && offerIsReady
				&& isAdministrative && hIsHere && totalAreaIsHere
				&& lIsHere && l > 0 && totalArea > 0 && h > 0) {
			response = "Ваше КП для промышленной части сформировано. " + "Объем воздуха в помещении = " + airVolume() + ". " + "Стоимость услуг составит " + costOfEquipment.returnOfPrice(airVolume());
		} else {
			response = """
					❌ Чтобы всё получилось, следуйте моим рекомендациям.
					Нажмите ➡️/start⬅️ и начните заново.""";
			offerIsReady = false;
			isAdministrative = false;
			isIndustrial = false;
			hIsHere = false;
			totalAreaIsHere = false;
			lIsHere = false;
			h = 0;
			totalArea = 0;
			l = 0;
		} if (isAdministrative && isIndustrial) {
			isIndustrial = false;
			isAdministrative = false;
			hIsHere = false;
			lIsHere = false;
			totalAreaIsHere = false;
			l = 0;
			h = 0;
			totalArea = 0;
			response = "\uD83D\uDEAB От выбранного типа помещения зависит конечная цена. Выберите ОДНО направление. \uD83D\uDEAB";
		}
		return response;
	}

	private boolean isNumber(String s) throws NumberFormatException { // Число или нет
		try {
			if (s.contains(",")) {
				Double.parseDouble(s.replace(",", "."));
			} else {
				Double.parseDouble(s);
			}
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private int airVolume() { // Формула объема воздуха в помещении
		return (int) Math.ceil((int) totalArea * h);
	}
}
