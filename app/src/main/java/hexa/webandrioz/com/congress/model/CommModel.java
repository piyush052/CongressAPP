package hexa.webandrioz.com.congress.model;

import java.util.Comparator;

/**
 * Created by gipsy_danger on 24/11/16.
 */

public class CommModel implements Comparable<BillsModel>{
    String commId,commName,commChamber,commSubComm,parentCommId,phone,office;

    public CommModel(String commId, String commName,
                     String commChamber, String commSubComm, String parentCommId, String phone,String office) {
        this.commId = commId;
        this.commName = commName;
        this.commChamber = commChamber;
        this.commSubComm = commSubComm;
        this.parentCommId = parentCommId;
        this.phone = phone;
        this.office=office;
    }
    public String getOffice() {
        return office;
    }

    public String getCommId() {
        return commId;
    }

    public String getCommName() {
        return commName;
    }

    public String getCommChamber() {
        return commChamber;
    }

    public String getCommSubComm() {
        return commSubComm;
    }

    public String getParentCommId() {
        return parentCommId;
    }

    public String getPhone() {
        return phone;
    }

    public void setCommId(String commId) {
        this.commId = commId;
    }

    public void setCommName(String commName) {
        this.commName = commName;
    }

    public void setCommChamber(String commChamber) {
        this.commChamber = commChamber;
    }

    public void setCommSubComm(String commSubComm) {
        this.commSubComm = commSubComm;
    }

    public void setParentCommId(String parentCommId) {
        this.parentCommId = parentCommId;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public static Comparator<CommModel> CommNameComparator = new Comparator<CommModel>() {

        public int compare(CommModel fruit1, CommModel fruit2) {

            String fruitName1 = fruit1.getCommName().toUpperCase();
            String fruitName2 = fruit2.getCommName().toUpperCase();

            //ascending order
            return fruitName1.compareTo(fruitName2);

            //descending order
            //return fruitName2.compareTo(fruitName1);
        }

    };

    @Override
    public int compareTo(BillsModel billsModel) {
        return 0;
    }
}
