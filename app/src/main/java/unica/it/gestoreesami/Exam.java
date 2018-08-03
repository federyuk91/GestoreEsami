package unica.it.gestoreesami;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Exam implements Parcelable{
    private String examName = "NoNameFound";
    private int pageNumber;
    private Date examDate=new Date();

    public Exam(String examName, long examDate, Integer pageNumber){
        this.examDate=new Date(examDate);
        this.examName=examName;
        this.pageNumber=pageNumber;
    }

    public Exam(String examName, int pageNumber){
        this.examName = examName;
        this.pageNumber= pageNumber;
        this.examDate = new Date();
    }





    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public Date getExamDate() {
        return examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    //Memorizzo gli elementi della mia classe in un Parcel, in questo modo potrò ricostruirla
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(examName);
        parcel.writeInt(pageNumber);
    }

    public static final Parcelable.Creator<Exam> CREATOR
            = new Parcelable.Creator<Exam>() {
        public Exam createFromParcel(Parcel in) {
            return new Exam(in);
        }

        public Exam[] newArray(int size) {
            return new Exam[size];
        }
    };


    private Exam(Parcel in) {
        examName = in.readString();
        pageNumber = in.readInt();
    }
}
