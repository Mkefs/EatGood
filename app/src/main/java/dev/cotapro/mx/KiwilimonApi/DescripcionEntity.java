package dev.cotapro.mx.KiwilimonApi;

import com.google.gson.annotations.SerializedName;

public class DescripcionEntity {
	@SerializedName("k")
	public String key;
	@SerializedName("cn")
	public String chef;
	@SerializedName("i")
	public String image;
	@SerializedName("n")
	public String name;
	@SerializedName("vr")
	public String rating;
	@SerializedName("t")
	public String type;
}
