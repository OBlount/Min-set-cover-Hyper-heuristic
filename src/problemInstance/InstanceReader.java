package problemInstance;

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
     * Reads through the given data file and adds each individual subset into the vector passed in.
     * @param arrayOfSubsets The array you wish to populate.
     */
    public void PopulateSubsetVector(Vector<Vector<Integer>> arrayOfSubsets)
    {
        Scanner scanner;
        try
        {
            scanner = new Scanner(this.file);
        } catch(FileNotFoundException e)
        {
            System.out.println("[ERROR] - File not found\n\r[EXITING]");
            System.exit(1);
            return;
        }
        // Skip headers:
        scanner.nextLine();
        int currentNumberOfElementsToRead;
        while(scanner.hasNextLine())
        {
            // Read the line containing the number of elements found on the next subset block:
            String cursor = scanner.nextLine();
            currentNumberOfElementsToRead = Integer.parseInt(cursor.split(" ")[1]);
            Vector<Integer> subsetBlock = new Vector<>(currentNumberOfElementsToRead);
            // Loop through each number for the next "currentNumberOfElementsToRead" times:
            for(int i = 0; i < currentNumberOfElementsToRead; ++i)
            {
                cursor = scanner.next();
                subsetBlock.add(Integer.parseInt(cursor));
            }
            arrayOfSubsets.add(subsetBlock);
            if(scanner.hasNextLine())
                scanner.nextLine();
        }
        scanner.close();
    }
}
