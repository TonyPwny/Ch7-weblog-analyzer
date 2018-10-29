import java.util.Iterator;

/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version    2016.02.29
 */
public class LogAnalyzer
{
    private int[] yearCounts;
    private int[] monthCounts;
    private int[] dayCounts;
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer()
    {
        yearCounts = new int[2019];
        monthCounts = new int[13];
        dayCounts = new int[29];
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        // Create the reader to obtain the data.
        reader = new LogfileReader();
    }
    
    public LogAnalyzer(String filename)
    {
        yearCounts = new int[2019];
        monthCounts = new int[13];
        dayCounts = new int[29];
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        // Create the reader to obtain the data.
        reader = new LogfileReader(filename);
    }
    
    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int year = entry.getYear();
            yearCounts[year]++;
            int month = entry.getMonth();
            monthCounts[month]++;
            int day = entry.getDay();
            dayCounts[day]++;
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
    
    public int busiestDay() {
        
        int busiestDay = -1;
        int peak = 0;
        for (int day = 1; day < dayCounts.length; day++) {
            System.out.println(day +": " + dayCounts[day]);
            
            if (dayCounts[day] >= peak && dayCounts[day] != 0) {
                peak = dayCounts[day];
                busiestDay = day;
            }
        }
        
        return busiestDay;
    }
    
    public int quietestDay() {
        
        int quietestDay = -1;
        int low = dayCounts[dayCounts.length - 1];
        int count = 0;
        
        for (int day = dayCounts.length - 1; day > 0 ; day--) {
            System.out.println(day +": " + dayCounts[day]);
                
            if (dayCounts[day] <=  low) {
                    
                low = dayCounts[day];
                quietestDay = day;
                count++;
            }
            if (count == 28) {
                
                quietestDay = -1;
            }
        }
        return quietestDay;
    }
    
    public void totalMonthlyCounts() {
        
        printMonthlyCounts();
    }
    
    public void printMonthlyCounts()
    {
        System.out.println("Month: Count");
        for(int month = 1; month < monthCounts.length; month++) {
            System.out.println(month + ": " + monthCounts[month]);
        }
    }
    
    public int busiestMonth() {
        
        int busiestMonth = -1;
        int peak = 0;
        for (int month = 1; month < monthCounts.length; month++) {
            System.out.println(month +": " + monthCounts[month]);
            
            if (monthCounts[month] >= peak && monthCounts[month] != 0) {
                peak = monthCounts[month];
                busiestMonth = month;
            }
        }
        
        return busiestMonth;
    }
    
    public int quietestMonth() {
        
        int quietestMonth = -1;
        int low = monthCounts[monthCounts.length - 1];
        int count = 0;
        
        for (int month = monthCounts.length - 1; month > 0 ; month--) {
            System.out.println(month +": " + monthCounts[month]);
            
            if (monthCounts[month] <=  low) {
                
                low = monthCounts[month];
                quietestMonth = month;
                count++;
            }
            if (count == 12) {
                
                quietestMonth = -1;
            }
        }
        
        return quietestMonth;
    }
    
    public int averageAccessesPerMonth() {
        int average = numberOfAccesses()/12;
        return average;
    }
}
