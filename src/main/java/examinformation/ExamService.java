package examinformation;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class ExamService {
    private final static double EXAM_LIMIT = 0.5;

    private int theoryMax;
    private int practiceMax;
    private Map<String, ExamResult> results = new TreeMap<>();

    public int getTheoryMax() {
        return theoryMax;
    }

    public void setTheoryMax(int theoryMax) {
        this.theoryMax = theoryMax;
    }

    public int getPracticeMax() {
        return practiceMax;
    }

    public void setPracticeMax(int practiceMax) {
        this.practiceMax = practiceMax;
    }

    public Map<String, ExamResult> getResults() {
        return results;
    }

    public List<String> findPeopleFailed() {
        List<String> result = new ArrayList<>();

        for (Map.Entry<String, ExamResult> element : results.entrySet()){
            if ((element.getValue().getTheory() <= (getTheoryMax() * EXAM_LIMIT)) ||
                    (element.getValue().getPractice() <= (getTheoryMax() * EXAM_LIMIT))) {
                result.add(element.getKey());
            }
        }
        return result;
    }

    public String findBestPerson() {
        List<String> result = new ArrayList<>();
        int maxResult = 0;
        for (Map.Entry<String, ExamResult> element : results.entrySet()) {
            if ((element.getValue().getTheory() > (getTheoryMax() * EXAM_LIMIT)) &&
                    (element.getValue().getPractice() > (getTheoryMax() * EXAM_LIMIT))) {
                int strudentResult =  element.getValue().getPractice()+element.getValue().getTheory();
                if (maxResult<strudentResult){
                    maxResult = strudentResult;
                }
            }
        }

        for (Map.Entry<String, ExamResult> element : results.entrySet()) {
            if ((element.getValue().getTheory() > (getTheoryMax() * EXAM_LIMIT)) &&
                    (element.getValue().getPractice() > (getTheoryMax() * EXAM_LIMIT))) {
                int strudentResult =  element.getValue().getPractice()+element.getValue().getTheory();
                if (maxResult==strudentResult){
                    result.add(element.getKey());
                }
            }
        }
        Collections.sort(result);
        return result.get(0);
    }

    public void readFromFIle(Path path) {
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            boolean head = true;
            while ((line = br.readLine()) != null) {
                if (head) {
                    parseHead(line);
                    head = false;
                } else {
                    parseLine(line);
                }
            }
        } catch (IOException e) {
            //throw new IllegalStateException("Cannot read file!");
            throw new IllegalArgumentException("Cannot read file: " + path, e);
        }
    }

    private void parseHead(String line) {
        String[] temp = line.split(" ");
        setTheoryMax(Integer.parseInt(temp[0]));
        setPracticeMax(Integer.parseInt(temp[1]));
    }

    private void parseLine(String line) {
        String[] temp = line.split(";");
        String name = temp[0];

        String[] tempAnother = temp[1].split(" ");
        int theory = Integer.parseInt(tempAnother[0]);
        int practice = Integer.parseInt(tempAnother[1]);

        ExamResult examResult = new ExamResult(theory, practice);
        results.put(name, examResult);
    }


}
