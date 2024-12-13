package com.adamo.dljava.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Incident {

    @SerializedName("id")
    private String id;

    @SerializedName("description")
    private String description;

    @SerializedName("date")
    private String date;

    @SerializedName("membreId")
    private String membreId;
}
