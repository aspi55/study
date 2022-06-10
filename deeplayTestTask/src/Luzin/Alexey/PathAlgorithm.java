package Luzin.Alexey;

import java.util.List;

public class PathAlgorithm {

    private int s = 0;
    private int w = 0;
    private int t = 0;
    private int p = 0;



    public int findHalfPath(List<Integer> arryField){
        int bestPath=Integer.MAX_VALUE;
        int FirstHalf [] = new int [8];
        //**best values
        int AD = Integer.MAX_VALUE;
        int AG = Integer.MAX_VALUE;
        int AJ = Integer.MAX_VALUE;
        int AM = Integer.MAX_VALUE;
        //****
        int arrAG [] = new int [3];//AG
        int arrAJ [] = new int [3];//AJ
        for(int i = 0; i<8; i++) {
            if (i < 4)
                FirstHalf[i] += arryField.get(1);//to point AB
            if (i >= 4 && i < 8)
                FirstHalf[i] += arryField.get(4);//to point AE
            if (i < 2)
                FirstHalf[i] += arryField.get(2);//to point AC
            if (i >= 2 && i < 6)
                FirstHalf[i] += arryField.get(5);//to point AF
            if (i >= 6 && i < 8)
                FirstHalf[i] += arryField.get(8); //to point Ai
        }
        for (int G = 0; G<arrAJ.length; G++)
            arrAG[G] = arryField.get(6);
        AD = FirstHalf[0] +arryField.get(3);//AD
        AM = FirstHalf[7] + arryField.get(12);//AM
        arrAG [0] += FirstHalf[1]; //AG1
        arrAG [1] += FirstHalf[2]; //AG2
        arrAG [2] += FirstHalf[4]; //AG3
        for (int J = 0; J<arrAJ.length; J++)
            arrAJ [J] = arryField.get(9);
        arrAJ [0] += FirstHalf[3]; //AJ1
        arrAJ [1] += FirstHalf[5]; //AJ2
        arrAJ [2] += FirstHalf[6]; //AJ3

        for (int i = 0; i < 3; i++) {//best AG & AJ
            if (AG >= arrAG[i])
                AG = arrAG[i];
            if (AJ >= arrAJ[i])
                AJ = arrAJ[i];
        }
        int secondPath [] = new int [4];
        for(int i = 0; i<secondPath.length; i++) {
            secondPath[i] = arryField.get(15);
            if (i < 2)
                secondPath[i] += arryField.get(11);
            if (i >= 2 && i < 4)
                secondPath[i] += arryField.get(14);
        }
        int PH = secondPath[0] + arryField.get(7);//PH
        int PK = arryField.get(10);
        PK = Math.min((PK + secondPath[1]), (PK + secondPath[2]));
        int PN =secondPath[3] + arryField.get(13);//PN
        int completePath [] = new int [6];
        completePath [0] = PH+AD;
        completePath [1] = PH+AG;
        completePath [2] = PK+AG;
        completePath [3] = PK+AJ;
        completePath [4] = PN+AJ;
        completePath [5] = PN+AM;


        for(int i = 0; i<completePath.length; i++){
            if(bestPath>=completePath[i])
                bestPath = completePath[i];
        }
        return bestPath;



    }

}
