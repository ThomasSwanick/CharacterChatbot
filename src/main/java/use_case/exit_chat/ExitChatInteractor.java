package use_case.exit_chat;

import entity.chat.Chat;
import entity.chat.ChatFactory;
import entity.message.Message;

import java.util.List;

public class ExitChatInteractor implements ExitChatInputBoundary {
    private final ExitChatOutputBoundary exitChatPresenter;
    private final SaveChatHistoryUserDataAccessInterface chatHistoryAccess;
    private final ChatFactory chatFactory;
    private Chat currentChat;

    public ExitChatInteractor(ExitChatOutputBoundary exitChatPresenter,
                              SaveChatHistoryUserDataAccessInterface chatHistoryAccess,
                              ChatFactory chatFactory) {
        this.exitChatPresenter = exitChatPresenter;
        this.chatHistoryAccess = chatHistoryAccess;
        this.chatFactory = chatFactory;
    }

    @Override
    public void execute(ExitChatInputData exitChatInputData) {

        // Get the current user from the input data.
        final String username = exitChatInputData.getUsername();

        // Get all the user inputs and bot responses from the current chat.
        final List<Message> lst = currentChat.getUserInputs();
        final List<Message> lst2 = currentChat.getBotResponses();

        // Save this chat (bot responses and user inputs) to the DB.
        for (int i = 0; i < lst.size(); i++) {
            chatHistoryAccess.saveHistory(username, lst.get(i).getContent());
            chatHistoryAccess.saveHistory("assistant", lst2.get(i).getContent());
            System.out.println(lst.get(i).getContent());
            System.out.println(lst2.get(i).getContent());
        }
        System.out.println(username);

        // Tell the presenter to update the views so that the chat "exits".
        final boolean endChat = true;
        final ExitChatOutputData exitChatOutputData = new ExitChatOutputData(endChat);
        exitChatPresenter.endChat(exitChatOutputData);
    }

    @Override
    public void newChat(String username) {
        chatHistoryAccess.setUp(username);
        currentChat = chatFactory.create();
    }

    @Override
    public void saveGreeting(String user, String initialResponse) {
        chatHistoryAccess.saveGreeting(user, initialResponse);
    }

    @Override
    public void saveHistory(String sender, String message) {
        chatHistoryAccess.saveHistory(sender, message);
    }

    @Override
    public void addBotResponse(String botResponse) {
        currentChat.addBotResponse(botResponse);
    }

    @Override
    public void addUserInput(String userInput) {
        currentChat.addUserInput(userInput);
    }
}