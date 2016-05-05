package imitatorparsera;

public class ImitatorParsera {

	public static void main(String[] args) {
		int kolvo = 90;
		System.out.println(parser(kolvo));
	}
	
	public static String parser(int x){
	
		if(x<0){
			return("Вас вызывают на границу!");
		}
		else{
			return("в очереди перед Вами " + x + " машин." );
		}
		
		 
	} 

}
