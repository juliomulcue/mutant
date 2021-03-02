package com.xmenms.utils;

import org.springframework.stereotype.Component;

import static com.xmenms.constant.MutantConstant.HEIGHT_SEQUENCE;

@Component
public class StructureUtils {

    public char[] getColumn(char [][] sequenceADN, int column){
        char[] sequenceHeight = new char[HEIGHT_SEQUENCE];

        for (int i=0; i<HEIGHT_SEQUENCE; i++) {
            sequenceHeight[i] = sequenceADN[i][column];
        }

        return sequenceHeight;
    }

    public char[][] getSubMatriz(int fileIni, int fileEnd, int colIni, int colEnd, char[][] sequenceADN) {

        char[][] subMatriz = new char[4][4];
        int file = 0;
        int col = 0;

        for (int i = fileIni; i < fileEnd; i++) {

            for (int y = colIni; y < colEnd; y++) {

                subMatriz[file][col] = sequenceADN[i][y];
                col++;
            }
            col=0;
            file++;
        }

        return subMatriz;
    }
}
