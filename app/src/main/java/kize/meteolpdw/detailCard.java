package kize.meteolpdw;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mavillaz on 15/03/2016.
 */
public class detailCard implements Parcelable {
    public String nom;
    public String description;
    public String img;
    public Integer morning;
    public Integer afternoon;
    public Integer chance;

    public detailCard(String nom, String description, String img, Integer morning, Integer afternoon, Integer chance){
        this.nom = nom;
        this.description = description;
        this.img = img;
        this.morning = morning;
        this.afternoon = afternoon;
        this.chance = chance;
    }
    protected detailCard(Parcel in) {
        nom = in.readString();
        description = in.readString();
        img = in.readString();
        morning = in.readByte() == 0x00 ? null : in.readInt();
        afternoon = in.readByte() == 0x00 ? null : in.readInt();
        chance = in.readByte() == 0x00 ? null : in.readInt();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nom);
        dest.writeString(description);
        dest.writeString(img);
        if (morning == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(morning);
        }
        if (afternoon == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(afternoon);
        }
        if (chance == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(chance);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<detailCard> CREATOR = new Parcelable.Creator<detailCard>() {
        @Override
        public detailCard createFromParcel(Parcel in) {
            return new detailCard(in);
        }

        @Override
        public detailCard[] newArray(int size) {
            return new detailCard[size];
        }
    };
}
