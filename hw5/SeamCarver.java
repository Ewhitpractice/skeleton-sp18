import edu.princeton.cs.algs4.Picture;
public class SeamCarver {
    //Instance variables
    private static final boolean horizontalOrientation = false;
    private static final boolean verticalOrientation = true;

    private Picture currentPicture;

    private boolean orientation;
    private int orientationHeight;
    private int orientationWidth;

    private int height;
    private int width;


    public SeamCarver(Picture picture) {
        currentPicture = picture;
        height = picture.height();
        width = picture.width();
        initiateOrientation(verticalOrientation);
    }

    public Picture picture() { return currentPicture; }

    public int width() {
        return currentPicture.width();
    }

    public int height() {
        return currentPicture.height();
    }

    /** calculates the energy of a certain pixel**/
    public double energy(int x, int y) {
        //get the column to the right and to the left and calculate delta RGB
        int rightX;
        int leftX;
        if(x == width() - 1) {rightX = 0;}
        else {rightX = x + 1;}
        if(x == 0) {leftX = width() - 1;}
        else {leftX = x - 1;}

        double RGBx = summedRGBDifferencesX(rightX, leftX, y);

        //get the rows to the right and to the left and calculate delta RGB
        int upperY;
        int lowerY;
        if(y == 0) {upperY = height() - 1;}
        else {upperY = y - 1;}
        if(y == height() - 1) {lowerY = 0;}
        else {lowerY = y + 1;}

        double RGBy = summedRGBDifferencesY(upperY, lowerY, x);

        return RGBx + RGBy;
    }


    public int[] findHorizontalSeam() {
        initiateOrientation(horizontalOrientation);
        int[] horizontalSeam = findVerticalSeam();
        initiateOrientation(verticalOrientation);
        return horizontalSeam;
    }

    /** returns an array of length H such that entry x is the column number of the pixel
     * to be removed from row x of the image
     */
    public int[] findVerticalSeam() {
        //energy values for each pixel
        double[][] energyMatrix = new double[orientationWidth][orientationHeight];
        double[][] distTo = new double[orientationWidth][orientationHeight];
        int[][] edgeTo = new int[orientationWidth][orientationHeight];

        //initialize the energy matrix
        for (int i = 0; i < orientationWidth; i++) {
            for (int j = 0; j < orientationHeight; j++) {
                if(orientation == horizontalOrientation) {
                    energyMatrix[i][j] = energy(j,i);
                }
                else {
                    energyMatrix[i][j] = energy(i, j);
                }
                if (j == 0)
                    distTo[i][j] = energyMatrix[i][j];
                else
                    distTo[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int i = 1; i < orientationHeight; i++) {
            for (int j = 0; j < orientationWidth; j++) {
                double minEnergy = distTo[j][i - 1];
                int colOfMin = j;
                if (j != 0) {
                    if (distTo[j - 1][i - 1] < minEnergy) {
                        minEnergy = distTo[j - 1][i - 1];
                        colOfMin = j - 1;
                    }
                }
                if (j != orientationWidth - 1) {
                    if (distTo[j + 1][i - 1] < minEnergy) {
                        minEnergy = distTo[j + 1][i - 1];
                        colOfMin = j + 1;
                    }
                }
                if (energyMatrix[j][i] + minEnergy < distTo[j][i]) {
                    distTo[j][i] = energyMatrix[j][i] + minEnergy;
                    edgeTo[j][i] = colOfMin;
                }
            }
        }

        //find the lowest energy in the last row with minimum distance
        double lastRowMin = Integer.MAX_VALUE;
        int colOfLastRowMin = 0;
        for(int i = 0; i < orientationHeight - 1; i++) {
            if(distTo[i][orientationHeight - 1] < lastRowMin) {
                lastRowMin = distTo[i][orientationHeight-1];
                colOfLastRowMin = i;
            }

        }

        int[] verticalSeam = new int[orientationHeight];
        for(int i = orientationHeight - 1; i >= 0; i--) {
            verticalSeam[i] = colOfLastRowMin;
            colOfLastRowMin = edgeTo[colOfLastRowMin][i];
        }
        return verticalSeam;
    }

    public void removeVerticalSeam(int[] seam) {
        Picture newPicture = new Picture(orientationWidth, height);

        for(int i = 0; i < newPicture.height(); i++) {
            for(int j = 0; j < newPicture.width(); j++) {
                if(j < seam[i]) {
                    newPicture.set(j, i, currentPicture.get(j,i));
                }
                else {
                    newPicture.set(j,i,currentPicture.get(j+1,i));
                }
            }
        }
        currentPicture = newPicture;
    }

    public void removeHorizontalSeam(int[] seam) {
        initiateOrientation(horizontalOrientation);
        removeVerticalSeam(seam);
        initiateOrientation(verticalOrientation);
    }

    //Helper methods
    private void initiateOrientation(boolean ori) {
        orientation = ori;
        //vertical orientation used for the vertical seam
        if(orientation == verticalOrientation) {
            orientationWidth = currentPicture.width();
            orientationHeight = currentPicture.height();
        }
        //horizontal orientation used for the horizontal seam
        else {
            orientationWidth = currentPicture.height();
            orientationHeight = currentPicture.width();
        }
    }

    private double summedRGBDifferencesX(int rightCol, int leftCol, int row) {
        int rgb2 = currentPicture.getRGB(rightCol, row);
        int r2 = calcRed(rgb2);
        int g2 = calcGreen(rgb2);
        int b2 = calcBlue(rgb2);

        int rgb1 = currentPicture.getRGB(leftCol, row);
        int r1 = calcRed(rgb1);
        int g1 = calcGreen(rgb1);
        int b1 = calcBlue(rgb1);

        //right column minus left column
        int Rx = Math.abs(r2 - r1);
        int Gx = Math.abs(g2 - g1);
        int Bx = Math.abs(b2 - b1);

        return Rx*Rx + Gx*Gx + Bx*Bx;
    }

    private double summedRGBDifferencesY(int upperRow, int lowerRow, int col) {
        int rgb2 = currentPicture.getRGB(col, upperRow);
        int r2 = calcRed(rgb2);
        int g2 = calcGreen(rgb2);
        int b2 = calcBlue(rgb2);

        int rgb1 = currentPicture.getRGB(col, lowerRow);
        int r1 = calcRed(rgb1);
        int g1 = calcGreen(rgb1);
        int b1 = calcBlue(rgb1);

        //upper row minus lower row
        int Ry = Math.abs(r2 - r1);
        int Gy = Math.abs(g2 - g1);
        int By = Math.abs(b2 - b1);

        return Ry*Ry + Gy*Gy + By*By;
    }

    private int calcRed(int rgb) {
        return (rgb >> 16) & 0xFF;
    }

    private int calcGreen(int rgb) {
        return (rgb >>  8) & 0xFF;
    }

    private int calcBlue(int rgb) {
        return (rgb) & 0xFF;
    }

}
