/**
 * Read in multiple images via selectImages method (will prompt for selection and call the makeGray method),
 * then vis makeGray() make a black and white copy of them, and save the copies
 * 
 */
import edu.duke.*;
import java.io.*;

public class BatchGrayscaleImageConverter {
    public ImageResource makeGray(ImageResource inImage) {
        //The color, original image (inImage) is passed into the method, which then converts it to B&W.
        //make a blank image of the same size:
        ImageResource resImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        //loop through pixels in the resulting image, resImage
        for (Pixel pixel: resImage.pixels()) {
            //the corresponding pixel in inImage is made of some mixture of green, red,blue; collect it:
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            //compute inPixel's red + blue + green, divide the sum by 3 - average
            int avg = (inPixel.getRed() + inPixel.getBlue() + inPixel.getGreen())/3;
            //set pixel's red, green and blue to average - this will assign it a particular shade of grey:
            pixel.setRed(avg);
            pixel.setGreen(avg);
            pixel.setBlue(avg);
        }
        //return the resulting B&W image, resImage
        return resImage;
    }

    public void selectImages () {
        // selects images, calls makeGray method to convert them to B&W, then show them and save a copy named "BW_"+old name
	DirectoryResource dr = new DirectoryResource();
	for (File f : dr.selectedFiles()) {
		ImageResource inImage = new ImageResource(f);
		String iname = inImage.getFileName();
		ImageResource gray = makeGray(inImage);
		String newName = "BW_" + iname;
		gray.setFileName(newName);
		gray.draw();
		gray.save();
        }
    }    
    
}
