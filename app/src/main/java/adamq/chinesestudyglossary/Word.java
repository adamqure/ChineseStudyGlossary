package adamq.chinesestudyglossary;

/**
 * Created by adamq on 2017/5/4.
 */

public class Word {
    String zhCharacter = "";
    String engDefinition = "";
    String sourceText = "";
    String partOfSpeech = "";
    String pinyin = "";

    // Constructor
    public Word(String zhCharacter, String engDefinition, String pinyin, String partOfSpeech, String sourceText) {
        this.zhCharacter = zhCharacter;
        this.engDefinition = engDefinition;
        this.sourceText = sourceText;
        this.partOfSpeech = partOfSpeech;
        this.pinyin = pinyin;
    }

    //Getters and Setters
    public String getZhCharacter() {
        return zhCharacter;
    }

    public void setZhCharacter(String zhCharacter) {
        this.zhCharacter = zhCharacter;
    }

    public String getEngDefinition() {
        return engDefinition;
    }

    public void setEngDefinition(String engDefinition) {
        this.engDefinition = engDefinition;
    }

    public String getSourceText() {
        return sourceText;
    }

    public void setSourceText(String sourceText) {
        this.sourceText = sourceText;
    }

    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    public void setPartOfSpeech(String partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }
}


