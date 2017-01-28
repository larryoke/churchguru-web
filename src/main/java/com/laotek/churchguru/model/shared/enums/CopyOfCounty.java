package com.laotek.churchguru.model.shared.enums;

import java.io.Serializable;

public enum CopyOfCounty implements Serializable{
	BEDFORDSHIRE("Bedfordshire"), 
	BECKSHIRE("Berkshire"), 
	BUCKINGHAMSHIRE("Buckinghamshire"),
	CAMBRIDGESHIRE("Cambridgeshire"),
	CHESHIRE("Cheshire"),
	CLEVELAND("Cleveland"),
	CORNWALL("Cornwall"),
	COUNTY_DURHAM("County Durham"),
	CUMBRIA("Cumbria"),
	DERBYSHIRE("Derbyshire"),
	DEVON("Devon"),
	DORSET("Dorset"),
	EAST_RIDING_OF_YORKSHIRE("East Riding of Yorkshire"),
	EAST_SUSSEX("East Sussex"),
	ESSEX("Essex"),
	GLOUCESTERSHIRE("Gloucestershire"),
	GREATER_LONDON("Greater London"),
	GREATER_MANCHESTER("Greater Manchester"),
	HAMPSHIRE("Hampshire"),
	HAEREFORDSHIRE("Herefordshire"),
	HERTFORDSHIRE("Hertfordshire"),
	ISLE_OF_WIGHT("Isle Of Wight"),
	ISLE_OF_SCILLY("Isle Of Scilly"),
	KENT("Kent"),
	LANCASHIRE("Lancashire"),
	LEICESTERSHIRE("Leicestershire"),
	LINCOLNSHIRE("Lincolnshire"),
	NORFOLK("Norfolk"),
	NORTH_YORKSHIRE("North Yorkshire"),
	NORTHAMPTONSHIRE("Northamptonshire"),
	NORTHUMBERLAND("Northumberland"),
	NOTTINGHAMSHIRE("Nottinghamshire"),
	OXFORD("Oxfordshire"),
	RUTHLAND("Ruthland"),
	SHROPSHIRE("Shropshire"),
	SOMERSET("Somerset"),
	SOUTH_YORKSHIRE("South Yorkshire"),
	STAFFORDSHIRE("Staffordshire"),
	SUFFOLK("Suffold"),
	SURREY("Surrey"),
	TYNE_AND_WEAR("Tyne & Wear"),
	WARWICKSHIRE("Warwickshire"),
	WEST_MIDLANDS("West Midlands"),
	WEST_SUSSEX("West Yorkshire"),
	WEST_YORKSHIRE("West Yorkshire"),
	WILTSHIRE("Wiltshire"),
	WORCESTERSHIRE("Worcestershire");
	
	private String desc;
	
	private CopyOfCounty(String desc){
		this.desc = desc;		
	}
	
	public String getDesc(){
		return desc;
	}
	
	public static CopyOfCounty find(String desc){
		for (CopyOfCounty county : CopyOfCounty.values()){
			if (county.getDesc().equals(desc)){
				return county;
			}
		}
		return null;
	}
}
