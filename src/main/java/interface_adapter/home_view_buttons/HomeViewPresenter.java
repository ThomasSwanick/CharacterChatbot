package interface_adapter.home_view_buttons;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
import use_case.home_view_buttons.HomeViewOutputBoundary;

/**
 * The Presenter for the home screen Use Cases.
 */
public class HomeViewPresenter implements HomeViewOutputBoundary {

    private final SignupViewModel signupViewModel;
    private final LoginViewModel loginViewModel;
    private final ViewManagerModel viewManagerModel;
    private boolean switchToLogInViewCalled;
    private boolean switchToSignupViewCalled;

    public HomeViewPresenter(ViewManagerModel viewManagerModel,
                             SignupViewModel signupViewModel,
                             LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.signupViewModel = signupViewModel;
        this.loginViewModel = loginViewModel;
        this.switchToLogInViewCalled = false;
        this.switchToSignupViewCalled = false;
    }

    @Override
    public void switchToLoginView() {
        switchToLogInViewCalled = true;
        viewManagerModel.setState(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public boolean isSwitchToLoggedInViewCalled() {
        return switchToLogInViewCalled;
    }

    @Override
    public void switchToSignupView() {
        switchToSignupViewCalled = true;
        viewManagerModel.setState(signupViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public boolean isSwitchToSignupViewCalled() {
        return switchToSignupViewCalled;
    }
}
