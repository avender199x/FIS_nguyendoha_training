package Mini_Exercises_1_2;

import java.util.Date;

public class transaction {
    private String Transaction_Type;
    private String Bank_Account;
    private Double Amount;
    private String Message;
    private Date DateTime;

    public transaction(String transaction_Type, String bank_Account, Double amount, String message, Date dateTime) {
        Transaction_Type = transaction_Type;
        Bank_Account = bank_Account;
        Amount = amount;
        Message = message;
        DateTime = dateTime;
    }

    public transaction() {
    }

    public String getTransaction_Type() {
        return Transaction_Type;
    }

    public void setTransaction_Type(String transaction_Type) {
        Transaction_Type = transaction_Type;
    }

    public String getBank_Account() {
        return Bank_Account;
    }

    public void setBank_Account(String bank_Account) {
        Bank_Account = bank_Account;
    }

    public Double getAmount() {
        return Amount;
    }

    public void setAmount(Double amount) {
        Amount = amount;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public Date getDateTime() {
        return DateTime;
    }

    public void setDateTime(Date dateTime) {
        DateTime = dateTime;
    }

    @Override
    public String toString() {
        return "transaction{" +
                "Transaction_Type='" + Transaction_Type + '\'' +
                ", Bank_Account='" + Bank_Account + '\'' +
                ", Amount=" + Amount +
                ", Message='" + Message + '\'' +
                ", DateTime='" + DateTime + '\'' +
                '}';
    }
}
