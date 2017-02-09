package pixLab.classes;

import java.awt.Color;

/**
 * This class contains class (static) methods
 * that will help you test the Picture class 
 * methods.  Uncomment the methods and the code
 * in the main to test.
 * 
 * @author Geran Kunz
 */
public class PictureTester
{
  /** Method to test zeroBlue */
  public static void testZeroBlue()
  {
    Picture beach = new Picture("beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
    beach = new Picture("beach.jpg");
    beach.explore();
    beach.onlyBlue();
    beach.explore();
    beach = new Picture("beach.jpg");
    beach.explore();
    beach.negate();
    beach.explore();
    beach = new Picture("beach.jpg");
    beach.explore();
    beach.greyScale();
    beach.explore();
  }
  
  /** Method to test mirrorVertical */
  public static void testMirrorVertical()
  {
    Picture caterpillar = new Picture("caterpillar.jpg");
    caterpillar.explore();
    caterpillar.mirrorVertical();
    caterpillar.explore();
  }
  
  /** Method to test mirrorTemple */
  public static void testMirrorTemple()
  {
    Picture temple = new Picture("beach.jpg");
    temple.explore();
    temple.mirrorTemple();
    temple.explore();
  }
  
  /** Method to test the collage method */
  public static void testCollage()
  {
    Picture canvas = new Picture("640x480.jpg");
    canvas.createCollage();
    canvas.explore();
  }
  
  /** Method to test edgeDetection */
  public static void testEdgeDetection()
  {
    Picture swan = new Picture("swan.jpg");
    swan.edgeDetection(10);
    swan.explore();
  }
  public static void testMirrorVerticalRightToLeft()
  {
	  String registry = "kitten2.jpg";
	  Picture kola = new Picture(registry);
	  kola.explore();
	  kola.mirrorVerticalRightToLeft();
	  kola.explore();
	  kola = new Picture(registry);
	  kola.mirrorHorizontaBottomToTop();
	  kola.explore();
	  kola = new Picture(registry);
	  kola.mirrorHorizontaTopToBottom();
	  kola.explore();
	  kola = new Picture(registry);
	  kola.mirrorVerticalRightToLeft();
	  kola.explore();
	  kola = new Picture(registry);
	  kola.mirrorVerticalRightToLeft();
	  kola.mirrorHorizontaTopToBottom();
	  kola.mirrorVertical();
	  kola.explore();
	  kola.write("/Users/gkun9931/Desktop/Pictures/dankoala.jpg");
  }
  
  public static void testDiagonal()
  {
	  String registry = "redMotorCycle.jpg";

//	  Picture picture = new Picte(registry);
//	  picture.explore();
//	  picture.mirrorDiagonalRightBottomToTop();
//	  picture.explore();
//	  picture.explore();
//	  picture.mirrorDiagonalTopToBottom();
//	  picture.explore();
//	  picture = new Picture(registry);
//	  picture.mirrorDiagonalBottomToTop();
//	  picture.explore();
//
	 Picture picture = new Picture(registry);
	  picture.goDerp();
	  picture.mirrorDiagonalBottomToTop();
	  picture.mirrorDiagonalRightTopToBottom();
	  picture.mirrorVertical();
	  picture.mirrorDiagonalTopToBottom();
	  picture.mirrorHorizontaBottomToTop();
	  picture.mirrorVerticalRightToLeft();
	  picture.greyScale();
 	  picture.negate();
  picture.mirrorHorizontaBottomToTop();
  picture.mirrorVerticalRightToLeft();
	 // picture.greyScale();
//	  picture.negate();


	  picture.negate();
 picture.explore();
 picture.write("MessedUpMotorCycle.jpg");
  }
  
  public static void testCollage1()
  {
	  Picture base = new Picture("640X480.jpg");
	  base.coolage();
	  base.explore();
  }
  
  public static void testFixUnderwater()
  {
	  Picture water = new Picture("redMotorcycle"
	  		+ ".jpg");
	  water.explore();
	  water.fixWater();
	  water.explore();
  }
  /** Main method for testing.  Every class can have a main
    * method in Java */
  public static void main(String[] args)
  {
    // uncomment a call here to run a test
    // and comment out the ones you don't want
    // to run
    //testZeroBlue();
    //testKeepOnlyBlue();
    //testKeepOnlyRed();
    //testKeepOnlyGreen();
    //testNegate();
    //testGrayscale();
    //testFixUnderwater();
	 // testDiagonal();

    //testMirrorVertical();
    //testMirrorTemple();
    //testMirrorArms();
    //testMirrorGull();
    //testMirrorDiagonal();
    //testCollage();
    //testCopy();
    //testEdgeDetection();
    //testEdgeDetection2();
    //testChromakey();
    //testEncodeAndDecode();
    //testGetCountRedOverValue(250);
    //testSetRedToHalfValueInTopHalf();
    //testClearBlueOverValue(200);
    //testGetAverageForColumn(0);
	 //  testMirrorVerticalRightToLeft();
	 // testCollage1();
	  testMeme();
  }

private static void testMeme()
{
	Picture swan = new Picture("640X480.jpg");
	swan.coolage();
	
	swan.makeMeme("Valantines is fun so this is a swan meme cause valintines", "Thats all i got you may go now. Really the meme is over, what are you still doing here go!", new Color(200,0,150),"Giddyup Std", 30,"DankMeme.jpg");
	swan.explore();
}

private static void testEdgeDetection2()
{

    Picture swan = new Picture("blueMotorcycle.jpg");
//    swan.edgeDetection2(15);
//    swan.explore();
    swan.goDerp();
    swan.explore();
}

private static void testMirrorArms()
{
	Picture snowMan = new Picture("snowman.jpg");
	snowMan.explore();
	snowMan.customMirror(true, 106, 155, 168, 191,null);
	snowMan.customMirror(true, 237, 173, 297, 198,null);
	snowMan.explore();
	
}

private static void testMirrorGull()
{
	Picture gull = new Picture("seagull.jpg");
	gull.explore();
	int[] test = {new Color(109,116,126).getRGB(),10,new Color(171,157,144).getRGB(),20,new Color(150,144,130).getRGB(),20,new Color(102,113,119).getRGB(),8,new Color(110,115,118).getRGB(),8};
	gull.customMirror(false, 234, 226, 346, 329,test);
	gull.explore();
	
}
}