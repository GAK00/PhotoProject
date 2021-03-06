package pixLab.classes;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

/**
 * A class that represents a picture. This class inherits from SimplePicture and
 * allows the student to add functionality to the Picture class.
 * 
 * @author Geran Kunz
 */
public class Picture extends SimplePicture
{
	private Pixel[][] pixels;
	///////////////////// constructors //////////////////////////////////

	/**
	 * Constructor that takes no arguments
	 */
	public Picture()
	{
		/*
		 * not needed but use it to show students the implicit call to super()
		 * child constructors always call a parent constructor
		 */
		super();
		pixels = this.getPixels2D();
	}

	/**
	 * Constructor that takes a file name and creates the picture
	 * 
	 * @param fileName
	 *            the name of the file to create the picture from
	 */
	public Picture(String fileName)
	{
		// let the parent class handle this fileName
		super(fileName);
		pixels = this.getPixels2D();
	}

	/**
	 * Constructor that takes the width and height
	 * 
	 * @param height
	 *            the height of the desired picture
	 * @param width
	 *            the width of the desired picture
	 */
	public Picture(int height, int width)
	{
		// let the parent class handle this width and height
		super(width, height);
		pixels = this.getPixels2D();
	}

	/**
	 * Constructor that takes a picture and creates a copy of that picture
	 * 
	 * @param copyPicture
	 *            the picture to copy
	 */
	public Picture(Picture copyPicture)
	{
		// let the parent class do the copy
		super(copyPicture);
		pixels = this.getPixels2D();
	}

	/**
	 * Constructor that takes a buffered image
	 * 
	 * @param image
	 *            the buffered image to use
	 */
	public Picture(BufferedImage image)
	{
		super(image);
		pixels = this.getPixels2D();
	}

	////////////////////// methods ///////////////////////////////////////

	/**
	 * Method to return a string with information about this picture.
	 * 
	 * @return a string with information about the picture such as fileName,
	 *         height and width.
	 */
	public String toString()
	{
		String output = "Picture, filename " + getFileName() + " height " + getHeight() + " width " + getWidth();
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

	private Color getMostCommonColor()
	{
		ArrayList<Color> colors = new ArrayList<Color>();
		ArrayList<Integer> uses = new ArrayList<Integer>();
		Pixel[][] pixels = this.getPixels2D();
		for(int row = 0; row < pixels.length; row++)
		{
			for(int col = 0; col < pixels[0].length; col++)
			{
				Color color = pixels[row][col].getColor();
				if(colors.contains(color))
				{
					int index = colors.indexOf(color);
					uses.set(index, uses.get(index)+1);
				}
				else
				{
					colors.add(color);
					uses.add(1);
				}
			}
		}
		int maxUses = uses.get(0);
		int maxIndex = 0;
		for(int index = 1; index < uses.size(); index++)
		{
			if(maxUses<uses.get(index))
			{
				maxUses = uses.get(index);
				maxIndex = index;
			}
		}
		return colors.get(maxIndex);
	}
	public void greenScreen(Picture overlay) throws Exception
	{
		greenScreen(50, overlay);
	}
	public void greenScreen(int tolorence, Picture overlay) throws Exception
	{
		Color color = getMostCommonColor();
		System.out.println("MostCommonColor: " + color);
		greenScreen(color,tolorence,overlay);
	}
	public void greenScreen(Color color, int  tolarence, Picture overlay) throws Exception
	{
		Pixel[][] greenScreenPixels = this.getPixels2D();
		Pixel[][] overlayPixels = overlay.getPixels2D();
		if(greenScreenPixels.length == overlayPixels.length && greenScreenPixels[0].length == overlayPixels[0].length)
		{
			for(int row = 0; row < greenScreenPixels.length; row++)
			{
				for(int col = 0; col < greenScreenPixels[0].length; col++)
				{
					Color currentColor = overlayPixels[row][col].getColor();
					if(greenScreenPixels[row][col].colorDistance(color)<=tolarence)
					{
						System.out.println("Setting color");
						greenScreenPixels[row][col].setColor(currentColor);
					}
				}
			}
		}
		else
		{
			throw new Exception("Picture sizes must match");
		}
	}
	public void stigonorgrophy(Picture toStigPic) throws Exception
	{
		Pixel[][] unStig = this.getPixels2D();
		Pixel[][] toStig = toStigPic.getPixels2D();
		if (toStig.length == unStig.length && toStig[0].length == unStig[0].length)
		{
			for (int row = 0; row < toStig.length; row++)
			{
				int toRow = getIndex(row, 0, toStig.length, 0)[0];
				if (toRow < 0)
				{
					toRow = toStig.length - 1 + toRow;
				}
				for (int col = 0; col < toStig[0].length; col++)
				{
					int toCol = getIndex(0, col, 0, toStig[0].length)[1];
					if (toCol < 0)
					{
						toCol = toStig[0].length - 1 + toCol;
					}

					System.out.println(toRow + "," + toCol);
					Pixel fromPixel = toStig[row][col];

					Pixel toPixel = unStig[toRow][toCol];
					int red = numberify((fromPixel.getGreen() / 32) + toPixel.getRed() - 15);

					int green = numberify((fromPixel.getBlue() / 32) + toPixel.getGreen() - 15);
					int blue = numberify((fromPixel.getRed() / 32) + toPixel.getBlue() - 15);
					Color newColor = new Color(red, green, blue);
					unStig[toRow][toCol].setColor(newColor);
				}
			}
		} else
		{
			throw new Exception("Picture sizes must match!!");
		}
	}

	private int numberify(int number)
	{
		if (number >= 255)
		{

			if (number > 300)
			{
				number = 254;
			} else
			{
				number = 254 + (((number % 2) * 2) - 254);
			}

		}
		if (number <= 0)
		{
			if (number < -100)
			{
				number = 0;
			} else
			{
				number = Math.abs(number);
				number = number / 2;
				number = number * 2;
			}
		} else if (number % 2 == 0)
		{
			number = number + 1;
		}
		return number;
	}

	private int unNumberify(int number)
	{
		if (number % 2 != 0)
		{
			return number;
		} else
		{
			if (number < 100)
			{
				return number * -1;
			} else if (number == 0)
			{
				return -100;
			} else if (number > 208)
			{
				number = 254 + (254 - number);
			} else if (number == 254)
			{
				return 240;
			} else
			{
				return 0;
			}
		}
		return number;
	}

	private int[] getIndex(int x, int y, int mX, int mY)
	{
		mX--;
		mY--;
		int[] newIndex = new int[2];
		if (x % 2 == 0 || x == 0)
		{
			newIndex[0] = mX - (x / 2);
		} else
		{
			newIndex[0] = x / 2;
		}
		if (y % 2 == 0 || y == 0)
		{
			newIndex[1] = mY - (y / 2);
		} else
		{
			newIndex[1] = y / 2;
		}

		return newIndex;
	}

	public void unstigonorgrophy(Picture toStigPic) throws Exception
	{
		Pixel[][] unStig = this.getPixels2D();
		Pixel[][] toStig = toStigPic.getPixels2D();
		Color[][] colors = new Color[toStig.length][toStig[0].length];
		if (toStig.length == unStig.length && toStig[0].length == unStig[0].length)
		{
			for (int row = 0; row < toStig.length; row++)
			{
				int toRow = getIndex(row, 0, toStig.length, 0)[0];
				if (toRow < 0)
				{
					toRow = toStig.length - 1 + toRow;
				}
				for (int col = 0; col < toStig[0].length; col++)
				{
					int toCol = getIndex(0, col, 0, toStig[0].length)[1];

					Pixel fromPixel = toStig[toRow][toCol];

					Pixel toPixel = unStig[toRow][toCol];
					int red = ((unNumberify(toPixel.getBlue()) + 15) - fromPixel.getBlue()) * 32;
					int green = ((unNumberify(toPixel.getRed()) + 15) - fromPixel.getRed()) * 32;
					int blue = ((unNumberify(toPixel.getGreen()) + 15) - fromPixel.getGreen()) * 32;
					int[] color = new int[] { red, green, blue };
					for (int index = 0; index < 3; index++)
					{
						if (color[index] > 255)
						{
							color[index] = 255;
						}
						if(color[index]<0)
						{
							color[index] = 0;
						}
					}
					Color newColor = new Color(color[0], color[1], color[2]);
					colors[row][col] = newColor;
				}
			}
			for (int row = 0; row < toStig.length; row++)
			{
				for (int col = 0; col < toStig[0].length; col++)
				{
					unStig[row][col].setColor(colors[row][col]);
				}
			}
		} else
		{
			throw new Exception("Picture sizes must match!!");
		}
	}

	/**
	 * Method that mirrors the picture around a vertical mirror in the center of
	 * the picture from left to right
	 */
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

	public void mirrorVerticalRightToLeft()
	{
		Pixel[][] pixels = this.getPixels2D();
		Pixel leftPixel = null;
		Pixel rightPixel = null;
		int width = pixels[0].length;
		for (int row = pixels.length - 1; row >= 0; row--)
		{
			for (int col = width / 2 - 1; col >= 0; col--)
			{
				leftPixel = pixels[row][col];
				rightPixel = pixels[row][width - 1 - col];
				leftPixel.setColor(rightPixel.getColor());
			}
		}
	}

	public void mirrorHorizontaBottomToTop()
	{
		Pixel[][] pixels = this.getPixels2D();
		Pixel topPixel = null;
		Pixel bottomPixel = null;
		int height = pixels.length - 1;
		for (int col = 0; col < pixels[0].length; col++)
		{
			for (int row = height / 2; row <= height; row++)
			{

				bottomPixel = pixels[row][col];
				topPixel = pixels[height - row][col];
				topPixel.setColor(bottomPixel.getColor());
			}
		}
	}

	public void mirrorHorizontaTopToBottom()
	{
		Pixel[][] pixels = this.getPixels2D();
		Pixel topPixel = null;
		Pixel bottomPixel = null;
		int height = pixels.length - 1;
		for (int col = 0; col < pixels[0].length; col++)
		{
			for (int row = height / 2; row <= height; row++)
			{

				bottomPixel = pixels[row][col];
				topPixel = pixels[height - row][col];
				bottomPixel.setColor(topPixel.getColor());
			}
		}
	}

	public void mirrorDiagonalTopToBottom()
	{
		Pixel[][] pixels = this.getPixels2D();
		Pixel topPixel = null;
		Pixel bottomPixel = null;
		int height = pixels.length - 1;
		for (int row = 0; row < height; row++)
		{
			for (int col = 0; col <= pixels[0].length; col++)
			{

				if (col != row && col <= height && row <= pixels[0].length && row < col)
				{
					// System.out.println(row + "," + col);
					bottomPixel = pixels[col][row];
					topPixel = pixels[row][col];
					bottomPixel.setColor(topPixel.getColor());
				}

			}
		}
	}

	public void mirrorDiagonalBottomToTop()
	{
		Pixel[][] pixels = this.getPixels2D();
		Pixel topPixel = null;
		Pixel bottomPixel = null;
		int height = pixels.length;
		for (int row = 0; row < height; row++)
		{
			for (int col = 0; col <= pixels[0].length; col++)
			{

				if (col != row && col < height && row < pixels[0].length && row > col)
				{
					// System.out.println(row + "," + col);
					bottomPixel = pixels[col][row];
					topPixel = pixels[row][col];
					bottomPixel.setColor(topPixel.getColor());
				}

			}
		}
	}

	public void Glitch(int glitchness, double ratio)
	{
		int major = (int) (ratio * 100);
		if (major > 100 || major < 0)
		{
			major = 100;
		}
		int minor = 100 - major;
		System.out.println(major + " , " + minor);
		Pixel[][] pixels = this.getPixels2D();
		Color[][] colors = new Color[pixels.length][pixels[0].length];
		for (int row = 0; row < pixels.length; row++)
		{
			for (int col = 0; col < pixels[0].length; col++)
			{
				Color avgAgcentPixelColor = getSurroundingPixelAvg(glitchness, row, col);
				int newRed = ((pixels[row][col].getColor().getRed() * major) + avgAgcentPixelColor.getRed() * minor) / 100;
				int newGreen = ((pixels[row][col].getColor().getGreen() * major) + avgAgcentPixelColor.getGreen() * minor) / 100;
				int newBlue = ((pixels[row][col].getColor().getBlue() * major) + avgAgcentPixelColor.getBlue() * minor) / 100;
				Color newColor = new Color(newRed, newGreen, newBlue);
				colors[row][col] = newColor;

			}

		}
		for (int row = 0; row < pixels.length; row++)
		{
			for (int col = 0; col < pixels[0].length; col++)
			{
				pixels[row][col].setColor(colors[row][col]);
			}
		}
	}

	private Color getSurroundingPixelAvg(int radius, int row, int col)
	{
		int startRow = row - radius;
		int startCol = col - radius;
		int endRow = row + radius + 1;
		int endCol = col + radius + 1;
		int totalRuns = 0;
		int totalRed = 0;
		int totalBlue = 0;
		int totalGreen = 0;
		for (int r = startRow; r < endRow; r++)
		{
			for (int c = startCol; c < endCol; c++)
			{
				if (r >= 0 && c >= 0 && r < (pixels.length) && c < (pixels[0].length))
				{
					totalRed += pixels[r][c].getColor().getRed();
					totalGreen += pixels[r][c].getColor().getGreen();
					totalBlue += pixels[r][c].getColor().getBlue();
					totalRuns++;
				}
			}
		}
		totalRed = totalRed / totalRuns;
		totalGreen = totalGreen / totalRuns;
		totalBlue = totalBlue / totalRuns;
		return new Color(totalRed, totalGreen, totalBlue);
	}

	public void mirrorDiagonalRightTopToBottom()
	{
		Pixel[][] pixels = this.getPixels2D();
		Pixel topPixel = null;
		Pixel bottomPixel = null;
		int height = pixels.length;

		int length = height - 1;
		if (pixels[0].length < height)
		{
			length = pixels[0].length - 1;
		}
		for (int row = 0; row < height; row++)
		{
			for (int col = 0; col <= pixels[0].length; col++)
			{

				if (col + row != length && col < length && row < length && length - row > col)
				{
					// System.out.println(row + "," + col);
					bottomPixel = pixels[length - col][length - row];
					topPixel = pixels[row][col];
					bottomPixel.setColor(topPixel.getColor());
				}

			}
		}
	}

	public void mirrorDiagonalRightBottomToTop()
	{
		Pixel[][] pixels = this.getPixels2D();
		Pixel topPixel = null;
		Pixel bottomPixel = null;
		int height = pixels.length;
		int length = Math.min(height - 1, pixels[0].length - 1);
		for (int row = 0; row < height; row++)
		{
			for (int col = 0; col <= pixels[0].length; col++)
			{

				if (col + row != length && col < length && row < length && length - row < col)
				{
					System.out.println(row + "," + col);
					bottomPixel = pixels[length - col][length - row];
					topPixel = pixels[row][col];
					bottomPixel.setColor(topPixel.getColor());
				}

			}
		}
	}

	/** Mirror just part of a picture of a temple */
	public void mirrorTemple()
	{
		int mirrorPoint = 276;
		Pixel leftPixel = null;
		Pixel rightPixel = null;
		Pixel[][] pixels = this.getPixels2D();

		// loop through the rows
		for (int row = 27; row < 97; row++)
		{
			// loop from 13 to just before the mirror point
			for (int col = 13; col < mirrorPoint; col++)
			{

				leftPixel = pixels[row][col];
				rightPixel = pixels[row][mirrorPoint - col + mirrorPoint];
				rightPixel.setColor(leftPixel.getColor());
			}
		}
	}

	public void customMirror(boolean isVertical, int startX, int startY, int endX, int endY, int[] ignoredColorRanges)
	{
		boolean isBad = false;
		int mirrorDif = endX - startX;
		if (isVertical)
		{
			System.out.println("ok");
			mirrorDif = endY - startY;
		}
		Pixel orginalPixel = null;
		Pixel copiedPixel = null;
		Pixel[][] pixels = this.getPixels2D();
		for (int row = startY; row <= endY; row++)
		{
			for (int col = startX; col <= endX; col++)
			{
				orginalPixel = pixels[row][col];
				if (isVertical)
				{
					copiedPixel = pixels[(endY + mirrorDif) - (row - startY)][col];
				} else
				{
					copiedPixel = pixels[row][(endX + mirrorDif) - (col - startX)];
				}
				if (ignoredColorRanges == null)
				{
					copiedPixel.setColor(orginalPixel.getColor());
				} else
				{
					try
					{
						boolean ignore = testColorRange(orginalPixel.getColor(), ignoredColorRanges);
						if (!ignore)
						{
							copiedPixel.setColor(orginalPixel.getColor());

						} else
						{
							System.out.println("ignored");
						}
					} catch (Exception e)
					{
						e.printStackTrace();
						isBad = true;
						break;

					}

				}
			}
			if (isBad)
			{
				break;
			}
		}
	}

	private boolean testColorRange(Color color, int[] colorRange) throws Exception
	{
		boolean isInColorRange = false;
		if (colorRange.length % 2 == 0 && colorRange.length > 0)
		{
			for (int index = 0; index < colorRange.length; index += 2)
			{
				int rgbAdder = colorRange[index + 1];
				Color color1 = new Color(colorRange[index]);
				Color rgbPlus = new Color((color1.getRed() + rgbAdder), (color1.getGreen() + rgbAdder), (color1.getBlue() + rgbAdder));
				Color rgbMinus = new Color((color1.getRed() - rgbAdder), (color1.getGreen() - rgbAdder), (color1.getBlue() - rgbAdder));
				if (rgbMinus.getRed() < color.getRed() && rgbMinus.getGreen() < color.getGreen() && rgbMinus.getBlue() < color.getBlue() && rgbPlus.getRed() > color.getRed() && rgbPlus.getGreen() > color.getGreen() && rgbPlus.getBlue() > color.getBlue())

				{
					isInColorRange = true;
				}
				// else{}
			}
		} else
		{
			throw new Exception("invalid color Ranges");
		}
		return isInColorRange;
	}

	public void onlyBlue()
	{
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels)
		{
			for (Pixel pixelObj : rowArray)
			{
				pixelObj.setGreen(0);
				pixelObj.setRed(0);
			}
		}
	}

	public void negate()
	{
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels)
		{
			for (Pixel pixelObj : rowArray)
			{
				pixelObj.setBlue(255 - pixelObj.getBlue());
				pixelObj.setGreen(255 - pixelObj.getGreen());
				pixelObj.setRed(255 - pixelObj.getRed());
			}
		}
	}

	public void greyScale()
	{
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels)
		{
			for (Pixel pixelObj : rowArray)
			{
				int newColorValue = (pixelObj.getBlue() + pixelObj.getGreen() + pixelObj.getRed()) / 3;
				pixelObj.setBlue(newColorValue);
				pixelObj.setGreen(newColorValue);
				pixelObj.setRed(newColorValue);
			}
		}
	}

	public void goDerp()
	{
		Random rand = new Random();
		Pixel[][] pixels = this.getPixels2D();
		int randomCoficient = 10;
		Color lastColor = null;
		for (Pixel[] rowArray : pixels)
		{
			for (Pixel pixelObj : rowArray)
			{
				Color currentColor = pixelObj.getColor();
				int temp;
				temp = randomCoficient;
				if (lastColor != null)
				{
					randomCoficient = Math.abs(rand.nextInt(temp * 2 + 1) - lastColor.getRed());
				}
				int red = Math.abs(currentColor.getRed() + rand.nextInt(randomCoficient * 2 + 1) - randomCoficient);
				if (red > 255)
				{
					red = 255;
				}
				randomCoficient = temp;
				temp = randomCoficient;
				if (lastColor != null)
				{
					randomCoficient = Math.abs(rand.nextInt(temp * 2 + 1) - lastColor.getGreen());
				}
				int green = Math.abs(currentColor.getGreen() + rand.nextInt(randomCoficient * 2 + 1) - randomCoficient);
				if (green > 255)
				{
					green = 255;
				}
				randomCoficient = temp;
				temp = randomCoficient;
				if (lastColor != null)
				{
					randomCoficient = Math.abs(rand.nextInt(temp * 2 + 1) - lastColor.getBlue());
				}
				int blue = Math.abs(currentColor.getBlue() + rand.nextInt(randomCoficient * 2 + 1) - randomCoficient);
				if (blue > 255)
				{
					blue = 255;
				}
				randomCoficient = temp;
				Color color = new Color(red, green, blue);
				lastColor = color;
				pixelObj.setColor(color);
			}
		}
	}

	public void fixWater()
	{
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels)
		{
			for (Pixel pixelObj : rowArray)
			{
				if (Math.abs(pixelObj.getGreen() - pixelObj.getBlue()) < 3)
				{
					pixelObj.setGreen((int) (pixelObj.getGreen() / 1.7));
					pixelObj.setBlue((int) (pixelObj.getBlue() / 1.7));
				} else if (pixelObj.getGreen() > pixelObj.getBlue() && Math.abs(pixelObj.getGreen() - pixelObj.getBlue()) < 10)
				{
					pixelObj.setGreen((int) (pixelObj.getGreen() / 1.6));
					pixelObj.setBlue((int) (pixelObj.getBlue() / 1.7));
				} else if (Math.abs(pixelObj.getGreen() - pixelObj.getBlue()) < 10)
				{
					pixelObj.setGreen((int) (pixelObj.getGreen() / 1.7));
					pixelObj.setBlue((int) (pixelObj.getBlue() / 1.6));
				} else if (pixelObj.getGreen() > pixelObj.getBlue())
				{
					pixelObj.setGreen((int) (pixelObj.getGreen() / 1.5));
					pixelObj.setBlue((int) (pixelObj.getBlue() / 1.7));
				} else
				{
					pixelObj.setGreen((int) (pixelObj.getGreen() / 1.7));
					pixelObj.setBlue((int) (pixelObj.getBlue() / 1.5));
				}

				int max;
				if (pixelObj.getRed() > pixelObj.getGreen() && pixelObj.getRed() > pixelObj.getGreen())
				{
					max = 255 - pixelObj.getRed();
				} else if (pixelObj.getGreen() > pixelObj.getBlue())
				{
					max = 255 - pixelObj.getGreen();
				} else
				{
					max = 255 - pixelObj.getBlue();
				}
				if (max > 35)
				{
					max = 35;
				}
				pixelObj.setRed(pixelObj.getRed() + max);
				pixelObj.setGreen(pixelObj.getGreen() + max);
				pixelObj.setBlue(pixelObj.getBlue() + max);
			}
		}

	}

	/**
	 * copy from the passed fromPic to the specified startRow and startCol in
	 * the current picture
	 * 
	 * @param fromPic
	 *            the picture to copy from
	 * @param startRow
	 *            the start row to copy to
	 * @param startCol
	 *            the start col to copy to
	 */
	public void copy(Picture fromPic, int startRow, int startCol)
	{
		Pixel fromPixel = null;
		Pixel toPixel = null;
		Pixel[][] toPixels = this.getPixels2D();
		Pixel[][] fromPixels = fromPic.getPixels2D();
		for (int fromRow = 0, toRow = startRow; fromRow < fromPixels.length && toRow < toPixels.length; fromRow++, toRow++)
		{
			for (int fromCol = 0, toCol = startCol; fromCol < fromPixels[0].length && toCol < toPixels[0].length; fromCol++, toCol++)
			{
				fromPixel = fromPixels[fromRow][fromCol];
				toPixel = toPixels[toRow][toCol];
				toPixel.setColor(fromPixel.getColor());
			}
		}
	}

	public void copyNoBlend(Picture fromPic, int startRow, int startCol, int endRow, int endCol, int toRow, int toCol)
	{
		Pixel fromPixel = null;
		Pixel toPixel = null;
		Pixel[][] toPixels = this.getPixels2D();
		Pixel[][] fromPixels = fromPic.getPixels2D();
		for (int fromRow = startRow; fromRow < endRow; fromRow++, toRow++)
		{
			for (int fromCol = startCol; fromCol <= endCol; fromCol++, toCol++)
			{
				fromPixel = fromPixels[fromRow][fromCol];
				toPixel = toPixels[toRow][toCol];
				// toRow++;
				// toCol++;
				toPixel.setColor(fromPixel.getColor());
			}
		}
	}

	public void Coolage()
	{
		Picture SnowMan = new Picture("");
	}

	public void copy(Picture fromPic, int startRow, int startCol, int endRow, int endCol, int toRow, int toCol)
	{
		Pixel fromPixel = null;
		Pixel toPixel = null;
		Pixel[][] toPixels = this.getPixels2D();
		Pixel[][] fromPixels = fromPic.getPixels2D();
		int norm = toCol;
		for (int fromRow = startRow; fromRow < endRow; fromRow++, toRow++)
		{
			for (int fromCol = startCol, toCol1 = norm; fromCol < endCol; fromCol++, toCol1++)
			{
				// System.out.println(fromCol+","+toCol);
				// System.out.println(toCol);
				fromPixel = fromPixels[fromRow][fromCol];
				toPixel = toPixels[toRow][toCol1];
				if (toPixel.getColor().equals(Color.white))
				{
					// System.out.println(toPixel.getColor());
					toPixel.setColor(fromPixel.getColor());
				} else
				{
					// System.out.println("ok");
					Color blend = new Color(((toPixel.getRed() + fromPixel.getRed()) / 2), ((toPixel.getGreen() + fromPixel.getGreen()) / 2), ((toPixel.getBlue() + fromPixel.getBlue()) / 2));
					toPixel.setColor(blend);
				}
			}
		}
	}

	/** Method to create a collage of several pictures */
	public void createCollage()
	{
		Picture flower1 = new Picture("flower1.jpg");
		Picture flower2 = new Picture("flower2.jpg");
		this.copy(flower1, 0, 0);
		this.copy(flower2, 100, 0);
		this.copy(flower1, 200, 0);
		Picture flowerNoBlue = new Picture(flower2);
		flowerNoBlue.zeroBlue();
		this.copy(flowerNoBlue, 300, 0);
		this.copy(flower1, 400, 0);
		this.copy(flower2, 500, 0);
		this.mirrorVertical();
		this.write("collage.jpg");
	}

	public void coolage()
	{
		int line = 45;
		Picture pic = new Picture("arch.jpg");
		// pic.edgeDetection(line);
		Picture pic2 = new Picture("femaleLionAndHall.jpg");
		// pic2.edgeDetection(line);
		Picture pic3 = new Picture("koala.jpg");
		// pic3.edgeDetection(line);
		Picture pic4 = new Picture("swan.jpg");
		// pic4.edgeDetection(line);
		Picture pic5 = new Picture("temple.jpg");
		// pic5.edgeDetection(line);
		for (int index = 0; index < 10; index++)
		{
			int[] pakaged = RandGenerate(pic.getHeight(), pic.getWidth());
			copy(pic, pakaged[0], pakaged[2], pakaged[1], pakaged[3], pakaged[5], pakaged[4]);
			pakaged = RandGenerate(pic3.getHeight(), pic3.getWidth());
			copy(pic3, pakaged[0], pakaged[2], pakaged[1], pakaged[3], pakaged[5], pakaged[4]);
			pakaged = RandGenerate(pic4.getHeight(), pic4.getWidth());
			copy(pic4, pakaged[0], pakaged[2], pakaged[1], pakaged[3], pakaged[5], pakaged[4]);
			pakaged = RandGenerate(pic5.getHeight(), pic5.getWidth());
			copy(pic5, pakaged[0], pakaged[2], pakaged[1], pakaged[3], pakaged[5], pakaged[4]);
			pakaged = RandGenerate(pic2.getHeight(), pic2.getWidth());
			copy(pic2, pakaged[0], pakaged[2], pakaged[1], pakaged[3], pakaged[5], pakaged[4]);
		}
	}

	private int[] RandGenerate(int row, int col)
	{
		Random rand = new Random();
		int fromStartRow = rand.nextInt(row);
		int fromEndRow = rand.nextInt((row) - fromStartRow) + fromStartRow;
		int fromStartCol = rand.nextInt(col);
		int fromEndCol = rand.nextInt((col) - fromStartCol) + fromStartCol;
		int toCol = rand.nextInt(635 - (fromEndCol - fromStartCol));

		int toRow = rand.nextInt(477 - (fromEndRow - fromStartRow));
		int[] packaged = { fromStartRow, fromEndRow, fromStartCol, fromEndCol, toCol, toRow };
		return packaged;

	}

	/**
	 * Method to show large changes in color
	 * 
	 * @param edgeDist
	 *            the distance for finding edges
	 */
	public void edgeDetection(int edgeDist)
	{
		Pixel leftPixel = null;
		Pixel rightPixel = null;
		Pixel[][] pixels = this.getPixels2D();
		Color rightColor = null;
		for (int row = 0; row < pixels.length - 1; row++)
		{
			for (int col = 0; col < pixels[0].length - 1; col++)
			{
				leftPixel = pixels[row][col];
				rightPixel = pixels[row][col + 1];
				rightColor = rightPixel.getColor();
				if (leftPixel.colorDistance(rightColor) > edgeDist)
				{
					leftPixel.setColor(Color.BLACK);
				} else
				{
					leftPixel.setColor(Color.WHITE);
				}
			}
		}
	}

	public void edgeDetection2(int edgeDist)
	{
		Pixel leftPixel = null;
		Pixel rightPixel = null;
		Pixel bottomPixel = null;
		Pixel diagonalPixel = null;
		Pixel[][] pixels = this.getPixels2D();
		Color rightColor = null;
		for (int row = 0; row < pixels.length - 1; row++)
		{
			for (int col = 0; col < pixels[0].length - 1; col++)
			{
				leftPixel = pixels[row][col];
				rightPixel = pixels[row][col + 1];
				bottomPixel = pixels[row + 1][col];
				diagonalPixel = pixels[row + 1][col];
				rightColor = rightPixel.getColor();
				if (leftPixel.colorDistance(rightColor) > edgeDist)
				{
					leftPixel.setColor(Color.BLACK);
				} else if (leftPixel.colorDistance(bottomPixel.getColor()) > edgeDist)
				{
					leftPixel.setColor(Color.BLACK);
				} else if (leftPixel.colorDistance(diagonalPixel.getColor()) > edgeDist)
				{
					leftPixel.setColor(Color.BLACK);
				} else
				{
					// leftPixel.setColor(Color.WHITE);
				}

			}
		}
	}

	public void makeMeme(String topText, String botText, Color color, String font, int size)
	{
		try
		{
			this.addMessage(topText, this.getWidth() / 2, 0, font, Font.BOLD, color, size, true);
			this.addMessage(botText, this.getWidth() / 2, 0, font, Font.BOLD, color, size, false);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void makeMeme(String topText, String botText, Color color, String font, int size, String name)
	{
		try
		{
			this.addMessage(topText, this.getWidth() / 2, 0, font, Font.BOLD, color, size, true);
			this.addMessage(botText, this.getWidth() / 2, 0, font, Font.BOLD, color, size, false);
			this.write(name);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void makeMemeRainbowTxt(String topText, String botText, String font, int size, String name)
	{
		try
		{
			int red = (int) (Math.random() * 255);
			int green = (int) (Math.random() * 255);
			int blue = (int) (Math.random() * 255);
			Color color = new Color(red, green, blue);
			this.addMessage(topText, this.getWidth() / 2, 0, font, Font.BOLD, color, size, true);
			red = (int) (Math.random() * 255);
			green = (int) (Math.random() * 255);
			blue = (int) (Math.random() * 255);
			color = new Color(red, green, blue);
			this.addMessage(botText, this.getWidth() / 2, 0, font, Font.BOLD, color, size, false);
			this.write(name);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/*
	 * Main method for testing - each class in Java can have a main method
	 */
	public static void main(String[] args)
	{
		Picture beach = new Picture("beach.jpg");
		beach.explore();
		beach.zeroBlue();
		beach.explore();
	}

	public String makeDank(int size)
	{
		if (size < 0)
		{
			size = 1;
		}
		String[] alphabet = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", " " };
		String[] dankArray = new String[size];
		if (size >= alphabet.length)
		{
			return (makeDank(alphabet.length - 1) + makeDank(size - (alphabet.length) + 1));
		}
		if (size < 0)
		{
			size = 1;
		}
		for (int index = 0; index < dankArray.length; index++)
		{
			int rand = (int) (Math.random() * alphabet.length);
			dankArray[index] = alphabet[rand];
		}
		for (int index = 0; index < dankArray.length; index++)
		{
			boolean isUnique = false;
			while (!isUnique)
			{
				isUnique = true;
				for (int index2 = 0; index2 < dankArray.length; index2++)
				{

					if (index == index2)
					{

					} else if (dankArray[index].equals(dankArray[index2]))
					{
						int rand = (int) (Math.random() * alphabet.length);
						dankArray[index] = alphabet[rand];
						isUnique = false;
					}
				}
			}
		}
		String dankString = "";
		for (int index = 0; index < dankArray.length; index++)
		{
			dankString += dankArray[index];
		}

		if (dankString.equals("dank"))
		{
			System.out.println("OH BABY A TRIPLE!");
		}

		return dankString;
	}

} // this } is the end of class Picture, put all new methods before this
