import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.font.FontRenderContext;

import javax.swing.JComponent;

public class CoinSimComponent extends JComponent 
{
	int numTrials;
	int numTwoHeads;
	int numTwoTails;
	int numHeadTails;
	
	public CoinSimComponent(int numTrials, int numTwoHeads, int numTwoTails, int numHeadTails)
	{
		this.numTrials = numTrials;
		this.numTwoHeads = numTwoHeads;
		this.numTwoTails = numTwoTails;
		this.numHeadTails = numHeadTails;
	}
	

	
	public void paintComponent(Graphics g)
	{
		
		Graphics2D g2 = (Graphics2D) g;
	
		//define some constant value
		int vb = 30;
		int width =80;
		

		Font font = g2.getFont();
		FontRenderContext context = g2.getFontRenderContext();
		Rectangle2D labelBounds = font.getStringBounds("", context);
		double widthOfLabel = labelBounds.getWidth();
		double heightOfLabel = labelBounds.getHeight();
		
		//get the location of the first bar
		int x = getWidth()/4;
	    int y = getHeight();
	    int xLeft = x - width/2;
	 	int yBottom = (int) (y - vb - heightOfLabel); 
	 	
		double scale = (y- 2*vb-heightOfLabel)/100.0;
		
		
		//compute the three percentage and express the three label
		double percentTwoHeads = (double)numTwoHeads/numTrials*100;
		String label1 = "Two Heads:"+numTwoHeads+"("+Math.round(percentTwoHeads)+"%)";
		double percentHeadTails = (double)numHeadTails/numTrials*100;
		String label2 = "A Head and a Tail:"+numHeadTails+"("+Math.round(percentHeadTails)+"%)";
		double percentTwoTails = (double)numTwoTails/numTrials*100;
		String label3 = "Two Tails:"+numTwoTails+"("+Math.round(percentTwoTails)+"%)";
		
		
		
		Bar bar1 = new Bar(yBottom , xLeft , width , (int) percentTwoHeads, scale,Color.red, label1);
		Bar bar2 = new Bar(yBottom , xLeft + x, width, (int) percentHeadTails, scale,Color.green, label2);
		Bar bar3 = new Bar(yBottom , xLeft + 2*x , width, (int) percentTwoTails, scale,Color.blue, label3);
		bar1.draw(g2);
		bar2.draw(g2);
		bar3.draw(g2);
		
		
	}
	

}
