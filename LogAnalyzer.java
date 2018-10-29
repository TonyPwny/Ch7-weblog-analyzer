import java.util.Iterator;

/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version    2016.02.29
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer()
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        // Create the reader to obtain the data.
        reader = new LogfileReader();
    }
    
        public LogAnalyzer(String filename)
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        // Create the reader to obtain the data.
        reader = new LogfileReader(filename);
    }

    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }

    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
        System.out.println("Hr: Count");
        for(int hour = 0; hour < hourCounts.length; hour++) {
            System.out.println(hour + ": " + hourCounts[hour]);
        }
    }
    
    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
    
    public int numberOfAccesses() {
        int total = 0;
        // Add the value in each element of hourCounts to total.
        for (int accesses : hourCounts) {
            total+= accesses;
        }
        return total;
    }
    
    public int busiestHour() {
        
        int busiestHour = -1;
        int peak = 0;
        for (int hour = 0; hour < hourCounts.length; hour++) {
            System.out.println(hour +": " + hourCounts[hour]);
            
            if (hourCounts[hour] >= peak && hourCounts[hour] != 0) {
                peak = hourCounts[hour];
                busiestHour = hour;
            }
        }
        
        return busiestHour;
    }
    
    public int quietestHour() {
        
        int quietestHour = -1;
        int low = hourCounts[hourCounts.length - 1];
        int count = 0;
        
        for (int hour = hourCounts.length - 1; hour >= 0 ; hour--) {
            System.out.println(hour +": " + hourCounts[hour]);
            
            if (hourCounts[hour] <=  low) {
                
                low = hourCounts[hour];
                quietestHour = hour;
                count++;
            }
            if (count == 24) {
                
                quietestHour = -1;
            }
        }
        
        return quietestHour;
    }
    
    public int busiestTwoHour() {
        
        int busiestTwoHour = -1;
        int peak = 0;
        for (int hour = 0; hour < hourCounts.length - 1; hour++) {
            System.out.println(hour +": " + hourCounts[hour]);
            
            if ((hourCounts[hour] + hourCounts[hour + 1]) >= peak && hourCounts[hour] != 0) {
                peak = hourCounts[hour];
                busiestTwoHour = hour;
            }
        }
        
        return busiestTwoHour;
    }
    
}
