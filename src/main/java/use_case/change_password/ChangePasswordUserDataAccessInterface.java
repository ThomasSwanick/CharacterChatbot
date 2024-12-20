package use_case.change_password;

import entity.user.User;

/**
 * The interface of the DAO for the Change Password Use Case.
 */
public interface ChangePasswordUserDataAccessInterface {

    /**
     * Updates the system to record this user's password.
     * @param user the user whose password is to be updated
     */
    void changePassword(User user);

    void save(User user);

    User get(String username);
}
