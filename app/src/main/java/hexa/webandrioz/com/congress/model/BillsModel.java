package hexa.webandrioz.com.congress.model;

import java.util.Comparator;

/**
 * Created by gipsy_danger on 24/11/16.
 */

public class BillsModel implements Comparable<BillsModel> {
    String billId,title,billType,sponsers,chamber,status,introducedOn,CongressUrl,versionStatus,billUrl;

    public BillsModel(String billId, String title, String billType, String sponsers, String chamber, String status, String introducedOn, String congressUrl, String versionStatus, String billUrl) {
        this.billId = billId;
        this.title = title;
        this.billType = billType;
        this.sponsers = sponsers;
        this.chamber = chamber;
        this.status = status;
        this.introducedOn = introducedOn;
        CongressUrl = congressUrl;
        this.versionStatus = versionStatus;
        this.billUrl = billUrl;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public String getSponsers() {
        return sponsers;
    }

    public void setSponsers(String sponsers) {
        this.sponsers = sponsers;
    }

    public String getChamber() {
        return chamber;
    }

    public void setChamber(String chamber) {
        this.chamber = chamber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIntroducedOn() {
        return introducedOn;
    }

    public void setIntroducedOn(String introducedOn) {
        this.introducedOn = introducedOn;
    }

    public String getCongressUrl() {
        return CongressUrl;
    }

    public void setCongressUrl(String congressUrl) {
        CongressUrl = congressUrl;
    }

    public String getVersionStatus() {
        return versionStatus;
    }

    public void setVersionStatus(String versionStatus) {
        this.versionStatus = versionStatus;
    }

    public String getBillUrl() {
        return billUrl;
    }

    public void setBillUrl(String billUrl) {
        this.billUrl = billUrl;
    }

    @Override
    public int compareTo(BillsModel billsModel) {
        String currBillId=billsModel.getBillId();
        return 0;
    }
    public static Comparator<BillsModel> FruitNameComparator = new Comparator<BillsModel>() {

        public int compare(BillsModel fruit1, BillsModel fruit2) {

            String fruitName1 = fruit1.getIntroducedOn().toUpperCase();
            String fruitName2 = fruit2.getIntroducedOn().toUpperCase();

            //ascending order
//            return fruitName1.compareTo(fruitName2);

            //descending order
            return fruitName2.compareTo(fruitName1);
        }

    };
}
