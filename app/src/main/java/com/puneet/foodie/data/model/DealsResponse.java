package com.puneet.foodie.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class DealsResponse implements Parcelable {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    @SerializedName("type")
    @Expose
    private String type;

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString( this.id );
        dest.writeTypedList( this.data );
        dest.writeString( this.type );
    }

    protected DealsResponse(Parcel in) {
        this.id = in.readString( );
        this.data = in.createTypedArrayList( Datum.CREATOR );
        this.type = in.readString( );
    }

    public static final Creator< DealsResponse > CREATOR = new Creator<
            DealsResponse >( ) {
        @Override
        public DealsResponse createFromParcel(Parcel source) {return new DealsResponse( source );}

        @Override
        public DealsResponse[] newArray(int size) {return new DealsResponse[ size ];}
    };
}
