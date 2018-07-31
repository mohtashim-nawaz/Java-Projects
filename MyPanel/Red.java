package MyPanel;
import java.awt.*;
public class Red extends Panel
{
	public int xr=4;
	public Red()
	{
		this.setBackground(new Color(0,0,0));
	}
	public void paint(Graphics g)
	{
		g.setColor(Color.red);
		g.fillOval(xr,4,7,7);
	}
}
