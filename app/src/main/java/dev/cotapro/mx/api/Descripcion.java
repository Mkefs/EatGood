package dev.cotapro.mx.api;

import com.google.gson.annotations.SerializedName;

public class Descripcion {
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
}
