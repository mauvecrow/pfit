package quangson.bradley.pfit.transaction;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "transactions")
@NamedQueries({
    @NamedQuery(name = "ownerTrx",
            query = "select t from Transaction t where t.trxOwner = :owner"),
    @NamedQuery(name = "recentOwnerTrx",
            query = "select t from Transaction t where t.trxOwner = :owner and t.transactionDate >= :date")
})
public class Transaction implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trx_id")
    private int transactionId;

    @Column(name = "trx_date")
    private LocalDate transactionDate;

    @Column(name = "source")
    private String transactionSource;

    private String vendor;

    private double amount;

    private String notes;

    @Column(name = "trx_owner")
    private String trxOwner;

    // getter and setters
    public int getTransactionId() {
        return transactionId;
    }
    // no setter for id

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionSource() {
        return transactionSource;
    }

    public void setTransactionSource(String transactionSource) {
        this.transactionSource = transactionSource;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getTrxOwner() {
        return trxOwner;
    }

    public void setTrxOwner(String trxOwner) {
        this.trxOwner = trxOwner;
    }

    // equals, hashcode, and toString overrides

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;

        Transaction that = (Transaction) o;

        return transactionId == that.transactionId;
    }

    @Override
    public int hashCode() {
        return transactionId;
    }

    @Override
    public String toString() {
        return "Transactions{" +
                "transactionId=" + transactionId +
                ", transactionDate=" + transactionDate +
                ", transactionSource='" + transactionSource + '\'' +
                ", vendor='" + vendor + '\'' +
                ", amount=" + amount +
                ", notes='" + notes + '\'' +
                '}';
    }
}
