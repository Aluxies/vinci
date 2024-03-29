public class Login {
   
 	private String login;
	
 	public Login(String login)throws IllegalArgumentException{
		if(login==null || login.length() != 7 || (!login.substring(0,4).equals("info")&&
				!login.substring(0,4).equals("mark")&&!login.substring(0,4).equals("admi")))
			throw new IllegalArgumentException("login incorrect");
		this.login = login;
	}

	public String toString(){
		return "/n"+login +"   "+login;
	}

	// renvoie un entier >= 0
	// cet entier represente de fa�on unique le login courant!
	public int hashCode(){

		 String prefixe = login.substring(0,4);

		 int hashDepartement;

		 if ( prefixe.equals( "info" ) ){

			 hashDepartement = 0;


		 } else if ( prefixe.equals( "mark" ) ){

			 hashDepartement = 137;


		 } else {

			 hashDepartement = 210;

		 }

		 return Integer.parseInt( login.substring( 4, 7 ) ) + hashDepartement - 1 ;

	}
	
}

