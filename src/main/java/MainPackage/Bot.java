package MainPackage;

import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;


public class Bot extends TelegramLongPollingBot {
	ReplyKeyboardMarkup replyKeyboardMarkup;

	public Bot() {
		initKeyboard();
	}

	MessageHandler messageHandler = new MessageHandler();

	@Override
	public String getBotUsername() {
		return "VentOffer_Bot";
	}

	public String getBotToken() {
		return "6074592220:AAG8dit8GJD-U2eLvjw-HMHmOrJuOuLGwWQ";
	}

	@Override
	@SneakyThrows
	public void onUpdateReceived(Update update) {
		long chatId = update.getMessage().getChatId();
		String text = update.getMessage().getText();
		if (update.hasMessage() && !text.isEmpty()) {
			String response = messageHandler.purposeOfThePremises(text);

			SendMessage sendMessage = new SendMessage();

			if (update.getMessage().getText().equals("/start")) {
				sendMessage.setChatId(chatId);
				sendMessage.setText(response);
				sendMessage.setReplyMarkup(replyKeyboardMarkup);
			} else {
				sendMessage.setChatId(chatId);
				sendMessage.setText(response);
			}
			execute(sendMessage);
		}
	}

	public void initKeyboard() {
		replyKeyboardMarkup = new ReplyKeyboardMarkup();
		replyKeyboardMarkup.setResizeKeyboard(true);
		replyKeyboardMarkup.setOneTimeKeyboard(true);

		ArrayList<KeyboardRow> keyboardRows = new ArrayList<>();

		KeyboardRow upKey = new KeyboardRow();
		KeyboardRow downKey = new KeyboardRow();

		keyboardRows.add(upKey);
		keyboardRows.add(downKey);

		downKey.add(new KeyboardButton("Административная часть"));
		upKey.add(new KeyboardButton("Промышленная часть"));


		replyKeyboardMarkup.setKeyboard(keyboardRows);
	}
}
