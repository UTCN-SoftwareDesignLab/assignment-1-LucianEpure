package database;

import static database.Constants.Tables.*;

/**
 * Created by Alex on 11/03/2017.
 */
public class SQLTableCreationFactory {

    public String getCreateSQLForTable(String table) {
        switch (table) {
            case CLIENT:
                return "CREATE TABLE IF NOT EXISTS client (" +
                        "  id int(11) NOT NULL AUTO_INCREMENT," +
                        "  name varchar(100) NOT NULL," +
                        "  address varchar(100) NOT NULL," +
                        "  cnp varchar(12) NOT NULL," +
                        "  cardIdNumber int(20) NOT NULL,"+
                        "  PRIMARY KEY (id)," +
                        "  UNIQUE INDEX cnp_UNIQUE (cnp ASC),"+
                        "  UNIQUE INDEX cardIdNumber_UNIQUE (cardIdNumber ASC));";
            

            case ACCOUNT:
                return "CREATE TABLE IF NOT EXISTS account (" +
                        "  id INT NOT NULL AUTO_INCREMENT," +
                        "  type VARCHAR(45) NOT NULL," +
                        "  sum DECIMAL(18,5) NOT NULL," +
                        "  date DATETIME NOT NULL,"+
                        "  client_id INT NOT NULL," +
                        "  PRIMARY KEY (id)," +
                        "  UNIQUE INDEX id_UNIQUE (id ASC),"+
                        "  CONSTRAINT client_id" +
                        "  FOREIGN KEY (client_id)" +
                        "    REFERENCES client (id)" +
                        "    ON DELETE CASCADE" +
                        "    ON UPDATE CASCADE);";
                
            case EMPLOYEE:
                return   "CREATE TABLE IF NOT EXISTS employee (" +
                        "  id INT NOT NULL AUTO_INCREMENT," +
                        "  username VARCHAR(100) NOT NULL," +
                        "  password VARCHAR(64) NOT NULL," +
                        "  id_role INT NOT NULL," +
                        "  PRIMARY KEY (id)," +
                        "  UNIQUE INDEX id_UNIQUE (id ASC)," +
                        "  UNIQUE INDEX username_UNIQUE (username ASC),"+
                        "  CONSTRAINT id_role" +
                        "  FOREIGN KEY (id_role)" +
                        "    REFERENCES role (id)" +
                        "    ON DELETE CASCADE" +
                        "    ON UPDATE CASCADE"+
                        ") ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;";
                


            case ROLE:
                return "  CREATE TABLE IF NOT EXISTS role (" +
                        "  id INT NOT NULL AUTO_INCREMENT," +
                        "  role VARCHAR(100) NOT NULL," +
                        "  PRIMARY KEY (id)," +
                        "  UNIQUE INDEX id_UNIQUE (id ASC)," +
                        "  UNIQUE INDEX role_UNIQUE (role ASC));";

            case RIGHTS:
                return "  CREATE TABLE IF NOT EXISTS `rights` (" +
                        "  `id` INT NOT NULL AUTO_INCREMENT," +
                        "  `rights` VARCHAR(100) NOT NULL," +
                        "  PRIMARY KEY (`id`)," +
                        "  UNIQUE INDEX `id_UNIQUE` (`id` ASC)," +
                        "  UNIQUE INDEX `rights_UNIQUE` (`rights` ASC));";

            case ROLE_RIGHT:
                return "  CREATE TABLE IF NOT EXISTS role_right (" +
                        "  id INT NOT NULL AUTO_INCREMENT," +
                        "  role_id INT NOT NULL," +
                        "  rights_id INT NOT NULL," +
                        "  PRIMARY KEY (id)," +
                        "  UNIQUE INDEX id_UNIQUE (id ASC)," +
                        "  INDEX role_id_idx (role_id ASC)," +
                        "  INDEX rights_id_idx (rights_id ASC)," +
                        "  CONSTRAINT role_id" +
                        "    FOREIGN KEY (role_id)" +
                        "    REFERENCES role (id)" +
                        "    ON DELETE CASCADE" +
                        "    ON UPDATE CASCADE," +
                        "  CONSTRAINT rights_id" +
                        "    FOREIGN KEY (rights_id)" +
                        "    REFERENCES `rights` (id)" +
                        "    ON DELETE CASCADE" +
                        "    ON UPDATE CASCADE);";

           default:
                return "";

        }
    }

}
