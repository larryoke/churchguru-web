package com.laotek.churchguru.model.shared.enums;

import java.io.Serializable;

public enum Country implements Serializable {
    UNITED_KINGDOM("United Kingdom"),

    AFGHANISTAN("Afghanistan"),

    ALBANIA("Albania"),

    ALGERIA("Algeria"),

    ANDORA("Andorra"),

    ANGOLA("Angola"),

    ANTIGUA_AND_BARBUDA("Antigua and Barbuda"),

    ARGENTINA("Argentina"),

    AUSTRALIA("Australia"),

    AUSTRIA("Austria"),

    BAHAMAS("Bahamas"),

    BAHRAIN("Bahrain"),

    BANGLADESH("Bangladesh"),

    BARBADOS("Barbados"),

    BELARUS("Belarus"),

    BELGIUM("Belgium"),

    CAMEROON("Cameroon"),

    CANADA("Canada"), CAPE_VERDE("Cape Verde"),

    CENTRAL_AFRICAN_REPUBLIC("Central African Republic"),

    CHILE(" Chile"),

    CHINA("China"),

    DENMARK("Denmark"),

    EGYPT("Egypt"),

    FRANCE("France"),

    GAMBIA("Gambia"),

    GEORGIA("Georgia"),

    GERMANY("Germany"),

    GHANA("Ghana"),

    GREECE("Greece"),

    GUYANA("Guyana"),

    HAITI("Haiti"),

    HONDURAS("Honduras"),

    HUNGARY("Hungary"),

    ICELAND("Iceland"),

    INDIA("India"),

    INDONESIA("Indonesia"),

    IRAN("Iran"),

    IRAQ("Iraq"),

    IRELAND("Ireland"),

    ISREAL("Isreal"),

    ITALY("Italy"),

    JAMAICA("Jamaica"),

    JAPAN("Japan"),

    JORDAN("Jordan"),

    KENYA("Kenya"),

    KOREA_SOUTH("Korea, South"),

    KUWAIT("Kuwait"),

    LEBANON("Lebanon"),

    LIBYA("Libya"),

    LUXEMBOURG("Luxembourg"),

    MALASIA("Malaysia"),

    MALI("Mali"),

    MALTA("Malta"),

    MAURITIUS("Mauritius"),

    MEXICO("Mexico"),

    MONGOLIA("Mongolia"),

    NAMIBIA("Namibia"),

    NETHERKANDS("Netherlands"),

    NIGER("Niger"),

    NIGERIA("Nigeria"),

    NORWAY("Norway"),

    PAKISTAN("Pakistan"),

    PANAMA("Panama"),

    PAPUA_NEW_GUINEA("Papua New Guinea"),

    PERU("Peru"),

    PHILIPPINES("Philippines"),

    POLAND("Poland"),

    PORTUGAL("Portugal"),

    QATAR("Qatar"),

    ROMANIA("Romania"),

    RUSSIA("Russia"),

    RWANDA("Rwanda"),

    SAINT_LUCIA("Saint Lucia"),

    SAUDI_ARABIA("Saudi Arabia"),

    SENEGAL("Senegal"),

    SIERRA_LEONE("Sierra Leone"),

    SLOVAKIA("Slovakia"),

    SLOVENIA("Slovenia"),

    SOMALIA("Somalia"),

    SOUTH_AFRICA("South Africa"),

    SOUTH_SUDAN("South Sudan"),

    SPAIN("Spain"),

    SRI_LANKA("Sri Lanka"),

    SUDAN("Sudan"),

    SWEDEN("Sweden"),

    SWITZERLAND("Switzerland"),

    SYRIA("Syria"),

    TANZANIA("Tanzania"),

    TOGO("Togo"),

    TRINIDAD_AND_TOBAGO("Trinidad and Tobago"),

    TURKEY("Turkey"),

    UGANDA("Uganda"),

    UKRAINE("Ukraine"),

    UNITED_ARAB_EMIRATES("United Arab Emirates"),

    UNITED_STATES_OF_AMERICA("United States of America")

    ;

    private String desc;

    private Country(String desc) {
	this.desc = desc;
    }

    public String getDesc() {
	return desc;
    }

    public static Country find(String desc) {
	for (Country county : Country.values()) {
	    if (county.getDesc().equals(desc)) {
		return county;
	    }
	}
	return null;
    }
}
