package pixLab.classes;
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
    Picture temple = new Picture("temple.jpg");
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
	  String registry = "redMotorcycle.jpg";
//	  Picture picture = new Picture(registry);
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
	  picture.mirrorDiagonalBottomToTop();
	  picture.mirrorDiagonalRightTopToBottom();
	  picture.mirrorVertical();
	  picture.mirrorDiagonalTopToBottom();
	  picture.mirrorHorizontaBottomToTop();
	  picture.mirrorVerticalRightToLeft();
	  picture.greyScale();
	  picture.negate();
	  picture.explore();
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
	  testDiagonal();
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
  }
}