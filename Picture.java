import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
      ///////////////////// constructors //////////////////////////////////
      
      /**
       * Constructor that takes no arguments 
       */
      public Picture ()
      {
        /* not needed but use it to show students the implicit call to super()
         * child constructors always call a parent constructor 
         */
        super();  
      }
      
      /**
       * Constructor that takes a file name and creates the picture 
       * @param fileName the name of the file to create the picture from
       */
      public Picture(String fileName)
      {
        // let the parent class handle this fileName
        super(fileName);
      }
      
      /**
       * Constructor that takes the width and height
       * @param height the height of the desired picture
       * @param width the width of the desired picture
       */
      public Picture(int height, int width)
      {
        // let the parent class handle this width and height
        super(width,height);
      }
      
      /**
       * Constructor that takes a picture and creates a 
       * copy of that picture
       * @param copyPicture the picture to copy
       */
      public Picture(Picture copyPicture)
      {
        // let the parent class do the copy
        super(copyPicture);
      }
      
      /**
       * Constructor that takes a buffered image
       * @param image the buffered image to use
       */
      public Picture(BufferedImage image)
      {
        super(image);
      }
      
      ////////////////////// methods ///////////////////////////////////////
      
      /**
       * Method to return a string with information about this picture.
       * @return a string with information about the picture such as fileName,
       * height and width.
       */
    public String toString()
    {
        String output = "Picture, filename " + getFileName() + 
          " height " + getHeight() 
          + " width " + getWidth();
        return output;
        
    }
      
      /** Method to set the blue to 0 */
    public void zeroBlue()
    {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
          for (Pixel pixelObj : rowArray)
          {
            pixelObj.setBlue(0);
          }
        }
    }
      
      /** Method to set the red to 0 */
      public void zeroRed()
      {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
          for (Pixel pixelObj : rowArray)
          {
            pixelObj.setRed(0);
          }
        }
      }
      
      /** Method to set the green to 0 */
    public void zeroGreen()
    {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
          for (Pixel pixelObj : rowArray)
          {
            pixelObj.setGreen(0);
          }
        }
    }
    public void zeroAqua()
    {
        Pixel[][] pixels = this.getPixels2D();
        for(int i = 0; i < pixels.length; i++)
        {
            for(int j = 0; j < pixels[0].length; j++)
            {
                if(pixels[i][j].getRed() <= 28 && pixels[i][j].getBlue() >= 150 && pixels[i][j].getGreen() >= 131)
                {
                    pixels[i][j].setGreen(pixels[i][j].getGreen() - 20);
                    pixels[i][j].setBlue(pixels[i][j].getBlue() - 20);
                    pixels[i][j].setRed(pixels[i][j].getRed() + 50);
                }
            }
        }
    }
      
      /** Method that mirrors the picture around a 
        * vertical mirror in the center of the picture
        * from left to right */
    public void mirrorVertical()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int width = pixels[0].length;
        for (int row = 0; row < pixels.length; row++)
        {
          for (int col = 0; col < width / 2; col++)
          {
            leftPixel = pixels[row][col];
            rightPixel = pixels[row][width - 1 - col];
            rightPixel.setColor(leftPixel.getColor());
          }
        } 
    }
      
    public void mirrorHorizontal2Point0()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int width = pixels[0].length;
        for (int row = 0; row < pixels.length; row++)
        {
            leftPixel = pixels[row][0];
            rightPixel = pixels[row][row];
            rightPixel.setColor(leftPixel.getColor());
        } 
    }
      /** Mirror just part of a picture of a temple */
    public void mirrorTemple()
    {
        int mirrorPoint = 276;
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int count = 0;
        Pixel[][] pixels = this.getPixels2D();
        
        // loop through the rows
        for (int row = 27; row < 97; row++)
        {
          // loop from 13 to just before the mirror point
          for (int col = 13; col < mirrorPoint; col++)
          {
            
            leftPixel = pixels[row][col];      
            rightPixel = pixels[row]                       
                             [mirrorPoint - col + mirrorPoint];
            rightPixel.setColor(leftPixel.getColor());
          }
        }
    }
      
      /** copy from the passed fromPic to the
        * specified startRow and startCol in the
        * current picture
        * @param fromPic the picture to copy from
        * @param startRow the start row to copy to
        * @param startCol the start col to copy to
        */
    public void copy(Picture fromPic, 
                     int startRow, int startCol)
    {
        Pixel fromPixel = null;
        Pixel toPixel = null;
        Pixel[][] toPixels = this.getPixels2D();
        Pixel[][] fromPixels = fromPic.getPixels2D();
        for (int fromRow = 0, toRow = startRow; 
             fromRow < fromPixels.length &&
             toRow < toPixels.length; 
             fromRow++, toRow++)
        {
          for (int fromCol = 0, toCol = startCol; 
               fromCol < fromPixels[0].length &&
               toCol < toPixels[0].length;  
               fromCol++, toCol++)
          {
            fromPixel = fromPixels[fromRow][fromCol];
            toPixel = toPixels[toRow][toCol];
            toPixel.setColor(fromPixel.getColor());
          }
        }   
    }
    
      /** Method to create a collage of several pictures */
    public void createCollage()
    {
        Picture flower1 = new Picture("flower1.jpg");
        Picture flower2 = new Picture("flower2.jpg");
        this.copy(flower1,0,0);
        this.copy(flower2,100,0);
        this.copy(flower1,200,0);
        Picture flowerNoBlue = new Picture(flower2);
        flowerNoBlue.zeroBlue();
        this.copy(flowerNoBlue,300,0);
        this.copy(flower1,400,0);
        this.copy(flower2,500,0);
        this.mirrorVertical();
        this.write("collage.jpg");
    }
      
      
      /** Method to show large changes in color 
        * @param edgeDist the distance for finding edges
        */
    public void edgeDetection(int edgeDist)
    {
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        Pixel[][] pixels = this.getPixels2D();
        Color rightColor = null;
        for (int row = 0; row < pixels.length; row++)
        {
          for (int col = 0; 
               col < pixels[0].length-1; col++)
          {
            leftPixel = pixels[row][col];
            rightPixel = pixels[row][col+1];
            rightColor = rightPixel.getColor();
            if (leftPixel.colorDistance(rightColor) > 
                edgeDist)
              leftPixel.setColor(Color.BLACK);
            else
              leftPixel.setColor(Color.WHITE);
          }
        }
    }
      
      /** Method to show large changes in color 
        * @param edgeDist the distance for finding edges
        */
    public void edgeDetection2point0() // makes the watermark a png
    {
        Picture watermark = new Picture("bandicam.png");
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        Pixel[][] pixels = this.getPixels2D();
        Color rightColor = null;
        for (int row = 0; row < pixels.length; row++)
        {
          for (int col = 0; 
               col < pixels[0].length-1; col++)
          {
            leftPixel = pixels[row][col];
            rightPixel = pixels[row][col+1];
            rightColor = rightPixel.getColor();
            if (leftPixel.colorDistance(rightColor) > 
                pixels.length)
              leftPixel.setColor(Color.BLACK);
            else
              leftPixel.setColor(Color.WHITE);
          }
        }
    }
     
    public void AbstractArtFilter1point0() // vertical lines (top to bottom)
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel topPixel = null;
        Pixel bottomPixel = null;
        int width = pixels[0].length;
        for (int col = 0; col < width; col++)
        {
            topPixel = pixels[0][col];
            for(int row = 0; row < pixels.length; row++)
            {
                bottomPixel = pixels[row][col];
                bottomPixel.setColor(topPixel.getColor());
            }   
        }
    }
      // only primary color filter? like that one guy who made all of the squares
    public void AbstractArtFilter2point0() // horizontal lines
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int width = pixels[0].length;
        for (int row = 0; row < pixels.length; row++)
        {
            leftPixel = pixels[row][0];
            for(int column = 0; column < width; column++)
            {
                rightPixel = pixels[row][column];
                rightPixel.setColor(leftPixel.getColor());
            }
        } 
    }
 
    public void combine() // combining the two
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int width = pixels[0].length;
        for (int row = 0; row < pixels.length; row++)
        {
            leftPixel = pixels[row][0];
            for(int column = 0; column < width; column++)
            {
                rightPixel = pixels[row][column];
                rightPixel.setColor(leftPixel.getColor());
            }
        } 
        Pixel[][] pixels2 = this.getPixels2D();
        Pixel topPixel = null;
        Pixel bottomPixel = null;
        int width2 = pixels2[0].length;
        for (int col = 0; col < width; col += 2)
        {
            topPixel = pixels[0][col];
            for(int row = 0; row < pixels2.length; row++)
            {
                bottomPixel = pixels2[row][col];
                bottomPixel.setColor(topPixel.getColor());
            }   
         }
    }
    public void bandicamWatermark(String pic) // adds a www.bandicam.com watermark
    {
        Picture beach1point5 = new Picture(pic);
        Pixel[][] pixels = beach1point5.getPixels2D();
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        Pixel[][] widthP = this.getPixels2D(); 
        int width = widthP[0].length;
        Picture watermark = new Picture("bandicam5.png");
        // watermark = watermark.scale((double) pixels.length / 1000, (double) pixels[0].length / 1000);
        Pixel[][] pixels2 = watermark.getPixels2D();
        Pixel leftPixel2 = null;
        Pixel rightPixel2 = null;
        int width2 = pixels2[0].length;
        Color rightColor = null;
        Pixel[][] pixels3 = beach1point5.getPixels2D();
        Pixel leftPixel3 = null;
        Pixel rightPixel3 = null;
        int x = width / 110;
        int y = width/4;
        for (int i = 0; i < pixels2.length; i++) // copies each pixel and adds transparency
        {
            for (int j = 0; j < pixels2[0].length; j++)
            {
                try
                {
                    if((Math.abs(pixels2[i][j].getRed() - pixels2[i][j].getGreen()) <= 10))
                    {
                        leftPixel2 = pixels2[i][j];
                        pixels[i][j].setColor(leftPixel2.getColor()); 
                        if ((pixels2[i][j].getRed() == 0) && (pixels2[i][j].getBlue() == 0) && (pixels2[i][j].getGreen() == 0) || (pixels2[i][j].getRed() == 255) && (pixels2[i][j].getBlue() == 255) && (pixels2[i][j].getGreen() == 255))
                        {
                            pixels2[i][j].setColor(pixels3[i + x][j + y].getColor());
                        }
                    }
                }
                catch(Exception e)
                {
                    ;
                }
            }
        }
        this.copy(watermark, x, y);
    }
    public void compress() // compresses an image
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel leftPixels = null;
        Pixel rightPixels = null;
        Pixel centerPixels = null;
        Pixel topPixels = null;
        Pixel bottomPixels = null;
        for(int i = 0; i <= pixels.length; i += 4)
        {
            for(int j = 0; j <= pixels[0].length - 1; j += 8)
            {
                    for(int k = 1; k <= 10; k++)
                    {   
                        for(int m = 1; m <= 10; m++)
                        {   
                            try
                            {
                                 if(!(pixels[i][j].getRed() == pixels[i][j].getBlue() && pixels[i][j].getGreen() == pixels[i][j].getBlue())) 
                                 {
                                     leftPixels = pixels[i - k][j - m];
                                     leftPixels.setColor(pixels[i][j].getColor());
                                 }
                            }
                            catch(Exception e)
                                {
                                    ;
                                }
                            }
                        }
                    }
            }
        }

  /* Main method for testing - each class in Java can have a main 
   * method 
   */
  public static void main(String[] args) 
  {
    // Picture beach1 = new Picture("macOS4.jpg");
    // beach1.explore();
    // beach1.AbstractArtFilter2point0();
    // beach1.explore();
    // Picture beach2 = new Picture("macOS4.jpg");
    // beach2.AbstractArtFilter1point0();
    // beach2.explore();
    String pic = "macOS3.jpg";
    Picture beach3 = new Picture(pic);
    beach3.explore();
    beach3.compress();
    beach3.bandicamWatermark(pic);
    beach3.explore();
  }
  
} // this } is the end of class Picture, put all new methods before this
