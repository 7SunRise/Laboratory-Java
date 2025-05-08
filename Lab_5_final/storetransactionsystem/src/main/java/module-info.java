module com.test.storetransactionsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.test.storetransactionsystem to javafx.fxml;
    exports com.test.storetransactionsystem;
}
