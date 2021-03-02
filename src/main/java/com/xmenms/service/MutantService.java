package com.xmenms.service;

import com.xmenms.models.Mutant;
import com.xmenms.repository.MutantRepository;
import com.xmenms.response.StatsResponse;
import com.xmenms.utils.StructureUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.xmenms.constant.MutantConstant.*;

@Service
public class MutantService {

    @Autowired
    private StructureUtils structureUtils;

    @Autowired
    private MutantRepository mutantRepository;

    public boolean isMutant(List<String> adn) {

        char [][] sequenceADN = new char[HEIGHT_SEQUENCE][WIDTH_SEQUENCE];
        boolean isMutant = false;

        getSequenceADN(adn, sequenceADN);

        int charSequenceWidth = analyzeWidthSequence(sequenceADN);
        int charSequenceHeight = analyzeHeightSequence(sequenceADN);
        int charSequenceDiagonal = analyzeDiagonalSequence(sequenceADN);

        isMutant = (charSequenceWidth + charSequenceHeight + charSequenceDiagonal) > 1;

        if( mutantRepository.findByandSequence(adn.toString()).size() == 0 ) {
            mutantRepository.save( new Mutant(adn.toString(), isMutant));
        }

        return isMutant;
    }

    public StatsResponse getStats(){

        StatsResponse statsResponse = new StatsResponse();

        List<Mutant> mutants = mutantRepository.findByIsMutant(true);
        List<Mutant> humans = mutantRepository.findByIsMutant(false);

        if( mutants.size()>0 ) {

            double ratio = (double) mutants.size() / (double) humans.size();

            statsResponse.setCountHumanDna(humans.size());
            statsResponse.setCountMutatnDna(mutants.size());
            statsResponse.setRatio(ratio);

        }

        return statsResponse;
    }

    private void getSequenceADN(List<String> adn, char [][] sequenceADN) {

        for (int i=0; i<adn.size(); i++) {
            sequenceADN[i] = adn.get(i).toCharArray();
        }
    }

    private int analyzeWidthSequence(char [][] sequenceADN) {
        int sequenceFourChar = 0;

        for (int i=0; i<sequenceADN.length; i++) {
            if(containSequenceFouChar(sequenceADN[i])) {
                sequenceFourChar++;
            }
        }

        return sequenceFourChar;
    }

    private int analyzeHeightSequence(char [][] sequenceADN) {
        int sequenceFourChar = 0;

        for (int i=0; i<sequenceADN.length; i++) {

            if(containSequenceFouChar(structureUtils.getColumn(sequenceADN, i))) {
                sequenceFourChar++;
            }
        }

        return sequenceFourChar;
    }

    private int analyzeDiagonalSequence(char [][] sequenceADN) {
        int fileIni = SUB_SEQUENCE_INIT;
        int fileEnd = SUB_SEQUENCE_END;
        int colIni = SUB_SEQUENCE_INIT;
        int colEnd = SUB_SEQUENCE_END;
        int sequenceFourChar = 0;


        while (fileEnd<WIDTH_SEQUENCE) {

            while(colEnd<HEIGHT_SEQUENCE) {

                boolean containSequense = false;

                char[][] subMatriz = structureUtils.getSubMatriz(fileIni, fileEnd, colIni, colEnd, sequenceADN);
                containSequense = containSequenceDiagonal(subMatriz);

                if(containSequense) {
                    sequenceFourChar++;
                }

                colIni++;
                colEnd++;
            }
            colIni = SUB_SEQUENCE_INIT;
            colEnd = SUB_SEQUENCE_END;
            fileIni++;
            fileEnd++;

        }

        return sequenceFourChar;
    }

    private boolean containSequenceDiagonal(char[][] sequenceADN) {

        char[] sequenceDiagonal = new char[4];

        sequenceDiagonal[0] = sequenceADN[0][0];
        sequenceDiagonal[1] = sequenceADN[1][1];
        sequenceDiagonal[2] = sequenceADN[2][2];
        sequenceDiagonal[3] = sequenceADN[3][3];

        return containSequenceFouChar(sequenceDiagonal);
    }

    private boolean containSequenceFouChar(char[] charSequence) {

        boolean isSequence = true;

        int baseChar = SUB_SEQUENCE_INIT;
        int endChar = SUB_SEQUENCE_END;
        int longCharSequense = charSequence.length;

        while( endChar<=longCharSequense ) {

            isSequence = true;

            for (int i=baseChar+1; i<endChar; i++) {
                if(charSequence[baseChar]!=charSequence[i]){
                    isSequence = false;
                    break;
                }
            }

            if(!isSequence) {
                baseChar++;
                endChar++;

            } else {
                endChar = longCharSequense+1;
            }

        }

        return isSequence;
    }

}
