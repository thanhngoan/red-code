
public class ShapeClient {
	public static void printCrosses(Shape a, Shape b, String name1, String name2)
	{
		System.out.println(name1 + " crosses " + name2 +":" + a.crosses(b));
	}
	public static void printEncircles(Shape a, Shape b, String name1, String name2)
	{
		System.out.println(name1 + " encircles " + name2 +":" + a.encircles(b));
	}
	public static void main(String[] args)
	{
		Shape a = new Shape("0 0 0 1 1 1 1 0"),
		  b = new Shape("10 10 10 11 11 11 11 10"),
		  c = new Shape("0.5 0.5 0.5 -10 1.5 0"),
		  d = new Shape("0.5 0.5 0.75 0.75 0.75 0.2");
		printCrosses(a,b,"a","b");
		printCrosses(a,c,"a","c");
		printCrosses(a,d,"a","d");
		printEncircles(a,b,"a","b");
		printEncircles(a,c,"a","c");
		printEncircles(a,d,"a","d");
	}
}
