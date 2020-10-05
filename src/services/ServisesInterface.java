package services;

import database.MySQLfunctions;

public interface ServisesInterface {

    void AddCredit(MySQLfunctions mySQLfunctions, Services services);
    void TakeCredit(MySQLfunctions mySQLfunctions, Services services);
    void ViewInfo();

}
