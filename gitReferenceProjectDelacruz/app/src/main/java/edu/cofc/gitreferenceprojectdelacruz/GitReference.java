package edu.cofc.gitreferenceprojectdelacruz;

/**
 * Created by kenso on 2/22/2018.
 */

public class GitReference
{
   private String command;
   private String example;
   private String explanation;
   private String section;

   public GitReference()
   {

   }

   public String getCommand()
   {
       return command;
   }

   public String getExample()
   {
       return example;
   }

    public String getExplanation() {
        return explanation;
    }

    public String getSection() {
        return section;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public void setSection(String section) {
        this.section = section;
    }
}
