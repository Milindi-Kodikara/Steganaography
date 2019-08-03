
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Steg {

	public static void main(String[] args) throws IOException {
		 int studNum = 3667779;
		 String newStudNum = Integer.toBinaryString(studNum);
		 System.out.println("Student number in binary=" + newStudNum +"\n");
		 BufferedImage image = null;
		 File file = null;
		 // original red ( int ) | binary of red value | bit to hide | modified binary of red | modified red value ( int )
		 try {
			file = new File("/Users/shadowking98/Documents/Year_1_Semester_2/Security 2536/mona_lisa.jpg");
			//Alpha red green blue 
			//image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
			image = ImageIO.read(file);
			int x = 10;
			int y = 50;
			int orMask = 0x00010000;
			int andMask = 0xFFFEFFFF;
			//get the pixel values of the corresponding coordinates
			for (int i = 0 ; i < 22 ; i++) {
				int pixel = image.getRGB(x,y);
				int changedPixel;
				int changedPixelRed;
				String changedRedPixelBinary;
				int pixelRed = (pixel >>16) & 0xff;
				String redPixelBinary = Integer.toBinaryString(pixelRed);

				
				System.out.println("x =" + x + " " + "y =" + y );
				System.out.print("Original red value=" + pixelRed +"	");
				System.out.print("Binary of red value= "+ redPixelBinary + "	" );
				System.out.println("Bit index="+ i + "	"+ "Bit to hide=" + newStudNum.charAt(i) );
				if (newStudNum.charAt(i) == '1') {
					pixel = pixel | orMask;
					changedPixelRed = (pixel >>16) & 0xff;
					changedRedPixelBinary = Integer.toBinaryString(changedPixelRed);
				}
				else {
					pixel = pixel & andMask;
					changedPixelRed = (pixel >>16) & 0xff;
					changedRedPixelBinary = Integer.toBinaryString(changedPixelRed);
				}
				
				System.out.print("Modified red value=" + changedPixelRed +"	");
				System.out.println("Modified binary of red value= "+ changedRedPixelBinary +"\n");
				image.setRGB(x, y, pixel);
				x += 10;
				y += 50;
			}
			file = new File ("/Users/shadowking98/Documents/Year_1_Semester_2/Security 2536/stego_mona_lisa.png");
			 ImageIO.write(image, "png", file);
		 }
		 catch (IOException e){
			 System.out.println("Error: "+e);
		 }
	}

}
