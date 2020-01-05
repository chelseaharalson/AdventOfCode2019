package adventofcode2019;
import java.io.*;
import java.util.*;

/**
 *
 * @author chelsea
 */
public class Day8 {
    
    	public static void main(String[] args) throws FileNotFoundException {
            File file = new File("input8.txt");
            Scanner scanner = new Scanner(file);

            String input = scanner.nextLine();
            scanner.close();

            int imgWidth = 25;
            int imgHeight = 6;

            int imgSize = imgWidth * imgHeight;

            int layers = input.length()/imgSize;

            System.out.println("There are " + layers + " layers");

            int[][][] image = new int[layers][imgHeight][imgWidth];

            int i = 0;
            for (int layer = 0; layer < layers; layer++) {
                for (int height=0; height < imgHeight; height++) {
                    for (int width = 0; width < imgWidth; width++) {
                        image[layer][height][width] = Character.getNumericValue(input.charAt(i));
                        i++;
                    }
                }
            }
		
            int layerOfFewestZeros = layerWithFewestZeroes(image, layers, imgWidth, imgHeight);

            System.out.println("Fewest number of zeroes is on layer " + layerOfFewestZeros);

            System.out.println("Verify layer calculation " + verifyImageCalculation(image, layerOfFewestZeros, imgWidth, imgHeight));

            int[][] finalImage = decodeImage(image, layers, imgWidth, imgHeight);

            System.out.println("FINAL IMAGE:");
            printImage(finalImage, imgWidth, imgHeight);
	}
	
	static int layerWithFewestZeroes(int[][][] image, int layers, int imgWidth, int imgHeight) {
            int minNumberOfZeroes = 99999;
            int layerWithFewestZeroes = -1;

            for (int layer = 0; layer < layers; layer++) {
                int numOfZeros = 0;
                for (int height = 0; height <imgHeight; height++) {
                    for (int width = 0; width < imgWidth; width++) {
                        if (image[layer][height][width] == 0) {
                            numOfZeros++;
                        }
                    }
                }

                if (numOfZeros < minNumberOfZeroes) {
                    layerWithFewestZeroes = layer;
                    minNumberOfZeroes = numOfZeros;
                }
            }

            return layerWithFewestZeroes;
        }

    static int verifyImageCalculation(int[][][] image, int layer, int imgWidth, int imgHeight) {
        int numberOfOnes = 0;
        int numberOfTwos = 0;

        for (int height = 0; height < imgHeight; height++) {
                for (int width = 0; width < imgWidth; width++) {
                    if (image[layer][height][width] == 1) {
                        numberOfOnes++;
                    }
                    if (image[layer][height][width] == 2) {
                        numberOfTwos++;
                    }
                }
        }

        return numberOfOnes*numberOfTwos;
    }

    static int[][] decodeImage (int[][][] image, int layers, int imgWidth, int imgHeight) {
        int[][] finalImage = new int[imgHeight][imgWidth];

        for (int width = 0; width < imgWidth; width++) {
            for (int height = 0; height < imgHeight; height++) {
                for (int layer = 0; layer < layers; layer++) {  // Only need to go certain amt of layers
                    if (image[layer][height][width] != 2) {
                        finalImage[height][width] = image[layer][height][width];
                        System.out.println(width + " " + height + " " + layer);
                        break;
                    }
                }
            }
        }
        return finalImage;
    }

    static void printImage(int[][] image, int img_width, int img_height) {
        for (int height=0; height<img_height; height++) {
            for (int width=0; width<img_width; width++) {
                if (image[height][width] == 0) {
                    System.out.print(' ');
                }
                if (image[height][width] == 1) {
                    System.out.print('X');
                }
            }
            System.out.print('\n');
        }
    }
}
