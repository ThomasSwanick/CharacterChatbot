package interface_adapter.chat.master_yoda;

import interface_adapter.chat.ChatViewModel;
import use_case.chat.master_yoda.MasterYodaOutputBoundary;
import use_case.chat.master_yoda.MasterYodaOutputData;

public class MasterYodaPresenter implements MasterYodaOutputBoundary {

    private final ChatViewModel chatViewModel;

    public MasterYodaPresenter(ChatViewModel chatViewModel) {
        this.chatViewModel = chatViewModel;
    }

    @Override
    public void beginChat(MasterYodaOutputData masterYodaOutputData) {
        final String username = masterYodaOutputData.getUsername();
        final String prompt = masterYodaOutputData.getPrompt();
        chatViewModel.setChatState(username, prompt);
        chatViewModel.firePropertyChanged("new chat");
    }
}
