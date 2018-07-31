package MyPanel;
import java.awt.*;
public class Blue extends Panel
{
	public int xb=4;
	public Blue()
	{
		this.setBackground(new Color(0,0,0));
	}
	public void paint(Graphics g)
	{
		g.setColor(Color.blue);
		g.fillOval(xb,4,7,7);
	}
}
