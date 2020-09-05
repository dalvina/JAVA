import javax.swing.JFrame;

import java.awt.Component;
import java.util.Scanner;

public class CoinSimViewer 
{
	public static void main(String [] args)
	{
		
		JFrame frame = new JFrame();
		
		frame.setSize(800,500);
		frame.setTitle("CoinSim");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
        CoinTossSimulator coinToss = new CoinTossSimulator();
		Scanner sc  = new Scanner(System.in);
		System.out.println("Please enter the total number of trials:"+"");
        int numTrials = sc.nextInt();
        coinToss.run(numTrials);;
		
		CoinSimComponent component = new CoinSimComponent(coinToss.getNumTrials(), coinToss.getTwoHeads(),coinToss.getTwoTails(),coinToss.getHeadTails());
		frame.add(component);
		
		frame.setVisible(true);
		
	}

}
