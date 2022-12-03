package headsup;

import java.util.ArrayList;

public class shipGenerator {
	static ArrayList<String> shipsLocation = new ArrayList<String>();
	static int [] shipSizes ;
	static void setShipSize(int n)
	{
		switch (n) {
		case 8:
		{
			int [] shipSize = {4,3,3,2};
			shipSizes =shipSize;
			break;
		}
		case 10:
		{
			int [] shipSize = {4,4,3,3,2};
			shipSizes =shipSize;
			break;

		}
		case 12:
		{
			int [] shipSize = {4,4,3,3,2,2};
			shipSizes =shipSize;
			break;
		}
	}
	}
	private static boolean toss (int n)// hàm này dùng để làm gì đây
	{
		boolean rs = false;
		switch (n) {
			case 8:
			{
				if( (int) (Math.random()*8+1) > 4) rs = true;// what can it do?
				break;
			}
			case 10:
			{
				if( (int) (Math.random()*10+1) > 5) rs = true;
				break;
			}
			case 12:
			{
				if( (int) (Math.random()*12+1) > 6) rs = true;
				break;
			}
		}
		return rs;
	}
	public static ArrayList<String> generateShip(int n)
	{
		setShipSize(n);
		ArrayList<String> shipsLocation = new ArrayList<String>();
		shipsLocation.add("/");
		for(int i : shipSizes)//shipsize ->i  
		{
			String [] currentShip  = new String[i];
			boolean flag = false;
			while(!flag)
			{
				flag = true;
				if(toss(n))//hình như cái này đang ngang
				{
					char l = (char) (int) (Math.random()*n + 65);//firstindex-> l
					int m = (int) (Math.random() *(n-i));//second index//secondindex = m
					currentShip[0] = l +""+m;
					for(int j = 1 ; j < i ;j++)
					{
						currentShip[j] = l + "" + (m+j);
					}
					for(int tmp = (m-1) ; tmp < (i + m +1 ); tmp++ )
					{
						if(shipsLocation.contains((char)(l-1)+""+tmp))
						{
							flag = false;
						
						}
						if(shipsLocation.contains((char)(l+1)+""+tmp))
						{
							flag = false;
							
						}
					}
					if(shipsLocation.contains((char)(l)+""+(m-1))||shipsLocation.contains((char)(l)+""+(m+i)))
					{
						flag = false;
						
					}
				}
				else
				{
					char l = (char)(int) (Math.random()*(n-i)+65);//firstindex -> l
					int m  = (int) (Math.random() * (n));//secondIndex->m
					currentShip[0] = l + "" + m;//shipsize ->i
					for(int j = 1 ; j < i ; j++)
					{
						currentShip[j] = (char) (l+j) + "" + m ; 
					}
					for(char tmp =(char) (l-1) ; tmp < (i + l +1 ); tmp++ )
					{
						if(shipsLocation.contains((char)tmp+""+(m-1)))
						{
							flag = false;
							
						}
						if(shipsLocation.contains((char)tmp+""+(m+1)))
						{
							flag = false;
						
						}
					}
					if(shipsLocation.contains((char)(l-1)+""+(m))||shipsLocation.contains((char)(l+i)+""+(m)))
					{
						flag = false;
					
					}
				}
				for(String s :currentShip)
				{

					if(shipsLocation.contains(s))
					{
						flag = false;
						break;
					}
				}

				
				
				if(flag)
				{
					for(String s : currentShip)
					{
						shipsLocation.add(s);
					}
				}
			}
			shipsLocation.add("/");
		}
		return shipsLocation;
	}
	public static void main(String[] args) {
		setShipSize(12);

	}
}
