package interface_adapter.change_password;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import use_case.change_password.ChangePasswordOutputBoundary;
import use_case.change_password.ChangePasswordOutputData;

/**
 * The Presenter for the Change Password Use Case.
 */
public class ChangePasswordPresenter implements ChangePasswordOutputBoundary {

    private final LoggedInViewModel loggedInViewModel;
    private final ChangePasswordViewModel changePasswordViewModel;
    private final ViewManagerModel viewManagerModel;

    public ChangePasswordPresenter(LoggedInViewModel loggedInViewModel,
                                   ChangePasswordViewModel changePasswordViewModel,
                                   ViewManagerModel viewManagerModel) {
        this.loggedInViewModel = loggedInViewModel;
        this.changePasswordViewModel = changePasswordViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(ChangePasswordOutputData outputData) {
        // currently there isn't anything to change based on the output data,
        // since the output data only contains the username, which remains the same.
        // We still fire the property changed event, but just to let the view know that
        // it can alert the user that their password was changed successfully..
        // loggedInViewModel.firePropertyChanged("password");
        changePasswordViewModel.firePropertyChanged("password");
    }

    @Override
    public void prepareFailView(String error) {
        final ChangePasswordState changePasswordState = changePasswordViewModel.getState();
        changePasswordState.setRepeatPasswordError(error);
        changePasswordViewModel.firePropertyChanged("error");
    }

    @Override
    public void switchToLoggedInView() {
        final ChangePasswordState changePasswordState = changePasswordViewModel.getState();
        changePasswordState.setRepeatPasswordError(null);
        changePasswordState.setUsername("");
        changePasswordState.setPassword("");
        changePasswordState.setRepeatPassword("");
        changePasswordViewModel.setState(changePasswordState);
        changePasswordViewModel.firePropertyChanged();

        viewManagerModel.setState(loggedInViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

}
