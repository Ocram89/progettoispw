package util;

public class CostantiNumeriche
{
	public static final int DIMBASEMENU=100;
	
	final public static String zero = "0";
	final public static String uno = "1";
	final public static String due = "2";
	final public static String tre = "3";
	final public static String quattro = "4";
	final public static String cinque = "5";
	final public static String sei = "6";
	final public static String sette = "7";
	final public static String otto = "8";
	final public static String nove = "9";
	
	final static public String[] numeri = 
		{
			uno, due, tre, quattro, cinque, sei, sette, otto, nove 
		};
	
	public static boolean isNumber(String s)
	{
		for (String t: numeri)
		{
			if (s.equals(t))return true;
		}
		return false;	
	}
	
	
	
}