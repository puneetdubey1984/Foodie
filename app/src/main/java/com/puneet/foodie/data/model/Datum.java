package com.puneet.foodie.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Datum implements Parcelable {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("aisle")
    @Expose
    private String aisle;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("guid")
    @Expose
    private String guid;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("index")
    @Expose
    private Integer index;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("salePrice")
    @Expose
    private String salePrice;
    @SerializedName("title")
    @Expose
    private String title;

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString( this.id );
        dest.writeString( this.aisle );
        dest.writeString( this.description );
        dest.writeString( this.guid );
        dest.writeString( this.image );
        dest.writeValue( this.index );
        dest.writeString( this.price );
        dest.writeString( this.salePrice );
        dest.writeString( this.title );
    }

    protected Datum(Parcel in) {
        this.id = in.readString( );
        this.aisle = in.readString( );
        this.description = in.readString( );
        this.guid = in.readString( );
        this.image = in.readString( );
        this.index = ( Integer ) in.readValue( Integer.class.getClassLoader( ) );
        this.price = in.readString( );
        this.salePrice = in.readString( );
        this.title = in.readString( );
    }

    public static final Creator< Datum > CREATOR = new Creator< Datum >( ) {
        @Override
        public Datum createFromParcel(Parcel source) {return new Datum( source );}

        @Override
        public Datum[] newArray(int size) {return new Datum[ size ];}
    };
}
