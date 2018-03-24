package database;

import java.util.*;

import static database.Constants.Rights.*;
import static database.Constants.Roles.*;


public class Constants {

    public static class Schemas {
        public static final String TEST = "test_bank";
        public static final String PRODUCTION = "bank";

        public static final String[] SCHEMAS = new String[]{TEST, PRODUCTION};
    }

    public static class Tables {
        public static final String ACCOUNT = "account";
        public static final String EMPLOYEE = "employee";
        public static final String CLIENT = "client";
        public static final String ROLE = "role";
        public static final String RIGHTS = "rights";
        public static final String ROLE_RIGHT = "role_right";
        public static final String EMPLOYEE_ROLE = "user_role";

        public static final String[] ORDERED_TABLES_FOR_CREATION = new String[]{ CLIENT, ROLE, RIGHTS, ROLE_RIGHT, ACCOUNT,EMPLOYEE};
    }

    public static class Roles {
        public static final String ADMINISTRATOR = "administrator";
        public static final String REGEMPLOYEE = "regEmployee";
        public static final String[] ROLES = new String[]{ADMINISTRATOR, REGEMPLOYEE};
    }

    public static class Rights {
        public static final String ADD_CLIENT = "add_client";
        public static final String UPDATE_CLIENT = "update_client";
        public static final String VIEW_CLIENT = "view_client";
        public static final String CREATE_ACCOUNT = "create_account";
        public static final String DELETE_ACCOUNT = "delete_account";
        public static final String UPDATE_ACCOUNT = "update_account";
        public static final String VIEW_ACCOUNT = "view_account";
        public static final String PROCESS_BILLS = "process_bills";
        public static final String COMPUTE_TRANSACTION = "compute_transaction";


        public static final String CREATE_EMPLOYEE = "create_employee";
        public static final String UPDATE_EMPLOYEE = "update_employee";
        public static final String REMOVE_EMPLOYEE = "remove_employee";
        public static final String VIEW_EMPLOYEE = "view_employee";
        public static final String GENERATE_REPORTS = "generate_reports";

        public static final String[] EMPLOYEE_RIGHTS = new String[]{  ADD_CLIENT, UPDATE_CLIENT,VIEW_CLIENT,CREATE_ACCOUNT, DELETE_ACCOUNT, UPDATE_ACCOUNT, VIEW_ACCOUNT,PROCESS_BILLS, COMPUTE_TRANSACTION};
        public static final String[] ADMINISTRATOR_RIGHTS = new String[]{CREATE_EMPLOYEE,UPDATE_EMPLOYEE,REMOVE_EMPLOYEE,VIEW_EMPLOYEE,GENERATE_REPORTS };
        public static final String[] RIGHTS = new String[]{  ADD_CLIENT, UPDATE_CLIENT,VIEW_CLIENT,CREATE_ACCOUNT, DELETE_ACCOUNT, UPDATE_ACCOUNT, VIEW_ACCOUNT,PROCESS_BILLS, COMPUTE_TRANSACTION,CREATE_EMPLOYEE,UPDATE_EMPLOYEE,REMOVE_EMPLOYEE,VIEW_EMPLOYEE,GENERATE_REPORTS };

    }

    public static Map<String, List<String>> getRolesRights() {
        Map<String, List<String>> ROLES_RIGHTS = new HashMap<>();
        for (String role : ROLES) {
            ROLES_RIGHTS.put(role, new ArrayList<>());
        }
        ROLES_RIGHTS.get(ADMINISTRATOR).addAll(Arrays.asList(ADMINISTRATOR_RIGHTS));

        ROLES_RIGHTS.get(REGEMPLOYEE).addAll(Arrays.asList(EMPLOYEE_RIGHTS));

       

        return ROLES_RIGHTS;
    }

}
