import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

public class InstanceReader
{
    private final File mFile;

    InstanceReader(String fileName)
    {
        this.mFile = new File("./src/resources/" + fileName + ".txt");
    }

    private String getHeaders()
    {
        try
        {
            Scanner scanner = new Scanner(this.mFile);
            String headers = scanner.nextLine();
            scanner.close();
            return headers;
        } catch(FileNotFoundException e)
        {
            System.out.println("[ERROR] - File not found");
            System.out.println("[EXITING]");
            System.exit(1);
            return null;
        }
    }

    public int GetHeadersM()
    {
        String[] header = getHeaders().split(" ");
        return Integer.parseInt(header[1]);
    }

    public int GetHeadersN()
    {
        String[] header = getHeaders().split(" ");
        return Integer.parseInt(header[2]);
    }

    public Vector<Integer> GetSubsetBlock(int indexBlock)
    {
        try
        {
            int lineNumber = 0;
            Scanner scanner = new Scanner(this.mFile);
            // Skip headers:
            scanner.nextLine();
            // Skip blocks until cursor is at the start of the desired block:
            String cursor = scanner.nextLine();
            while(lineNumber < indexBlock)
            {
                if(scanner.hasNextLine())
                {
                    cursor = scanner.nextLine();
                    if(cursor.length() < 6)
                        lineNumber++;
                }
            }
            // Allocate the size of the subset to the new vector:
            int sizeOfSubsetBlock = Integer.parseInt(cursor.split(" ")[1]);
            Vector<Integer> subsetBlock = new Vector<>(sizeOfSubsetBlock);
            // Add the desired block to the vector which will be returned:
            boolean isCursorOutOfBlock = false;
            while(!isCursorOutOfBlock)
            {
                if(scanner.hasNextLine())
                    cursor = scanner.nextLine();
                else
                    isCursorOutOfBlock = true;
                // Currently any lines less than 6 chars are deemed to be "next block":
                if(cursor.length() > 6)
                {
                    String[] elements = cursor.split(" ");
                    for(int i = 1; i < elements.length; ++i)
                        subsetBlock.add(Integer.parseInt(elements[i]));
                }
                else
                    isCursorOutOfBlock = true;
            }
            scanner.close();
            return subsetBlock;
        } catch(FileNotFoundException e)
        {
            System.out.println("[ERROR] - File not found");
            System.out.println("[EXITING]");
            System.exit(1);
            return null;
        }
    }
}
