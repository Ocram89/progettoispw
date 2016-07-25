package util;

import javax.sound.sampled.*;

	public class Tone 
	{
		public static void sound(int hz,int msecs) throws LineUnavailableException 
		{
			byte[] buf = new byte[msecs*8];
		
			for (int i=0; i<buf.length; i++) 
			{
				double angle = i / (8000.0 / hz) * 2.0 * Math.PI;
				buf[i] = (byte)(Math.sin(angle) * 80.0);
			}
	
			AudioFormat af = new AudioFormat(8000f,8,1,true,false);
			SourceDataLine sdl = AudioSystem.getSourceDataLine(af);
			sdl.open(af);
			sdl.start();
			sdl.write(buf,0,buf.length);
			sdl.drain();
			sdl.close();
		}

		public static void main(String[] args) 
		{
			try 
			{
				Tone.sound(550,150);
				Tone.sound(450,250);
			} 
			catch (LineUnavailableException lue) 
			{
				System.out.println(lue);
			}
		}
}