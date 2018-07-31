import MyPanel.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
public class Program extends WindowAdapter implements MouseListener,MouseMotionListener
{
	Panel p;
	Red r;
	Green g;
	Blue b;
	Frame f;
	int choice;
	public Program()
	{
		f=new Frame();
		p=new Panel();
		r=new Red();
		g=new Green();
		b=new Blue();
		
		r.setBounds(10,30,240,15);
		g.setBounds(10,50,240,15);
		b.setBounds(10,70,240,15);
		p.setBounds(10,90,240,200);
		p.setBackground(new Color(0,0,0));
		
		f.add(r);
		f.add(g);
		f.add(b);
		f.add(p);
		
		r.addMouseListener(this);
		g.addMouseListener(this);
		b.addMouseListener(this);
		
		r.addMouseMotionListener(this);
		g.addMouseMotionListener(this);
		b.addMouseMotionListener(this);
		
		f.addWindowListener(this);
		f.setVisible(true);
		f.setSize(255, 255);
		
	}
	public void windowClosing(WindowEvent arg0) {
		int a=JOptionPane.showConfirmDialog(f,"Are you sure?");
		if(a==0)
		{
			f.dispose();
		}
		
	}
	public static void main(String[] args)
	{
		Program pr=new Program();
	}
	
	public void mouseDragged(MouseEvent arg0) 
	{
		if(choice==1)
		{
			r.xr=arg0.getX();
			r.repaint();
			r.setBackground(new Color(r.xr,0,0));
			p.setBackground(new Color(r.xr,g.xg,b.xb));
		}
		else if(choice==2)
		{
			g.xg=arg0.getX();
			g.repaint();
			g.setBackground(new Color(0,g.xg,0));
			p.setBackground(new Color(r.xr,g.xg,b.xb));
		}
		else
		{
			b.xb=arg0.getX();
			b.repaint();
			b.setBackground(new Color(0,0,b.xb));
			p.setBackground(new Color(r.xr,g.xg,b.xb));
		}
	}
	
	public void mouseMoved(MouseEvent arg0) 
	{
			
	}
	
	public void mouseClicked(MouseEvent arg0) 
	{
			
	}
	
	public void mouseEntered(MouseEvent arg0) 
	{
			
	}
	
	public void mouseExited(MouseEvent arg0) 
	{
			
	}
	
	public void mousePressed(MouseEvent arg0) 
	{
		Panel pl=(Panel)arg0.getSource();
		if(pl==r)
		{
			choice=1;
			r.xr=arg0.getX();
		}
		else if(pl==g)
		{
			choice=2;
			g.xg=arg0.getX();
		}
		else if(pl==b)
		{
			choice=3;
			b.xb=arg0.getX();
		}
	}
	
	public void mouseReleased(MouseEvent arg0) 
	{
		
	}
}
