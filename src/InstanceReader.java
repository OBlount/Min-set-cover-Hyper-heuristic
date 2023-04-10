import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

public class InstanceReader
{
    private final File file;

    InstanceReader(String fileName)
    {
        this.file = new File("./src/resources/" + fileName + ".txt");
    }

    /**
     * Gets the first line in the formatted data txt file. It scans the first line, and will exit the program if the
     * file doesn't exist.
     * @return The first line as a string.
     */
    private String getHeaders()
    {
        try
        {
            Scanner scanner = new Scanner(this.file);
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

    /**
     * Parses the first number in the header (first line of the file).
     * @return The first number as an int.
     */
    public int GetHeadersM()
    {
        String[] header = getHeaders().split(" ");
        return Integer.parseInt(header[1]);
    }

    /**
     * Parses the second number in the header (first line of the file).
     * @return The second number as an int.
     */
    public int GetHeadersN()
    {
        String[] header = getHeaders().split(" ");
        return Integer.parseInt(header[2]);
    }

    /**
     * Gets the desited "block" of data. The txt file is formatted such that each "subset" of the MIN-SET-COVER problem
     * is in "blocks". This function, given an index, will find and return that block. Will exit the program if the file
     * cannot be opened.
     * @param indexBlock The number correlating to the block.
     * @return A vector of integers present within that subset/block.
     */
    public Vector<Integer> GetSubsetBlock(int indexBlock)
    {
        try
        {
            int lineNumber = 0;
            Scanner scanner = new Scanner(this.file);
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
