package problemInstance;

import java.io.FileWriter;
import java.io.IOException;

public class InstanceFileWriter
{
    private final String fileName;
    private final FileWriter writer;

    public InstanceFileWriter(String name) throws IOException {
        this.fileName = "./output/" + name;
        this.writer = new FileWriter(this.fileName);
    }

    public void WriteIteration(long iterationNumber, int bestSolutionScore, int currentSolutionScore)
    {
        try
        {
            this.writer.write("Iteration #" + (iterationNumber + 1) + ": \n");
            this.writer.write(
                    "Best Solution Score: " + bestSolutionScore +
                    "\t\tCurrent Solution Score: " + currentSolutionScore +
                    "\n\r");
        } catch(IOException e) {
            System.out.println("[ERROR] - File `" + this.fileName + "` can't be written to\n\r[EXITING]");
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void CloseFile() throws IOException
    {
        this.writer.close();
    }
}
