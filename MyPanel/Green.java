package MyPanel;
import java.awt.*;
public class Green extends Panel
{
	public int xg=4;
	public Green()
	{
		this.setBackground(new Color(0,0,0));
	}
	public void paint(Graphics g)
	{
		g.setColor(Color.green);
		g.fillOval(xg,4,7,7);
	}
}
