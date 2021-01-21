public class New {

	public New() {
		System.out.println("It's a New Game");
	}

	public void Hello(int a )
	{
		for ( int i = 0; i < a ; i++)
		{
			System.out.println("Hello");
		}
	}
	public static void main(String[] args){
		New wow = new New();
		wow.Hello(3);
		}

}


