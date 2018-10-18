import java.util.Calendar;

public class DateManager {
	
	public String getToday() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int date = cal.get(Calendar.DATE);
		
		return dateString(year, month, date);
	}
	
	public int getWeekInMonths(int year, int month, int date){
        int y = year;
        int m = month;
        int d = date;

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, y);
        calendar.set(Calendar.MONTH, m);
        calendar.set(Calendar.DATE, d);

        return calendar.get(Calendar.WEEK_OF_MONTH);
    }
	
	public String getSunday(int year, int month, int date){
        int y = year;
        int m = month;
        int d = date;
        int w = getWeekInMonths(y, m, d);

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.YEAR, y);
        calendar.set(Calendar.MONTH, m);
        calendar.set(Calendar.WEEK_OF_MONTH, w);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        calendar.add(Calendar.DATE, 0);
        
        int cYear = calendar.get(Calendar.YEAR);
        int cMonth = calendar.get(Calendar.MONTH) + 1;
        int cDate = calendar.get(Calendar.DATE);

        return dateString(cYear, cMonth, cDate);

    }
	
	public String dateString(int year, int month, int date) {
		return String.format("%04d%02d%02d", year, month, date);
	}
}
