package validators;

import model.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Alex on 18/03/2017.
 */
public class EmployeeValidator {
    private static final String EMAIL_VALIDATION_REGEX = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    private static final int MIN_PASSWORD_LENGTH = 8;

    private final Employee employee;

    

    private final List<String> errors;

    public List<String> getErrors() {
        return errors;
    }
    
    public EmployeeValidator(Employee employee) {
        this.employee = employee;
        errors = new ArrayList<>();
    }

    public boolean validate() {
        validateUsername(employee.getUsername());
        validatePassword(employee.getPassword());
        return errors.isEmpty();
    }

    private void validateUsername(String username) {
        if (!Pattern.compile(EMAIL_VALIDATION_REGEX).matcher(username).matches()) {
            errors.add("Invalid email!");
        }
    }

    private void validatePassword(String password) {
        if (password.length() < MIN_PASSWORD_LENGTH) {
            errors.add("Password too short!");
        }
        if (!containsSpecialCharacter(password)) {
            errors.add("Password must contain at least one special character!");
        }
        if (!containsDigit(password)) {
            errors.add("Password must contain at least one number!");
        }
    }

    private boolean containsSpecialCharacter(String s) {
        if (s == null || s.trim().isEmpty()) {
            return false;
        }
        Pattern p = Pattern.compile("[^A-Za-z0-9]");
        Matcher m = p.matcher(s);
        return m.find();
    }

    private static boolean containsDigit(String s) {
        if (s != null && !s.isEmpty()) {
            for (char c : s.toCharArray()) {
                if (Character.isDigit(c)) {
                    return true;
                }
            }
        }
        return false;
    }


}
